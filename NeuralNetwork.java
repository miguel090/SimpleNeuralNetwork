import java.lang.Math;

public class NeuralNetwork{
  double learning_rate;
  double inputNodes[];
  double hiddenNodes[];
  double outputNodes[];
  Matrix weightsIH;
  Matrix weightsHO;
  Matrix biasH;
  Matrix biasO;
  NeuralNetwork(){
    this.learning_rate=0.5;
    this.inputNodes= new double[4];
    this.hiddenNodes= new double[4];
    this.outputNodes= new double[1];
    this.weightsIH=new Matrix(4,4);
    this.weightsIH=Matrix.randomize_M(weightsIH);
    this.weightsHO=new Matrix(1,4);
    this.weightsHO= Matrix.randomize_M(weightsHO);
    this.biasH=new Matrix(4,1);
    this.biasH= Matrix.randomize_M(biasH);
    this.biasO=new Matrix(1,1);
    this.biasO=Matrix.randomize_M(biasO);
  }
  public void setLearnigRate(double nr){
    this.learning_rate=nr;
  }
  public double feedforward(double[] inputArray){
    //Generating the Hidden Outputs
    Matrix inputs= Matrix.transformInMatrix(inputArray);
    Matrix hidden = Matrix.multiply(this.weightsIH, inputs);
    hidden=Matrix.add(hidden,this.biasH);
    hidden=sigmoid(hidden);
    Matrix output=Matrix.multiply(this.weightsHO, hidden);
    output=Matrix.add(output, this.biasO);
    output=sigmoid(output);
    return output.data[0][0];
  }
  public Matrix sigmoid(Matrix m){
    Matrix result= new Matrix(m.rows,m.cols);
    for (int i=0; i<m.rows; i++){
      for (int j=0; j<m.cols; j++) {
        result.data[i][j]= 1/(1+Math.exp(-(m.data[i][j])));
      }
    }
    return result;
  }
  public Matrix dsigmoid(Matrix m){
    Matrix result= new Matrix(m.rows,m.cols);
    for (int i=0; i<m.rows; i++){
      for (int j=0; j<m.cols; j++) {
        result.data[i][j]= m.data[i][j]*(1-m.data[i][j]);
      }
    }
    return result;
  }

  public void train(double[] inputArray, double[] targetArray){
    Matrix inputs= Matrix.transformInMatrix(inputArray);
    Matrix hidden = Matrix.multiply(this.weightsIH, inputs);
    hidden=Matrix.add(hidden,this.biasH);
    hidden=sigmoid(hidden);
    Matrix output=Matrix.multiply(this.weightsHO, hidden);
    output=Matrix.add(output, this.biasO);
    output=sigmoid(output);

    Matrix targets = Matrix.transformInMatrix(targetArray);
    //calculate the error
    /*System.out.println("targets");
    Matrix.print (targets);
    System.out.println("output");
    Matrix.print (output);*/
    Matrix outputErrors= Matrix.subtract(targets, output);
    /*System.out.println("outputErrors");
    Matrix.print (outputErrors);*/
    //calculate gradient
    Matrix gradient = dsigmoid(output);
    /*Matrix.print(gradient);
    System.out.println("gradient");
    Matrix.print (gradient);*/
    gradient = Matrix.multiply_ha(gradient,outputErrors);
    //Matrix.print (gradient);
    gradient = Matrix.scale(gradient,this.learning_rate);
    //Matrix.print (gradient);

    //calculate deltas
    Matrix hidden_t = Matrix.transpose(hidden);
    Matrix weightHOdeltas = Matrix.multiply(gradient, hidden_t);

    //Change the weight by deltas
    this.weightsHO= Matrix.add(this.weightsHO,weightHOdeltas);
    this.biasO= Matrix.add(this.biasO,gradient);
    //System.out.println("weights hidden to output");
    //Matrix.print(this.weightsHO);
    //Calculate hidden layer Errors
    Matrix weightsHO_t = Matrix.transpose(this.weightsHO);
    Matrix hiddenErrors = Matrix.multiply(weightsHO_t, outputErrors);

    //Calculate the hidden gradient
    //Matrix.print(hidden);
    Matrix hiddenGradient = dsigmoid(hidden);
    /*System.out.println("hidden Gradient after dsigmoid");
    Matrix.print(hiddenGradient);
    System.out.println("Hidden Errors");
    Matrix.print(hiddenErrors);*/

    hiddenGradient = Matrix.multiply_ha(hiddenGradient,hiddenErrors);
    hiddenGradient = Matrix.scale(hiddenGradient,this.learning_rate);

    //Calculate Input to hidden deltas
    Matrix inputs_t = Matrix.transpose(inputs);
    //System.out.println("Inputs");
    //Matrix.print(inputs_t);
    Matrix weightsIHdeltas = Matrix.multiply(hiddenGradient, inputs_t);
    //System.out.println("weightsIHdeltas");
    //Matrix.print(weightsIHdeltas);
    this.weightsIH= Matrix.add(this.weightsIH, weightsIHdeltas);
    this.biasH= Matrix.add(this.biasH,hiddenGradient);
    //System.out.println("weights input to hidden");
    //Matrix.print(this.weightsIH);
    //System.out.println("hidden Gradient");
    //Matrix.print(hiddenGradient);

  }
}
