import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by nash on 5/26/17.
 */
public class Knot{


    int id;
    String type; // тип узла: состояние(true) или управление (false)
    int x;      // координата x
    int y;     // координата y

    public static int WIDTH = 40;
    public static int HEIGHT = 40;


    public Knot(String type, int id, int x, int y){

        this.x = x - WIDTH / 2;
        this.y = y - HEIGHT / 2;
        this.type = type;
        this.id = id;
    }


     protected Ellipse2D getView(int x, int y) {
        Ellipse2D view = new Ellipse2D.Double(x ,y, WIDTH, HEIGHT);
        return view;
    }


    public int getId() { return id;  }

    public int getX() { return x; }

    public int getY() { return y; }

    public String getType() { return type; }

}
