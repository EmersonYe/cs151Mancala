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
	private static final int PLAYER_A_MANCALA = 13;
	private static final int PLAYER_B_MANCALA = 0;
	
	
	/**
	 * Create DataModel object with all pits filled with given number of stones
	 * @param initialStoneCount number of stones to put in each pit
	 */
	public DataModel(int initialStoneCount)
	{
		pits = new int[14];
		for (int i = 1; i <= 12; i++) // 0th and 13th indeces are mancalas
		{
			pits[i] = initialStoneCount;
		}
		clone = pits.clone();
		
		isPlayerAsTurn = true;
		lastStonePlaced = null;
		undos = 0;
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
		else if (pitChosen >= 7 && pitChosen <= 12)
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
		if (lastStonePlaced >= 1 && lastStonePlaced <= 6) 
		{ 
			pits[lastStonePlaced] = pits[lastStonePlaced + 6];
			pits[lastStonePlaced + 6] = 0;
		}
	}
	
	
	
}
