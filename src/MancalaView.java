package src;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


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
		
		// Frame for asking the number of stones user wants
		JFrame openFrame2 = new JFrame("Welcome to the Mancala Game! Please choose the number of starting stones.");
		openFrame2.setLayout(new FlowLayout());
		
		JButton stone3 = new JButton("Three stones");
		stone3.addActionListener(new
				ActionListener()
				{
				public void actionPerformed(ActionEvent event){
					board.setNumStones(threeStone);
					openFrame2.dispose();
				}});
		JButton stone4 = new JButton("Four stones");
		stone4.addActionListener(new
				ActionListener()
				{
				public void actionPerformed(ActionEvent event){
					board.setNumStones(fourStone);
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
		
		JButton style1 = new JButton("Style 1");
		style1.addActionListener(new
				ActionListener()
				{
				public void actionPerformed(ActionEvent event){
					pit = new Pit(board, new BoardStyle1());
					openFrame1.dispose();
					openFrame2.pack();
					openFrame2.setVisible(true);
				}});
		JButton style2 = new JButton("Style 2");
		style1.addActionListener(new
				ActionListener()
				{
				public void actionPerformed(ActionEvent event){
					pit = new Pit(board, new BoardStyle2());
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
		
		// This is the frame for the game adding the game board panel
		JFrame gameFrame = new JFrame("Mancala");
		gameFrame.setLayout(new FlowLayout());
		JPanel boardPanel = new JPanel();
		boardPanel.setPreferredSize(new Dimension (1400,600));
		boardPanel.setBorder(BorderFactory.createEtchedBorder());
		gameFrame.add(boardPanel);
		boardPanel.setLayout(new BorderLayout());
		
		// Adding player A to game board
		JLabel playerAL = new JLabel("Player A");
		JPanel playerAP = new JPanel();
		playerAP.setLayout(new GridBagLayout());
		playerAL.setFont(new Font("Arial", 5, 25));
		playerAP.add(playerAL);
		boardPanel.add(playerAP, BorderLayout.SOUTH);
		gameFrame.pack();
		
		// Adding player B to game board
		JLabel playerBL = new JLabel("Player B");
		JPanel playerBP = new JPanel();
		playerBP.setLayout(new GridBagLayout());
		playerBL.setFont(new Font("Arial", 5, 25));
		playerBP.add(playerBL);
		boardPanel.add(playerBP, BorderLayout.NORTH);
		gameFrame.pack();
		gameFrame.setVisible(true);
		
class VerticalText extends JFrame {
	String text1;
	public VerticalText(String text){
		String text1 = text;
	}
	public void paint(Graphics g){
		super.paint(g);
		
		g.setFont(new Font("Arial", 5,25));
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform at = g2d.getTransform();
		
		AffineTransform me = new AffineTransform();
		me.rotate(- Math.PI / 2);
		g2d.setTransform(me);
		g2d.drawString(text1, -200, 50);
	}
}
}

	public void stateChanged(ChangeEvent e) {
		super.repaint();
		}
	}
