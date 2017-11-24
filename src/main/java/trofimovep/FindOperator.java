package trofimovep;

import org.ejml.simple.SimpleMatrix;
import java.util.ArrayList;


public abstract class FindOperator {

    static ArrayList<State> states = new ArrayList<>();

    static void FoundedOperator(ArrayList<Knot> knots){

       states = Identificater.getStates(knots);

        for (State st: states) {
            if(st.getInputVector() != null && st.getOutY() != null){
                double[][] M = new double[st.getInputVector().length][st.getOutY().length];

                if(st.getInputVector()[0].length == 1 && st.getInputVector().length == 1)
                for(int i = 0; i < st.getInputVector().length; i++){
                    for(int j = 0; j < st.getOutY().length; j++){

                        if(i != j) {
                            M[i][j] = 0;

                        }
                        else {
                            M[i][j] = st.getOutY()[0][j] / st.getInputVector()[0][j];
                        }
                    }
                }
                st.setM(M);
            }
            else{
                double[][] M;
                SimpleMatrix out = (new SimpleMatrix(st.getOutputVector())).mult((new SimpleMatrix(st.getInputVector())).pseudoInverse());
                M = MixIdentifire.SimpleToDouble(out);
                st.setOutY(M);
            }

        }
    }
}
