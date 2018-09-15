/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.ruiling.ics4u.unit4.Fractions;

/**
 *
 * @author maruiling
 */
public class Fraction {

    private int denominator;
    private int numerator;

    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        } else {
            return gcd(q, p % q);
        }

    }

    public Fraction() {
        denominator = 0;
        numerator = 0;
    }

    public Fraction(int n, int d) {
        if (d == 0) {
            System.out.println("Denominator can not be 0.");
            denominator = 0;
            numerator = 0;
        } else if (d < 0) {
            denominator = -d;
            numerator = -n;
        } else {
            denominator = d;
            numerator = n;
        }
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public double size() {
        if (this.denominator == 0) {
            System.out.println("Fraction not properly initialized. ");
        }
        return (double) this.numerator / (double) this.denominator;
    }

    public Fraction larger(Fraction f) {
        if (this.denominator == 0 || f.denominator == 0) {
            System.out.println("Fraction not properly initialized. ");
            if (this.denominator == 0 && f.denominator != 0) {
                return f;
            } else {
                return this;
            }
        } else if (this.size() > f.size()) {
            return this;
        } else if (this.size() < f.size()) {
            return f;
        } else {
            return this;
        }

    }

    public Fraction larger(Fraction f, Fraction g) {
        return f.larger(g);
    }

    public Fraction times(Fraction f) {
        if (this.denominator == 0 || f.denominator == 0) {
            System.out.println("Fraction not properly initialized. ");
            return null;
        }
        return new Fraction(f.numerator * this.numerator, f.denominator * this.denominator);
    }

    public Fraction times(Fraction f, Fraction g) {
        return f.times(g);
    }

    public void reduce() {
        if (this.denominator == 0) {
            System.out.println("Fraction not properly initialized. ");
        } else {
            int gcd = gcd(Math.abs(this.numerator), Math.abs(this.denominator));
            this.numerator /= gcd;
            this.denominator /= gcd;
        }
    }

    @Override
    public String toString() {
        if (this.denominator == 0) {
            return "Fraction not properly initialized. ";
        } else {
            return this.numerator + " / " + this.denominator;
        }
    }

    public boolean equals(Fraction f) {
        if (this.denominator == 0 || f.denominator == 0) {
            System.out.println("Fraction not properly initialized. ");
            return false;
        }
        return f.size() == this.size();
    }

    public void invert() {
        if (this.denominator == 0) {
            System.out.println("Fraction not properly initialized. ");
            return;
        }
        if (this.numerator < 0) {
            int d = this.denominator;
            this.denominator = -this.numerator;
            this.numerator = -d;
        } else {
            int d = this.denominator;
            this.denominator = this.numerator;
            this.numerator = d;
        }
    }

}
