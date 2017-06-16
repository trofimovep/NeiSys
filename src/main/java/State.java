import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by nash on 5/26/17.
 */

public class State extends Knot {
    boolean type;
    int id;
    int x;
    int y;
    public Ellipse2D view;

public State(boolean type, int id, int x, int y){
    System.out.println("x внутри конструктора до this" + x);
    view = new Ellipse2D.Double(x - 20 ,y - 20 , 40, 40);

    this.x = x;
    this.y = y;
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
