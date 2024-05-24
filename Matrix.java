public class Matrix {
  private double[][] data;

  public Matrix(int row, int col) {
    data = new double[row][col];
    for (int x = 0; x < data.length; x++) {
      for (int y = 0; y < data[0].length; y++) {
        data[x][y] = (Math.random() * 2) - 1;
      }
    }
  }

  public Matrix(double[][] values) {
    data = values;
  }

  public void set(double[][] newData) {
    data = newData;
  }

  public void set(int x, int y, double num) {
    data[x][y] = num;
  }

  public double getNum(int x, int y) {
    return data[x][y];
  }

  public double[][] getData() {
    return data;
  }

  public int getX() {
    return data.length;
  }

  public int getY() {
    return data[0].length;
  }

  public void add(float num) {
    for (int x = 0; x < data.length; x++) {
      for (int y = 0; y < data[0].length; y++) {
        data[x][y] += num;
      }
    }
  }

  public void add(Matrix other) {
    if (getX() != other.getX() || getY() != other.getY()) {
      System.out.println("Incorrect Sizes When Adding");
    }
    for (int x = 0; x < data.length; x++) {
      for (int y = 0; y < data[0].length; y++) {
        data[x][y] += other.data[x][y];
      }
    }
  }

  public double getError(Matrix other) {
    double sum = 0;
    int len = 0;
    
    for (int x = 0; x < data.length; x++) {
      for (int y = 0; y < data[0].length; y++) {
        len++;
        sum += Math.abs(data[x][y] - other.getNum(x, y));
      }
    }

    return Math.pow((double) sum / len, 2);
  }

  public void muliply(int num) {
    for (int x = 0; x < data.length; x++) {
      for (int y = 0; y < data[0].length; y++) {
        data[x][y] *= num;
      }
    }
  }

  public void multiply(Matrix other) {
    if (getX() != other.getX() || getY() != other.getY()) {
      System.out.println("Incorrect Sizes When Multiplying");
    }
    for (int x = 0; x < data.length; x++) {
      for (int y = 0; y < data[0].length; y++) {
        data[x][y] *= other.getNum(x, y);
      }
    }
  }

  public void applyLambda() {
    for(int x = 0; x < data.length; x++) {
      for(int y = 0; y < data[0].length; y++) {
        data[x][y] = Matrix.lambda(data[x][y]);
      }
    }
  }

  public static Matrix multiply(Matrix one, Matrix two) {
    Matrix result = new Matrix(one.getX(), two.getY());

    for (int i = 0; i < one.data.length; i++) { // aRow
        for (int j = 0; j < two.data[0].length; j++) { // bColumn
            for (int k = 0; k < one.data[0].length; k++) { // aColumn
                result.data[i][j] += one.data[i][k] * two.data[k][j];
            }
        }
    }
    
    return result;
  }

  public void applyAlphabet() {
      for(int x = 0; x < data.length; x++) {
        for(int y = 0; y < data[0].length; y++) {
          data[x][y] = (int)(data[x][y] * 13 + 78);
        }
      }
  }
  
  public String toString() {
    String result = "[\n";
    for(double[] row : data) {
      result += "\t[";
      for(double val : row) {
        result += " " + val + ", ";
      }
      result = result.substring(0, result.length() - 2) + " ";
      result += "]\n";
    }
    return result + "]";
  }
  public static double lambda(double x) {
    return (Math.exp(x) - Math.exp(-x)) / (Math.exp(x) + Math.exp(-x));
  }

  public static Matrix flip(Matrix other) {
    Matrix result = new Matrix(other.getX(), other.getY());
    for(int y = 0; y < other.data[0].length; y++) {
      result.data[0][y] = other.data[0][other.data[0].length - y - 1]; 
    }
    
    return result;
  }

}