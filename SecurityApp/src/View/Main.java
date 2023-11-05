package View;

import form.*;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Main extends JFrame{

    private JPanel form;
    private PanleMenu menu;

    public JPanel getForm() {
        return form;
    }

    public void setForm(JPanel form) {
        this.form = form;
    }

    public Main(){
        init();
    }
    public void init(){
        this.setTitle("CryptaKey");
        ImageIcon image = new ImageIcon("ImageIcon\\logo.png");

        form = new PanelBody();
        menu = new PanleMenu(this);
        this.getContentPane().add(menu, BorderLayout.WEST);
        this.getContentPane().add(form, BorderLayout.CENTER);

        this.setSize(1200, 700);
        this.setIconImage(image.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public static void main(String[] args) {

        new Main();
    }
}