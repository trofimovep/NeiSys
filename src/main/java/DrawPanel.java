import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPanel extends JComponent {
    ArrayList<Knot> knots;

    public DrawPanel(ArrayList<Knot> knots) {
        this.knots = knots;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//        g2d.setBackground(Color.DARK_GRAY);
        for (Knot k: knots) {
            if(k.isType() == true)
                g2d.setColor(Color.ORANGE);
            else
                g2d.setColor(Color.BLUE);
            g2d.fill(k.getView(k.getX(), k.getY()));
        }
    }
}
