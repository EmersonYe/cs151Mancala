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
	private static final int mancalaSize = 14;
	
	/**
	 * Constructs a Mancala component.
	 * @param model the given DataModel
	 * @param formatter the given style MancalaFormatter
	 */
	public Pit(final DataModel model, MancalaFormatter formatter)
	{
		this.model = model;
		this.formatter = formatter;
		stones = new int[mancalaSize];

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
		final int mancalaHeight = 305;
		final int mancalaBWidth = 10;
		final int aIndex = 6;
		final int bIndex = 13;

		Color pitColor = formatter.setPitColor();
		Color stoneColor = formatter.setStoneColor();
		
		// Adds components to ArrayList pit depending on what kind of component it is
		int n = aIndex;
		for (int i = 0; i < mancalaSize; i++)
		{
			if (i == aIndex)
			{
				pits.add(new MancalaShape((i + 1) * X, topY+55, WIDTH, mancalaHeight, pitColor, stoneColor));
				pits.get(i).setShape(formatter.setMancalaShape(pits.get(i)));
				pits.get(i).setNumStones(stones[i]);
			}
			else if (i >= 0 && i < aIndex)
			{
				pits.add(new MancalaShape((i + 1) * X, bottomY, WIDTH, HEIGHT, pitColor, stoneColor));
				pits.get(i).setShape(formatter.setPitShape(pits.get(i)));
				pits.get(i).setNumStones(stones[i]);
			}
			else if (i == bIndex)
			{
				pits.add(new MancalaShape(mancalaBWidth, topY+55, WIDTH, mancalaHeight, pitColor, stoneColor));
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
		
		// Paints the list of pits
		for (MancalaShape p : pits)
		{
			p.paintShape(g2);
		}
		
		g2.setFont(new Font("Arial", 3, 30));
		FontMetrics fontMetrics = g.getFontMetrics();
		
		// Sets the text on the board displaying current player, error message, free turn message, game over message and undo message
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
			g2.drawString("Current Player:  A       Undos:" + model.getPlayerAUndo(), this.getX()-75 + this.getWidth() / 2
					- fontMetrics.stringWidth("" + stones) / 2, this.getY()
					+ this.getHeight() / 2 - fontMetrics.getAscent()
					- LOCATION_OFFSET);
		}
		else
		{
			g2.setColor(Color.RED);
			g2.drawString("Current Player:  B       Undos:" + model.getPlayerBUndo(), this.getX()-75 + this.getWidth() / 2
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
		// Reset array with pits with the current data
		boardComponent();
		
		stones = model.getBoardData();
		
		// Sets stone for the new pits
		for (int i = 0; i < pits.size(); i++)
		{
			pits.get(i).setNumStones(stones[i]);
		}

		repaint();
	}
}