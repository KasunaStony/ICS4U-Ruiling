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
public class FractionTest {
    
    public static void main(String args[]){
        
        Fraction f = new Fraction(0,0);
        System.out.println(f.size());
        assert(f.getDenominator() == 0);
        assert(f.getNumerator() == 0);
        f.invert();
        assert(f.larger(new Fraction(6,7)).equals(new Fraction(6,7)));
        f.reduce();
        assert(f.times(new Fraction(8,9)) == null);
        
        f = new Fraction(2,4);
        f.reduce();
        assert(f.getDenominator() == 2);
        assert(f.getNumerator() == 1);
        f = new Fraction(2,-4);
        f.reduce();
        assert(f.getDenominator() == 2);
        assert(f.getNumerator() == -1);
        
        
        try{
            f = new ProperFraction(4,3);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        
        f = new ProperFraction(3,4);
        assert(f.size() == 3/4.0);
        assert(f.equals(new Fraction(6, 8)));
        assert(f.getDenominator() == 4);
        assert(f.getNumerator() == 3);
        assert(f.larger(new Fraction(1,1)).equals(new Fraction(1,1)));
        assert(f.times(new Fraction(6,7)).equals(new Fraction(18,28)));
        System.out.println(f.toString());
        f.invert();
        assert(f.size() == 4.0/3);
        
        f = new ProperFraction(-3,4);
        assert(f.size() == -3/4.0);
        assert(f.equals(new Fraction(-6, 8)));
        assert(f.getDenominator() == 4);
        assert(f.getNumerator() == -3);
        assert(f.larger(new Fraction(1,1)).equals(new Fraction(1,1)));
        assert(f.times(new Fraction(6,7)).equals(new Fraction(-18,28)));
        System.out.println(f.toString());
        f.invert();
        assert(f.size() == -4.0/3);
        
        
        try{
            f = new ImproperFraction(3,4);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        
        f = new ImproperFraction(4,3);
        assert(f.size() == 4.0/3);
        assert(f.equals(new Fraction(8, 6)));
        assert(f.getDenominator() == 3);
        assert(f.getNumerator() == 4);
        assert(f.larger(new Fraction(1,1)).equals(f));
        assert(f.times(new Fraction(6,7)).equals(new Fraction(24,21)));
        assert(((ImproperFraction)f).toMix().equals(new MixedFraction(4,3)));
        System.out.println(f.toString());
        f.invert();
        assert(f.size() == 3/4.0);
        
        f = new ImproperFraction(4,-3);
        assert(f.size() == -4.0/3);
        assert(f.equals(new Fraction(-8, 6)));
        assert(f.getDenominator() == 3);
        assert(f.getNumerator() == -4);
        assert(f.larger(new Fraction(1,1)).equals(new Fraction(1,1)));
        assert(f.times(new Fraction(6,7)).equals(new Fraction(-24,21)));
        assert(((ImproperFraction)f).toMix().equals(new MixedFraction(-4,3)));
        System.out.println(f.toString());
        f.invert();
        assert(f.size() == -3/4.0);
        
        
        f = new MixedFraction(4,3);
        //System.out.println(f.getDenominator());
        assert(f.getDenominator() == 3);
        assert(f.getNumerator() == 1);
        assert(((MixedFraction)f).getWholeNumber() == 1);
        assert(f.size() == 4.0/3);
        assert(f.equals(new Fraction(8, 6)));
        assert(f.larger(new Fraction(1,1)).equals(f));
        assert(f.times(new Fraction(6,7)).equals(new Fraction(24,21)));
        assert(((MixedFraction)f).toImproper().equals(new ImproperFraction(4,3)));
        System.out.println(f.toString());
        f.invert();
        assert(f.size() == 3/4.0);
        
        f = new MixedFraction(4,-3);
        //System.out.println(f.getDenominator());
        assert(f.getDenominator() == 3);
        assert(f.getNumerator() == 1);
        assert(((MixedFraction)f).getWholeNumber() == -1);
        assert(f.size() == -4.0/3);
        assert(f.equals(new Fraction(-8, 6)));
        assert(f.larger(new Fraction(1,1)).equals(new Fraction(1,1)));
        assert(f.times(new Fraction(6,7)).equals(new Fraction(-24,21)));
        assert(((MixedFraction)f).toImproper().equals(new ImproperFraction(-4,3)));
        System.out.println(f.toString());
        f.invert();
        assert(f.size() == -3/4.0);
        assert(f.getDenominator() == 4);
        assert(f.getNumerator() == -3);
        f.invert();
        assert(((MixedFraction)f).getWholeNumber() == -1);
        assert(f.getNumerator() == 1);
        assert(f.getDenominator() == 3);
        f.invert();
        assert(f.size() == -3/4.0);
        assert(f.getDenominator() == 4);
        assert(f.getNumerator() == -3);     
    }
    
}
