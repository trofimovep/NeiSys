package trofimovep;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.Optional;

public class MixIdentifire {

    SimpleMatrix out;

    ArrayList<Relation> relate = new ArrayList<>();
    ArrayList<double[][]> multRelation = new ArrayList<>();
    ArrayList<double[][]> addRelation = new ArrayList<>();

    SimpleMatrix sum1;
    SimpleMatrix sum2;


    protected void MixCounter(ArrayList<Knot> knots, State s) {
        FindOperator.FoundedOperator(knots);

        long startTime = System.nanoTime();
            relate = s.getInnerRealations();

            if (relate.size() == 0) {
                if (s.getInputVector() != null) {
                    out = (new SimpleMatrix(s.getM())).plus(new SimpleMatrix(s.getInputVector()));
                } else {
                    out = new SimpleMatrix(s.getM());
                }

            }

            else {

                for (Relation r : relate) {

                    Thread addThread;
                    Thread multThread;

                    if (r.getType() == "m") {

                        multRelation.add(SimpleToDouble(CSI(r)));

                    } else if (r.getType() == "a") {

                        addRelation.add(SimpleToDouble(CplusRplusR(r)));

                    }

                    multThread = new Thread(() -> {

                        double[][] doubleMultSum = new double[s.getM().length][s.getM()[0].length];
                        for (double[][] d : multRelation) {
//                            for (int i = 0; i < s.getM().length; i++) {
                            /*
                            * для соблюдения размерностей*/

                             for (int i = 0; i < multRelation.get(0).length; i++) {
                                for (int j = 0; j < multRelation.get(0)[0].length; j++) {
                                    doubleMultSum[i][j] += d[i][j];
                                }
                            }
                        }

                        sum1 = new SimpleMatrix(doubleMultSum);
                    });
                    multThread.start();

                    addThread = new Thread(() -> {
                        double[][] doubleAddSum = new double[s.getM().length][s.getM()[0].length];

                        for (double[][] d : addRelation) {
                            for (int i = 0; i < s.getM().length; i++) {
                                for (int j = 0; j < s.getM()[0].length; j++) {
                                    doubleAddSum[i][j] += d[i][j];
                                }
                            }
                        }

                        sum2 = new SimpleMatrix(doubleAddSum);
                    });
                    addThread.start();


                    try {
                        addThread.join();
                        multThread.join();
                        if (!addThread.isAlive()) {
                        }
                        if (!multThread.isAlive()) {
                        }

                        out = sum1.plus(sum2);

                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
                        System.out.println("duration... " + duration);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        s.setOutputVector(out);
    }

     private SimpleMatrix CSI(Relation r){


         SimpleMatrix inter;
         SimpleMatrix product = null;
         SimpleMatrix input;

         if(((State) r.getKnot2()).getInputVector() != null) {

             if (r.getSizeParameteres()[0] == 1 && r.getSizeParameteres()[1] == 1) {

                 inter = (new SimpleMatrix(r.getKnot1().getM())).scale(r.getM()[0][0]).mult(new SimpleMatrix(r.getKnot2().getM()));
                 input = new SimpleMatrix(((State) r.getKnot2()).getInputVector());
                 product = inter.mult(input);
             }
             else if (r.getKnot1().getSizeParameteres()[0] == 1 && r.getKnot1().getSizeParameteres()[1] == 1) {

                 inter = (new SimpleMatrix(r.getM())).scale(r.getKnot1().getM()[0][0]).mult(new SimpleMatrix(r.getKnot2().getM()));
                 input = new SimpleMatrix(((State) r.getKnot2()).getInputVector());
                 product = inter.mult(input);

             }
             else if (r.getKnot2().getSizeParameteres()[0] == 1 && r.getKnot2().getSizeParameteres()[1] == 1) {

                 inter = (new SimpleMatrix(r.getKnot1().getM())).mult(new SimpleMatrix(r.getM())).scale(r.getKnot2().getM()[0][0]);
                 input = new SimpleMatrix(((State) r.getKnot2()).getInputVector());
                 product = inter.mult(input);
             }
             else if (((State) r.getKnot2()).getInputVector().length == 1 && ((State) r.getKnot2()).getInputVector()[0].length == 1) {
                 inter = (new SimpleMatrix(r.getKnot1().getM())).mult(new SimpleMatrix(r.getM())).mult(new SimpleMatrix(r.getKnot2().getM()));
                 product = inter.scale(((State) r.getKnot2()).getInputVector()[0][0]);
             }
             else{

                 inter = (new SimpleMatrix(r.getKnot1().getM())).mult(new SimpleMatrix(r.getM())).mult(new SimpleMatrix(r.getKnot2().getM()));
                 product = inter.mult(new SimpleMatrix(((State) r.getKnot2()).getInputVector()));

             }

         }
         else{

             if (r.getSizeParameteres()[0] == 1 && r.getSizeParameteres()[1] == 1) {

                 product = (new SimpleMatrix(r.getKnot1().getM())).scale(r.getM()[0][0]).mult(new SimpleMatrix(r.getKnot2().getM()));

             }
             else if (r.getKnot1().getSizeParameteres()[0] == 1 && r.getKnot1().getSizeParameteres()[1] == 1) {

                 product = (new SimpleMatrix(r.getM())).scale(r.getKnot1().getM()[0][0]).mult(new SimpleMatrix(r.getKnot2().getM()));

             }
             else if (r.getKnot2().getSizeParameteres()[0] == 1 && r.getKnot2().getSizeParameteres()[1] == 1) {

                 product = (new SimpleMatrix(r.getKnot1().getM())).mult(new SimpleMatrix(r.getM())).scale(r.getKnot2().getM()[0][0]);

             }
             else if (r.getKnot2().getSizeParameteres()[0] == 1 && r.getKnot2().getSizeParameteres()[1] == 1 &&
                     r.getKnot1().getM().length == 1 && r.getKnot1().getM()[0].length == 1 &&
                     r.getM().length == 1 && r.getM()[0].length == 1) {
                 double in = r.getKnot1().getM()[0][0] * r.getM()[0][0] * r.getKnot2().getM()[0][0];
                 product.set(in);
             }
             else{

                 product = (new SimpleMatrix(r.getKnot1().getM())).mult(new SimpleMatrix(r.getM())).mult(new SimpleMatrix(r.getKnot2().getM()));

             }

         }

         System.out.println("product" + product);

         return product;
     }


    private SimpleMatrix CplusRplusR(Relation r){

        SimpleMatrix inter;
        SimpleMatrix sum;
        SimpleMatrix input;

        if(((State) r.getKnot2()).getInputVector() != null) {

            if (r.getSizeParameteres()[0] == 1 && r.getSizeParameteres()[1] == 1) {

                inter = (new SimpleMatrix(r.getKnot1().getM())).scale(r.getM()[0][0]).plus(new SimpleMatrix(r.getKnot2().getM()));
                input = new SimpleMatrix(((State) r.getKnot2()).getInputVector());
                sum = inter.plus(input);
            }
            else if (r.getKnot1().getSizeParameteres()[0] == 1 && r.getKnot1().getSizeParameteres()[1] == 1) {

                inter = (new SimpleMatrix(r.getM())).scale(r.getKnot1().getM()[0][0]).plus(new SimpleMatrix(r.getKnot2().getM()));
                input = new SimpleMatrix(((State) r.getKnot2()).getInputVector());
                sum = inter.plus(input);

            }
            else if (r.getKnot2().getSizeParameteres()[0] == 1 && r.getKnot2().getSizeParameteres()[1] == 1) {

                inter = (new SimpleMatrix(r.getKnot1().getM())).plus(new SimpleMatrix(r.getM()).scale(r.getKnot2().getM()[0][0]));
                input = new SimpleMatrix(((State) r.getKnot2()).getInputVector());
                sum = inter.plus(input);
            }
            else if (((State) r.getKnot2()).getInputVector().length == 1 && ((State) r.getKnot2()).getInputVector()[0].length == 1) {
                inter = (new SimpleMatrix(r.getKnot1().getM())).plus(new SimpleMatrix(r.getM())).plus(new SimpleMatrix(r.getKnot2().getM()));
                sum = inter.scale(((State) r.getKnot2()).getInputVector()[0][0]);
            }

            else{

                inter = (new SimpleMatrix(r.getKnot1().getM())).plus(new SimpleMatrix(r.getM())).plus(new SimpleMatrix(r.getKnot2().getM()));
                sum = inter.plus(new SimpleMatrix(((State) r.getKnot2()).getInputVector()));

            }

        }
        else{

            if (r.getSizeParameteres()[0] == 1 && r.getSizeParameteres()[1] == 1) {

                sum = (new SimpleMatrix(r.getKnot1().getM())).scale(r.getM()[0][0]).plus(new SimpleMatrix(r.getKnot2().getM()));

            }
            else if (r.getKnot1().getSizeParameteres()[0] == 1 && r.getKnot1().getSizeParameteres()[1] == 1) {

                sum = (new SimpleMatrix(r.getM())).scale(r.getKnot1().getM()[0][0]).plus(new SimpleMatrix(r.getKnot2().getM()));

            }
            else if (r.getKnot2().getSizeParameteres()[0] == 1 && r.getKnot2().getSizeParameteres()[1] == 1) {

                sum = (new SimpleMatrix(r.getKnot1().getM())).plus(new SimpleMatrix(r.getM()).scale(r.getKnot2().getM()[0][0]));



                /* remember for case when some matrix is scalar:
                * C + (R * Scalar) != (C+R) * scalar
                * if smth is scalar it influences on nearest matrix!
                * */

            }
            else{

                sum = (new SimpleMatrix(r.getKnot1().getM())).plus(new SimpleMatrix(r.getM())).mult(new SimpleMatrix(r.getKnot2().getM()));
            }
        }
        return sum;
    }

     static double[][] SimpleToDouble(SimpleMatrix M){

        double[][] A = new double[M.getMatrix().getNumCols()][M.getMatrix().getNumRows()];


        for(int i = 0; i < M.getMatrix().getNumRows(); i++){
            for(int j = 0; j < M.getMatrix().getNumCols(); j++){

                A[i][j] = M.getMatrix().get(i, j);

            }

        }

        return A;
    }

}//class
