import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Relation {
    ArrayList<Knot> knots;
    int x1, y1, x2, y2;
    public Relation(ArrayList<Knot> knots, int x1, int y1, int x2, int y2) {
        this.knots = knots;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    private void drawRelation(Graphics g, int x1, int y1, int x2, int y2) {
        for (Knot k: knots
             ) {
            if(Math.abs(x1 - k.getX()) < Knot.WIDTH + 5 && Math.abs(y1 - k.getY()) < Knot.HEIGHT + 5) {
            drawArrow(g, x1, x2, y1,y2);
            }
        }
    }

    void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        final int ARR_SIZE = 4;
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
