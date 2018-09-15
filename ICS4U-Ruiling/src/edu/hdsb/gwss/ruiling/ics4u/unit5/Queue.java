/*
 * Name: Ruiling Ma
 * Date: Apr. 25, 2018
 * Version: 1.0
 * Description: Queue class for queue objects
 */
package edu.hdsb.gwss.ruiling.ics4u.unit5;

/**
 *
 * @author maruiling
 */
public class Queue implements QueueInterface {
    
    //private varible, array of data
    private Integer data[];
    //front 
    private int front = -1;
    //back
    private int back = -1;
    /**
     * Default constructor
     */
    public Queue(){
        //an empty queue
        data = new Integer[0];
    }
    /**
     * Constructor with size
     * @param size the size of the queue
     */
    public Queue(int size){
        data = new Integer[size];
    }
    /**
     * Return the front Integer front
     * @return the Integer front or -1 if the queue is empty
     */
    @Override
    public Integer front() {
        //if queue is empty
        if(this.isEmpty()){
            //print a message
            System.out.println("Queue is empty");
            //return -1
            return -1;
        }else{
            //return the front integer
            return data[front % this.capacity()];
        }
    }
    /**
     * Return the back Integer
     * @return the back Integer or -1 if the queue is empty
     */
    @Override
    public Integer back() {
        //if queue is empty
        if(this.isEmpty()){
            //print a message
            System.out.println("Queue is empty");
            //return -1
            return -1;
        }else{
            //return the back integer
            return data[back % this.capacity()];
        }
    }
    /**
     * Add a Integer at the end of the queue.
     * @param value the value of the Integer to be added.
     */
    @Override
    public void enqueue(Integer value) {
        //if the queue is full, print a message
        if(this.isFull()){
            System.out.println("Queue is full.");
        }else if(this.isEmpty()){
            //if the queue is empty, both front and back are at 0
            front++;
            back++;
            //set the first element to be the value
            this.data[this.back % this.capacity()] = value;
        }else{
            //not a empty queue, back +1
            back++;
            //set the back data to be the value
            this.data[this.back % this.capacity()] = value;
        }
    }
    /**
     * Remove a Integer at the start of the queue.
     * @return the value of the back Integer
     */
    @Override
    public Integer dequeue() {
        //if the queue is empty, print a message and return -1
        if(this.isEmpty()){
            System.out.println("Queue is empty.");
            return -1;
        }else if(this.size() == 1){
            //if the size of the queue is 1, set both front and back to -1 and return the old front value
            int oldFront = this.front();
            front = -1;
            back = -1;
            return oldFront;
        }else{
            //set the front to the next element and return the old front
            int oldFront = this.front();
            this.front ++;
            return this.data[oldFront];
        }
            
    }
    /**
     * Calculate the size of the queue
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        //if the queue is empty, return 0
        if(this.isEmpty())
            return 0;
        else
            //or return the number of elements
            return back - front + 1;
    }
    /**
     * Get the maximum number of elements can be stored in the queue
     * @return the capacity of the queue
     */
    @Override
    public int capacity() {
        //the capacity is the length of array
        return data.length;
    }
    /**
     * Tell a queue is empty or not.
     * @return true if is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        //if both front and back is at -1, then it is empty
        return (front == -1 && back == -1);
    }
    /**
     * Tell a queue is full or not.
     * @return true if is full, false otherwise
     */
    @Override
    public boolean isFull() {
        //a queue is full if the size is equal to the capacity or when the capacity is 0
        return (this.size() == this.capacity() || this.capacity() == 0);
    }
    /**
     * Make the queue to be empty
     */
    @Override
    public void makeEmpty() {
        //set both front and back to -1
        front = -1;
        back = -1;
    }

}
