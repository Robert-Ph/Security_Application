package View;

import model.MenuItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuBar extends JPanel {
    private JPanel panel;
    private List<model.MenuItem> list;

    public List<model.MenuItem> getList() {
        return list;
    }

    public void setList(List<model.MenuItem> list) {
        this.list = list;
    }

    public MenuBar(){
        list = new ArrayList<>();
        JPanel panelTilie = new JPanel();
        panelTilie.setLayout(new BorderLayout());
        panelTilie.setOpaque(false);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0,0,0,0));


       model.MenuItem caesar = new model.MenuItem(1,"     Caesar", null);

       model.MenuItem affine = new model.MenuItem(2,"     Affine",null);

       model.MenuItem rsa = new model.MenuItem(3,"     RSA",null);



       model.MenuItem kdx = new model.MenuItem(0,"Khoa Doi Xung",null,caesar,affine);
       kdx.getLbName().setFont(new Font(kdx.getLbName().getFont().getName(), Font.BOLD, 16));

        model.MenuItem kbdx = new model.MenuItem(0,"Khoa Bat Doi Xung",null,rsa);
        kbdx.getLbName().setFont(new Font(kdx.getLbName().getFont().getName(), Font.BOLD, 16));

       addMenu(kdx,kbdx);

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
