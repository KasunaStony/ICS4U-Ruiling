/*
 * Name: Ruiling Ma
 * Date: Apr. 25, 2018
 * Version: 1.0
 * Description: Tester for the queue class
 */
package edu.hdsb.gwss.ruiling.ics4u.unit5;

/**
 *
 * @author maruiling
 */
public class QueueTest {
    
    public static void main(String args[]){
        System.out.println("--------TEST CASE 1----------"+
                "\n---Empty queue with no capacity---");
        //create new queue with default constructor
        Queue q = new Queue();
        //test every method
        assert(q.front() == -1);
        assert(q.back() == -1);
        q.enqueue(34);
        assert(q.dequeue() == -1);
        assert(q.size() == 0);
        assert(q.capacity() == 0);
        assert(q.isEmpty());
        assert(q.isFull());
        System.out.println("-----TEST CASE 1 PASSED-------\n");
        
        System.out.println("--------TEST CASE 2----------"+
                "\n---Queue with 1 capacity---");
        //capacity 1 queue
        q = new Queue(1);
        //add a int
        q.enqueue(54);
        //test every method
        assert(q.isFull());
        assert(!q.isEmpty());
        assert(q.capacity() == 1);
        assert(q.size() == 1);
        assert(q.dequeue() == 54);
        assert(q.isEmpty());
        System.out.println("--------TEST CASE 2 PASSED----------\n");
        
        System.out.println("--------TEST CASE 3----------"+
                "\n---Queue with 1 - 100 capacity---");
        //random capacity queue
        int c = (int)(Math.random() * 100 +1);
        q = new Queue(c);
        System.out.println("--------TEST CASE 3A----------"+
                "\n---Queue is empty---");
        //test when it is empty
        assert(q.front() == -1);
        assert(q.back() == -1);
        assert(q.dequeue() == -1);
        assert(q.size() == 0);
        assert(q.capacity() == c);
        assert(q.isEmpty());
        assert(!q.isFull());
        System.out.println("--------TEST CASE 3A PASSED----------");
        System.out.println("--------TEST CASE 3B----------"+
                "\n---Test enqueue and dequeue---");
        //assign values to the queue
        for(int i = 0 ;i < c; i++){
            q.enqueue(i);
            //test methods
            assert(q.front() == 0);
            assert(q.back() == i);
            assert(q.capacity() == c);
            assert(q.size() == i+1);
        }
        //should be full
        assert(q.isFull());
        //test makeEmpty
        q.makeEmpty();
        //should be empty
        assert(q.isEmpty());
        //re-assgin the queue
        for(int i = 0; i < c; i++){
            q.enqueue(i);
        }
        //test dequeue
        for(int i = 0; i < c; i++){
            assert(q.dequeue() == i);           
            assert(q.size() == c - i - 1);
            if(q.size() != 0){
                assert(q.front() == i + 1);
                assert(q.back() == c - 1);
            }else{
                assert(q.front() == -1);
                assert(q.back() == -1);
            }
        }
        //should be empty
        assert(q.isEmpty());  
        System.out.println("--------TEST CASE 3B PASSED----------");
        System.out.println("----------TEST CASE 3 PASSED------------");
    }
    
}
