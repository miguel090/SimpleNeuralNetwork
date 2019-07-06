import java.util.Random;

public class TestRN{
  public static void main(String[] args) {
    double input[] = new double[4];
    double target[] = new double[1];
    NeuralNetwork NN = new NeuralNetwork();
    double sum=0;
    int iteractions=0;
    double diff1=1,diff2=1;
    double result;
    while(diff1>0.05 && diff2>0.05) {
      //  System.out.println(diff1);
      sum=0;
      input[0]=random();
      input[1]=random();
      input[2]=random();
      input[3]=random();
      for (int j=0; j<4; j++) {
        if(input[j]==1){
          sum++;
        }
      }

      if (sum%2==0) target[0]=1;
      else target[0]=0;
      /*System.out.println(input[0] + " " + input[1] + " " +input[2] + " " + input[3]);
      System.out.println(target[0]);
      System.out.println();*/
      NN.train(input,target);
    input[0]=1;
    input[1]=0;
    input[2]=0;
    input[3]=0;
    result =NN.feedforward(input);
    diff1= result;
    input[0]=0;
    input[1]=1;
    input[2]=1;
    input[3]=0;
    result =NN.feedforward(input);
    diff2= 1-result;
    iteractions++;
  }
  System.out.println(iteractions);
  }
  public static double random(){
      Random r= new Random();
      double nr=r.nextDouble();
      if(nr>=0.5) return 1;
      else return 0;
  }
}
