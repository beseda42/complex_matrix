import complex.Complex;
import matrix.Matrix;

class Main {
    public static void main(String[] args) {
        //создание произвольных матриц
        Matrix A = new Matrix (3,2,new Complex(3,5), new Complex(2,5), new Complex(8,3), new Complex(2,1), new Complex(2,2), new Complex(1,7));
        Matrix B = new Matrix (2,2, 1, 0, 3, 5);
        //сложение
        System.out.println(B.add(B) + "\n");
        //вычитание
        System.out.println(B.subtract(B) + "\n");
        //умножение
        System.out.println(A.multiply(B) + "\n");
        //транспонирование
        System.out.println(A.transpose() + "\n");
        //детерминант
        System.out.println("Детерминант матрицы B = " + B.determinant());
        //деление
        System.out.println(A.divide(B));
        //всё!!
        //System.out.print("надеюсь на 1.5 :)");
    }
}