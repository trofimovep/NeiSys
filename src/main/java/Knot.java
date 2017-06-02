/**
 * Created by nash on 5/26/17.
 */
public abstract class Knot{

    boolean type; // тип узла: состояние(true) или управление (false)
    int x;      // координата x
    int y;     // координата y


    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isType() { return type; }
}
