package oop.y2022.encapsulation;

public class RationalNumber {

    // поля класса открыты, так делать не стоит.
    // при записи значений в поля невозможно проверить допустимы ли значения,
    // также нельзя привести объект к согласованному состоянию
    public int numerator;
    public int denominator;

    public void setNumerator(int numerator) {
        // нормализуем числитель и знаменатель
        int gcd = gcd(numerator, denominator);
        if (gcd > 1) {
            numerator = numerator / gcd;
            denominator = denominator / gcd;
        }

        this.numerator = numerator;
    }

    public void setDenominator(int denominator) {
        // при установке знаменателя проверяем допустимо ли переданное значение
        if (denominator <= 0) {
            throw new IllegalArgumentException("Set positive number");
        }

        // нормализуем числитель и знаменатель
        int gcd = gcd(numerator, denominator);
        if (gcd > 1) {
            numerator = numerator / gcd;
            denominator = denominator / gcd;
        }

        this.denominator = denominator;
    }

    // реализация вычисления НОД из интернета
    private int gcd(int n1, int n2) {
        int gcd = 1;
        for(int i = 1; i <= n1 && i <= n2; ++i) {
            // Checks if i is factor of both integers
            if(n1 % i == 0 && n2 % i == 0)
                gcd = i;
        }

        return gcd;
    }

    @Override
    public String toString() {
        return "RationalNumber{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}
