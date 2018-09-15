package edu.hdsb.gwss.ruiling.ics4u.unit4.Locks;

/**
 * This is a class for Android Lock.
 * @since 2018-05-16
 * @version 1.0
 * @author Ruiling Ma
 */
public class AndroidLock extends ConfigurableLock{
    
    //the number of digits in the combo
    private static final int DIGITS = 3;
    //min and max of the range
    private static final int MIN = 0;
    private static final int MAX = 9;
    //does not have a default combo
    private static final boolean HASDEFAULTCOMBO = false;
    
    /**
     * Default constructor.
     */
    public AndroidLock(){
        super(HASDEFAULTCOMBO, DIGITS, MAX, MIN);
    }
    
    /**
     * Constructor with the combo
     * @param first first number
     * @param second second number
     * @param third third number
     */
    public AndroidLock(int first, int second, int third){
        super(HASDEFAULTCOMBO, DIGITS, MAX, MIN, first, second, third);
    }
    
    /**
     * Change the combo
     * @param combo the combo to be set
     */
    public void configure(int...combo){
        super.configure(MIN, MAX, combo);
    }
    
    /**
     * Set the lock to default.
     */
    public void setToDefault(){
        super.setToDefault(HASDEFAULTCOMBO);
    }  
}
