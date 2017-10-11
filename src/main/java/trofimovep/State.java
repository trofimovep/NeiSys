package trofimovep;

import org.ejml.simple.SimpleMatrix;

public class State extends trofimovep.Knot {

    double[][] inputVector;
    SimpleMatrix outputVector;

    public State(String type, int id, int x, int y){

        super(type, id, x, y);

        }

    public double[][] getInputVector() {
        return inputVector;
    }

    public void setInputVector(double[][] inputVector) {
        this.inputVector = inputVector;
    }

    public void setOutputVector(SimpleMatrix outputVector) {
        this.outputVector = outputVector;
    }

    public SimpleMatrix getOutputVector() {
        return outputVector;
    }
}

