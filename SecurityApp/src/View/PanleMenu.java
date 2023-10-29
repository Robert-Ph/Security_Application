package View;

import form.*;
import model.MenuItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class PanleMenu extends JPanel {
    private LogoPanel logoPanel ;
    private MenuBar menu = new MenuBar();
    private JPanel panel;
    private java.util.List<MenuItem> list;

    public java.util.List<MenuItem> getList() {
        return list;
    }

    public void setList(List<MenuItem> list) {
        this.list = list;
    }

    public MenuBar getMenu() {
        return menu;
    }

    public void setMenu(MenuBar menu) {
        this.menu = menu;
    }

    public PanleMenu(Main main){
        this.setLayout(new BorderLayout());
        logoPanel =new LogoPanel(main);

        this.add(logoPanel, BorderLayout.NORTH);
        init(main);

        this.setPreferredSize(new Dimension(200,700));


    }
    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }
    public void init(Main main){

        list = new ArrayList<>();
        JPanel panelTilie = new JPanel();
        panelTilie.setLayout(new BorderLayout());
        panelTilie.setOpaque(false);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0,0,0,0));


        model.MenuItem caesar = new model.MenuItem(1,"     Caesar", null);
        caesar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.remove(main.getForm());
                main.setForm(new UICaesar());
                main.add(main.getForm(), BorderLayout.CENTER);
                main.repaint();
                main.revalidate();
                System.out.println("click caesar");
            }

        });


        model.MenuItem affine = new model.MenuItem(2,"     Affine",null);
        affine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                main.remove(main.getForm());
                main.setForm(new UIAffine());
                main.add(main.getForm(), BorderLayout.CENTER);
                main.repaint();
                main.revalidate();
                System.out.println("click affine");
            }
        });
        model.MenuItem vigenere = new model.MenuItem(2,"     Vigenere",null);
        vigenere.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                main.remove(main.getForm());
                main.setForm(new UIVigenere());
                main.add(main.getForm(), BorderLayout.CENTER);
                main.repaint();
                main.revalidate();
                System.out.println("click vigenere");
            }
        });

        model.MenuItem rsa = new model.MenuItem(3,"     RSA",null);
        rsa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                main.remove(main.getForm());
                main.setForm(new UIRSA());
                main.add(main.getForm(), BorderLayout.CENTER);
                main.repaint();
                main.revalidate();
                System.out.println("click RSA");
            }
        });

        model.MenuItem transposition = new model.MenuItem(3,"     Transposition",null);
        transposition.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                main.remove(main.getForm());
                main.setForm(new UIRSA());
                main.add(main.getForm(), BorderLayout.CENTER);
                main.repaint();
                main.revalidate();
                System.out.println("click Transposition");
            }
        });

        model.MenuItem des = new model.MenuItem(3,"     DES",null);
        des.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                main.remove(main.getForm());
                main.setForm(new UIDES());
                main.add(main.getForm(), BorderLayout.CENTER);
                main.repaint();
                main.revalidate();
                System.out.println("click DES");
            }
        });

        model.MenuItem aes = new model.MenuItem(3,"     AES",null);
        aes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                main.remove(main.getForm());
                main.setForm(new UIAES());
                main.add(main.getForm(), BorderLayout.CENTER);
                main.repaint();
                main.revalidate();
                System.out.println("click SES");
            }
        });


        model.MenuItem kdx = new model.MenuItem(0,"Khoa Doi Xung",null,caesar,affine,vigenere, transposition, des,aes);
        kdx.getLbName().setFont(new Font(kdx.getLbName().getFont().getName(), Font.BOLD, 16));

        model.MenuItem kbdx = new model.MenuItem(0,"Khoa Bat Doi Xung",null,rsa);
        kbdx.getLbName().setFont(new Font(kdx.getLbName().getFont().getName(), Font.BOLD, 16));

        model.MenuItem hash = new model.MenuItem(11,"Hash",null);
        hash.getLbName().setFont(new Font(kdx.getLbName().getFont().getName(), Font.BOLD, 16));
        list.add(hash);
        hash.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                main.remove(main.getForm());
                main.setForm(new UIHash());
                main.add(main.getForm(), BorderLayout.CENTER);
                main.repaint();
                main.revalidate();
                System.out.println("click Hash");
            }
        });

        addMenu(kdx,kbdx,hash);

        this.add(panelTilie);
        this.add(panel);
        this.setOpaque(false);

    }
    private void addMenu(model.MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            panel.add(menu[i]);
            ArrayList<model.MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                list.add(m);
                addMenu(m);
            }
        }
        panel.revalidate();
    }
}
