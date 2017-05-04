package src;

import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Holds information about pits, how to manipulate them,
 * and other move functionalities of the game
 * @author Team SJSD - Karl Lapuz, Matt Sternquist, Emerson Ye
 */
public class DataModel {

	private boolean isPlayerAsTurn;
	private boolean canUndo;
	private int[] pits;
	private int[] clone;
	private int AUndos;
	private int BUndos;
	private int lastStonePlaced;
	private String errorMsg;
	private String freeTurn;
	private ArrayList<ChangeListener> listeners;

	private static final int MAX_UNDO = 3;
	private static final int PLAYER_A_MANCALA = 6;
	private static final int PLAYER_B_MANCALA = 13;


	/**
	 * Create DataModel object with all pits with 0 as initial number of stones
	 */
	public DataModel()
	{
		pits = new int[14];
		listeners = new ArrayList<ChangeListener>();
		clone = pits.clone(); // utilized for undo function
		isPlayerAsTurn = true;
		canUndo = false;
		lastStonePlaced = 0;
		errorMsg= "";
		freeTurn = "";
		AUndos = 0;
		BUndos = 0;
	}

	/**
	 * Attach a listener to the Model
	 * @param c the listener
	 */
	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}

	/**
	 * Updates the listener with the changes in the data made by user
	 */
	public void update()
	{
		for (ChangeListener c : listeners)
		{
			c.stateChanged(new ChangeEvent(this));
		}
	}

	/**
	 * Sets the number of stones inside the pits
	 * @param stones the number of stones inside the pits
	 */
	public void setStones(int stones)
	{
		for (int i = 0; i < 13; i++) // 0th and 13th indeces are mancalas
		{
			if (i != PLAYER_A_MANCALA )
			{
				pits[i] = stones;
			}
		}
		this.update();
	}

	/**
	 * Accessor
	 * Gets the current player turn
	 * @return the name of the player that gets the next turn
	 */
	public String getTurn()
	{
		if (isPlayerAsTurn)
		{
			return "Player A";
		}
		return "Player B";
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
	
	public String getFreeTurn(){
		return freeTurn;
	}

	/**
	 * Sets the next turn based on the move made
	 */
	private void setTurn()
	{
		if (lastStonePlaced == PLAYER_A_MANCALA && isPlayerAsTurn)
		{
			isPlayerAsTurn = true;
			AUndos = 0;
		}
		else if (lastStonePlaced == PLAYER_B_MANCALA && !isPlayerAsTurn)
		{
			isPlayerAsTurn = false;
			BUndos = 0;
		}
		else
		{
			isPlayerAsTurn = !isPlayerAsTurn;
		}
	}

	/**
	 * Determines whether the pit is a mancala or not
	 * @param pit the pit chose
	 * @return true if it is mancala and false otherwise
	 */
	private boolean isMancala(int pit)
	{
		return (pit == PLAYER_A_MANCALA || pit == PLAYER_B_MANCALA);
	}

	/**
	 * Determines whether the pit is the current player's own pit
	 * @param pit the pit chosen
	 * @return true if the the put is the current player's pit, false otherwise
	 */
	private boolean isOwnPit(int pit)
	{
		if (isPlayerAsTurn && pit >= 1 && pit < 7)
		{
			return true;
		}
		else if (!isPlayerAsTurn && pit >= 8 && pit <= 13)
		{
			return true;
		}
		return false;
	}

	/**
	 * Gets the pit where the last stone was placed
	 * @return the int 
	 */
	public int getLastStonePlacement()
	{
		return lastStonePlaced;
	}

	/**
	 * Override
	 * Prints the current state of the game in board format
	 * @return the state of the game in the form where it's readable for the user
	 */
	public String toString()
	{
		String pitData = "";
		pitData += pits[0];
		for (int i = 13; i >= 8; i--)
		{
			pitData += ", " + pits[i];
		}
		pitData += "\n   " + pits[1];
		for (int i = 2; i <= 7; i++)
		{
			pitData += ", " + pits[i];
		}
		return pitData;
	}

	/**
	 * Gets the data of the board
	 * @return the array of ints representing the state of game
	 */
	public int[] getBoardData()
	{
		return pits;
	}

	/**
	 * Undoes a move of the current player
	 */
	public void undo()
	{
		if (canUndo)
		{
			setTurn();
			if (isPlayerAsTurn)
			{
				if (AUndos < MAX_UNDO)
				{
					pits = clone;
					AUndos++;
					BUndos = 0;
					canUndo = false;
				}
				else
				{
					System.out.println("Can't undo, Player B has to move!");
					isPlayerAsTurn = !isPlayerAsTurn;
				}
			}
			else
			{
				if (BUndos < MAX_UNDO)
				{
					pits = clone;
					BUndos++;
					AUndos = 0;
					canUndo = false;
				}
				else
				{
					System.out.println("Can't undo, Player A has to move!");
					isPlayerAsTurn = !isPlayerAsTurn;
				}
			}
		}
		this.update();

		/*{
			if (this.getTurn() == "Player A" && BUndos <MAX_UNDO)
			{
				pits = clone;
				BUndos++;
				AUndos = 0;
				isPlayerAsTurn = false;
			}
			else if(AUndos <MAX_UNDO)
			{
				pits = clone;
				AUndos++;
				BUndos = 0;
				isPlayerAsTurn = true;
			}
			this.update();
		}*/
	}

	/**
	 * Determines whether the pit chosen is a valid option for the current player
	 * @param pitChosen the pit chosen by the current player
	 * @return true if the pit is accessible for current player
	 */
	public boolean isMoveValid(int pitChosen)
	{
		freeTurn = "";
		if (pits[pitChosen] == 0 && !isMancala(pitChosen))
		{
			errorMsg = "This pit is empty";
			System.out.println("This pit is empty");
			return false;
		}
		if (pitChosen >= 0 && pitChosen < 6)
		{
			if (isPlayerAsTurn)
			{
				return true;
			}
			errorMsg = "Can't access that pit";
			System.out.println("Can't access that pit");
			return false;
		}
		else if (pitChosen >= 7 && pitChosen < 13)
		{
			if (isPlayerAsTurn)
			{
				errorMsg = "Can't access that pit";
				System.out.println("Can't access that pit");
				return false;
			}
			return true;
		}
		errorMsg = "That's a mancala";
		System.out.println("That's a mancala");
		return false;
	}

	/**
	 * Makes a move
	 * @param pit the pit that the current player would like to access
	 */
	public void move(int pit)
	{
		if (isMoveValid(pit))
		{
			errorMsg = "";
			freeTurn = "";
			clone = pits.clone();
			int numStones = pits[pit];
			pits[pit] = 0;

			for (int i = 1; i <= numStones; i++)
			{
				pit = (pit + 1) % 14;
				if (pit == PLAYER_A_MANCALA && !isPlayerAsTurn || pit == PLAYER_B_MANCALA
						&& isPlayerAsTurn)
				{
					i--; // don't lose a stone
				}
				else
				{
					pits[pit % 14]++;
				}
			}
			lastStonePlaced = pit % 14;
			if(isMancala(lastStonePlaced)){
				freeTurn = "Congratulations! You get a free turn!";
			}
			if (pits[lastStonePlaced] == 1 && !isMancala(lastStonePlaced)
					&& isOwnPit(lastStonePlaced)) // lands on an empty pit
			{
				captureOpposite(lastStonePlaced);
			}

			if (isGameOver())
			{
				captureAllStones();
				getWinner();
				canUndo = false;
			}
			else
			{
				canUndo = true;
				setTurn();
			}
		}
		this.update();
	}

	/**
	 * Helper method that captures the opposite when player lands on an own empty pit
	 * @param lastStonePlaced where the last stone was placed
	 */
	private void captureOpposite(int lastStonePlaced)
	{
		if (lastStonePlaced != PLAYER_A_MANCALA && lastStonePlaced != PLAYER_B_MANCALA) 
		{ 
			if (pits[lastStonePlaced] >=0 && pits[lastStonePlaced] <6 ){
				pits[lastStonePlaced] += pits[12 - lastStonePlaced];
				pits[12 - lastStonePlaced] = 0;
			}
			else{
				pits[lastStonePlaced] += pits[Math.abs(lastStonePlaced - 12)];
				pits[Math.abs(lastStonePlaced - 12)] = 0;
			}
		}
	}

	/**
	 * Captures all stone and puts them all in the players' respective mancala
	 */
	private void captureAllStones()
	{
		int playerA = 0;
		int playerB = 0;
		for (int i = 0; i < 6; i++)
		{
			playerA += pits[i];
			pits[i] = 0;
			playerB += pits[i + 7];
			pits[i + 7] = 0;

		}
		pits[PLAYER_A_MANCALA] += playerA;
		pits[PLAYER_B_MANCALA] += playerB;
	}

	/**
	 * Determines whether the game is over
	 * @return true if the game is over, false otherwise
	 */
	boolean isGameOver()
	{
		int playerAStones = 0;
		int playerBStones = 0;
		for (int i = 0; i < 6; i++)
		{
			playerAStones += pits[i];
			playerBStones += pits[i + 7];
		}
		return (playerAStones == 0 || playerBStones == 0);
	}

	/**
	 * Gets the winner of the game by comparing stones inside mancalas
	 */
	public String getWinner()
	{
		if (pits[PLAYER_A_MANCALA] > pits[PLAYER_B_MANCALA])
		{
			return "Player A is the winner!";
		}
		else if (pits[PLAYER_B_MANCALA] > pits[PLAYER_A_MANCALA])
		{
			return "Player B is the winner!";
		}
		else
		{
			return "It's a draw!";
		}
	}
}
