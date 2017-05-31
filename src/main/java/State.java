import java.awt.*;
/**
 * Created by nash on 5/26/17.
 */

public class State extends Knot {
boolean type;
int id;
int x;
int y;

public State(boolean type, int id, int x, int y){
    this.x = x;
    this.y = y;
}


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void drawState(Graphics g, int x, int y){
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 20, 20);
    }
}
