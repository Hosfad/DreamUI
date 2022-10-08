package DreamUI.utils;

import DreamUI.utils.Base64.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageUtils {


    /**
     * @param url  direct link for an image
     *
     */
    public static Image getImageFromUrl(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * @param imageString  Base64 String
     */
    public static BufferedImage decodeToImage(String imageString) {
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    /**
     * @param img  Image you want to resize
     * @param newW  New image width
     * @param newH  New image height
     */
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    /**
     * @param component  UI component you want to save
     * @return Saves the component to System.getProperty("user.home") /Pictures and returns an Image
     */
    public static Image saveComponent(Component component) throws IOException {
        Dimension size = component.getSize();
        BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = myImage.createGraphics();
        component.paint(g2);
        String newIMagePath = System.getProperty("user.home") + "/Pictures/" + component.getName() + ".png";
        File playerImage = new File(newIMagePath);
        ImageIO.write(myImage, "png", playerImage);
        return myImage;
    }
}
