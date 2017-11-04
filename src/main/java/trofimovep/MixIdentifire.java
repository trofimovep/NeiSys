package trofimovep;

import org.ejml.simple.SimpleMatrix;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MixIdentifire {

    SimpleMatrix out;


    ArrayList<SimpleMatrix> outSimpleMatrix = new ArrayList<SimpleMatrix>() ;
    ArrayList<State> st = new ArrayList<>();
    ArrayList<Relation> relate = new ArrayList<>();
    ArrayList<SimpleMatrix> multRelation = new ArrayList<>();
    ArrayList<SimpleMatrix> addRelation = new ArrayList<>();

    Identificater identificater = new Identificater();


    protected SimpleMatrix MixCounter(ArrayList<Knot> knots){

        st = identificater.getStates(knots);

        for(State s : st){

            relate = s.getInnerRealations();

            if(relate.size() == 0){

                // IF INPUT VECTOR == NULL ????

                out = (new SimpleMatrix(s.getM())).plus(new SimpleMatrix(s.getInputVector()));

            }

            /*
            * *
            * */

            else{

                for(Relation r : relate){

                    if(r.getType() == "m"){
                        multRelation.add(CSI(r));
                    }

                    if(r.getType() == "a"){
                        //
                    }


                }

            }

        }

        return out;
    }


     private SimpleMatrix CSI(Relation r) {

         SimpleMatrix inter;
         SimpleMatrix product = null;
         SimpleMatrix input;

         if(((State) r.getKnot2()).getInputVector() != null) {

             if (r.getSizeParameteres()[0] == 1 && r.getSizeParameteres()[1] == 1) {

                 inter = (new SimpleMatrix(r.getKnot1().getM())).scale(r.getM()[0][0]).mult(new SimpleMatrix(r.getKnot2().getM()));
                 input = new SimpleMatrix(((State) r.getKnot2()).getInputVector());
                 product = inter.mult(input);
             }
             if (r.getKnot1().getSizeParameteres()[0] == 1 && r.getKnot1().getSizeParameteres()[1] == 1) {

                 inter = (new SimpleMatrix(r.getM())).scale(r.getKnot1().getM()[0][0]).mult(new SimpleMatrix(r.getKnot2().getM()));
                 input = new SimpleMatrix(((State) r.getKnot2()).getInputVector());
                 product = inter.mult(input);

             }
             if (r.getKnot2().getSizeParameteres()[0] == 1 && r.getKnot2().getSizeParameteres()[1] == 1) {

                 inter = (new SimpleMatrix(r.getKnot1().getM())).mult(new SimpleMatrix(r.getM())).scale(r.getKnot2().getM()[0][0]);
                 input = new SimpleMatrix(((State) r.getKnot2()).getInputVector());
                 product = inter.mult(input);
             }
             if (((State) r.getKnot2()).getInputVector().length == 1 && ((State) r.getKnot2()).getInputVector()[0].length == 1) {
                 inter = (new SimpleMatrix(r.getKnot1().getM())).mult(new SimpleMatrix(r.getM())).mult(new SimpleMatrix(r.getKnot2().getM()));
                 product = inter.scale(((State) r.getKnot2()).getInputVector()[0][0]);
             }
         }
         else{

             if (r.getSizeParameteres()[0] == 1 && r.getSizeParameteres()[1] == 1) {

                 product = (new SimpleMatrix(r.getKnot1().getM())).scale(r.getM()[0][0]).mult(new SimpleMatrix(r.getKnot2().getM()));

             }
             if (r.getKnot1().getSizeParameteres()[0] == 1 && r.getKnot1().getSizeParameteres()[1] == 1) {

                 product = (new SimpleMatrix(r.getM())).scale(r.getKnot1().getM()[0][0]).mult(new SimpleMatrix(r.getKnot2().getM()));

             }
             if (r.getKnot2().getSizeParameteres()[0] == 1 && r.getKnot2().getSizeParameteres()[1] == 1) {

                 product = (new SimpleMatrix(r.getKnot1().getM())).mult(new SimpleMatrix(r.getM())).scale(r.getKnot2().getM()[0][0]);

             }
             if (((State) r.getKnot2()).getInputVector().length == 1 && ((State) r.getKnot2()).getInputVector()[0].length == 1) {
                 product = (new SimpleMatrix(r.getKnot1().getM())).mult(new SimpleMatrix(r.getM())).mult(new SimpleMatrix(r.getKnot2().getM()));
             }

         }

         return product;
     }


    private SimpleMatrix CplusRplusR(Relation r){
        SimpleMatrix sum = null;

        return sum;
    }




}
