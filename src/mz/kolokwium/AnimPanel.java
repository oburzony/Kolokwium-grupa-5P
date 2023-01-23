package mz.kolokwium;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AnimPanel extends JPanel implements ActionListener, KeyListener {

    private static final long serialVersionUID = 1L;

    Image image;
    Graphics2D device;
    Graphics2D buffer;
    Circle circle;
    private int delay = 10;
    private Color color;
    protected Timer timer;
    public AnimPanel(){
        super();
        timer = new Timer(delay, this);
    }

    public  void initialize() {
        int width = getWidth();
        int height = getHeight();
        image = createImage(width, height);
        buffer = (Graphics2D) image.getGraphics();
        buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        device = (Graphics2D) getGraphics();
        device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    protected void animate() {
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }
    protected void addCircle() {
        int x = 240;
        int y = 20;
        color = color.RED;
        circle = new Circle(buffer, delay, getWidth(), getHeight(), x,y,color,this);
        timer.addActionListener(circle);
        new Thread(circle).start();
    }

    protected void addObstacle(int x , int y ) {
        color = color.BLACK;
        Obstacle fig;
        fig = new Obstacle(buffer, delay, getWidth(), getHeight(), x,y,color,this);
        timer.addActionListener(fig);
        new Thread(fig).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        device.drawImage(image, 0, 0, null);
        buffer.clearRect(0, 0, getWidth(), getHeight());
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            circle.goDirection(Circle.RIGHT_DIR);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            circle.goDirection(Circle.LEFT_DIR);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            circle.goDirection(Circle.DOWN_DIR);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            circle.goDirection(Circle.UP_DIR);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }


}

