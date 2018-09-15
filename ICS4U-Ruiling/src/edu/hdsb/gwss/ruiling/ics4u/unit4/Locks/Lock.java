package edu.hdsb.gwss.ruiling.ics4u.unit4.Locks;

/**
 * This is a abstract class for locks, implement LockInterface.
 * @since 2018-05-16
 * @version 1.0
 * @author Ruiling Ma
 */
public abstract class Lock implements LockInterface {

    //the serial number of locks
    private static int countSerial = 1;
    //the status of the lock
    private boolean isUnlocked;
    //the number of numbers in the combo
    private final int numberOfDigits;
    //the serial number for the lock
    private final int serialNumber;
    //attemps to unlock the lock
    private int attemps = 0;
    //the status of revealed combo or not
    private boolean isRevealed = false;

    /**
     * Default constructor.
     */
    public Lock() {
        //the lock is locked
        this.isUnlocked = false;
        //0 digits for the combo
        this.numberOfDigits = 0;
        //serial number 
        this.serialNumber = countSerial;
        //created a new lock, tracker plus 1
        countSerial++;
    }

    /**
     * Constructor with the number of numbers in the combo.
     *
     * @param digits the number of numbers in the combo
     */
    public Lock(int digits) {
        //the lock is lockied
        this.isUnlocked = false;
        //set number of digits
        this.numberOfDigits = digits;
        //serial number
        serialNumber = countSerial;
        //created a new lock, tracker plus 1
        countSerial++;
    }

    /**
     * Get the serial number.
     *
     * @return serial number
     */
    public final int getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * Set the status of the lock, can only be accessed by subclass and class in
     * the package, can not override.
     *
     * @param b the status of the lock
     */
    protected final void setIsUnlocked(boolean b) {
        isUnlocked = b;
    }

    /**
     * Get the number of wrong attempts to unlock the lock, can only be accessed
     * by subclass and class in the package, can not override.
     *
     * @return the number of attempts
     */
    protected final int getAttempts() {
        return this.attemps;
    }

    /**
     * Set the number of attempts, can only be accessed by subclass and class in
     * the package, can not override.
     *
     * @param a new number of attempts
     */
    protected final void setAttempts(int a) {
        this.attemps = a;
    }

    /**
     * Try to unlock the lock, to be implement by subclasses.
     *
     * @param combo the array of combo
     */
    @Override
    public abstract void unLock(int... combo);

    /**
     * Lock the lock.
     */
    @Override
    public void lock() {
        this.isUnlocked = false;
        System.out.println("The lock is locked. ");
    }

    /**
     * Get the status of the lock whether it is unlocked or locked, can not
     * override.
     *
     * @return true if the lock is unlocked, false otherwise.
     */
    @Override
    public final boolean isUnlocked() {
        return this.isUnlocked;
    }

    /**
     * Get the combo of the lock, to be implement by subclasses.
     *
     * @return a string of combo.
     */
    public abstract String getCombo();

    /**
     * Get the status of the lock whether the combo has been revealed or not.
     *
     * @return true if revealed, false otherwise
     */
    protected final boolean isRevealed() {
        return isRevealed;
    }

    /**
     * Set the status of the revealed problem, can only be accessed by subclass
     * and class in the package, can not override.
     *
     * @param b the boolean to be set
     */
    protected final void setIsRevealed(boolean b) {
        this.isRevealed = b;
    }

    /**
     * Get the number of digits in the combo, can not be override.
     *
     * @return the number of digits
     */
    public final int getNumberOfDigits() {
        return this.numberOfDigits;
    }

    /**
     * Generate the hash code of the lock based on the serial number, can not
     * override.
     *
     * @return the hash code
     */
    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.getSerialNumber();
        return hash;
    }

    /**
     * Compare two objects.
     *
     * @param obj the object to compare
     * @return true if they are the same, false otherwise
     */
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lock other = (Lock) obj;
        //if the serial number matches, they are the same
        return this.serialNumber == other.serialNumber;
    }
    
    /**
     * Get the information of the lock.
     * @return the information of the lock
     */
    @Override
    public String toString() {
        //try to get the combo
        String combo = this.getCombo();
        //if nothing is returned, access failed
        if (combo.equals("")) {
            combo = "fail";
        }
        //the result of the method
        String result = "\n";
        //get the name of the lock
        result += "This is a " + this.getClass().getSimpleName() + ".\n";
        //the serial number of the lock
        result += "The Serial Number of this lock is " + this.getSerialNumber() + "\n";
        //the result of access the combo
        result += "Attempting to access the combo: " + combo + "\n";
        //time of wrong attemps
        result += "Times of wrong attemps to open the lock: " + this.attemps + "\n";
        //the status 
        result += "The status of the lock: ";
        if (this.isUnlocked()) {
            //if is unlocked
            result += "is unlocked. " + "\n";
        } else {
            //if locked
            result += "is locked.\n";
        }
        //return the result
        return result;
    }
}
