package DreamUI.components;


import DreamUI.UIColours;
import DreamUI.utils.ImageUtils;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class DreamFrame extends JFrame {


    public DreamFrame() {
        this("");
    }

    public DreamFrame(String title) {
        this(title, null);
    }

    public DreamFrame(String title, BufferedImage logo) {
        setLayout(new BorderLayout());
        setUndecorated(true);
        setTitle(title);
        setBackground(UIColours.BODY_COLOUR);
        getContentPane().setBackground(UIColours.BODY_COLOUR);
        Header header = new Header(this, title, logo);
        add(header, BorderLayout.PAGE_START);
    }

    @Override
    public void setSize(Dimension d) {
        setSize(d.width, d.height);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        setShape(new RoundRectangle2D.Double(0, 0, width, height, 12, 12));
    }

    public class Header extends DreamPanel {

        public String title;
        public BufferedImage img;
        public DreamLabel logoContainer;
        public DreamLabel titleContainer;
        public DreamPanel left, right;
        public DreamButton closeBtn;
        public DreamButton minBtn;
        public Point initialClick;
        public JFrame frame;
        int xDrag;
        int yDrag;
        int xPress;
        int yPress;

        Header(JFrame frame, String title, BufferedImage img) {
            this.frame = frame;
            add(left = new DreamPanel(new FlowLayout(FlowLayout.LEFT)), BorderLayout.WEST);
            add(right = new DreamPanel(new FlowLayout(FlowLayout.RIGHT)), BorderLayout.EAST);
            setBorder(new EmptyBorder(3, 3, 3, 3));
            this.title = title;
            this.img = img;
            if (this.img != null) {
                this.img = ImageUtils.resize(img, 20, 20);
                left.add(logoContainer = new DreamLabel());
                logoContainer.setIcon(new ImageIcon(this.img));
            }
            left.add(titleContainer = new DreamLabel(this.title));
            titleContainer.setBorder(new EmptyBorder(0, 2, 0, 2));
            minBtn = new DreamButton("-");
            minBtn.setSize(50,10);
            right.add(minBtn);
            right.add(closeBtn = new DreamButton("X"));
            closeBtn.addActionListener(l -> System.exit(0));
            minBtn.addActionListener(l -> setState(JFrame.ICONIFIED));
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    initialClick = e.getPoint();
                    getComponentAt(initialClick);
                }
            });


            addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    xDrag = e.getX();
                    yDrag = e.getY();

                    frame.setLocation(frame.getLocation().x+xDrag-xPress,
                            frame.getLocation().y+yDrag-yPress);
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    xPress = e.getX();
                    yPress = e.getY();
                }
            });
        }
    }
}

