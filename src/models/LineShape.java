package models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class LineShape extends Shape {
	
	public LineShape(Rectangle rectangle, Color fillColor, int borderThickness, Color borderColor) {
		super(rectangle);
		this.fillColor = fillColor;
		this.borderThickness = borderThickness;
		this.borderColor = borderColor;
	}
	
	public LineShape(LineShape line) {}
	
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
        graphics.drawLine(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
	
}
