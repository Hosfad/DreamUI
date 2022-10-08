package DreamUI.utils;

import DreamUI.UIColours;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class IconListRenderer extends DefaultListCellRenderer {

    private Map<Object, Icon> icons = null;

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

        Icon icon = icons.get(value);
        label.setIcon(icon);
    //    list.setSelectionBackground(UIColours.BODY_COLOUR);
        label.setHorizontalAlignment(CENTER);

        return label;
    }
        @Override
        public void paint(Graphics g){
            setBackground(UIColours.BUTTON_COLOUR);

            super.paint(g);
        }
    public void setIcons(Map<Object, Icon> icons) {
        this.icons = icons;

    }
}
