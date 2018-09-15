package edu.hdsb.gwss.ruiling.ics4u.unit5;

/**
 * Lesson: 6.02 - Queue w/Integer
 */
public interface QueueInterface {
    
    public Integer front();
    
    public Integer back();
    
    public void enqueue( Integer value );

    public Integer dequeue();

    public int size();
    
    public int capacity();

    public boolean isEmpty();
    
    public boolean isFull();

    public void makeEmpty();

}
