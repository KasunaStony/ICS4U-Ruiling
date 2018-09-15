/*
 * Name: Ruiling Ma
 * Date: Apr. 25, 2018
 * Version: 1.0
 * Description: Node class for Node objects
 */
package edu.hdsb.gwss.ruiling.ics4u.unit5;

/**
 *
 * @author maruiling
 */
public class Node implements NodeInterface {

    //private varible data and next node
    private String data;
    private Node next;

    //default constructor
    public Node() {
        //no data and not pointing to other node
        data = null;
        next = null;
    }

    //constructor with the data
    public Node(String s) {
        //data is s
        data = s;
        //not pointint to other node
        next = null;
    }

    /**
     * Get the next node
     *
     * @return the next node
     */
    @Override
    public Node getNext() {
        return this.next;
    }

    /**
     * Set next node
     *
     * @param newNode the node to set
     */
    @Override
    public void setNext(Node newNode) {
        this.next = newNode;
    }

    /**
     * Get the value of the data
     *
     * @return the data
     */
    @Override
    public String getValue() {
        return this.data;
    }

}
