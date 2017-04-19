import java.util.ArrayList;

import javax.swing.event.ChangeListener;

/**
 * Holds information about pits and how many stones/pit
 * 4/11/17
 * @author emersonye
 *
 */
public class DataModel {
	
	// +1 is for the Mancala
	private String current;
	int[] pits = new int[7];
	int[] aClone;
	int[] bClone;
	private static final int MAX_UNDO = 3;
	private int undos;
	private ArrayList<ChangeListener> listeners;
	
	
	/**
	 * Create DataModel object with all pits filled with given number of stones
	 * @param initialStoneCount number of stones to put in each pit
	 */
	public DataModel(int initialStoneCount)
	{
		//Set all 6 pits of each player = to initial stone count. Skip over Mancala
		for (int i = 0; i < 6; i++)
		{
			a[i] = initialStoneCount;
			b[i] = initialStoneCount;
			aClone = a.clone();
			bClone = b.clone(); 
		}
	}
	
	public int[] getAData()
	{
		return a;
	}
	
	public int[] getBData()
	{
		return b;
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
		undos = 0;
		if (undos <= 3)
		{
			a = 
		}
	}
	
	public void move()
	{
		a = a.clone();
		b = b.clone();
	}
	
	public void captureOpposite(int lastStonePlaced)
	{
		if (lastStonePlaced >= 1 && lastStonePlaced <= 6) 
		{ 
			pits[lastStonePlaced] = pits[lastStonePlaced + 6];
			pits[lastStonePlaced + 6] = 0;
			// test
		}
	}
	
	
	
}
