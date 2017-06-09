package com.capella.bing.wallpaper.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ramesh Rajendran
 */
public class ImageHelper {

    public static BufferedImage writeText(InputStream inputStream, String text) throws IOException {
        final BufferedImage image = ImageIO.read(inputStream);

        Graphics g = image.getGraphics();
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString(text.substring(0, text.indexOf("(")), 10, 50);
        g.drawString(text.substring(text.indexOf("(") + 1, text.indexOf(")")), 10, 90);
        g.dispose();
        return image;
    }
   /* public static BufferedImage writeText(InputStream inputStream, String text) throws IOException {
        BufferedImage img = ImageIO.read(inputStream); // try/catch IOException
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // draw graphics
        g2d.drawImage(img, 0, 0, null);
        g2d.drawString(text, 100,100);

        g2d.dispose();

       return bufferedImage;
    }*/


}
