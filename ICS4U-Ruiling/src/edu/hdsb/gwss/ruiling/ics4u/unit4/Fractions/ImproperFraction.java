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
public class ImproperFraction extends Fraction{
    
    public ImproperFraction(){
        super();
    }
    
    public ImproperFraction(int n, int d){
        super(n, d);
        if(Math.abs(n) < Math.abs(d)){
            throw new RuntimeException("Not a improper fraction");
        }
    }
    
    public MixedFraction toMix(){
        return new MixedFraction(this.getNumerator(), this.getDenominator());
    }
}
