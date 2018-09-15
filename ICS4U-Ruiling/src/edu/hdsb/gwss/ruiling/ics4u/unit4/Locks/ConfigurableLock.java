package edu.hdsb.gwss.ruiling.ics4u.unit4.Locks;

/**
 * This is a abstract class for configurable lock.
 * @since 2018-05-16
 * @version 1.0
 * @author Ruiling Ma
 */
public abstract class ConfigurableLock extends Lock {

    //combo for the lock
    private int[] combo;
    //is the lock default setting or not
    private boolean isDefault;

    /**
     * Constructor with number of digits.
     *
     * @param digits number of digits
     */
    public ConfigurableLock(int digits) {
        //lock with certain digits
        super(digits);
        //no combo set
        combo = null;
        //is default setting
        isDefault = true;
    }

    /**
     * Constructor with combo.
     *
     * @param hasDefaultCombo does the lock has a default combo
     * @param digits number of digits
     * @param max max in range
     * @param min min in range
     * @param setCombo the combo
     */
    public ConfigurableLock(boolean hasDefaultCombo, int digits, int max, int min, int... setCombo) {
        //a lock with certain digits
        super(digits);
        //if the new combo does not match the number of digits, set to default
        if (setCombo.length != digits) {
            combo = null;
            isDefault = true;
            System.out.println("Combo does not match the number of digits. Lock is set to default. ");
            //if the lock has a default combo, set to default (which is 0)
            if (hasDefaultCombo) {
                combo = new int[digits];
                for (int i = 0; i < digits; i++) {
                    combo[i] = 0;
                }
                System.out.println("The " + this.getClass().getSimpleName() + " is set to default combo. ");
            } else {
                //if there is no default combo, set the lock unlocked
                this.setIsUnlocked(true);
                System.out.println("The " + this.getClass().getSimpleName() + " is set to unlock. ");
            }
        } else {
            //check if all the number is in the range
            boolean inRange = true;
            //go throgh the new combo to check
            for (int i = 0; i < setCombo.length; i++) {
                //if anything out of the range
                if (setCombo[i] < min || setCombo[i] > max) {
                    inRange = false;
                }
            }
            //if everything fits
            if (inRange) {
                //set the combo
                combo = new int[digits];
                for (int i = 0; i < setCombo.length; i++) {
                    combo[i] = setCombo[i];
                }
                this.isDefault = false;
                System.out.println("A new " + this.getClass().getSimpleName() + " is created. ");
            } else {
                //if not in range, set to default
                combo = null;
                isDefault = true;
                System.out.println("Combo not in range. ");
                //if has default combo, set to default combo
                if (hasDefaultCombo) {
                    combo = new int[digits];
                    for (int i = 0; i < digits; i++) {
                        combo[i] = 0;
                    }
                    System.out.println("The " + this.getClass().getSimpleName() + " is set to default combo. ");
                } else {
                    //no default combo, set to unlock
                    this.setIsUnlocked(true);
                    System.out.println("The " + this.getClass().getSimpleName() + " is set to unlock. ");
                }
            }
        }
    }

    /**
     * Try to unlock the lock.
     *
     * @param key the combo user entered
     */
    @Override
    public void unLock(int... key) {
        //if is already unlocked
        if (this.isUnlocked()) {
            System.out.println("This lock is already unlocked. ");
        } else if (this.getAttempts() >= 3) {
            //if reach the max attempts
            System.out.println("Max attempts, can not unlock. ");
        } else if (key.length != this.getNumberOfDigits()) {
            //if number of digits does not match
            this.setAttempts(this.getAttempts() + 1);
            System.out.println("Number of digits is wrong. ");
        } else {
            //check if everything is match
            boolean correct = true;
            //compare two array
            for (int i = 0; i < key.length; i++) {
                if (key[i] != combo[i]) {
                    correct = false;
                }
            }
            //set the status
            this.setIsUnlocked(correct);
            //if the key is correct
            if (correct) {
                System.out.println("The lock is unlocked. ");
                //set attempts to 0
                this.setAttempts(0);
            } else {
                //attempts plus 1
                this.setAttempts(this.getAttempts() + 1);
                System.out.println("Wrong combo. ");
            }
        }
    }

    /**
     * Get the combo of the lock.
     *
     * @return the combo
     */
    @Override
    public String getCombo() {
        //if the lock is default and combo is null
        if (this.isDefault && combo == null) {
            System.out.println("Lock is set to default. ");
            return "";
        } else if (this.isUnlocked() || !this.isRevealed()) {
            //if the lock is unlocked or is not revealed
            String str = "";
            //get every number in the combo
            for (int i = 0; i < combo.length; i++) {
                str += String.valueOf(combo[i] + " ");
            }
            System.out.println("Access to the combo is allowed. ");
            //set reveal to be true
            this.setIsRevealed(true);
            //return the combo
            return str;
        } else {
            //cannot get the combo
            System.out.println("Access to the combo is not allowed. ");
            return "";
        }
    }

    /**
     * Change the combo of the lock.
     *
     * @param min the min of the range
     * @param max the max of the range
     * @param newCombo the new combo to be set
     */
    protected void configure(int min, int max, int... newCombo) {
        //only can change the combo when unlocked
        if (this.isUnlocked()) {
            //if length does not match
            if (newCombo.length != this.getNumberOfDigits()) {
                System.out.println("Wrong number of digits for this lock. ");
            } else {
                //check if the new combo is in range
                boolean inRange = true;
                //go through the array to check
                for (int i = 0; i < newCombo.length; i++) {
                    if (newCombo[i] < min || newCombo[i] > max) {
                        inRange = false;
                    }
                }
                //if it is in range
                if (inRange) {
                    //renew the combo array
                    combo = new int[this.getNumberOfDigits()];
                    for (int i = 0; i < newCombo.length; i++) {
                        combo[i] = newCombo[i];
                    }
                    //not default anymore
                    this.setIsDefault(false);
                    System.out.println("The lock is set to be the new combo. ");
                    //set revealed to false
                    this.setIsRevealed(false);
                } else {
                    //out of the range
                    System.out.println("New combo is out of range. ");
                }
            }
        } else {
            //the lock is locked
            System.out.println("Unlock the lock first to set new combo. ");
        }
    }
    
    /**
     * Set to the default combo.
     * @param hasDefaultCombo does the lock has the default combo or not
     */
    protected void setToDefault(boolean hasDefaultCombo) {
        //if does not have a default combo and is unlocked
        if (!hasDefaultCombo && combo != null && this.isUnlocked()) {
            //set combo to be null
            combo = null;
            //is default
            this.isDefault = true;
            //is unlocked
            this.setIsUnlocked(true);
            this.setIsDefault(true);
            System.out.println("The lock is set to default. ");
        } else if (!hasDefaultCombo && combo != null) {
            //is locked
            System.out.println("Have to unlock first to set to default. ");
        } else {
            //can not set
            System.out.println("Locks have a default combo can not be set to default. ");
        }
    }
    
    /**
     * Set the default status.
     * @param b the boolean to be set
     */
    protected final void setIsDefault(boolean b) {
        this.isDefault = b;
    }
    
    /**
     * Lock the lock.
     */
    @Override
    public void lock() {
        //if the lock is default and there is no combo, can not lock
        if (this.isDefault && combo == null) {
            System.out.println("Can not lock a lock that does not have a combo. ");
        } else {
            //else lock the lock
            super.lock();
        }
    }
}
