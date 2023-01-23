package mz.kolokwium;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AnimatorWindow extends JFrame{
    private static final long serialVersionUID = 1L;
    public JPanel contentPanel;
    protected static final Random rand = new Random();

    public AnimatorWindow() {
        CreateWindowElement createWindowElement = new CreateWindowElement();
        setTitle("Koło");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int ww = 500, wh = 600;
        setResizable(false);
        setBounds((screen.width - ww) / 2, (screen.height - wh) / 2, ww, wh);

        contentPanel = new JPanel();
        setContentPane(contentPanel);
        contentPanel.setLayout(null);

        AnimPanel kanwa = new AnimPanel();
        kanwa.setBounds(0,0,500,500);
        contentPanel.add(kanwa);
        addKeyListener(kanwa);
        SwingUtilities.invokeLater(kanwa::initialize);

        JPanel functionPanel = new JPanel();
        GridLayout functionLayout = new GridLayout(1, 2);
        functionPanel.setBounds(0,500,500 ,65);
        functionPanel.setLayout(functionLayout);
        contentPanel.add(functionPanel);

        JButton btnCircle = createWindowElement.createButton("Add Circle");
        JButton btnObstacle = createWindowElement.createButton("Add Obstacle");
        JButton btnAnimate = createWindowElement.createButton("Start");
        functionPanel.add(btnCircle);
        functionPanel.add(btnObstacle);
        functionPanel.add(btnAnimate);

        btnCircle.addActionListener(e -> kanwa.addCircle());
        btnObstacle.addActionListener(e -> kanwa.addObstacle(rand.nextInt(480), rand.nextInt(480)));
        btnAnimate.addActionListener(e -> kanwa.animate());

    }

}
