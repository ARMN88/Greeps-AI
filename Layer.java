public class Layer {
  private Matrix weights;
  private Matrix bias;
  
  public Layer(int input, int output) {
    weights = new Matrix(input, output);
    bias = new Matrix(1, output);
  }

  public Matrix apply(Matrix input) {
    Matrix result = Matrix.multiply(input, weights);
    result.add(bias);
    result.applyLambda();
    return result;
  }

  public String toString() {
    return weights.getX() + " x " + weights.getY();
  }
}