package complex;

import java.text.DecimalFormat;
import java.text.NumberFormat;

//класс комплексных чисел
public class Complex {

    private double real; //действительная часть
    private double img; //мнимая часть

    //конструктор класса вида real + img
    public Complex(double real, double img) {
        this.real = real;
        this.img = img;
    }

    //конструктор класса вида real
    public Complex(double real){
        this.real = real;
        this.img = 0;
    }

    //сеттеры-геттеры
    public void setReal (double real){this.real = real;}
    public void setImg (double img){this.img = img;}
    public double getReal() {return this.real;};
    public double getImg (){return this.img;}

    //метод сложения complex + complex
    public Complex add(Complex n) {
        return new Complex(this.real + n.real, this.img + n.img);
    }

    //метод сложения complex + double
    public Complex add(double n) {
        return new Complex(this.real + n, this.img);
    }

    //метод вычитания complex - complex
    public Complex subtract(Complex n) {
        return new Complex(this.real - n.real, this.img - n.img);
    }

    //метод вычитания complex - double
    public Complex subtract(double n){
        return new Complex(this.real - n, this.img);
    }

    //метод умножения complex * complex
    public Complex multiply(Complex n) {
        return new Complex((this.real * n.real - this.img * n.img), (this.real * n.img + this.img * n.real));
    }

    //метод умножения complex * double
    public Complex multiply(double n) {
        return new Complex((this.real * n), (this.img * n));
    }

    //метод деления complex / complex
    public Complex divide(Complex n) throws ArithmeticException {
        if ((n.real == 0) && (n.img == 0)) {throw new ArithmeticException("Division by zero");}
        return new Complex((this.real * n.real + this.img * n.img) / (n.real * n.real + n.img * n.img),
                (this.img * n.real - this.real * n.img) / (n.real * n.real + n.img * n.img));

    }

    //метод деления complex / double
    public Complex divide(double n) throws ArithmeticException{
        if (n == 0){throw new ArithmeticException("Division by zero");}
        return new Complex ((this.real * n) / (n * n), (this.img * n) / (n * n));
    }

    //метод сравнения на равенство complex ? complex
    public boolean equals(Complex n) {
        if ((this.real == n.real) && (this.img == n.img)) {return true;}
        else {return false;}
    }

    //метод сравнения на равенство complex ? double
    public boolean equals (double n){
        if ((this.real == n) && (this.img == 0)){return true;}
        else{return false;}
    }

    //метод сравнения на неравенство complex ? complex
    public boolean unequals(Complex n) {
        if ((this.real == n.real) && (this.img == n.img)) {return false;}
        else {return true;}
    }

    //метод сравнения на неравенство complex ? double
    public boolean unequals(double n){
        if ((this.real == n) && (this.img == 0)){return false;}
        else{return true;}
    }

    //перегрузка toString для вывода через print()
    @Override
    public String toString() {
        NumberFormat cut = new DecimalFormat("#.######");
        if (this.img >= 0) {
            return ("(" + cut.format(this.real) + " + " + cut.format(this.img) + "*i)");
        } else {
            return ("(" + cut.format(this.real) + " - " + cut.format((this.img * (-1))) + "*i)");
        }
    }
}
