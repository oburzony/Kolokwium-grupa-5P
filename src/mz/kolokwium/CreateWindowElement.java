package mz.kolokwium;

import javax.swing.*;
public class CreateWindowElement {

    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        return button;
    }
}


