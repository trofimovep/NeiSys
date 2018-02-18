package trofimovep;

import org.ejml.simple.SimpleMatrix;
import java.util.ArrayList;


public abstract class FindOperator {

    static ArrayList<State> states = new ArrayList<>();

    static void FoundedOperator(ArrayList<Knot> knots) {

        states = Identificater.getStates(knots);

        for (State st : states) {
            if (st.getInputVector() != null && st.getOutY() != null) {
                double[][] M = new double[st.getOutY().length][st.getInputVector().length];

                if (st.getInputVector()[0].length == 1 && st.getInputVector()[0].length == 1) {

                    for (int i = 0; i < st.getOutY().length; i++) {
                        for (int j = 0; j < st.getInputVector().length; j++) {

                            if (i != j) {
                                M[i][j] = 0;

                            } else if(i == j) {
                                M[i][j] = st.getOutY()[i][0] / st.getInputVector()[j][0];
                            }
                        }
                    }
                    st.setM(M);
                } else {

                    SimpleMatrix pinvInput = (new SimpleMatrix(st.getInputVector()).pseudoInverse());
                    SimpleMatrix out = new SimpleMatrix(st.getOutY()).mult(pinvInput);
                    M = new MixIdentifire().SimpleToDouble(out);
                    st.setM(M);

                }

            }
        }
    }
}
