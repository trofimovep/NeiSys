import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Relation{


    ArrayList<Knot> knots;

    Knot knot1;
    Knot knot2;

    int x1, y1, x2, y2;
    int id1, id2;

    int[] sizeParameteres = new int[StartWindow.OPTION_SIZE];
    double[][] M = new double[sizeParameteres[0]][sizeParameteres[0]];

    public Relation(int id1, int id2, ArrayList<Knot> knots) {

        this.knots = knots;

        this.id1 = id1;
        this.id2 = id2;

        this.x1 = findCoord(id1, knots)[0];
        this.y1 = findCoord(id1, knots)[1];

        this.x2 = findCoord(id2, knots)[0];
        this.y2 = findCoord(id2, knots)[1];


        for(Knot k : knots){
            if(k.getId() == id1)
                knot1 = k;
            if(k.getId() == id2)
                knot2 = k;
        }

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


    private int[] findCoord(int id, ArrayList<Knot> knots) {

        int[] XY = new int[2];

        for(Knot k : knots) {

            if (id == k.getId()) {

                XY[0] = k.getX() + Knot.WIDTH / 2;
                XY[1] = k.getY() + Knot.HEIGHT / 2;

            }
        }

            return XY;

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

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public int[] getSizeParameteres() {
        return sizeParameteres;
    }

    public double[][] getM() {
        return M;
    }

    public void setM(double[][] m) {
        M = m;
    }

    public void setSizeParameteres(int[] sizeParameteres) {
        this.sizeParameteres = sizeParameteres;
    }

    public Knot getKnot1() {
        return knot1;
    }

    public Knot getKnot2() {
        return knot2;
    }
}
