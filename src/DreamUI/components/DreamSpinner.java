package DreamUI.components;

import DreamUI.UIColours;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class DreamSpinner extends JSpinner {
    public DreamSpinner(){
    this(0,0,100,1);

    }
    public DreamSpinner(int value, int minimum, int maximum, int stepSize){

        SpinnerModel m = new SpinnerNumberModel(value,minimum,maximum,stepSize);
        setModel(m);
        setBackground(UIColours.TEXTFIELD_COLOR);
        setForeground(UIColours.TEXT_COLOR);
        // Text editor
        getEditor().getComponent(0).setBackground(UIColours.TEXTFIELD_COLOR);
        getEditor().getComponent(0).setForeground(UIColours.TEXT_COLOR);
        JTextField field = (JTextField) getEditor().getComponent(0);
        field.setHorizontalAlignment(JTextField.CENTER);
        // Up button
        getComponent(0).setBackground(UIColours.BUTTON_COLOUR);
        // dOWN button
        getComponent(1).setBackground(UIColours.BUTTON_COLOUR);
        setBorder(new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                return;
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(0,0,0,0);
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }
        });
    }

}
