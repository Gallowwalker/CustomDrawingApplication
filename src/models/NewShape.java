package models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class NewShape extends Shape {
	
	 public NewShape(Rectangle rectangle, Color fillColor, int borderThickness, Color borderColor) {
	        super(rectangle);
	        this.fillColor = fillColor;
			this.borderThickness = borderThickness;
			this.borderColor = borderColor;
	    }

	    public NewShape(RectangleShape rectangle) {}
	    
	    @Override
	    public boolean contains(Point point) {
	        if (super.contains(point)) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	    
	    @Override
	    public void drawSelf(Graphics graphics) {
	        super.drawSelf(graphics);
	        Graphics2D graphics2d = (Graphics2D)graphics;
	        Rectangle rectangle = getWrappingRectangle();
	        graphics.setColor(getFillColor());
	        graphics.fillOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	        graphics.setColor(getBorderColor());
	        graphics2d.setStroke(new BasicStroke(getBorderThickness(), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1f, new float[] {1f}, 0f));
	        
	        int newWidth = rectangle.width / 10;
            int newHeight = rectangle.height / 10;
            
            graphics.drawOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            graphics.drawLine((rectangle.x + (rectangle.width / 2)), rectangle.y + (rectangle.height / 2), rectangle.x, newHeight*5+rectangle.y);
            graphics.drawLine(rectangle.x,newHeight*5+rectangle.y, newWidth*10 +rectangle.x, newHeight * 5 + rectangle.y);
            graphics.drawLine(newWidth*5 +rectangle.x, newHeight * 5 + rectangle.y, newWidth*5 + rectangle.x, rectangle.y);
	    }

}
