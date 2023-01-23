package mz.kolokwium;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Circle extends Figure {

    public static Integer LEFT_DIR = 1;
    public static Integer RIGHT_DIR = 2;
    public static Integer DOWN_DIR = 3;
    public static Integer UP_DIR = 4;
    boolean stop;
    public Circle(Graphics2D buffer, int delay, int width, int height, int x, int y, Color color, AnimPanel panel) {
        super(buffer, delay, width, height, x, y, color, panel);
        shape = new Ellipse2D.Float(x, y, 20, 20);
        aft = new AffineTransform();
        area = new Area(shape);
        circleList.add(this);
    }
    protected Shape nextFrame() {
        area = new Area(area);
        aft = new AffineTransform();
        Rectangle bounds = area.getBounds();
        int cx = bounds.x + bounds.width/2;
        int cy = bounds.y + bounds.height/2;
        area.transform(aft);
        return area;
    }
    protected Shape goDirection(int d) {
        area = new Area(area);
        aft = new AffineTransform();
        Rectangle bounds = area.getBounds();
        int rbx = bounds.x;
        int lbx = bounds.x + bounds.width;
        int uby = bounds.y;
        int dby = bounds.y + bounds.height;
        if (d == RIGHT_DIR && lbx < width && !stop) {
            aft.translate(20, 0);
        }
        if (d == LEFT_DIR && rbx > 0 && !stop) {
            aft.translate(-20, 0);
        }
        if (d == DOWN_DIR && dby < height && !stop) {
            aft.translate(0, 20);
        }
        if (d == UP_DIR && uby > 0 && !stop) {
            aft.translate(0, -20);
        }
        area.transform(aft);
        return area;

    }
    protected void collisionWithObstacle() {
        this.stop = true;
    }
}

