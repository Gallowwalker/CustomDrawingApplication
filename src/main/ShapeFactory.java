package main;

import java.awt.Color;
import java.awt.Rectangle;

import models.LineShape;
import models.NewShape;
import models.OvalShape;
import models.PolygonShape;
import models.RectangleShape;
import models.Shape;

public class ShapeFactory {
	
	public Shape getShape(String type, int x, int y, int width, int height, int sides, Color fillColor, int borderThickness, Color borderColor) {
		
		if (type == null) {
			return null;
		}
		
		switch (type) {
			case "RectangleShape": return new RectangleShape(new Rectangle(x, y, width, height), fillColor, borderThickness, borderColor);
			case "PolygonShape": return new PolygonShape(new Rectangle(x, y, width, height), sides, fillColor, borderThickness, borderColor);
			case "OvalShape": return new OvalShape(new Rectangle(x, y, width, height), fillColor, borderThickness, borderColor);
			case "LineShape": return new LineShape(new Rectangle(x, y, width, height), fillColor, borderThickness, borderColor);
			case "NewShape": return new NewShape(new Rectangle(x, y, width, height), fillColor, borderThickness, borderColor);
			default: return null;
		}

	}
	
}
