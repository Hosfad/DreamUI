package DreamUI.components;

import DreamUI.UIColours;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class DreamSlider extends JSlider {
    public DreamSlider(int value) {
        setBackground(UIColours.BODY_COLOUR);
        setValue(value);
        setFocusable(false);
        setUI(new SliderUi(this));
        setPaintTicks(true);
        setPaintTicks(true);
        SliderPopupListener popupHandler = new SliderPopupListener();
        addMouseMotionListener(popupHandler);
        addMouseListener(popupHandler);
        addMouseWheelListener(popupHandler);

    }

    public DreamSlider(int value, int min, int max) {
        this(value);
        setMinimum(min);
        setMaximum(max);
        setValue(value);
    }

    class SliderUi extends BasicSliderUI {

        private static final int TRACK_HEIGHT = 8;
        private static final int TRACK_WIDTH = 8;
        private static final int TRACK_ARC = 5;
        private static final Dimension THUMB_SIZE = new Dimension(20, 20);
        private final RoundRectangle2D.Float trackShape = new RoundRectangle2D.Float();

        public SliderUi(final JSlider b) {
            super(b);
        }


        @Override
        protected void calculateTrackRect() {
            super.calculateTrackRect();
            if (isHorizontal()) {
                trackRect.y = trackRect.y + (trackRect.height - TRACK_HEIGHT) / 2;
                trackRect.height = TRACK_HEIGHT;
            } else {
                trackRect.x = trackRect.x + (trackRect.width - TRACK_WIDTH) / 2;
                trackRect.width = TRACK_WIDTH;
            }
            trackShape.setRoundRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height, TRACK_ARC, TRACK_ARC);
        }

        @Override
        protected void calculateThumbLocation() {
            super.calculateThumbLocation();
            if (isHorizontal()) {
                thumbRect.y = trackRect.y + (trackRect.height - thumbRect.height) / 2;
            } else {
                thumbRect.x = trackRect.x + (trackRect.width - thumbRect.width) / 2;
            }
        }

        @Override
        protected Dimension getThumbSize() {
            return THUMB_SIZE;
        }

        private boolean isHorizontal() {
            return slider.getOrientation() == JSlider.HORIZONTAL;
        }

        @Override
        public void paint(final Graphics g, final JComponent c) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paint(g, c);
        }

        @Override
        public void paintTrack(final Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Shape clip = g2.getClip();

            boolean horizontal = isHorizontal();
            boolean inverted = slider.getInverted();

            // Paint shadow.
            g2.setColor(UIColours.TEXT_COLOR);
            g2.fill(trackShape);

            // Paint track background.
            g2.setColor(UIColours.TEXT_COLOR);
            g2.setClip(trackShape);
            trackShape.y += 1;
            g2.fill(trackShape);
            trackShape.y = trackRect.y;

            g2.setClip(clip);

            // Paint selected track.
            if (horizontal) {
                boolean ltr = slider.getComponentOrientation().isLeftToRight();
                if (ltr) inverted = !inverted;
                int thumbPos = thumbRect.x + thumbRect.width / 2;
                if (inverted) {
                    g2.clipRect(0, 0, thumbPos, slider.getHeight());
                } else {
                    g2.clipRect(thumbPos, 0, slider.getWidth() - thumbPos, slider.getHeight());
                }

            } else {
                int thumbPos = thumbRect.y + thumbRect.height / 2;
                if (inverted) {
                    g2.clipRect(0, 0, slider.getHeight(), thumbPos);
                } else {
                    g2.clipRect(0, thumbPos, slider.getWidth(), slider.getHeight() - thumbPos);
                }
            }
            g2.setColor(UIColours.ORANGE_COLOR.brighter());
            g2.fill(trackShape);
            g2.setClip(clip);
        }

        @Override
        public void paintThumb(final Graphics g) {
            g.setColor(UIColours.ORANGE_COLOR);
            g.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
        }

        @Override
        public void paintFocus(final Graphics g) {
        }

    }

    private final JWindow toolTip = new JWindow();

    class SliderPopupListener extends MouseAdapter {
        private final JLabel label = new JLabel("", SwingConstants.CENTER);
        private final Dimension size = new Dimension(60, 20);
        private int prevValue = -1;

        public SliderPopupListener() {
            super();
            label.setOpaque(false);
            toolTip.setBackground(UIColours.BODY_COLOUR);
            setBackground(UIColours.BODY_COLOUR);
            label.setBackground(UIColours.BODY_COLOUR);
            label.setBorder(BorderFactory.createLineBorder(UIColours.ORANGE_COLOR));
            toolTip.add(label);
            toolTip.setSize(size);
        }

        protected void updateToolTip(MouseEvent me) {
            JSlider slider = (JSlider) me.getComponent();
            int intValue = (int) slider.getValue();
            if (prevValue != intValue) {
                label.setText(slider.getValue() + "/" + slider.getMaximum());
                Point pt = me.getPoint();
                pt.y = -size.height;
                SwingUtilities.convertPointToScreen(pt, me.getComponent());
                pt.translate(-size.width / 2, 0);
                toolTip.setLocation(pt);
            }
            prevValue = intValue;
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            updateToolTip(me);
            toolTip.setVisible(true);
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            toolTip.setVisible(false);

        }
    }
}
