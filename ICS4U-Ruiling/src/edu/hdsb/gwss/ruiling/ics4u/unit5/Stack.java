/*
 * Name: Ruiling Ma
 * Date: Apr. 25, 2018
 * Version: 1.0
 * Description: Stack class for stacks objects
 */
package edu.hdsb.gwss.ruiling.ics4u.unit5;

/**
 *
 * @author maruiling
 */
public class Stack implements StackInterface {

    //private varibles, array data and top
    private int data[];
    private int top = -1;

    //default construcor
    public Stack() {
        //the data's length is 0 if not consumized
        this.data = new int[0];
    }

    //construtor
    public Stack(int s) {
        //data's length is the given number
        this.data = new int[s];
    }

    /**
     * Get the top value of the stack
     *
     * @return the top value
     */
    @Override
    public int top() {
        //if the stack is empty, return -1
        if (this.isEmpty()) {
            return -1;
        } else //return the top data
        {
            return data[top];
        }
    }

    /**
     * Remove the top data from the queue.
     *
     * @return the removed data
     */
    @Override
    public int pop() {
        //if the stack is empty, print a message and return -1
        if (this.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        } else {
            //top goes down 1 position
            this.top--;
            //return the old top data
            return this.data[top + 1];
        }
    }

    /**
     * Add the data to the queue at top.
     *
     * @param value the data to be added
     */
    @Override
    public void push(int value) {
        //if the stack is full, print a message
        if (this.isFull()) {
            System.out.println("Stack is full.");
        } else {
            //top point to the next element
            this.top++;
            //set data at top be the new value
            this.data[top] = value;
        }
    }

    /**
     * Get the size of the stack
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        //the number of elements in the stack is top + 1
        return this.top + 1;
    }

    /**
     * Get the total capacity of the stack
     *
     * @return the capacity of the stack
     */
    @Override
    public int capacity() {
        //the capacity of the stack is the lenght of data
        return this.data.length;
    }

    /**
     * Tell a stack is empty or not.
     *
     * @return true if is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        //if top is pointing to -1, it is empty
        return this.top == -1;
    }

    /**
     * Tell a stack is full or not.
     *
     * @return true if is full, false otherwise
     */
    @Override
    public boolean isFull() {
        //if the size is equal to the capacity, it is full
        return this.size() == this.capacity();
    }

    /**
     * Make the stack to be empty.
     */
    @Override
    public void makeEmpty() {
        //set the top to point to 0
        this.top = -1;
    }

}
