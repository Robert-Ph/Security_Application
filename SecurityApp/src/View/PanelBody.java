package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelBody extends JPanel {

    public PanelBody(){

    }

    @Override
    protected  void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image scaledImage;
        try {
            BufferedImage image = ImageIO.read(new File("ImageIcon\\Backgound.png"));
            int width = getWidth();
            int height = getHeight();
            scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(scaledImage, 0, 0, this);
    }
}
