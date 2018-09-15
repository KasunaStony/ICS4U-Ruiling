package edu.hdsb.gwss.ruiling.ics4u.unit4.Locks;

/**
 * This is a class for Master Lock.
 * @since 2018-05-16
 * @version 1.0
 * @author Ruiling Ma
 */
public class MasterLock extends RotatablePadlock {
    
    //min and max of the digits
    private final static int MIN = 0;
    private final static int MAX = 39;
    
    /**
     * Default constructor.
     */
    public MasterLock() {
        super();     
    }
    
    /**
     * Constructor with the combo.
     * @param first first number
     * @param second second number
     * @param third third number
     */
    public MasterLock(int first, int second, int third) {
        //initial with min and max
        super(first, second, third, MIN, MAX);
    }
    
}
