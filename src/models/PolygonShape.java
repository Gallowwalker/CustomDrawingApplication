package models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PolygonShape extends Shape{
	
	public PolygonShape(Rectangle rectangle) {
		super(rectangle);
	}
	
	public PolygonShape(Rectangle rectangle, int sides, Color fillColor, int borderThickness, Color borderColor) {
		super(rectangle);
		this.sides = sides;
		this.fillColor = fillColor;
		this.borderThickness = borderThickness;
		this.borderColor = borderColor;
	}
	
	public PolygonShape(PolygonShape polygon) {}
	
	@Override
    public boolean contains(Point point) {
        if (super.contains(point)) {
            return true;
        }
        else {
            return false;
        }
    }
	
	@Override
    public void drawSelf(Graphics graphics) {
        super.drawSelf(graphics);
        Graphics2D graphics2d = (Graphics2D)graphics;
        Rectangle rectangle = getWrappingRectangle();
        graphics.setColor(getFillColor());
        graphics.setColor(getBorderColor());
        graphics2d.setStroke(new BasicStroke(getBorderThickness(), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1f, new float[] {1f}, 0f));
        int[] x = new int[sides];
        int[] y = new int[sides];
        
        for (int i = 0; i < this.sides; i++) {
        	x[i] = (int) ((rectangle.x) + 80 * Math.cos(i * 2 * Math.PI / this.sides));
        	y[i] = (int) ((rectangle.y) + 80 * Math.sin(i * 2 * Math.PI / this.sides));
          //p.addPoint((int) ((rectangle.x) + 50 * Math.cos(i * 2 * Math.PI / this.sides)), (int) ((rectangle.y) + 50 * Math.sin(i * 2 * Math.PI / this.sides)));
        }
        Polygon p = new Polygon(x, y, sides);
        graphics.drawPolygon(p);
    }
	
}
