package src;

import java.awt.Color;
import java.awt.Shape;
/**
 * This is the interface following strategy pattern for mancala formats
 * @author Team SJSD - Karl Lapuz, Matt Sternquist, Emerson Ye
 * 
 */
public interface MancalaFormatter {
	
	Shape setPitShape(MancalaShape s);
	
	Shape setMancalaShape(MancalaShape s);
	
	Color setPitColor();
	
	Color setStoneColor();
}
