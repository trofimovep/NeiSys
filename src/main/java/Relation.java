import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Relation {
    ArrayList<Knot> knots;
    int x1, y1, x2, y2;
    Graphics g;
    public Relation(int x1, int y1, int x2, int y2,ArrayList<Knot> knots) {

        this.knots = knots;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

    }


static void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {

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
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len}, new int[] {0, -4, 4, 0}, 3);

    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}
