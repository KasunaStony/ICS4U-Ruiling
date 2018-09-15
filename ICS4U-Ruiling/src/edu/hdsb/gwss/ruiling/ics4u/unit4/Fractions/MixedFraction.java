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
public class MixedFraction extends Fraction{
    
    private int wholeNumber;
    private int n;
    private int d;
    
    public MixedFraction()
    {
        super();
        wholeNumber = 0;
        n = 0;
        d = 0;
    } 
    
    public MixedFraction(int n, int d){
        super(n, d);
        if(Math.abs(n) < Math.abs(d)){
            throw new RuntimeException("Not a mixed fraction");
        }
        this.n = super.getNumerator();
        this.d = super.getDenominator();
        wholeNumber = n/d;
        this.n = Math.abs(this.n - this.wholeNumber * this.d);
        
    }

    public int getWholeNumber() {
        return wholeNumber;
    }

    public int getNumerator() {
        return n;
    }

    public int getDenominator() {
        return d;
    }
    
    public void reduce(){
        int gcd = gcd(this.n, this.d);       
        this.n /= gcd;
        this.d /= gcd;        
    }    
    
    public ImproperFraction toImproper(){
        return new ImproperFraction(super.getNumerator(), super.getDenominator());
    }
    
    @Override
    public void invert(){
        super.invert();
        n = super.getNumerator();
        d = super.getDenominator();
        this.wholeNumber = n/d;
        this.n = Math.abs(this.n - this.wholeNumber * this.d);
        if(super.getNumerator() < 0 && this.wholeNumber == 0)
            n = -n;
    }
    
    @Override
    public String toString(){
        return this.wholeNumber + " & " + this.n + " / " + this.d;
    }
}
