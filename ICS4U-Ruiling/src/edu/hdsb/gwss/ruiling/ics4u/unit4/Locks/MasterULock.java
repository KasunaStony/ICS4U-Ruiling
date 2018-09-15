package edu.hdsb.gwss.ruiling.ics4u.unit4.Locks;

/**
 * This is a class for Master U Lock.
 * @since 2018-05-16
 * @version 1.0
 * @author Ruiling Ma
 */
public class MasterULock extends ConfigurableLock{
    
    //the number of digits in the combo
    private static final int DIGITS = 4;
    //max and min of the range
    private static final int MIN = 0;
    private static final int MAX = 9;
    //has a default combo
    private static final boolean HASDEFAULTCOMBO = true;
    
    /**
     * Default constructor.
     */
    public MasterULock(){
        super(HASDEFAULTCOMBO, DIGITS, MAX, MIN, 0,0,0,0);
    }
    
    /**
     * Constructor with the combo.
     * @param first first number
     * @param second second number
     * @param third third number
     * @param forth forth number
     */
    public MasterULock(int first, int second, int third, int forth){
        super(HASDEFAULTCOMBO, DIGITS, MAX, MIN, first, second, third, forth);
    }
    
    /**
     * Change the combo.
     * @param combo the new combo to be set
     */
    public void configure(int...combo){
        super.configure(MIN, MAX, combo);
    }
    
}
