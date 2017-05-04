package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
    private int numberOfStones;
    private Color pitColor;
    private Color stoneColor;
    private static final int STONE_SIZE = 20;
    
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
        numberOfStones = 0;
        shape = null;
    }
    public int getNumStone(){
    	return numberOfStones;
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
    public void setNumberOfStones(int number)
    {
        numberOfStones = number;
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
     * Paints the shape.
     * @param g2 the graphics
     */
    public void paintComponentShape(Graphics2D g2)
    {	
        g2.setColor(pitColor);
        g2.fill(shape);
        g2.setColor(Color.BLACK);
        g2.draw(shape);
       
        double radius = Math.min(this.height, this.width) / 4.0;
        
        if (numberOfStones > 0)
        {
             g2.setColor(Color.WHITE);
             FontMetrics fontMetrics = g2.getFontMetrics();
             g2.setFont(new Font("Arial", Font.BOLD, 20));

             
             g2.drawString("" + numberOfStones, this.x + width/2 - fontMetrics.stringWidth(""+numberOfStones) / 2, this.y + height/2 + fontMetrics.getDescent() / 2);
        }
        for(int i = 0; i < numberOfStones; i++)
        {
            double angle = (2 * Math.PI) / numberOfStones * (i + 1);
            double aX = this.x + width / 2 + Math.cos(angle) * radius - radius / 2.0;
            double aY = this.y + height / 2 + Math.sin(angle) * radius - radius / 2.0;    
           
            Ellipse2D.Double stone = new Ellipse2D.Double(aX, aY, STONE_SIZE, STONE_SIZE);
           
            g2.setPaint(stoneColor);
            g2.fill(stone);
            g2.setColor(Color.BLACK);
            g2.draw(stone);
        }
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
}
