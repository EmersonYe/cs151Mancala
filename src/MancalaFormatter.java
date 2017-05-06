package src;

import java.awt.Color;
import java.awt.Shape;
/**
 * This is the interface following strategy pattern for mancala formats
 * @author Team SJSD - Karl Lapuz, Matt Sternquist, Emerson Ye
 */
public interface MancalaFormatter {
	
	/**
     * Sets Pit shape 
     * @param s a MancalaShape
     */
	Shape setPitShape(MancalaShape s);
	
	/**
     * Sets Mancala shape 
     * @param s a MancalaShape
     */
	Shape setMancalaShape(MancalaShape s);
	
	/**
     * Sets Pit Color 
     */
	Color setPitColor();
	
	/**
     * Sets Stone color
     */
	Color setStoneColor();
}
