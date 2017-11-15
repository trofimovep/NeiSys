package trofimovep;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Created by nash on 5/26/17.
 */
public class Knot{


    int id;
    String type;
    int x;
    int y;

    static int WIDTH = 40;
    static int HEIGHT = 40;

    protected int[] sizeParameteres = new int[StartWindow.OPTION_SIZE];
    protected double[][] M = new double[sizeParameteres[0]][sizeParameteres[0]];

    protected ArrayList<Relation> innerRealations = new ArrayList<Relation>();
    protected ArrayList<Relation> outputRealations = new ArrayList<Relation>();



    public Knot(String type, int id, int x, int y){
        this.x = x - WIDTH / 2;
        this.y = y - HEIGHT / 2;
        this.type = type;
        this.id = id;
        for(int i = 0; i < StartWindow.OPTION_SIZE; i++){
        sizeParameteres[i] = 0;
        }
    }


     protected Ellipse2D getView(int x, int y) {
        Ellipse2D view = new Ellipse2D.Double(x ,y, WIDTH, HEIGHT);
        return view;
    }


    protected int getId() { return id;  }

    protected int getX() { return x; }

    protected int getY() { return y; }

    protected String getType() { return type; }

    protected int[] getSizeParameteres() {
        return sizeParameteres;
    }

    protected void setSizeParameteres(int[] sizeParameteres) {
        this.sizeParameteres = sizeParameteres;
    }

    protected void setM(double[][] m) {
        M = m;
    }

    protected double[][] getM() {
        return M;
    }

    protected void addInnerRelations(Relation r){
        innerRealations.add(r);
    }

    protected void addOutputRelations(Relation r){
        outputRealations.add(r);
    }

    protected ArrayList<Relation> getInnerRealations() { return innerRealations;
    }

    protected ArrayList<Relation> getOutputRealations() {
        return outputRealations;
    }

    protected int getOutputSIzeRel(){
        return outputRealations.size();
    }
}
