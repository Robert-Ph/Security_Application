package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LogoPanel extends JPanel {
    public LogoPanel(Main main) {

        BufferedImage image = null;
        try {

            image = ImageIO.read(Main.class.getResource("/ImageIcon/logo.png"));
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
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                main.remove(main.getForm());
                main.setForm(new panel(main));
                main.add(main.getForm(), BorderLayout.CENTER);
                main.repaint();
                main.revalidate();
            }
        });

        Font customFont = label.getFont().deriveFont(Font.BOLD, 24f);
        label.setFont(customFont);
        label.setForeground(Color.white);
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        this.add(label);
        this.setOpaque(false);

    }
}
