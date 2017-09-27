import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class Identificater {

    ArrayList<SimpleMatrix>  inputSimpleMatrix = new ArrayList<SimpleMatrix>() ;
    ArrayList<SimpleMatrix>  outSimpleMatrix = new ArrayList<SimpleMatrix>() ;
    ArrayList<State>  st = new ArrayList<State>() ;


    /*
    * по умножению
    * */
public ArrayList<SimpleMatrix> MultCounter(ArrayList<Knot> knots, ArrayList<Relation> relations){

    for(Knot k : knots){

        int amountVectors = k.getInnerRealations().size();

        if(k.getType() == "State"){

            for(int i = 0; i < amountVectors; i++){

                inputSimpleMatrix.add(new SimpleMatrix(((State) k).getInputVector()));
                st.add((State) k);

            }

        }

    }


    for(State s : st){

        SimpleMatrix sMatrix = new SimpleMatrix(s.getM());
        SimpleMatrix inputVectorMatrix = new SimpleMatrix(s.getInputVector());

        for(int i = 0; i < inputSimpleMatrix.size(); i++){

            SimpleMatrix relationMatrix = new SimpleMatrix(s.getInnerRealations().get(i).getM());
            SimpleMatrix out = (relationMatrix.mult(sMatrix)).mult(inputVectorMatrix);
            outSimpleMatrix.add(out);

        }

    }

    return outSimpleMatrix;
}

public int getOutMatrixesSize(){
    return outSimpleMatrix.size();
}




}//class
