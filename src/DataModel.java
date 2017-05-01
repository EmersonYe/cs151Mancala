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
	public DataModel(int initialStoneCount)
	{
		pits = new int[14];
		for (int i = 1; i <= 13; i++) // 0th and 13th indeces are mancalas
		{
			if (i != PLAYER_A_MANCALA)
			{
				pits[i] = initialStoneCount;
			}
		}
		clone = pits.clone();
		
		isPlayerAsTurn = true;
		lastStonePlaced = 0;
		undos = 0;
	}
	
	public String toString()
	{
		String pitData = "[" + pits[0];
		for (int i = 1; i <= 13; i++)
		{
			pitData += ", " + pits[i];
		}
		return pitData + "]";
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
				return false;
			}
			return true;
		}
		else if (pitChosen >= 8 && pitChosen <= 13)
		{
			if (isPlayerAsTurn)
			{
				return true;
			}
			return false;
		}
	}
	
	public void move(int pitChosen)
	{
		if (isMoveValid(pitChosen))
		{
			clone = pits.clone();
			int numStones = pits[pitChosen];
			pits[pitChosen] = 0;
			
			for (int i = 1; i <= numStones; i++)
			{
				if ()
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
}
