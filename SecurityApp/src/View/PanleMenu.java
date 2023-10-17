package View;

import javax.swing.JPanel;
import java.awt.*;

public class PanleMenu extends JPanel {
    private LogoPanel logoPanel = new LogoPanel();
    private MenuBar menu = new MenuBar();
    public PanleMenu(){
        this.setLayout(new BorderLayout());


        this.add(logoPanel, BorderLayout.NORTH);
        this.add(menu, BorderLayout.CENTER);

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
}
