package src;

/**
 * This is the driver class of this Mancala game
 * @author Team SJSD - Karl Lapuz, Matt Sternquist, Emerson Ye
 */
public class MancalaTest {
	public static void main(String[] args)
	{	
		DataModel model = new DataModel();
		MancalaView view = new MancalaView(model);
	}
}
