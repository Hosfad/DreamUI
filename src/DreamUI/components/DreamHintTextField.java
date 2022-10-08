package DreamUI.components;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by: Hawolt
 * Date: 23.12.2018
 * Time: 12:34
 */

public class DreamHintTextField extends DreamTextField {

    public String hint;
    public BufferedImage image;
    public DreamHintTextField(String hint) {
        this(hint,null);
    }
    public DreamHintTextField(String hint , Image image) {
        this.hint = hint;
        this.image = (BufferedImage) image;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().length() == 0) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            int height = getHeight();
            Insets insets = getInsets();
            FontMetrics metrics = g.getFontMetrics();
            int background = getBackground().getRGB();
            int foreground = getForeground().getRGB();
            int mask = 0xfefefefe;
            int mixed = ((background & mask) >>> 1) + ((foreground & mask) >>> 1);
            int extraX =0;
            g.setColor(new Color(mixed, true));
            if (image!=null){
                g.drawImage(image,insets.left +2,insets.top +2 ,null);
                extraX += image.getWidth();
                extraX += 10;
            }
            g.drawString(hint, insets.left + extraX , height / 2 + metrics.getAscent() / 2 - 2);
        }
    }

}
