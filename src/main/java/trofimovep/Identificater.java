package trofimovep;

import org.ejml.simple.SimpleMatrix;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*КЛАСС ПРЕДНАЗНАЧЕННЫЙ ДЛЯ РАСЧЕТА КОГДВА ВСЕ СВЯЗИ АДДИТИВНЫЕ, СЕЙЧАС, ВОЗМОЖНО, УЖЕ НЕ НУЖЕН*/

public abstract class Identificater {

    ArrayList<SimpleMatrix>  outSimpleMatrix = new ArrayList<SimpleMatrix>() ;
    static ArrayList<State> st = new ArrayList<>();
    static ArrayList<Relation> relate = new ArrayList<>();

    /*
    * по умножению
    * */

  static  SimpleMatrix ProductMotion(ArrayList<Knot> knots) {

     SimpleMatrix out;
     SimpleMatrix simpleOut = null;
     SimpleMatrix inter;

     st = getStates(knots);


         for (State s : st) {

             relate = s.getInnerRealations();

             if (relate.size() != 0) {

         /*first */
                 SimpleMatrix first;
                 if (relate.get(0).getSizeParameteres()[0] == 1 & relate.get(0).getSizeParameteres()[1] == 1) {
                     first = (new SimpleMatrix(relate.get(0).getKnot1().getM())).scale(relate.get(0).getM()[0][0]);
                 } else if (relate.get(0).getKnot1().getSizeParameteres()[0] == 1 & relate.get(0).getKnot1().getSizeParameteres()[1] == 1) {
                     first = (new SimpleMatrix(relate.get(0).getM())).scale(relate.get(0).getKnot1().getM()[0][0]);
                 } else {
                     first = (new SimpleMatrix(relate.get(0).getKnot1().getM())).mult(new SimpleMatrix(relate.get(0).getM()));
                 }


                 if(relate.size() == 1){
                     inter = first; //&&&&&&&&&&&&&??????????????????????
                 }
                 else {

                     inter = first;
                     for (int i = 1; i < relate.size(); i++) {

                         SimpleMatrix help;

                         if (relate.get(i).getSizeParameteres()[0] == 1 & relate.get(i).getSizeParameteres()[1] == 1) {
                             help = (new SimpleMatrix(relate.get(i).getKnot1().getM())).scale(relate.get(i).getM()[0][0]);

                         } else if (relate.get(i).getKnot1().getSizeParameteres()[0] == 1 & relate.get(i).getKnot1().getSizeParameteres()[1] == 1) {
                             help = (new SimpleMatrix(relate.get(i).getM())).scale(relate.get(i).getKnot1().getM()[0][0]);
                         } else {
                             help = (new SimpleMatrix(relate.get(i).getKnot1().getM())).mult(new SimpleMatrix(relate.get(i).getM()));
                         }

                         inter = help.mult(inter);

                     }
                 }

                 if (s.getInputVector() != null) {

                     if (s.getInputVector().length == 1 & s.getInputVector()[0].length == 1) {
                         out = (new SimpleMatrix(s.getM())).scale(s.getInputVector()[0][0]);
                     } else if (s.getM().length == 1 & s.getM()[0].length == 1) {
                         out = (new SimpleMatrix(s.getInputVector())).scale(s.getM()[0][0]);
                     } else {
                         out = (new SimpleMatrix(s.getM())).mult(new SimpleMatrix(s.getInputVector()));
                     }

                 /*Здесь предполагаем, что ни inter ни out не скаляры*/

                     simpleOut = inter.mult(out);

                 /* Задать вектор для State.setOutputVector */
                 } else {

                     if (s.getM().length == 1 & s.getM()[0].length == 1) {
                         simpleOut = inter.scale(s.getM()[0][0]);
                     } else if (inter.isInBounds(1, 1)) {
                         simpleOut = (new SimpleMatrix(s.getM())).scale(inter.get(0, 0));
                     } else {
                         simpleOut = inter.mult(new SimpleMatrix(s.getM()));
                     }

                 }
             }
             /* if no relation */
             else{

                 if (s.getInputVector() != null) {

                     if (s.getInputVector().length == 1 & s.getInputVector()[0].length == 1) {
                         out = (new SimpleMatrix(s.getM())).scale(s.getInputVector()[0][0]);
                     } else if (s.getM().length == 1 & s.getM()[0].length == 1) {
                         out = (new SimpleMatrix(s.getInputVector())).scale(s.getM()[0][0]);
                     } else {
                         out = (new SimpleMatrix(s.getM())).mult(new SimpleMatrix(s.getInputVector()));
                     }
                     simpleOut = out;

                 } else {
                     simpleOut = new SimpleMatrix(s.getM());

                 }
             }
         }

         return simpleOut;
     }


 static ArrayList<State> getStates(ArrayList<Knot> knots){
     System.out.println("knot.size: " + knots.size());

     for(Knot k : knots){
         if(k instanceof State){
             st.add((State) k);
         }
     }

     System.out.println("st.size: " + st.size());
     return st;
 }

    /*
    * Getters and Setters
    * */

public int getOutMatrixesSize(){
    return outSimpleMatrix.size();
}


}//class
