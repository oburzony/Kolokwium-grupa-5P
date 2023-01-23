package mz.kolokwium;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;


public abstract class Figure implements Runnable, ActionListener {

    protected Graphics2D buffer;
    protected Area area;
    protected Shape shape;
    protected AffineTransform aft;
    private final int delay;
    protected final int width;
    protected final int height;
    protected final Color color;
    public AnimPanel panel;
    static ArrayList<Circle> circleList = new ArrayList<>();


    public Figure(Graphics2D buf, int del, int w, int h, int x, int y, Color c, AnimPanel p) {
        delay = del;
        buffer = buf;
        width = w;
        height = h;
        panel =  p;
        color = c;
    }

    @Override
    public void run() {

        area.transform(aft);
        shape = area;

        while (true) {
            shape = nextFrame();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }

        }
    }

    protected Shape nextFrame() {
        area = new Area(area);
        aft = new AffineTransform();
        return area;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        buffer.setColor(color.brighter());
        buffer.fill(shape);
        buffer.setColor(color.darker());
        buffer.draw(shape);
    }

    protected int[] figurePosition() {
        area = new Area(area);
        Rectangle bounds = area.getBounds();
        return new int[]{bounds.x, bounds.y, bounds.width, bounds.height};
    }

}
