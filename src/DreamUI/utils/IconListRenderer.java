package DreamUI.utils;

import DreamUI.UIColours;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class IconListRenderer extends DefaultListCellRenderer {

    private Map<Object, Icon> icons = null;
    private boolean paint = true;
    public IconListRenderer(Map<Object, Icon> icons) {
        this.icons = icons;
    }


    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        JLabel label =
                (JLabel) super.getListCellRendererComponent(list,
                        value, index, isSelected, cellHasFocus);
        label.setHorizontalAlignment(CENTER);
        paint = true;
        if (icons!=null){
            Icon icon = icons.get(value);
            label.setIcon(icon);
        }
        setForeground(UIColours.TEXT_COLOR);
        if (isSelected || cellHasFocus){
            paint = false;
            label.setForeground(Color.black);
            label.setBackground(UIColours.ORANGE_COLOR.brighter());
        }

        return label;
    }
        @Override
        public void paint(Graphics g){
        if (paint){
            setBackground(UIColours.BUTTON_COLOUR);
        }
            super.paint(g);
        }
    public void setIcons(Map<Object, Icon> icons) {
        this.icons = icons;

    }
}
