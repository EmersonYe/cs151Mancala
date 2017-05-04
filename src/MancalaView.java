package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This is the main view class 
 * @author Team SJSD - Karl Lapuz, Matt Sternquist, Emerson Ye
 */
public class MancalaView extends JPanel implements ChangeListener{
	
		private static final int threeStone = 3;
		private static final int fourStone = 4;
		public static final int boardWidth = 1400;
		public static final int boardHeight = 600;
		public static final int frameWidth = 1400;
		public static final int Width = 1400;
		private DataModel board;
		private Pit pit;
		
		
	public MancalaView (final DataModel board){
		
		this.board = board;

		// This is the frame for the game adding the game board panel
		JFrame gameFrame = new JFrame("Mancala");
		gameFrame.setLayout(new FlowLayout());
		JPanel boardPanel = new JPanel();
		boardPanel.setPreferredSize(new Dimension (1400,600));
		boardPanel.setBorder(BorderFactory.createEtchedBorder());
		gameFrame.add(boardPanel);
		boardPanel.setLayout(new BorderLayout());
		
		// Frame for asking the number of stones user wants
		JFrame openFrame2 = new JFrame("Welcome to the Mancala Game! Please choose the number of starting stones.");
		openFrame2.setLayout(new FlowLayout());
		
		JButton stone3 = new JButton("Three stones");
		stone3.addActionListener(new
				ActionListener()
				{
				public void actionPerformed(ActionEvent event){
					board.setStones(threeStone);
					gameFrame.pack();
					gameFrame.setVisible(true);
					openFrame2.dispose();
				}});
		JButton stone4 = new JButton("Four stones");
		stone4.addActionListener(new
				ActionListener()
				{
				public void actionPerformed(ActionEvent event){
					board.setStones(fourStone);
					gameFrame.pack();
					gameFrame.setVisible(true);
					openFrame2.dispose();
				}});
		JPanel openPanel2 = new JPanel();
		openPanel2.setPreferredSize(new Dimension(800,100));
		openPanel2.add(stone3);
		openPanel2.add(stone4);
		openFrame2.add(openPanel2);
		openFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Frame for asking which style user wants
		JFrame openFrame1 = new JFrame("Welcome to the Mancala Game! Please choose a board style.");
		
		JPanel aLabelPanel = new JPanel();
		
		JButton style1 = new JButton("Style 1");
		style1.addActionListener(new
				ActionListener()
				{
				public void actionPerformed(ActionEvent event){
					pit = new Pit(board, new BoardStyle1());
					aLabelPanel.add(pit, BorderLayout.CENTER);
					openFrame1.dispose();
					openFrame2.pack();
					openFrame2.setVisible(true);
				}});
		JButton style2 = new JButton("Style 2");
		style2.addActionListener(new
				ActionListener()
				{
				public void actionPerformed(ActionEvent event){
					pit = new Pit(board, new BoardStyle2());
					aLabelPanel.add(pit, BorderLayout.CENTER);
					openFrame1.dispose();
					openFrame2.pack();
					openFrame2.setVisible(true);
				}});
		openFrame1.setLayout(new FlowLayout());
		
		JPanel openPanel1 = new JPanel();
		openPanel1.setPreferredSize(new Dimension(800,100));
		openFrame1.add(openPanel1);
		openFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		openFrame1.pack();
		openFrame1.setVisible(true);
		openPanel1.add(style1);
		openPanel1.add(style2);
		
		
		// Adding player A to game board
		JLabel playerAL = new JLabel("Player A");
		JPanel playerAP = new JPanel();
		playerAP.setLayout(new GridBagLayout());
		playerAL.setFont(new Font("Arial", 5, 25));
		playerAP.add(playerAL);
		boardPanel.add(playerAP, BorderLayout.SOUTH);
		
		// Adding player B to game board
		JLabel playerBL = new JLabel("Player B");
		JPanel playerBP = new JPanel();
		playerBP.setLayout(new GridBagLayout());
		playerBL.setFont(new Font("Arial", 5, 25));
		playerBP.add(playerBL);
		boardPanel.add(playerBP, BorderLayout.NORTH);
		
		// Adding Mancala A to game board
		DrawTextVertically mancalaAL = new DrawTextVertically("Mancala A");
		JPanel mancalaAP = new JPanel();
		mancalaAP.setLayout(new GridBagLayout());
		mancalaAP.add(mancalaAL);
		boardPanel.add(mancalaAP, BorderLayout.EAST);
		
		// Add Mancala B to game board
		DrawTextVertically mancalaBL = new DrawTextVertically("Mancala B");
		JPanel mancalaBP = new JPanel();
		mancalaBP.setLayout(new GridBagLayout());
		mancalaBP.add(mancalaBL);
		boardPanel.add(mancalaBP, BorderLayout.WEST);
		
		// Labeling player A pits and adding borderline
		aLabelPanel.setPreferredSize(new Dimension(900,600));
		aLabelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
		boardPanel.add(aLabelPanel, BorderLayout.CENTER);
		aLabelPanel.setLayout(new BorderLayout());
		JLabel pitALabel = new JLabel( "A6                  A5                 A4                  A3                    A2                   A1");
		JPanel pitAPanel = new JPanel();
		pitAPanel.setLayout(new FlowLayout());
		pitALabel.setFont(new Font("Arial",5, 25));
		pitAPanel.add(pitALabel);
		aLabelPanel.add(pitAPanel, BorderLayout.SOUTH);
		
		// Labeling player B pits
		JLabel pitBLabel = new JLabel( "B6                  B5                 B4                  B3                    B2                   B1");
		JPanel pitBPanel = new JPanel();
		pitBPanel.setLayout(new FlowLayout());
		pitBLabel.setFont(new Font("Arial",5, 25));
		pitBPanel.add(pitBLabel);
		aLabelPanel.add(pitBPanel, BorderLayout.NORTH);
		
		board.attach(this);
		
		
		JButton undoButton = new JButton("Undo move");
		undoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				board.undo();
			}
		});
		
		JButton resetButton = new JButton("Reset Game");
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				openFrame2.pack();
				openFrame2.setVisible(true);
			}
		});
		
		gameFrame.add(resetButton);
		gameFrame.add(undoButton);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		

	}

	public void stateChanged(ChangeEvent e) {
		super.repaint();
		}
	
	// Class to draw text vertically
	private class DrawTextVertically extends JLabel
	{
		private String text;
		private static final int width = 55;
		private static final int height = 300;
		private static final int offset = 15;

		public DrawTextVertically(String text){
			this.text = text;
		}

		public Dimension getPreferredSize(){
			return new Dimension(width, height);
		}

		protected void paintComponent(Graphics g){
			
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			setFont(new Font("Arial", 5, 25));
			FontMetrics fontMetrics = g2.getFontMetrics();
			int y = fontMetrics.getHeight();
			
			for (int i = 0; i < text.length(); i++){
			
				int x = offset + (fontMetrics.charWidth(text.charAt(i)) / 10);
				g2.drawString(Character.toString(text.charAt(i)), x, y);

				y = y + fontMetrics.getHeight();
			}
		g2.dispose();
		}
	}
}
	
