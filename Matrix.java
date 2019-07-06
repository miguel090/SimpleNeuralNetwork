import java.lang.Error;
import java.util.Arrays;
import java.util.Random;
public class Matrix{
  int rows;
  int cols;
  double data[][];

  Matrix(int rows, int cols){
    this.rows=rows;
    this.cols=cols;
    data= new double[rows][cols];
  }
  public static Matrix copy(Matrix m){
    Matrix newm = new Matrix(m.rows,m.cols);
    for (int i=0; i<newm.rows; i++){
      for (int j=0; j<newm.cols; j++) {
        newm.data[i][j] = m.data[i][j];
      }
    }
    return newm;
  }



  public static double[] transformInArray(Matrix m){
    double[] result;
    if(m.rows==1){
      result = new double[m.rows];
      for(int i=0; i<m.cols; i++){
        result[i]=m.data[0][i];
      }
    }
    else if(m.cols==1){
      result = new double[m.cols];
      for(int i=0; i<m.cols; i++){
        result[i]=m.data[i][0];
      }
    }
    else{
      throw new Error("Matrix can't be converted to array");
    }

    return result;
  }




  public static Matrix  transformInMatrix(double[] array){
    Matrix result = new Matrix(array.length,1);
    //System.out.println("fodasse" + array.length);
    for(int i=0; i<array.length; i++){
      //System.out.println("fodasse" + array[i]);
      result.data[i][0] =array[i];
    }
    return result;
  }



  public static Matrix subtract(Matrix m1, Matrix m2){
    Matrix result= new Matrix(m1.rows,m1.cols);
     if (m1.rows != m2.rows || m1.cols != m2.cols){
       throw new Error("Columns and rows need to match between them");
     }
     else{
       for (int i=0; i<result.rows; i++){
         for (int j=0; j<result.cols; j++) {
           result.data[i][j] =m1.data[i][j]-m2.data[i][j];
         }
       }
     }
     return result;
  }




  public static Matrix add(Matrix m1, Matrix m2){
    Matrix result= new Matrix(m1.rows,m1.cols);
     if (m1.rows != m2.rows || m1.cols != m2.cols){
       throw new Error("Columns and rows need to match between them");
     }
     else{
       for (int i=0; i<result.rows; i++){
         for (int j=0; j<result.cols; j++) {
           result.data[i][j] =m1.data[i][j]+m2.data[i][j];
         }
       }
     }
     return result;
  }


  public static Matrix transpose(Matrix m){
    Matrix result= new Matrix(m.cols,m.rows);
    for (int i=0; i<m.rows; i++){
      for (int j=0; j<m.cols; j++) {
        result.data[j][i] = m.data[i][j];
      }
    }
    return result;
  }




  public static Matrix scale(Matrix m, double amount){
    for (int i=0; i<m.rows; i++){
      for (int j=0; j<m.cols; j++) {
        m.data[i][j] = m.data[i][j]*amount;
      }
    }
    return m;
}
//hadamard product
public static Matrix multiply_ha(Matrix m1, Matrix m2){
  Matrix result= new Matrix(m1.rows,m1.cols);
  for (int i=0; i<result.rows; i++){
    for (int j=0; j<result.cols; j++) {
      result.data[i][j]= m1.data[i][j]*m2.data[i][j];
    }
  }
  return result;
}



  public static Matrix multiply(Matrix m1, Matrix m2){
    if(m1.cols!=m2.rows){
      throw new Error("cols of A must equal rows of B");
    }
    Matrix result= new Matrix(m1.rows,m2.cols);
    double sum = 0;
    for (int i=0; i<result.rows; i++){
      for (int j=0; j<result.cols; j++) {
        sum=0;
        for (int k=0; k<m1.cols; k++) {
          sum = sum + (m1.data[i][k] * m2.data[k][j]);
        }
        result.data[i][j]=sum;
      }
    }
    return result;
  }


  public static Matrix randomize_M(Matrix m){
    Random r= new Random();
    for (int i=0; i<m.rows; i++){
      for (int j=0; j<m.cols; j++) {
        m.data[i][j]=r.nextDouble()*2-1;
      }
    }
    return m;
  }

  public static double[] randomize_A(double[] array){
    Random r= new Random();
    for (int i=0; i<array.length; i++){
        array[i]=r.nextDouble();
    }
    return array;
}

  public static double randomDouble(double nr){
    Random r= new Random();
    nr=r.nextDouble();
    return nr;
}
public static void print(Matrix m){
  for (int i=0; i<m.rows; i++){
    for (int j=0; j<m.cols; j++) {
      System.out.print(m.data[i][j] + " ");
    }
    System.out.println();
  }
  System.out.println();
}
}
