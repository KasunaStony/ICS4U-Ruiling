/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.ruiling.ics4u.unit4.Locks;

/**
 * This is a class for Dudley Lock.
 * @since 2018-05-16
 * @version 1.0
 * @author Ruiling Ma
 */
public class DudleyLock extends RotatablePadlock{
    
    //min and max of the range
    private static final int MIN = 0;
    private final static int MAX = 59;
    
    /**
     * Default constructor.
     */
    public DudleyLock(){
        super();
    }
    
    /**
     * Constructor with the combo
     * @param first first number
     * @param second second number
     * @param third third number
     */
    public DudleyLock(int first, int second, int third){
        //initial with min and max
        super(first, second, third, MIN, MAX);
    }
    
}
