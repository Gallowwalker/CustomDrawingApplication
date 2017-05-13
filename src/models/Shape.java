package models;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class Shape {
	
    protected Rectangle wrappingRectangle = null;
    protected Color fillColor = Color.WHITE, borderColor = Color.BLACK;
    protected int borderThickness = 1;
    protected int sides = 0;
    
	public int getSides() {
		return sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}

	public Shape() {}

    public Shape(Rectangle rectangle) {
        this.wrappingRectangle = rectangle;
    }

    public Shape(Shape shape) {
    	this.wrappingRectangle = shape.wrappingRectangle;
    	this.setLocation(shape.getLocation());
    	this.setWidth(shape.getWidth());
        this.setHeight(shape.getHeight());
        this.setFillColor(shape.getFillColor());
        this.setBorderColor(shape.getBorderColor());
        this.setBorderThickness(shape.getBorderThickness());
    }
    
    public boolean contains(Point point) {
        return getWrappingRectangle().contains(point);
    }
    
    public void drawSelf(Graphics graphics) {}
    
    public void translate(int dx, int dy) {
    	setLocation(new Point(getLocation().x + dx, getLocation().y + dy));
    }

    public Rectangle getWrappingRectangle() {
        return this.wrappingRectangle;
    }

    public void setWrappingRectangle(Rectangle rectangle) {
        this.wrappingRectangle = rectangle;
    }

    public int getWidth() {
        return this.getWrappingRectangle().width;
    }

    public void setWidth(int width) {
        this.wrappingRectangle.width = width;
    }

    public int getHeight() {
        return this.getWrappingRectangle().height;
    }

    public void setHeight(int height) {
        this.wrappingRectangle.height = height;
    }

    public Point getLocation() {
        return this.getWrappingRectangle().getLocation();
    }

    public void setLocation(Point point) {
        this.wrappingRectangle.setLocation(point);
    }

    public Color getFillColor() {
        return this.fillColor;
    }

    public void setFillColor(Color color) {
        this.fillColor = color;
    }
    
    public Color getBorderColor() {
		return this.borderColor;
	}

	public void setBorderColor(Color color) {
		this.borderColor = color;
	}
	
	public int getBorderThickness() {
		return this.borderThickness;
	}

	public void setBorderThickness(int thickness) {
		this.borderThickness = thickness;
	}
}
