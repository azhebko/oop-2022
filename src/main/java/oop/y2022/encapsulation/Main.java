package oop.y2022.encapsulation;

public class Main {

    public static void main(String[] args) {
        RationalNumber rationalNumber = new RationalNumber();
        rationalNumber.setNumerator(2);
        rationalNumber.setDenominator(3);
        System.out.println(rationalNumber);

        rationalNumber.setDenominator(4);
        System.out.println(rationalNumber);
    }
}
