public class NeuralNetwork {
  private Layer[] layers;
  private double error;
  private double fitness;
  
  public NeuralNetwork(int[] sizes) {
    layers = new Layer[sizes.length - 1];
    for(int i = 0; i < sizes.length - 1; i++) {
      layers[i] = new Layer(sizes[i], sizes[i + 1]);
    }
  }

  public Layer[] getLayers() {
    return layers;
  }

  public double getError() {
    return error;
  }

  public void setError(double val) {
    error = val;
  }
  public double getFitness() {
    return fitness;
  }

  public void setFitness(double val) {
    fitness = val;
  }

  public Matrix apply(Matrix input) {
    Matrix result = input;
    for(Layer layer : layers) {
      result = layer.apply(result);
    }

    return result;
  }

  public double getError(Matrix input, Matrix expected) {
    Matrix output = apply(input);
    return output.getError(expected);
  }
}