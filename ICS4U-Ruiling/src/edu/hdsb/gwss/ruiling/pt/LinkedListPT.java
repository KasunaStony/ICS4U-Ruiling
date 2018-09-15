package edu.hdsb.gwss.ruiling.pt;

/**
 * Linked List of Students 
 *
 * @version v2018.S2
 */
public class LinkedListPT {

    private NodePT head;
    private NodePT tail;

    /**
     * Constructor: Empty Linked List
     */
    public LinkedListPT() {
        this.head = null;
        this.tail = null;
    }

    /**
     * @return returns the size (# of students) in the linked list.
     */
    public int size() {
        if (this.head == null && this.tail == null) {
            return 0;
        }
        //counter start with head
        int count = 0;
        NodePT next = head;
        //while next is not the tail, counter plus 1
        while (!next.equals(tail)) {
            next = next.getNext();
            count++;
        }
        //return counter + tail
        return count + 1;
    }

    /**
     * This method makes the linked list empty.
     */
    public void makeEmpty() {
        //both head and tail point to null
        this.head = null;
        this.tail = null;
    }

    /**
     * This method returns true if the linked list is empty
     *
     * @return returns true if there are no student objects in the linked list
     */
    public boolean isEmpty() {
        //if size is 0, then it is empty
        return this.size() == 0;
    }

    /**
     * This method adds a student object to the end of the linked list.
     *
     * @param student
     */
    public void addAtEnd(CSStudent student) {
        //if the LinkList is empty, point both head and tail to the new node
        if (this.isEmpty()) {
            head = new NodePT(student);
            tail = head;
        } else {
            //create a new node by the given data
            NodePT newNode = new NodePT(student);
            //set the old tail to point to the new node
            tail.setNext(newNode);
            //set the tail point to the new node
            tail = newNode;
        }
    }

    /**
     * This method will get a student object from the linked list, given the
     * student number
     *
     * @param key student number (key)
     * @return returns the student object for the given key, or null if it
     * doesn't exist.
     */
    public CSStudent get(int key) {
        if (this.isEmpty()) {
            return null;
        }
        NodePT next = head;
        CSStudent student = null;
        while (next != tail) {
            if (next.getData().getKey() == key) {
                //System.out.println(key);
                student = next.getData();
                break;
            }
            next = next.getNext();
        }
        if (next.getData().getKey() == key) {
            student = next.getData();
        }
        return student;
    }

    /**
     * This method will remove a student object from the linked list, given the
     * student number.
     *
     * @param key student number (key)
     * @return returns the removed student object, or null if the key is not
     * found
     */
    public CSStudent remove(int key) {
        if (this.isEmpty()) {
            return null;
        }
        CSStudent oldStudent = null;

        if (head.getData().getKey() == key) {
            oldStudent = head.getData();
            if (this.size() == 1) {
                head = null;
                tail = null;
            } else {
                head = head.getNext();
            }
            return oldStudent;
        } else if (tail.getData().getKey() == key) {
            oldStudent = tail.getData();
            if (this.size() == 1) {
                this.makeEmpty();
            } else {
                //find the node before tail, start with head
                NodePT beforeTail = head;
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
            return oldStudent;
        } else {
            NodePT before = head;
            NodePT target;
            while (!before.equals(tail)) {
                target = before.getNext();
                if (!target.equals(tail) && target.getData().getKey() == key) {
                    oldStudent = target.getData();
                    //set the node before the target to the next node of target, skip target
                    before.setNext(target.getNext());
                    //break the loop
                    break;
                } 
                before = target;
            }
            return oldStudent;
        }

    }

    /**
     * DONE FOR YOU
     */
    @Override
    public String toString() {
        return "HEAD: " + toString(this.head);
    }

    /**
     * DONE FOR YOU
     */
    private String toString(NodePT n) {
        String s = "";
        if (n != null) {
            s = n.getData() + " --> " + this.toString(n.getNext());
        }
        return s;
    }

}
