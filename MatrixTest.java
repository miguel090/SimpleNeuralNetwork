public class MatrixTest{
  public static void main(String[] args) {
    Matrix m1=new Matrix(2,3);
    Matrix m2=new Matrix(3,2);
    m1.data[0][0]=1;
    m1.data[1][0]=3;
    m1.data[2][0]=5;
    m1.data[0][1]=2;
    m1.data[1][1]=4;
    m1.data[2][1]=6;


    m2.data[0][0]=1;
    m2.data[1][0]=3;
    m2.data[2][0]=5;
    m2.data[0][1]=2;
    m2.data[1][1]=4;
    m2.data[2][1]=6;

    System.out.println("________________________");
    Matrix m3 = Matrix.multiply(m1,m2);
    Matrix.print(m1);
    Matrix.print(m2);
    Matrix.print(m3);
  }
}
