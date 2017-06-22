import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by nash on 5/26/17.
 */
public abstract class Knot{

    boolean type; // тип узла: состояние(true) или управление (false)
    int x;      // координата x
    int y;     // координата y

     protected static Ellipse2D getView(Graphics2D g, int x, int y) {
        Ellipse2D view = new Ellipse2D.Double(x - 40 / 2 ,y - 40 / 2, 40, 40);
        return view;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isType() { return type; }



}
