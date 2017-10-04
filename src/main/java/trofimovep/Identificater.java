package trofimovep;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class Identificater {

    ArrayList<SimpleMatrix>  inputSimpleMatrix = new ArrayList<SimpleMatrix>() ;
    ArrayList<SimpleMatrix>  outSimpleMatrix = new ArrayList<SimpleMatrix>() ;
    ArrayList<State>  st = new ArrayList<State>() ;


    public SimpleMatrix smm (ArrayList<Knot> knots) {
        SimpleMatrix m = null;
        for (Knot k : knots) {

            if (k.getType() == "trofimovep.State") {

                m = new SimpleMatrix((k).getInputVector());
            }
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
