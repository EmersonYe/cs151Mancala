package src;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class BoardStyle1 implements MancalaFormatter{

	public Shape setPitShape(MancalaShape s) {
		return new Ellipse2D.Double(s.getX(), s.getY(), s.getWidth(), s.getHeight());
	}

	public Shape setMancalaShape(MancalaShape s) {
		return new RoundRectangle2D.Double(s.getX(), s.getY(), s.getWidth(), s.getWidth()+200, 20, 20);
	}

	public Color setPitColor() {
		return Color.YELLOW;
	}

	public Color setStoneColor() {
		return Color.GREEN;
	}

}
