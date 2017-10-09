package trofimovep;

import org.ejml.simple.SimpleMatrix;
import java.util.ArrayList;

public class Identificater {

    ArrayList<SimpleMatrix>  inputSimpleMatrix = new ArrayList<SimpleMatrix>() ;
    ArrayList<SimpleMatrix>  outSimpleMatrix = new ArrayList<SimpleMatrix>() ;
    ArrayList<State> st = new ArrayList<>();
    ArrayList<Relation> relate = new ArrayList<>();
    ArrayList<Control> con = new ArrayList<>();

    double[][] out;



    public SimpleMatrix smm (ArrayList<Knot> knots) {

        boolean isCorrect;

        SimpleMatrix m = null;
        for (Knot k : knots) {
            if (k instanceof State) {
                st.add((State) k);
            }
        }


        for(State s : st){

            out = new double[s.getInputVector().length][s.getInputVector()[0].length];
            double[][] input = s.getInputVector();

            System.out.println("0: "+s.getSizeParameteres()[0] +"\n"+"1: "+s.getSizeParameteres()[1]);


            if(s.getSizeParameteres()[0] == 1 & s.getSizeParameteres()[1] == 1){

                for(int i = 0; i < s.getInputVector().length; i++){
                    for(int j = 0; j < s.getInputVector()[0].length; j++){
                        out[i][j] = input[i][j] * s.getM()[0][0];
                        System.out.println("s.getM()[0][0] = " + s.getM()[0][0]);
                        System.out.println("out = " + out[i][j]);
                    }

                    System.out.println("\n");

                }
            }


        }
        SimpleMatrix out2 = new SimpleMatrix(out);

        return out2;
    }


    /*
    * по умножению
    * */


public int getOutMatrixesSize(){
    return outSimpleMatrix.size();
}


}//class
