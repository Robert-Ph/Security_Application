package View;

import form.*;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {

    private JPanel form;
    private PanleMenu menu;
    private List<MenuItem> list;
    public Main(){
        init();
    }
    public void init(){
        JFrame frame = new JFrame();
        frame.setTitle("CryptaKey");
        ImageIcon image = new ImageIcon("ImageIcon\\logo.png");

        form = new PanelBody();
//        form = new UITransposition();
        menu = new PanleMenu();



        frame.getContentPane().add(menu, BorderLayout.WEST);
        frame.getContentPane().add(form, BorderLayout.CENTER);
//        frame.getContentPane().add(new UIAffine(), BorderLayout.CENTER);

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