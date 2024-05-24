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

  public void mutate() {
    for(Layer layer : layers) {
      Matrix weights = layer.getWeights();
      for(int x = 0; x < weights.getX(); x++) {
        for(int y = 0; y < weights.getY(); y++) {
          if((int)(Math.random() * 100) == 0) {
            weights.set(x, y, (Math.random() * 2) - 1);
          }
        }
      }

      Matrix bias = layer.getBias();
      for(int x = 0; x < bias.getX(); x++) {
        for(int y = 0; y < bias.getY(); y++) {
          if((int)(Math.random() * 100) == 0) {
            bias.set(x, y, (Math.random() * 2) - 1);
          }
        }
      }
    }
  }

  public double getError(Matrix input, Matrix expected) {
    Matrix output = apply(input);
    return output.getError(expected);
  }
  public static NeuralNetwork crossover(NeuralNetwork parentA, NeuralNetwork parentB) {
    NeuralNetwork child = new NeuralNetwork(new int[]{6, 8, 10, 7, 5});
    for(int l = 0; l < parentA.layers.length; l++) {
      Matrix weightsA = parentA.layers[l].getWeights();
      Matrix weightsB = parentB.layers[l].getWeights();

      int count = 0;
      int midpoint = weightsA.getX() * weightsA.getY() / 2;

      for(int x = 0; x < weightsA.getX(); x++) {
        for(int y = 0; y < weightsA.getY(); y++) {
          if(count > midpoint) {
            child.layers[l].getWeights().set(x, y, weightsB.get(x, y));
          } else {
            child.layers[l].getWeights().set(x, y, weightsA.get(x, y));
          }
          count++;
        }
      }

      Matrix biasA = parentA.layers[l].getBias();
      Matrix biasB = parentB.layers[l].getBias();

      count = 0;
      midpoint = biasA.getX() * biasA.getY() / 2;

      for(int x = 0; x < biasA.getX(); x++) {
        for(int y = 0; y < biasA.getY(); y++) {
          if(count > midpoint) {
            child.layers[l].getBias().set(x, y, biasB.get(x, y));
          } else {
            child.layers[l].getBias().set(x, y, biasA.get(x, y));
          }
          count++;
        }
      }
    }

    return child;
  }
}