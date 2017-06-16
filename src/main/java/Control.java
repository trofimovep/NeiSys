import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by nash on 6/16/17.
 */
public class Control extends Knot {
    boolean type;
    int id;
    int x;
    int y;
    public Ellipse2D view;
    public Control(boolean type, int id, int x, int y){
        System.out.println("x внутри конструктора до this" + x);
        view = new Ellipse2D.Double(x - 20 ,y - 20 , 40, 40);
        System.out.println("x внутри конструктора " + x); // прост проверял
    }
    public int getX() {
        System.out.println("x вне конструктора " + x);
        return x;
    }
    public int getY() {
        return y;
    }
    public Shape getView() {
        return view;
    }

}
