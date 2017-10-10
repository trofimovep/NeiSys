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

    double[][] inputVector;
    double[][] outputVector;

    public static int WIDTH = 40;
    public static int HEIGHT = 40;

    int[] sizeParameteres = new int[StartWindow.OPTION_SIZE];
    double[][] M = new double[sizeParameteres[0]][sizeParameteres[0]];

    ArrayList<Relation> innerRealations = new ArrayList<Relation>();
    ArrayList<Relation> outputRealations = new ArrayList<Relation>();


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



    public int getId() { return id;  }

    public int getX() { return x; }

    public int getY() { return y; }

    public String getType() { return type; }

    public int[] getSizeParameteres() {
        return sizeParameteres;
    }

    public void setSizeParameteres(int[] sizeParameteres) {
        this.sizeParameteres = sizeParameteres;
    }

    public void setM(double[][] m) {
        M = m;
    }

    public double[][] getM() {
        return M;
    }

    public void addInnerRelations(Relation r){
        innerRealations.add(r);
    }

    public void addOutputRelations(Relation r){
        outputRealations.add(r);
    }

    public ArrayList<Relation> getInnerRealations() {
        return innerRealations;
    }

    public ArrayList<Relation> getOutputRealations() {
        return outputRealations;
    }

    public int getOutputSIzeRel(){
        return outputRealations.size();
    }






}
