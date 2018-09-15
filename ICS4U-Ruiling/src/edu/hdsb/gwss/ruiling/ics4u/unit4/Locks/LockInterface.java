package edu.hdsb.gwss.ruiling.ics4u.unit4.Locks;

/**
 * This is a interface for locks.
 * @since 2018-05-16
 * @version 1.0
 * @author Ruiling Ma
 */
public interface LockInterface {
    /**
     * Implement to unlock the certain lock
     * @param combo the array of combo, vary in length
     */
    public void unLock(int... combo);
    /**
     * Lock the certain lock.
     */
    public void lock();
    /**
     * Get the status of the lock.
     * @return true if the lock is open, false otherwise.
     */
    public boolean isUnlocked();
    
}
