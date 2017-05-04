package src;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class BoardStyle1 implements MancalaFormatter{

	@Override
	public Shape setPitShape(MancalaShape s) {
		return new RoundRectangle2D.Double(s.getX(), s.getY(), s.getWidth(), s.getWidth(), 20, 20);
	}

	@Override
	public Shape setMancalaShape(MancalaShape s) {
		return new Ellipse2D.Double(s.getX(), s.getY(), s.getWidth(), s.getHeight());
	}

	@Override
	public Color setPitColor() {
		// TODO Auto-generated method stub
		return Color.YELLOW;
	}

	@Override
	public Color setStoneColor() {
		// TODO Auto-generated method stub
		return Color.GREEN;
	}

}
