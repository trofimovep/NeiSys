package trofimovep;

import org.ejml.simple.SimpleMatrix;

import javax.swing.*;
import java.util.ArrayList;

public class Identificater {

    ArrayList<SimpleMatrix>  inputSimpleMatrix = new ArrayList<SimpleMatrix>() ;
    ArrayList<SimpleMatrix>  outSimpleMatrix = new ArrayList<SimpleMatrix>() ;
    ArrayList<State> st = new ArrayList<>();
    ArrayList<Relation> relate = new ArrayList<>();
    ArrayList<Control> con = new ArrayList<>();

    SimpleMatrix simpleM;
    SimpleMatrix simpleInputVector;
    SimpleMatrix simpleOut;



    double[][] out;



    public SimpleMatrix smm (ArrayList<Knot> knots) {


        SimpleMatrix m = null;
        for (Knot k : knots) {
            if (k instanceof State) {
                st.add((State) k);
            }
        }


        // StateParameteres * Vectors
        for(State s : st) {

            if (s.getInputVector() != null) {
                if (s.getSizeParameteres()[0] == 1 & s.getSizeParameteres()[1] == 1) {

                    out = new double[s.getInputVector().length][s.getInputVector()[0].length];

                    for (int i = 0; i < s.getInputVector().length; i++) {
                        for (int j = 0; j < s.getInputVector()[0].length; j++) {
                            out[i][j] = s.getInputVector()[i][j] * s.getM()[0][0];
                        }
                    }
                    simpleOut = new SimpleMatrix(out);
                } else if (s.getInputVector().length == 1 & s.getInputVector()[0].length == 1) {

                    out = new double[s.getM().length][s.getM()[0].length];

                    for (int i = 0; i < s.getM().length; i++) {
                        for (int j = 0; j < s.getM()[0].length; j++) {
                            out[i][j] = s.getM()[i][j] * s.getInputVector()[0][0];
                        }
                    }
                    simpleOut = new SimpleMatrix(out);
                } else {
                    try {
                        simpleM = new SimpleMatrix(s.getM());
                        simpleInputVector = new SimpleMatrix(s.getInputVector());
                        simpleOut = simpleM.mult(simpleInputVector);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }

            }//inputVector != 0
        }
        return simpleOut;
    }


    /*
    * по умножению
    * */


public int getOutMatrixesSize(){
    return outSimpleMatrix.size();
}


}//class
