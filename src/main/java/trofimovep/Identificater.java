package trofimovep;

import org.ejml.simple.SimpleMatrix;
import java.util.ArrayList;

public class Identificater {

    ArrayList<SimpleMatrix>  inputSimpleMatrix = new ArrayList<SimpleMatrix>() ;
    ArrayList<SimpleMatrix>  outSimpleMatrix = new ArrayList<SimpleMatrix>() ;
    ArrayList<State> st = new ArrayList<>();
    ArrayList<Relation> relate = new ArrayList<>();
    ArrayList<Control> con = new ArrayList<>();


    public SimpleMatrix smm (ArrayList<Knot> knots) {
        SimpleMatrix m = null;
        for (Knot k : knots) {
            if (k instanceof State) {
                st.add((State) k);
            }
        }

        for(State s : st){



        }




        return m;
    }


    /*
    * по умножению
    * */






public int getOutMatrixesSize(){
    return outSimpleMatrix.size();
}


}//class
