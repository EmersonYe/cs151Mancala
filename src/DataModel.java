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
	int[] a = new int[7];
	int[] b = new int[7];
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
}
