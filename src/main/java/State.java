import java.awt.*;

/**
 * Created by nash on 5/26/17.
 */
public class State extends Knot{

    int id;
    int x;
    int y;


    public State(int id, int x, int y){



    }

    public static void drawState(Graphics g, int x, int y){
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 20, 20);
    }




}
