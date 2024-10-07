package matrix;

import complex.*;

//класс матриц комплексных чисел
public class Matrix {

    private int row; //строки
    private int col; //столбцы
    private Complex[] matrix; //массив под элементы матрицы

    //конструктор пустой матрицы размера row * col
    public Matrix (int row, int col) throws ArrayStoreException{
        if ((row < 1) || (col < 1)){throw new ArrayStoreException("Incorrect matrix's size: row or column < 1");}
        this.row = row;
        this.col = col;
        this.matrix = new Complex[row * col];
    }

    //конструктор матрицы размера row * col, заполненной числом Complex val
    public Matrix (int row, int col, Complex val) throws ArrayStoreException {
        if ((row < 1) || (col < 1)) {
            throw new ArrayStoreException("Incorrect matrix's size: row or column < 1");
        }
        this.row = row;
        this.col = col;
        this.matrix = new Complex[row * col];
        for (int i = 0; i < row * col; i++) {
            matrix[i] = val;
        }
    }

    //конструктор матрицы размера row * col, заполненной числом int val
    public Matrix (int row, int col, int val) throws ArithmeticException {
        if ((row < 1) || (col < 1)) {
            throw new ArithmeticException ("Incorrect matrix's size: row or column < 1");
        }
        this.row = row;
        this.col = col;
        this.matrix = new Complex[row * col];
        for (int i = 0; i < row * col; i++) {
            matrix[i] = new Complex (val, 0);
        }
    }

    //конструктор матрицы размера row * col, заполненной числами (double)val
    public Matrix (int row, int col, double ... val) throws ArithmeticException{
        if ((row < 1) || (col < 1)) {
            throw new ArithmeticException ("Incorrect matrix's size: row or column < 1");
        }
        this.row = row;
        this.col = col;
        this.matrix = new Complex[row * col];
        for (int i = 0; i < val.length; i++){
            this.matrix[i] = new Complex(val[i]);
        }
    }

    //конструктор матрицы размера row * col, заполненной числами (complex)val
    public Matrix (int row, int col, Complex ... val) throws ArithmeticException{
        if ((row < 1) || (col < 1)) {
            throw new ArithmeticException ("Incorrect matrix's size: row or column < 1");
        }
        this.row = row;
        this.col = col;
        this.matrix = new Complex[row * col];
        for (int i = 0; i < val.length; i++){
            this.matrix[i] = val[i];
        }
    }

    //конструктор копирования
    public Matrix (Matrix M){
        this.row = M.row;
        this.col = M.col;
        this.matrix = new Complex[this.row * this.col];
        for (int i = 0; i < this.row * this.col; i++){
            this.matrix[i] = M.matrix[i];
        }
    }

    //метод, возвращающий [n, m] элемент матрицы
    public Complex elemAt(int n, int m) throws ArrayIndexOutOfBoundsException{
        if ((n >= this.row) || (m > this.col)){throw new ArrayIndexOutOfBoundsException("Index is out of range");}
        return this.matrix[n * col + m];
    }

    //геттеры размера
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }


    //единичная матрица размера row * col
    public static Matrix Identity(int row, int col) throws ArithmeticException{
        if (row != col) {throw new ArithmeticException("Incorrect matrix's size: row != column");}
        Matrix I = new Matrix (row, col, 0);
        I.matrix[0] = new Complex(1);
        for (int i = 0; i < row; i++) {I.matrix[row * i + i] = new Complex(1);}
        return I;
    }

    //метод транспонирования
    public Matrix transpose() {
        Matrix res = new Matrix(this.col, this.row);
        int i = 0, j = 0;
        while (i < this.row * this.col) {
            while (j < this.row * this.col) {
                res.matrix[i] = this.matrix[j + (i / this.row)];
                j = j + col;
                i++;
            }
            j = 0;
        }
        return res;
    }

    //метод нахождения обратной матрицы
    public Matrix Inverse(Matrix M){
        Matrix n = new Matrix (M);
        return n;
    }

    //метод сложения матриц
    public Matrix add(Matrix M) throws ArithmeticException{
        if ((this.row != M.row) || (this.col != M.col)){throw new ArithmeticException("Incorrect matrix's size: unequal");}
        Matrix res = new Matrix(row, col);
        for (int i = 0; i < row * col; i++){
            res.matrix[i] = (this.matrix[i]).add(M.matrix[i]);
        }
        return res;
    }

    //метод вычитания матриц
    public Matrix subtract(Matrix M) throws ArithmeticException{
        if ((this.row != M.row) || (this.col != M.col)){throw new ArithmeticException("Incorrect matrix's size: unequal");}
        Matrix res = new Matrix(row, col);
        for (int i = 0; i < row * col; i++){
            res.matrix[i] = (this.matrix[i]).subtract(M.matrix[i]);
        }
        return res;
    }

    //метод умножения матрицы на матрицу
    public Matrix multiply(Matrix M) throws ArithmeticException{
        if (this.col != M.row){throw new ArithmeticException("Incorrect matrix's size: left Matrix's column size != right Matrix's row size");}
        Matrix res = new Matrix(this.row, M.col, 0);
        int j = 0, k = 0;
        for (int i = 0; i < this.row * M.col; i++) {
            while (j < this.col) {
                res.matrix[i] = res.matrix[i].add((this.matrix[j + this.col * (i / M.col)]).multiply(M.matrix[k + (i % M.col)]));
                j++;
                k += M.col;
            }
            j = 0;
            k = 0;
        }
        return res;
    }

    //метод умножения матрицы на число
    public Matrix multiply(double n) {
        Matrix res = new Matrix(this.row, this.col);
        for (int i = 0; i < this.row * this.col; i++){
            res.matrix[i] = this.matrix[i].multiply(n);
        }
        return res;
    }

    //метод деления
    public Matrix divide(Matrix M) throws ArithmeticException {
        if (this.col != M.row) {throw new ArithmeticException ("Incorrect matrix's size: left Matrix's column size != right Matrix's row size");}
        if (M.row != M.col) {throw new ArithmeticException ("Incorrect matrix's size: right Matrix must be a square Matrix");}
        if (M.determinant().equals(0)){throw new ArithmeticException ("Incorrect matrix's determinant: right's Matrix determinant can't be zero");}

        Matrix res = new Matrix(this.row, M.col, 0);

        Matrix A = new Matrix(M);
        Matrix E = Matrix.Identity(M.row, M.col);
        int N = M.row;
        Complex temp;
        for (int k = 0; k < N; k++) {
            temp = A.elemAt(k, k);
            for (int j = 0; j < N; j++) {
                A.matrix[k * col + j] = (A.elemAt(k, j)).divide(temp);
                E.matrix[k * col + j] = (E.elemAt(k, j)).divide(temp);
            }
            for (int i = k + 1; i < N; i++) {
                temp = A.elemAt(i, k);
                for (int j = 0; j < N; j++) {
                    A.matrix[i * col + j] = (A.elemAt(i, j)).subtract(A.elemAt(k, j).multiply(temp));
                    E.matrix[i * col + j] = (E.elemAt(i, j)).subtract(E.elemAt(k, j).multiply(temp));
                }
            }
        }
        for (int k = N - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                temp = A.elemAt(i, k);
                for (int j = 0; j < N; j++) {
                    A.matrix[i * col + j] = (A.elemAt(i, j)).subtract(A.elemAt(k, j).multiply(temp));
                    E.matrix[i * col + j] = (E.elemAt(i, j)).subtract(E.elemAt(k, j).multiply(temp));
                }
            }
        }
        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < M.col; j++){
                for (int k = 0; k < this.col; k++){
                    res.matrix[i * col + j] = (res.elemAt(i, j)).add(this.matrix[i * this.col + k].multiply(E.elemAt(k, j)));
                }
            }
        }
        return res;
    }

    //метод поиска детерминанта
    public Complex determinant() throws ArithmeticException{
        if (this.row != this.col){throw new ArithmeticException("Incorrect matrix's size: Matrix must be a square Matrix");}

        Complex res = new Complex(1, 0);
        Complex tmp = new Complex (0, 0);
        Matrix A = new Matrix(this);
        for (int k = 0; k < this.row - 1; k++) {
            for (int i = k + 1; i < this.row; i++) {
                tmp = (this.elemAt(i, k).divide(this.elemAt(k, k))).multiply(-1);
                for (int j = 0; j < this.row; j++) {
                    A.matrix[i * col + j] = this.elemAt(i, j).add(this.elemAt(k, j).multiply(tmp));
                }
            }
        }
        for (int i = 0; i < this.row; i++){
            res = res.multiply(A.elemAt(i, i));
        }
        return res;
    }

    //перегрузка toString для вывода через print()
    @Override
    public String toString(){
        if (this.matrix == null) {return("Matrix is empty");}
        else {
            String r = "";
            r += "[";
            for (int i = 0; i < row * col; i++) {
                if ((i % col == 0) && (i != 0)) {r += "]\n[";}
                r += this.matrix[i].toString();
                if (i % col != col - 1) {r += ", ";}
            }
            r += "]";
            return r;
        }
    }
}
