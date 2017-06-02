import java.awt.*;
import java.awt.geom.Ellipse2D;


public class State extends Knot{
    public void f() {
        System.out.println(x);
    }
    public State(){

    }


}



//public class State extends Knot {
//    boolean type;
//    int id;
//    int x;
//    int y;
//    public Ellipse2D view;
//public State(boolean type, int id, int x, int y){
//    view = new Ellipse2D.Double(x - 20 / 2 ,y - 20 / 2, 20, 20);
//    this.x = x;
//    this.y = y;
//}
//    public int getX() { return x; }
//    public int getY() {
//        return y;
//    }
//    public int getCenterX() { return x - 20 /2; }
//    public int getCenterY() { return y - 20 /2 ; }
//    public Shape getView() {
//        return view;
//    }
//
//}//class
