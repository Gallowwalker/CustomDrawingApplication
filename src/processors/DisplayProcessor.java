package processors;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.util.List;
import java.util.ArrayList;
import models.Shape;

public class DisplayProcessor {

    public List<Shape> shapeList = new ArrayList<>();

    public DisplayProcessor() {}
    
    public void reDraw(Object sender, Graphics graphics) {
        Graphics2D graphics2d = (Graphics2D)graphics;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        draw(graphics);
    }
    
    public void draw(Graphics graphics) {
        for (int i = 0; i < this.shapeList.size(); i++) {
            drawShape(graphics, this.shapeList.get(i));
        }
    }
    
    public void drawShape(Graphics graphics, Shape shape) {
        shape.drawSelf(graphics);
    }
    
    public List<Shape> getShapeList() {
        return this.shapeList;
    }

    public void setShapeList(List<Shape> shapeList) {
        this.shapeList = shapeList;
    }

}
