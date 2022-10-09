package DreamUI.components;


import DreamUI.UIColours;
import DreamUI.utils.IconListRenderer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.util.HashMap;
import java.util.Map;

public class DreamComboBox<T> extends JComboBox<T> {

    public DefaultComboBoxModel<T> model;


    public DreamComboBox(T... objects) {
        setBorder(new LineBorder(UIColours.BUTTON_COLOUR.brighter()));
        setOpaque(true);
        setFocusable(false);
        setBackground(UIColours.COMBOBOX_COLOR);
        setForeground(UIColours.TEXT_COLOR);
        setUI(new BasicComboBoxUI() {
            @Override
            public JButton createArrowButton() {
                return new DreamButton("\u2193");
            }
        });
        setModel(model = new DefaultComboBoxModel<>());
        Map<Object,Icon> iconTextMap = new HashMap<>();

        for (T object : objects) {
            model.addElement(object);
            iconTextMap.put(object ,null);
        }

        setRenderer(new IconListRenderer(iconTextMap));
    }

    public DreamComboBox(Map<Object,Icon> iconTextMap) {
        setBorder(new LineBorder(UIColours.BUTTON_COLOUR.brighter()));
        setOpaque(true);
        setFocusable(false);
        setBackground(UIColours.COMBOBOX_COLOR);
        setForeground(UIColours.TEXT_COLOR);
        setUI(new BasicComboBoxUI() {
            @Override
            public JButton createArrowButton() {
                return new DreamButton("\u2193");
            }
        });
        setModel(model = new DefaultComboBoxModel<>());
        setRenderer(new IconListRenderer(iconTextMap));
        for (Object s : iconTextMap.keySet()) {
            model.addElement((T) s);
        }
    }



}


