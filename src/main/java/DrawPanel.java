import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class DrawPanel extends JComponent {
    ArrayList<Knot> knots;

    public DrawPanel(ArrayList<Knot> knots) {
        this.knots = knots;
    }
//    public int stX = StartWindow.getStartX();

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
            drawArrow(g, 45, 10, k.getX(), k.getY());
        }
    }
    void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        final int ARR_SIZE = 10;
        Graphics2D g = (Graphics2D) g1.create();
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
        // Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

}
