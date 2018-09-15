/*
 * Name: Ruiling Ma
 * Date: Apr. 25, 2018
 * Version: 1.0
 * Description: LinkList class for LinkList objects
 */
package edu.hdsb.gwss.ruiling.ics4u.unit5;

/**
 *
 * @author maruiling
 */
public class LinkList implements LinkListInterface {
    
    //private variable head and tail
    private Node head;
    private Node tail;
    //default constructor
    public LinkList() {
    }

    
    /**
     * Calculate the number of Nodes in the LinkList
     *
     * @return the number of Nodes in the LinkList
     */
    @Override
    public int size() {
        
        if (this.head == null && this.tail == null) {
            return 0;
        }
        //counter start with head
        int count = 0;
        Node next = head;
        //while next is not the tail, counter plus 1
        while (!next.equals(tail)) {
            next = next.getNext();
            count++;
        }
        //return counter + tail
        return count + 1;

    }
    /**
     * Make the LinkList empty
     *
     */
    @Override
    public void makeEmpty() {
        //both head and tail point to null
        this.head = null;
        this.tail = null;
    }
    
    /**
     * Check if the LinkList is empty
     *
     * @return true if is empty
     */
    @Override
    public boolean isEmpty() {
        //if size is 0, then it is empty
        return this.size() == 0;
    }
    /**
     * Add a Node to the front
     *
     * @param str the data of the node to be added
     */
    @Override
    public void addAtFront(String str) {
        //if the LinkList is empty, point both head and tail to the new node
        if (this.isEmpty()) {
            head = new Node(str);
            tail = head;
        } else {
            //create a new node by the given data
            Node newNode = new Node(str);
            //set the nextNode of the new node to the old head
            newNode.setNext(head);
            //point the head to the new node
            head = newNode;
        }
    }
    /**
     * Add a Node to the end
     *
     * @param str the data of the node to be added
     */
    @Override
    public void addAtEnd(String str) {
        //if the LinkList is empty, point both head and tail to the new node
        if (this.isEmpty()) {
            head = new Node(str);
            tail = head;
        } else {
            //create a new node by the given data
            Node newNode = new Node(str);
            //set the old tail to point to the new node
            tail.setNext(newNode);
            //set the tail point to the new node
            tail = newNode;
        }
    }
    /**
     * Removes the first occurrence of the given string.
     *
     * @param str the data of the node to be removed
     */
    @Override
    public void remove(String str) {
        //if the required node is in the linklist
        boolean isSuchNode = false;
        //if the linklist is empty, no node found or deleted
        if (isEmpty()) {
            System.out.println("LinkList is empty.");
        } 
        //if the data given is the head, remove head
        else if (head.getValue().equals(str)) {
            //there is such node
            isSuchNode = true;
            //remove head
            removeHead();
        } 
        //start to search from head.getNext
        else {
            //the node before target
            Node before = head;
            //the node to delete
            Node target;
            //while next node of front is not null, before is not tail
            while (!before.equals(tail)) {
                //set target to the next node of before
                target = before.getNext();
                //if target is not tail and has the data given
                if (!target.equals(tail) && target.getValue().equals(str)) {
                    //foung the node
                    isSuchNode = true;
                    //set the node before the target to the next node of target, skip target
                    before.setNext(target.getNext());
                    //break the loop
                    break;
                } 
                //if the target is the tail
                else if(target.getValue().equals(str)){
                    //found the node
                    isSuchNode = true;
                    //remove tail
                    removeTail();
                    //break the loop
                    break;
                }
                //if not found the node, set before to the next node
                before = target;
            }
        }
        //if did not find the node, print a message
        if (!isSuchNode) {
            System.out.println("No such element in the LinkList.");
        }
    }
    
    /**
     * Remove head from the LinkList
     *
     * @return the data of head
     */
    @Override
    public String removeHead() {
        //str to store the data of old head
        String str;
        //if the LinkList is empty
        if (this.isEmpty()) {
            //no data of head
            str = null;
            //print a message
            System.out.println("LinkList is empty.");
        } 
        //if only one node
        else if (this.size() == 1) {
            //str is the data of the old head
            str = head.getValue();
            //set both head and tail to null
            head = null;
            tail = null;
        } else {
            //str is the data of the old head
            str = head.getValue();
            //set head to the next node
            head = head.getNext();
        }
        //return the data of the old head
        return str;
    }
     /**
     * Remove tail from the LinkList
     *
     * @return the data of tail
     */
    @Override
    public String removeTail() {
        //String to store the data of the old tail
        String str;
        //if the Linklist is empty
        if (isEmpty()) {
            //no data of the tail
            str = null;
            //print a message
            System.out.println("LinkList is empty");
        } 
        //if only 1 node in the list
        else if (size() == 1) {
            //store the data of the old tail
            str = tail.getValue();
            //set both head and tail to be null
            head = null;
            tail = null;
        } else {
            //store the data of the old tail
            str = tail.getValue();
            //find the node before tail, start with head
            Node beforeTail = head;
            //while beforeTail is not tail
            while (beforeTail.getNext() != null) {
                //if the beforeTail is before tail
                if (beforeTail.getNext().equals(tail)) {
                    //set tail to the node before the old one
                    tail = beforeTail;
                    //break loop
                    break;
                }
                //if before tail is not before tail, set it to the next node
                beforeTail = beforeTail.getNext();
            }
        }
        //return the data of the old tail
        return str;
    }
     /**
     * Get the data of head
     *
     * @return the data of the head
     */
    @Override
    public String head() {
        //if the LinkList is empty
        if (this.isEmpty()) {
            //there is no head
            return null;
        } else {
            //return the data of the head
            return head.getValue();
        }
    }
     /**
     * Get the data of tail
     *
     * @return the data of the tail
     */
    @Override
    public String tail() {
        //if the LinkList is empty
        if (this.isEmpty()) {
            //no tail
            return null;
        } else {
            //return the data of the tail
            return tail.getValue();
        }
    }
    /**
     * Return the String contains every data of every node in the LinkList
     *
     * @return the String of each data, each data per line
     */
    @Override
    public String toString() {
        //the String of the data
        String str = "";
        //start with the head
        Node n = head;
        //while the node is not the tail
        while (!(n.equals(tail))) {
            //str add the data of the node
            str += n.getValue() + "\n";
            //to the next node
            n = n.getNext();
        }
        //add the data of the tail
        str += this.tail();
        //return all the data
        return str;
    }

}
