/*
 * Name: Ruiling Ma
 * Date: Apr. 25, 2018
 * Version: 1.0
 * Description: Tester for the LinkList class
 */
package edu.hdsb.gwss.ruiling.ics4u.unit5;

/**
 *
 * @author maruiling
 */
public class LinkListTest {

    public static void main(String args[]) {
        System.out.println("---------TEST CASE 1------------\n"
                + "-------TEST CASE 1A------\n"
                + "---Empty LinkList----");
        //create LinkList
        LinkList l = new LinkList();
        //nothing in the list
        //check every method
        assert (l.head() == null);
        assert (l.tail() == null);
        assert (l.size() == 0);
        assert (l.isEmpty());
        l.remove("B");
        assert (l.removeHead() == null);
        assert (l.removeTail() == null);
        System.out.println("-------TEST 1A PASSED-------");
        System.out.println("------TEST CASE 1B--------\n"
                + "-----LinkList Size 1-----");
        //LinkList with size 1
        l.addAtFront("A");
        //test every method
        assert (l.head().equals("A"));
        assert (l.tail().equals("A"));
        assert (l.size() == 1);
        assert (!l.isEmpty());
        l.remove("B");
        assert (l.removeHead().equals("A"));
        l.addAtEnd("A");
        assert (l.removeTail().equals("A"));
        assert (l.isEmpty());
        System.out.println("------TEST 1B PASSED------");
        System.out.println("------TEST CASE 1C--------\n"
                + "---LinkList with 4 - 200 size---");
        //random size
        int s = (int)(Math.random() * 99 + 2);
        //add each node to the LinkList
        for (int i = 0; i < s; i++) {
            l.addAtFront(String.valueOf(i));
            l.addAtEnd(String.valueOf(i));
            //test methods
            assert (l.size() == (i + 1) * 2);
            assert (!l.isEmpty());
            assert (l.head().equals(String.valueOf(i)));
            assert (l.tail().equals(String.valueOf(i)));
        }
        //size should be double the s
        assert (l.size() == s * 2);
        //test removeHead or removeTail
        for (int i = s; i > 0; i--) {
            assert (l.size() == (i) * 2);
            assert (l.removeHead().equals(String.valueOf(i - 1)));
            assert (l.removeTail().equals(String.valueOf(i - 1)));
        }
        //should be empty at the end
        assert(l.isEmpty());
        //re-assign nodes to the LinkList
        for(int i = 1; i <= s; i++){
            l.addAtEnd(String.valueOf(i));
        }
        //test the remove method by removing nodes in the middle
        for(int i = 2; i < s; i++){
            l.remove(String.valueOf(i));
        }
        //should be left with a head and a tail
        assert(l.toString().equals("1\n" + s));
        //remove head
        l.remove("1");
        //remove tail
        l.remove(String.valueOf(s));
        //should be empty 
        assert(l.isEmpty());
        //re-assign the nodes
        for(int i = 1; i <= s; i++){
            l.addAtEnd(String.valueOf(i));
        }
        //test makeEmpty method
        l.makeEmpty();
        //should be empty
        assert(l.isEmpty());
        System.out.println("------TEST 1C PASSED------");
        System.out.println("---------TEST CASE 1 PASSED------------");
    }

}
