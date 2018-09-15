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
public class ProperFraction extends Fraction{
    
    public ProperFraction(){
        super();
    }
    
    public ProperFraction(int n, int d){
        super(n,d);
        if(Math.abs(n) >= Math.abs(d) && d!=0){
            throw new RuntimeException("Not a proper fraction");
        }
    }
    
}
