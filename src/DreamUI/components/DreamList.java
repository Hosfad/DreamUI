package DreamUI.components;


import DreamUI.UIColours;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DreamList<T> extends JList<T> {

    DefaultListModel<T> model;

    public DreamList() {
        model = new DefaultListModel<>();
        setModel(model);
        setBackground(UIColours.BUTTON_COLOUR);
        setLayoutOrientation(VERTICAL);
        setBorder(new CompoundBorder(new LineBorder(UIColours.BUTTON_COLOUR.brighter()), new EmptyBorder(2, 2, 2, 2)));
        setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            Component l = (Component) value;
            if (index % 2 == 0) {
                l.setBackground(UIColours.BODY_COLOUR.brighter());
            } else {
                l.setBackground(UIColours.BODY_COLOUR);
            }
            l.setForeground(UIColours.TEXT_COLOR);
            if (isSelected || cellHasFocus) {
                l.setBackground(UIColours.ORANGE_COLOR.brighter());
                l.setForeground(Color.BLACK);
            }

            return l;
        });
    }

    public void add(T element) {
        model.addElement(element);
    }

}
