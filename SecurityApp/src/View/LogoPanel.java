package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LogoPanel extends JPanel {
    public LogoPanel() {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("ImageIcon\\logo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int width = 50;
        int height = 50;
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(scaledImage);
        JLabel label = new JLabel();
        label.setIcon(logo);
        label.setText("CryptaKey");
        Font customFont = label.getFont().deriveFont(Font.BOLD, 24f);
        label.setFont(customFont);
        label.setForeground(Color.white);
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        this.add(label);
        this.setOpaque(false);

    }
}
