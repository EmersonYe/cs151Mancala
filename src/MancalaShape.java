package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * This class creates the shapes of the Mancala.
 * @author Team SJSD - Karl Lapuz, Matt Sternquist, Emerson Ye
 */
public class MancalaShape
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Shape shape;
    private int numStones;
    private Color pitColor;
    private Color stoneColor;
    private static final int stoneSize = 20;
    
    /**
     * Constructs a Mancala component shape.
     * @param x the x location
     * @param y the y location
     * @param width the width
     * @param height the height
     * @param pitColor the color of the pit or Mancala
     * @param stoneColor the color of the stones
     */
    public MancalaShape(int x, int y, int width, int height, Color pitColor, Color stoneColor)
    {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.pitColor = pitColor;
        this.stoneColor = stoneColor;
        numStones = 0;
        shape = null;
    }
    
    /**
     * Gets the stones of the shape.
     * @return the numStone
     */
    public int getNumStone(){
    	return numStones;
    }
    /**
     * Gets the x of the shape.
     * @return the x
     */
    public int getX()
    {
        return x;
    }

    /**
     * Gets the y of the shape.
     * @return the y
     */
    public int getY()
    {
        return y;
    }
    
    /**
     * Gets the width of the shape.
     * @return the width
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * Gets the height of the shape.
     * @return the height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Sets the number of the stones.
     * @param number the given number
     */
    public void setNumStones(int number)
    {
        numStones = number;
    }
    
    /**
     * Sets the shape.
     * @param aShape the given shape
     */
    public void setShape(Shape aShape)
    {
        shape = aShape;
    }
    
    /**
     * CHecks if a point is in the shape.
     * @param aPoint the point
     * @return false if the point is not in the shape, otherwise true
     */
    public boolean contains(Point2D aPoint)
    {
        if(!shape.contains(aPoint))
            return false;
        else
            return true;
    }
    
    /**
     * Paints the shape.
     * @param g2 the graphics
     */
    public void paintShape(Graphics2D g2)
    {	
        g2.setColor(pitColor);
        g2.fill(shape);
        g2.setColor(Color.BLACK);
        g2.draw(shape);
       
        double radius = Math.min(this.height, this.width) / 4.0;
        
        if (numStones > 0)
        {
             g2.setColor(Color.WHITE);
             FontMetrics metrics = g2.getFontMetrics();
             g2.setFont(new Font("Arial", Font.BOLD, 20));

             
             g2.drawString("" + numStones, this.x + width/2 - metrics.stringWidth(""+numStones) / 2, this.y + height/2 + metrics.getDescent() / 2);
        }
        for(int i = 0; i < numStones; i++)
        {
            double angle = (2 * Math.PI) / numStones * (i + 1);
            double aX = this.x + width / 2 + Math.cos(angle) * radius - radius / 2.0;
            double aY = this.y + height / 2 + Math.sin(angle) * radius - radius / 2.0;    
           
            Ellipse2D.Double stone = new Ellipse2D.Double(aX, aY, stoneSize, stoneSize);
           
            g2.setPaint(stoneColor);
            g2.fill(stone);
            g2.setColor(Color.BLACK);
            g2.draw(stone);
        }
    }

}
