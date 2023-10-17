package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuBar extends JPanel {
    private JPanel panel,p;


    public MenuBar(){
        p = new Body();
        JPanel panelTilie = new JPanel();
        panelTilie.setLayout(new BorderLayout());
        panelTilie.setOpaque(false);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0,0,0,0));


       MenuItem caesar = new MenuItem("     Caesar", new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               p.add(new UICaesar());
               
               System.out.println("click");
           }
       });

       MenuItem affine = new MenuItem("     Affine",null);

        MenuItem rsa = new MenuItem("     RSA",null);



       MenuItem kdx = new MenuItem("Khoa Doi Xung",null,caesar,affine);
       kdx.getLbName().setFont(new Font(kdx.getLbName().getFont().getName(), Font.BOLD, 16));

        MenuItem kbdx = new MenuItem("Khoa Bat Doi Xung",null,rsa);
        kbdx.getLbName().setFont(new Font(kdx.getLbName().getFont().getName(), Font.BOLD, 16));

       addMenu(kdx,kbdx);

        this.add(panelTilie);
        this.add(panel);
        this.setOpaque(false);

    }

    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            panel.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        panel.revalidate();
    }

}
