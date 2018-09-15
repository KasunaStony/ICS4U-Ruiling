/*
 * Name: Ruiling Ma
 * Date: Apr. 25, 2018
 * Version: 1.0
 * Description: Tester for the stack class
 */
package edu.hdsb.gwss.ruiling.ics4u.unit5;


/**
 *
 * @author maruiling
 */
public class StackTest {
    
    public static void main(String args[]){
        
        System.out.println("------------TEST CASE 1---------------");
        System.out.println("--This is a empty stack with no capacity.--");
        //create a stack with default constructor, capacity 0
        Stack s = new Stack();    
        //test every method with empty stack with no capacity
        assert(s.top() == -1);
        assert(s.pop() == -1);
        s.push(0);
        assert(s.size() == 0);
        assert(s.isEmpty());
        assert(s.isFull());
        System.out.println("---------TEST CASE 1 PASSED------------\n");
        
        System.out.println("------------TEST CASE 2---------------");
        System.out.println("--This is a stack with 1 to 100 capacity--");
        //random a capacity for the stack
        int c = (int)(Math.random() * 100 + 1);
        //create a new stack
        s = new Stack(c);
        System.out.println("--TEST 2A Test when empty--");
        //test when it is empty, test methods
        assert(s.top() == -1);
        assert(s.pop() == -1);
        assert(s.size() == 0);
        assert(s.isEmpty());
        assert(!s.isFull());
        assert(s.capacity() == c);
        System.out.println("--TEST 2A PASSED--");
        //test push
        System.out.println("--TEST 2B Test push--");
        //assign values to the stack
        for(int i = 0; i < c; i++){
            //size should be i
            assert(s.size() == i);            
            s.push(i);           
            //top should be i
            assert(s.top() == i);          
        }
        //should be full 
        assert(s.isFull());
        //try to push when is full, should print a warning
        s.push(0);
        System.out.println("--TEST 2B PASSED--");
        //test pop
        System.out.println("--TEST 2C Test pop--");
        //re-assign the stack
        for(int i = c - 1; i >=0; i--){
            //should pop i
            assert(s.pop() == i);
            //size should be i
            assert(s.size() == i);          
        }
        //shoud be empty now
        assert(s.size() == 0);
        assert(s.isEmpty());
        System.out.println("--TEST 2C PASSED--");
        //test makeEmpty
        System.out.println("--TEST 2C Test makeEmpty--");
        //re-assign stack
        for(int i = 0; i < c; i++){
            s.push(i);
        }
        s.makeEmpty();
        //should be empty now
        assert(s.isEmpty());
        System.out.println("--TEST 2C PASSED.--");
        System.out.println("---------TEST CASE 2 PASSED------------\n");
    }   
}
