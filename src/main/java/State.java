public class State extends Knot {

    double[][] inputVector;
    double[][] outputVector;

    public State(String type, int id, int x, int y){

        super(type, id, x, y);

        }

    public double[][] getInputVector() {
        return inputVector;
    }

    public void setInputVector(double[][] inputVector) {
        this.inputVector = inputVector;
    }

    public void setOutputVector() {
        this.outputVector = outputVector;
    }

}

