package View;

import javax.swing.*;
import java.awt.*;

public class Main {


    public Main(){
        init();
    }
    public void init(){
        JFrame frame = new JFrame();
        frame.setTitle("CryptaKey");
        ImageIcon image = new ImageIcon("ImageIcon\\logo.png");


        PanleMenu panle = new PanleMenu();

        frame.getContentPane().add(panle, BorderLayout.WEST);
        frame.getContentPane().add(new Body(), BorderLayout.CENTER);
//        frame.getContentPane().add(new UICaesar(), BorderLayout.CENTER);


        frame.setSize(1200, 700);
        frame.setIconImage(image.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void main(String[] args) {

        new Main();
    }
}