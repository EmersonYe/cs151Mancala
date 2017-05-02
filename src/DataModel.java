import java.util.ArrayList;
import javax.swing.event.ChangeListener;

/**
 * Holds information about pits and how many stones/pit
 * 4/11/17
 * @author emersonye, Karl Lapuz
 *
 */
public class DataModel {
	
	private boolean isPlayerAsTurn;
	private int numStones;
	private int[] pits;
	private int[] clone;
	private int undos;
	private int lastStonePlaced;
	private ArrayList<ChangeListener> listeners;
	
	private static final int MAX_UNDO = 3;
	private static final int PLAYER_A_MANCALA = 7;
	private static final int PLAYER_B_MANCALA = 0;
	
	
	/**
	 * Create DataModel object with all pits filled with given number of stones
	 * @param initialStoneCount number of stones to put in each pit
	 */
	public DataModel()
	{
		pits = new int[14];
		for (int i = 1; i <= 13; i++) // 0th and 13th indeces are mancalas
		{
			if (i != PLAYER_A_MANCALA)
			{
				pits[i] = numStones;
			}
		}
		clone = pits.clone();
		
		isPlayerAsTurn = true;
		lastStonePlaced = 0;
		undos = 0;
	}
	
	public String getTurn()
	{
		if (isPlayerAsTurn)
		{
			return "Player A";
		}
		return "Player B";
	}
	
	public void setTurn()
	{
		if (lastStonePlaced == PLAYER_A_MANCALA && isPlayerAsTurn)
		{
			isPlayerAsTurn = true;
		}
		else if (lastStonePlaced == PLAYER_B_MANCALA && !isPlayerAsTurn)
		{
			isPlayerAsTurn = false;
		}
		else
		{
			isPlayerAsTurn = !isPlayerAsTurn;
		}
	}
	
	public boolean isMancala(int pit)
	{
		return (pit == PLAYER_A_MANCALA || pit == PLAYER_B_MANCALA);
	}
	
	public boolean isOwnPit(int pit)
	{
		if (isPlayerAsTurn && pit >= 1 && pit < 7)
		{
			return true;
		}
		else if (!isPlayerAsTurn && pit >= 8 && pit < 13)
		{
			return true;
		}
		return false;
	}
	
	public int getLastStonePlacement()
	{
		return lastStonePlaced;
	}
	
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
	
	public int[] getBoardData()
	{
		return pits;
	}
	
	/**
    Attach a listener to the Model
    @param c the listener
	 */
	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	
	public void undo()
	{
		if (undos <= 3)
		{
			pits = clone;
			undos++;
		}
	}
	
	public boolean isMoveValid(int pitChosen)
	{
		if (pitChosen >= 1 && pitChosen <= 6)
		{
			if (isPlayerAsTurn)
			{
				return true;
			}
			System.out.println("Can't access that pit");
			return false;
		}
		else if (pitChosen >= 8 && pitChosen <= 13)
		{
			if (isPlayerAsTurn)
			{
				System.out.println("Can't access that pit");
				return false;
			}
			return true;
		}
		System.out.println("That's a mancala");
		return false;
	}
	
	public void move(int pit)
	{
		if (isMoveValid(pit))
		{
			clone = pits.clone();
			int numStones = pits[pit];
			pits[pit] = 0;
			
			for (int i = 1; i <= numStones; i++)
			{
				pit++;
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
			if (pits[lastStonePlaced] == 1 && !isMancala(lastStonePlaced) 
					&& isOwnPit(lastStonePlaced))
			{
				captureOpposite(lastStonePlaced);
			}
			
			if (isGameOver())
			{
				captureAllStones();
			}
			else
			{
				setTurn();
			}
		}
	}
	
	public void captureOpposite(int lastStonePlaced)
	{
		if (lastStonePlaced != PLAYER_A_MANCALA && lastStonePlaced != PLAYER_B_MANCALA) 
		{ 
			pits[lastStonePlaced] += pits[14 - lastStonePlaced];
			pits[14 - lastStonePlaced] = 0;
		}
	}
	
	public void captureAllStones()
	{
		int playerA = 0;
		int playerB = 0;
		for (int i = 1; i <= 6; i++)
		{
			playerA += pits[i];
			pits[i] = 0;
			playerB += pits[i + 7];
			pits[i + 7] = 0;
			
		}
		pits[PLAYER_A_MANCALA] += playerA;
		pits[PLAYER_B_MANCALA] += playerB;
	}
	
	public boolean isGameOver()
	{
		int playerAStones = 0;
		int playerBStones = 0;
		for (int i = 1; i <= 6; i++)
		{
			playerAStones += pits[i];
			playerBStones += pits[i + 7];
		}
		return (playerAStones == 0 || playerBStones == 0);
	}
	
	public String getWinner()
	{
		if (pits[PLAYER_A_MANCALA] > pits[PLAYER_B_MANCALA])
		{
			return "Player A wins!";
		}
		else if (pits[PLAYER_B_MANCALA] > pits[PLAYER_A_MANCALA])
		{
			return "Player B wins!";
		}
		else
		{
			return "It's a draw!";
		}
	}
}
