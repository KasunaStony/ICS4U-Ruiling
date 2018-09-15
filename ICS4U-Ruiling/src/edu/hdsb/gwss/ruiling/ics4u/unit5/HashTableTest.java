/*
 * Name: Ruiling Ma
 * Date: May. 03, 2018
 * Version: 1.0
 * Description: HashTable Tester for testing HashTable objects
 */
package edu.hdsb.gwss.ruiling.ics4u.unit5;

/**
 *
 * @author maruiling
 */
public class HashTableTest {

    public static void main(String args[]) {
        //new hashTable
        HashTable ht = new HashTable();
        //student object
        Student s = new Student();
        //test empty hash table
        System.out.println("--------TEST 1 EMPTY HASHTABLE-------");
        //test every method
        assert (ht.isEmpty());
        assert (ht.size() == 0);
        assert (ht.capacity() == 53);
        assert (!ht.contains(s));
        assert (ht.get(0) == null);
        assert (!ht.containsKey(s.hashCode()));
        assert (ht.loadFactor() == 0);
        System.out.println("--------TEST 1 PASSED-------");
        //test when no rehash happened
        System.out.println("--------TEST 2 LOADFACTOR LESS THAN 75%--------");
        for (int i = 0; i < 39; i++) {
            s = new Student("A" + (i + 1), "B" + (i + 1), i + 1, i + 2);
            //put the new student and test every method
            ht.put(s.hashCode(), s);
            assert (!ht.isEmpty());
            assert (ht.size() == i + 1);
            assert (ht.capacity() == 53);
            assert (ht.contains(s));
            assert (ht.containsKey(s.hashCode()));
            assert (ht.get(s.hashCode()).equals(s));
            assert (Math.abs(ht.loadFactor() - (i + 1) / 53.0) < 0.00001);
        }
        System.out.println("--------TEST 2 PASSED--------");
        //new student
        s = new Student("A40", "B40", 40, 41);
        //test rehash
        System.out.println("---------TEST 3 REHASH-------");
        //load factor should be bigger than 0.75 now
        ht.put(s.hashCode(), s);
        //test every method after reshash
        assert (!ht.isEmpty());
        assert (ht.size() == 40);
        assert (ht.capacity() == 163);
        assert (ht.contains(s));
        assert (ht.get(s.hashCode()).equals(s));
        assert (ht.containsKey(s.hashCode()));
        assert (Math.abs(ht.loadFactor() - 40.0 / 163) < 0.00001);
        System.out.println("---------TEST 3 PASSED-------");
        //test adding the same student
        System.out.println("-------TEST 4 SAME STUDENT-----");
        for (int i = 0; i < 40; i++) {
            s = new Student("A" + (i + 1), "B" + (i + 1), i + 1, i + 2);
            ht.put(s.hashCode(), s);
            //test every method
            assert (!ht.isEmpty());
            assert (ht.size() == 40);
            assert (ht.capacity() == 163);
            assert (ht.contains(s));
            assert (ht.get(s.hashCode()).equals(s));
            assert (ht.containsKey(s.hashCode()));
            assert (Math.abs(ht.loadFactor() - 40.0 / 163) < 0.00001);
        }
        System.out.println("---------TEST 4 PASSED--------");
        //test collisions
        System.out.println("---------TEST 5 COLLISION------");
        //should be 40 collisions each time add a new student
        for (int i = 0; i < 40; i++) {
            s = new Student("A" + (i + 1), "B" + (i + 1), i + 1 + 163, i + 2);
            //test every method
            ht.put(s.hashCode(), s);
            assert (!ht.isEmpty());
            assert (ht.size() == 40 + i + 1);
            assert (ht.capacity() == 163);
            assert (ht.contains(s));
            assert (ht.get(s.hashCode()).equals(s));
            assert (ht.containsKey(s.hashCode()));
            assert (Math.abs(ht.loadFactor() - (40 + i + 1) / 163.0) < 0.00001);
        }
        System.out.println("-----------TEST 5 PASSED---------");

    }

}
