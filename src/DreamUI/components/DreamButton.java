package DreamUI.components;


import DreamUI.UIColours;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DreamButton extends JButton {

    public DreamButton(String text) {
        this(text, null);
    }

    public DreamButton(String text, Image icon) {
        super(text);
        setFocusPainted(false);
        setFocusable(false);
        setBackground(UIColours.BUTTON_COLOUR);
        setForeground(UIColours.TEXT_COLOR);
        setOpaque(true);
        setBorder(new CompoundBorder(new LineBorder(UIColours.BUTTON_COLOUR.brighter()),
                new EmptyBorder(5, 5, 5, 5)));
        setContentAreaFilled(false);
        if (icon != null) {
            setIcon(new ImageIcon(icon));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(UIColours.BUTTON_COLOUR.brighter().brighter());
        } else if (getModel().isRollover()) {
            g.setColor(UIColours.BUTTON_COLOUR.brighter());
        } else {
            g.setColor(UIColours.BUTTON_COLOUR);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

}
