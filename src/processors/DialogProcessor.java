package processors;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.ShapeFactory;
import models.GroupShape;
import models.Shape;

public class DialogProcessor extends DisplayProcessor {
    
    private List<Shape> selectedShapes = new ArrayList<>();
    private Point lastMouseLocation = null;
    private GroupShape group = new GroupShape();
    private JFrame frame = null;
    private ShapeFactory factory = new ShapeFactory();

    public DialogProcessor() {}
    
    public DialogProcessor(JFrame frame) {
    	this.frame = frame;
    }
    
    @Override
    public void draw(Graphics graphics) {
    	super.draw(graphics);
    	if (this.selectedShapes != null) {
    		Graphics2D graphics2d = (Graphics2D)graphics;
    		graphics.setColor(Color.BLACK);
    		graphics2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1f, new float[] {2f}, 0f));
    		int n = 0, m = 0;
    		for (Shape shape : selectedShapes) {
    			n = (4 + (shape.getBorderThickness() / 2));
        		m = (n*2);
                graphics.drawRect(shape.getLocation().x - n, shape.getLocation().y - n, shape.getWidth() + m, shape.getHeight() + m);
			}
    	}
    }
    
    public void addRandomRectangle() {
        int x = 100 + (int)Math.round(Math.random()*900);
        int y = 100 + (int)Math.round(Math.random()*500);
        Shape rectangle = factory.getShape("RectangleShape", x, y, 100, 200, 0, Color.WHITE, 1, Color.BLACK);
        this.shapeList.add(rectangle);
    }
    
    public void addRandomOval(int width, int height) {
    	int x = 100 + (int)Math.round(Math.random()*900);
        int y = 100 + (int)Math.round(Math.random()*500);
        Shape oval = factory.getShape("OvalShape", x, y, width, height, 0, Color.WHITE, 1, Color.BLACK);
        this.shapeList.add(oval);
	}
    
    public void addRandomLine(int width, int height) {
    	int x = 100 + (int)Math.round(Math.random()*900);
        int y = 100 + (int)Math.round(Math.random()*500);
        int x2 = 100 + (int)Math.round(Math.random()*900);
        int y2 = 100 + (int)Math.round(Math.random()*500);
        Shape line = factory.getShape("LineShape", x, y, x2, y2, 0, Color.WHITE, 1, Color.BLACK);
        this.shapeList.add(line);
	}
    
    public void addRandomPolygon(int width, int height, int sides) {
    	int x = 100 + (int)Math.round(Math.random()*900);
        int y = 100 + (int)Math.round(Math.random()*500);
        Shape polygon = factory.getShape("PolygonShape", x, y, width, height, sides, Color.WHITE, 1, Color.BLACK);
        this.shapeList.add(polygon);
	}
    
    public void addRandomNewShape(int width, int height, int sides) {
    	int x = 100 + (int)Math.round(Math.random()*900);
        int y = 100 + (int)Math.round(Math.random()*500);
        Shape newShape = factory.getShape("NewShape", x, y, width, height, sides, Color.WHITE, 1, Color.BLACK);
        this.shapeList.add(newShape);
	}
    
    public void group() {
		if (this.selectedShapes.size() < 2) {
			Object[] buttons = {"Ok"};
			showErrorDialog(this.frame, "Error", "You must select at least 2 shapes to group them.", buttons);
		}
		int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
		for (Shape shape : this.selectedShapes) {
			if (minX > shape.getLocation().x) {
				minX = shape.getLocation().x;
			}
			if (minY > shape.getLocation().y) {
				minY = shape.getLocation().y;
			}
			if (maxX < (shape.getLocation().x + shape.getWidth())) {
				maxX = (shape.getLocation().x + shape.getWidth());
			}
			if (maxY < (shape.getLocation().y + shape.getHeight())) {
				maxY = (shape.getLocation().y + shape.getHeight());
			}
	    }
		group = new GroupShape(new Rectangle(minX, minY, (maxX - minX), (maxY - minY)));
		group.groupShapes = this.selectedShapes;
		for (Shape shape : this.selectedShapes) {
			shapeList.remove(shape);
		}
		this.selectedShapes = new ArrayList<>();
		this.selectedShapes.add(group);
		this.shapeList.add(group);
    }
    
    public void unGroup() {
    	if (this.group.groupShapes == null || this.group.groupShapes.size() < 2) { // null pointer here
    		Object[] buttons = {"Ok"};
			showErrorDialog(this.frame, "Error", "You must select group first.", buttons);
		}
    	this.selectedShapes.remove(group);
    	this.shapeList.remove(group);
    	for (Shape shape : this.group.groupShapes) {
			this.shapeList.add(shape);
		}
    	//this.group.groupShapes = new ArrayList<>();
    	this.group.groupShapes = null;
    	this.group = null;
    }
    
    public void deleteSelectedShapes() {
    	for (Shape shape : this.selectedShapes) {
			shapeList.remove(shape);
		}
    	selectedShapes = new ArrayList<>();
    }
    
    public void rotateSelectedShapesLeft(double degrees) {
    	int newX, newY, newWidth, newHeight;
    	for (Shape shape : this.selectedShapes) {
    		newX = (int) (shape.getLocation().x * Math.cos(degrees) - shape.getLocation().y * Math.sin(degrees));
    		newY = (int) (shape.getLocation().x * Math.sin(degrees) + shape.getLocation().y * Math.cos(degrees));
    		
    		newWidth = (int) (shape.getWidth() * Math.cos(degrees) - shape.getHeight() * Math.sin(degrees));
    		newHeight = (int) (shape.getWidth() * Math.sin(degrees) + shape.getHeight() * Math.cos(degrees));
    		
    		shape.setLocation(new Point(newX, newY));
    		shape.setWidth(newWidth);
    		shape.setHeight(newHeight);
    		
    	}

    }
    
    public void rotateSelectedShapesRight(int degrees) {
    	
    }
    
    public Shape containsPoint(Point point) {
        for (int i = this.shapeList.size() - 1; i >= 0; i--) {
            if (this.shapeList.get(i).contains(point)) {
                return this.shapeList.get(i);
            }
        }
        return null;
    }
    
    public void translateTo(Point point) {
        if (selectedShapes != null) {
        	for (Shape shape : selectedShapes) {
				//shape.setLocation(new Point(((shape.getLocation().x + point.x) - this.lastMouseLocation.x), ((shape.getLocation().y + point.y) - this.lastMouseLocation.y)));
				shape.translate((point.x - lastMouseLocation.x), (point.y - lastMouseLocation.y));
        	}
            this.lastMouseLocation = point;
        }
    }
    
    public void showErrorDialog(JFrame frame, String dialogTitle, String dialogMessage, Object[] dialogOptionButtons) {
		JOptionPane.showOptionDialog(frame, dialogMessage, dialogTitle, JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, dialogOptionButtons, dialogOptionButtons[0]);
		return;
	}
	
	public int showColorDialog(JFrame frame, String dialogTitle, String dialogMessage, Object[] dialogOptionButtons) {
		int option = JOptionPane.showOptionDialog(frame, dialogMessage, dialogTitle, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, dialogOptionButtons, dialogOptionButtons[0]);
		return option;
	}
	
	public int showDeleteDialog(JFrame frame, String dialogTitle, String dialogMessage, Object[] dialogOptionButtons) {
		int option = JOptionPane.showOptionDialog(frame, dialogMessage, dialogTitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, dialogOptionButtons, dialogOptionButtons[0]);
		return option;
	}
	
	public int showRotateDialog(JFrame frame, String dialogTitle, String dialogMessage) {
		String input = JOptionPane.showInputDialog(frame, dialogMessage, dialogTitle, 1);
		if (input.length() == 0) {
			return 0;
		} else {
			if (input.matches("^[0-9]*$")) {
				int degrees = Integer.parseInt(input);
				return degrees;
			} else {
				Object[] buttons = {"Ok"};
				showErrorDialog(frame, "Error", "You can enter only numbers.", buttons);
				showResizeWidthDialog(frame, dialogTitle, dialogMessage);
			}
			return 0; // check it 
		}
	}
	
	public int showDialog(JFrame frame, String dialogTitle, String dialogMessage, Object[] dialogOptionButtons) {
		int option = JOptionPane.showOptionDialog(frame, dialogMessage, dialogTitle, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, dialogOptionButtons, dialogOptionButtons[0]);
		return option;
	}
	
	public void showPolygonDialog(JFrame frame, String dialogTitle, String dialogMessage) {
		String sides = JOptionPane.showInputDialog(frame, dialogMessage, dialogTitle, 1);
		if (sides.length() == 0) {
			return;
		} else {
			if (sides.matches("^[0-9]*$")) {
				int newSides = Integer.parseInt(sides);
					addRandomPolygon(200, 200, newSides);
			} else {
				Object[] buttons = {"Ok"};
				showErrorDialog(frame, "Error", "You can enter only numbers.", buttons);
				showPolygonDialog(frame, dialogTitle, dialogMessage);
			}
		}
	}
	
	public void showScaleDialog(JFrame frame, String dialogTitle, String dialogMessage, String type) {
		Object[] dialogOptionButtons = {"x2", "x3", "x5"};
		int option = JOptionPane.showOptionDialog(frame, dialogMessage, dialogTitle, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, dialogOptionButtons, dialogOptionButtons[0]);
		int scale = 1;
		switch (option) {
			case 0: 
				scale = 2;
				break;
			case 1: 
				scale = 3;
				break;
			case 2: 
				scale = 5;
				break;
			default: return;
		}
		if (type.equals("Up")) {
			for (Shape shape : this.selectedShapes) {
				shape.setWidth(shape.getWidth() * scale);
				shape.setHeight(shape.getHeight() * scale);
			}
		} else if (type.equals("Down")) {
			for (Shape shape : this.selectedShapes) {
				shape.setWidth(shape.getWidth() / scale);
				shape.setHeight(shape.getHeight() / scale);
			}
		} else;
	}
	
	public void showResizeWidthDialog(JFrame frame, String dialogTitle, String dialogMessage) {
		String width = JOptionPane.showInputDialog(frame, dialogMessage, dialogTitle, 1);
		if (width.length() == 0) {
			return;
		} else {
			if (width.matches("^[0-9]*$")) {
				int newWidth = Integer.parseInt(width);
				for (Shape shape : this.selectedShapes) {
					shape.setWidth(newWidth);
				}
			} else {
				Object[] buttons = {"Ok"};
				showErrorDialog(frame, "Error", "You can enter only numbers.", buttons);
				showResizeWidthDialog(frame, dialogTitle, dialogMessage);
			}
		}
	}
	
	public void showResizeHeightDialog(JFrame frame, String dialogTitle, String dialogMessage) {
		String height = JOptionPane.showInputDialog(frame, dialogMessage, dialogTitle, 1);
		if (height.length() == 0) {
			return;
		} else {
			if (height.matches("^[0-9]*$")) {
				int newHeight = Integer.parseInt(height);
				for (Shape shape : this.selectedShapes) {
					shape.setHeight(newHeight);
				}
			} else {
				Object[] buttons = {"Ok"};
				showErrorDialog(frame, "Error", "You can enter only numbers.", buttons);
				showResizeHeightDialog(frame, dialogTitle, dialogMessage);
			}
		}
	}
	
	public void showResizeBorderThicknessDialog(JFrame frame, String dialogTitle, String dialogMessage) {
		String borderThickness = JOptionPane.showInputDialog(frame, dialogMessage, dialogTitle, 1);
		if (borderThickness.length() == 0) {
			return;
		} else {
			if (borderThickness.matches("^[0-9]*$")) {
				int newBorderThickness = Integer.parseInt(borderThickness);
				for (Shape shape : this.selectedShapes) {
					shape.setBorderThickness(newBorderThickness);
				}
			} else {
				Object[] buttons = {"Ok"};
				showErrorDialog(frame, "Error", "You can enter only numbers.", buttons);
				showResizeWidthDialog(frame, dialogTitle, dialogMessage);
			}
		}
	}
    
    public List<Shape> getSelectedShapes() {
        return this.selectedShapes;
    }

    public void setSelectedShapes(List<Shape> shapes) {
        this.selectedShapes = shapes;
    }

    public Point getLastMouseLocation() {
        return this.lastMouseLocation;
    }

    public void setLastMouseLocation(Point point) {
        this.lastMouseLocation = point;
    }
    
    public void setFillColor(Color color) {
        if (this.selectedShapes != null) {
        	for (Shape shape : selectedShapes) {
        		
        		if (shape.getClass().isInstance(group)) {
        			for (Shape shape2 : group.groupShapes) {
        				shape2.setFillColor(color);
        			}
        		} else {
        			shape.setFillColor(color);
        		}
        		
			}
        }
    }
    
    public void setBorderColor(Color color) {
        if (this.selectedShapes != null) {
        	for (Shape shape : selectedShapes) {
        		if (shape.getClass().isInstance(group)) {
        			for (Shape shape2 : group.groupShapes) {
        				shape2.setBorderColor(color);
        			}
        		} else {
        			shape.setBorderColor(color);
        		}
			}
        }
    }
    
}
