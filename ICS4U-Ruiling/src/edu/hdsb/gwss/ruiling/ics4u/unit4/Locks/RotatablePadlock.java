package edu.hdsb.gwss.ruiling.ics4u.unit4.Locks;

/**
 * This is a abstract class for Rotatable padlock.
 * @since 2018-05-16
 * @version 1.0
 * @author Ruiling Ma
 */
public abstract class RotatablePadlock extends Lock {
    
    //the first, second and third number of the combo
    private final int firstDigit;
    private final int secondDigit;
    private final int thirdDigit;
    //number of digits
    private static final int numberOfDigits = 3;
    
    /**
     * Default constructor.
     */
    public RotatablePadlock() {
        //a lock with 3 digits
        super(numberOfDigits);
        //the combo is in default form
        firstDigit = -1;
        secondDigit = -1;
        thirdDigit = -1;
        //print a message
        System.out.println("A new " + this.getClass().getSimpleName() + " is created, but not initialized with a valid combo. ");
    }
    
    /**
     * Constructor with the initialized combo
     * @param first first number
     * @param second second number
     * @param third third humber
     * @param min min value
     * @param max max value
     */
    public RotatablePadlock(int first, int second, int third, int min, int max) {
        //a lock with 3 digits
        super(numberOfDigits);
        //if any of the number of out of the range, set to default
        if (first < min || first > max || second < min || second > max || third < min || third > max) {
            System.out.println("Not a valid combo. ");
            firstDigit = -1;
            secondDigit = -1;
            thirdDigit = -1;
            System.out.println("A new " + this.getClass().getSimpleName() + " is created, but not initialized with a valid combo. ");
        } else {
            //initial the combo
            firstDigit = first;
            secondDigit = second;
            thirdDigit = third;
            System.out.println("A new " + this.getClass().getSimpleName() + " is created. ");
        }
    }
    
    /**
     * Unlock the lock.
     * @param combo the combo entered by the user
     */
    @Override
    public void unLock(int... combo) {
        //if is already unlocked
        if (this.isUnlocked()) {
            System.out.println("The lock is already unlocked. ");
        } else if (this.getAttempts() >= 3) {
            //if reach the maxium attempts
            System.out.println("Max attempts, can not unlock. ");
        } else if (firstDigit == -1 || secondDigit == -1 || thirdDigit == -1) {
            //if the lock is default
            System.out.println("Combo is not set, can not unlock. ");
        } else if (combo.length != numberOfDigits) {
            //if the number of digits in combo does not match 3
            System.out.println("Not the right combo. This lock has three number for the combo. ");
            //attempts plus 1
            super.setAttempts(this.getAttempts() + 1);
        } else {
            //check if every number is correct
            boolean correct;
            //if everything match
            correct = (combo[0] == firstDigit && combo[1] == secondDigit && combo[2] == thirdDigit);
            //set status of the lock
            this.setIsUnlocked(correct);
            if (correct) {
                //if correct, set attempts to 0
                this.setAttempts(0);
                System.out.println("You unlock the lock. ");
            } else {
                //if not correct, attempts plus 1
                super.setAttempts(super.getAttempts() + 1);
                System.out.println("Not the right combo. ");
            }
        }
    }
    
    /**
     * Get the combo of the lock
     * @return the string of the combo, separate by space
     */
    @Override
    public String getCombo() {
        //if default
        if(firstDigit == -1 || secondDigit == -1 || thirdDigit == -1){
            System.out.println("No combo is set. ");
            return "";
        }
        //if revealed
        if (this.isRevealed()) {
            System.out.println ("Access to the combo is not allowed. ");
            return "";
        } else {
            //if not revealed
            this.setIsRevealed(true);
            System.out.println("Access to the combo is allowed. ");
            return this.firstDigit + " " + this.secondDigit + " " + this.thirdDigit;
        }
    }
}
