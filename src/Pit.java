package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class creates a component and then paints the Mancala shapes on it.
 * @author Team SJSD - Karl Lapuz, Matt Sternquist, Emerson Ye
 * 
 */
public class Pit extends JComponent implements ChangeListener
{
	private ArrayList<MancalaShape> pits;
	private DataModel model;
	private MancalaFormatter formatter;
	private int[] stones;
	private static final int MANCALA_SIZE = 14;
	
	/**
	 * Constructs a Mancala component.
	 * @param model the given DataModel
	 * @param formatter the given style MancalaFormatter
	 */
	public Pit(final DataModel model, MancalaFormatter formatter)
	{
		this.model = model;
		this.formatter = formatter;
		stones = new int[MANCALA_SIZE];

		model.attach(this);

		addMouseListener(new 
			MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					for (int i = 0; i < pits.size(); i++)
					{
						if (pits.get(i).contains(e.getPoint()))
						{
							model.move(i);
						}
					}
				}
			});
	}

	/**
	 * Creates the components of the Mancala.
	 */
	private void boardComponent()
	{
		pits = new ArrayList<MancalaShape>();

		final int WIDTH = 120;
		final int HEIGHT = 120;
		final int X = 160;
		final int topY = 0;
		final int bottomY = 320;
		final int MANCALA_HEIGHT = 305;
		final int MANCALA_B_X = 10;
		final int MANCALA_A_INDEX = 6;
		final int MANCALA_B_INDEX = 13;

		Color pitColor = formatter.setPitColor();
		Color stoneColor = formatter.setStoneColor();

		int n = MANCALA_A_INDEX;
		for (int i = 0; i < MANCALA_SIZE; i++)
		{
			if (i == MANCALA_A_INDEX)
			{
				pits.add(new MancalaShape((i + 1) * X, topY+55, WIDTH, MANCALA_HEIGHT, pitColor, stoneColor));
				pits.get(i).setShape(formatter.setMancalaShape(pits.get(i)));
				pits.get(i).setNumStones(stones[i]);
			}
			else if (i >= 0 && i < MANCALA_A_INDEX)
			{
				pits.add(new MancalaShape((i + 1) * X, bottomY, WIDTH, HEIGHT, pitColor, stoneColor));
				pits.get(i).setShape(formatter.setPitShape(pits.get(i)));
				pits.get(i).setNumStones(stones[i]);
			}
			else if (i == MANCALA_B_INDEX)
			{
				pits.add(new MancalaShape(MANCALA_B_X, topY+55, WIDTH, MANCALA_HEIGHT, pitColor, stoneColor));
				pits.get(i).setShape(formatter.setMancalaShape(pits.get(i)));
				pits.get(i).setNumStones(stones[i]);
			}
			else
			{
				pits.add(new MancalaShape(n * X, topY, WIDTH, HEIGHT, pitColor, stoneColor));
				pits.get(i).setShape(formatter.setPitShape(pits.get(i)));
				pits.get(i).setNumStones(stones[i]);
				n--;
			}
		}
	}

	/**
	 * Paints the component.
	 */
	public void paintComponent(Graphics g)
	{
		final int LOCATION_OFFSET = 15;

		Graphics2D g2 = (Graphics2D) g;

		for (MancalaShape p : pits)
		{
			p.paintShape(g2);
		}
		
		g2.setFont(new Font("Arial", 3, 30));
		FontMetrics fontMetrics = g.getFontMetrics();
		if (model.isGameOver() == false){
		if(model.getFreeTurn() !=""){
			g2.setColor(Color.ORANGE);
			g2.drawString(model.getFreeTurn(), this.getX()-75 + this.getWidth() / 2
					- fontMetrics.stringWidth("" + stones) / 2, this.getY()+55
					+ this.getHeight() / 2 - fontMetrics.getAscent()
					- LOCATION_OFFSET);
		}
		if (model.getErrorMsg() != ""){
			g2.setColor(Color.BLACK);
			g2.drawString("*Error: " + model.getErrorMsg() + "*", this.getX()-75 + this.getWidth() / 2
					- fontMetrics.stringWidth("" + stones) / 2, this.getY()+55
					+ this.getHeight() / 2 - fontMetrics.getAscent()
					- LOCATION_OFFSET);
		}
		if (model.getTurn().equals("Player A"))
		{
			g2.setColor(Color.RED);
			g2.drawString("Current Player:  A", this.getX()-75 + this.getWidth() / 2
					- fontMetrics.stringWidth("" + stones) / 2, this.getY()
					+ this.getHeight() / 2 - fontMetrics.getAscent()
					- LOCATION_OFFSET);
		}
		else
		{
			g2.setColor(Color.RED);
			g2.drawString("Current Player:  B", this.getX()-75 + this.getWidth() / 2
					- fontMetrics.stringWidth("" + stones) / 2, this.getY()
					+ this.getHeight() / 2 - fontMetrics.getAscent()
					- LOCATION_OFFSET);
		}
		}
		else{
			g2.setColor(Color.RED);
			g2.drawString(model.getWinner(), this.getX()-75 + this.getWidth() / 2
					- fontMetrics.stringWidth("" + stones) / 2, this.getY()
					+ this.getHeight() / 2 - fontMetrics.getAscent()
					- LOCATION_OFFSET);
		}
	}

	/**
	 * Called when the data in the model is changed.
	 */
	public void stateChanged(ChangeEvent e)
	{
		boardComponent();
		stones = model.getBoardData();

		for (int i = 0; i < pits.size(); i++)
		{
			pits.get(i).setNumStones(stones[i]);
		}

		repaint();
	}

	/**
	 * Sets the style formatter.
	 * @param aFormatter a new style formatter
	 */
	public void setFormatter(MancalaFormatter aFormatter)
	{
		formatter = aFormatter;
	}
}