package models;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class GroupShape extends Shape {
	
	public List<Shape> groupShapes = new ArrayList<>();
	
	public GroupShape() {}
	
	public GroupShape(Rectangle rectangle) {
		super(rectangle);
	}

	public GroupShape(GroupShape shape) {}
	
	@Override
    public boolean contains(Point point) {
		for (Shape shape : this.groupShapes) {
			if (shape.contains(point)) {
				return true;
			}
		}
		return false;
    }
	
	@Override
    public void drawSelf(Graphics graphics) {
		for (Shape shape : this.groupShapes) {
			shape.drawSelf(graphics);
		}
    }
	
	@Override
	public void translate(int dx, int dy) {
		super.translate(dx, dy);
		for (Shape shape : this.groupShapes) {
			shape.translate(dx, dy);
			//shape.setLocation(new Point(getLocation().x + dx, getLocation().y + dy));
		}
	}
	
	public List<Shape> getGroupShapes() {
		return this.groupShapes;
	}

	public void setGroupShapes(List<Shape> shapes) {
		this.groupShapes = shapes;
	}

}
