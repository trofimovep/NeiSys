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
    view = new Ellipse2D.Double(x - 20 ,y - 20 , 20, 20);

    this.x = x;
    this.y = y;
    System.out.println("x внутри конструктора " + x); // прост проверял
}


    public int getX() {
        System.out.println("x вне конструктора " + x);
        return x;
    }

//Рисовать самого себя не лучшая идея
//    + он выкидывает координаты правого верзнего угла фигуры, а не центра. Поэтому нужно учитывать с какой стороны стоят они друг от друга
//    чтобы было точно
//    а так можно проще

    public int getY() {
        return y;
    }
    public Shape getView() {
        return view;
    }
//    public static void drawState(Graphics g, int x, int y){
//        g.setColor(Color.YELLOW);
//        g.fillOval(x, y, 20, 20);
//    }
}
