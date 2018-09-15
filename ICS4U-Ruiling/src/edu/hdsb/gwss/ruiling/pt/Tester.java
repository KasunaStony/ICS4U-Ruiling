package edu.hdsb.gwss.ruiling.pt;

/**
 * Open Hash Table Tester 
 *
 * @version v2016.S2
 */
public class Tester {

    /**
     */
    public static void main( String[] args ) {
        
        LinkedListPT ll = new LinkedListPT();
        

        CSStudent studentAA1 = new CSStudent( "Arielle", "A", 7111, 11 );
        CSStudent studentAA2 = new CSStudent( "Ali", "A", 2221, 12 );
        CSStudent studentJB = new CSStudent( "James", "B", 2332, 12 );
        CSStudent studentRD = new CSStudent( "Raidi", "D", 1443, 12 );
        CSStudent studentAE = new CSStudent( "Ahmad", "E", 2554, 12 );
        CSStudent studentJG = new CSStudent( "Gillian", "J", 6665, 12 );
        CSStudent studentYK = new CSStudent( "Yulia", "K", 7976, 12 );
        CSStudent studentRL = new CSStudent( "Ryan", "L", 8387, 12 );
        CSStudent studentRM = new CSStudent( "Ruiling", "M", 9098, 11 );
        CSStudent studentYM = new CSStudent( "Youssef", "M", 1120, 12 );
        CSStudent studentFS = new CSStudent( "Faisal", "S", 2231, 12 );
        CSStudent studentKSE = new CSStudent( "Karim", "SE", 8695, 12 );
        CSStudent studentMS = new CSStudent( "Matthew", "S", 3695, 12 );
        CSStudent studentCW = new CSStudent( "Christiana", "W", 2691, 12 );
        CSStudent studentEX = new CSStudent( "Ellie", "X", 3333, 11 );
        CSStudent studentIY = new CSStudent( "Irmak", "Y", 1234, 12 );
        
        ll.addAtEnd(studentAA1);
        ll.addAtEnd(studentAA2);
        ll.addAtEnd(studentJB);
        ll.addAtEnd(studentRD);
        ll.addAtEnd(studentAE);
        ll.addAtEnd(studentJG);
        ll.addAtEnd(studentYK);
        ll.addAtEnd(studentRL);
        ll.addAtEnd(studentRM);
        ll.addAtEnd(studentYM);
        ll.addAtEnd(studentFS);
        ll.addAtEnd(studentKSE);
        ll.addAtEnd(studentMS);
        ll.addAtEnd(studentCW);
        ll.addAtEnd(studentEX);
        ll.addAtEnd(studentIY);
        
        assert(ll.size() == 16);
        assert(ll.get(studentRM.getKey()).equals(studentRM));
        assert(!ll.isEmpty());
        assert(ll.remove(studentRM.getKey()).equals(studentRM));
        assert(ll.get(studentRM.getKey()) == null);
        assert(ll.size() == 15);
        assert(ll.remove(studentAA1.getKey()).equals(studentAA1));
        assert(ll.get(studentAA1.getKey()) == null);
        assert(ll.size() == 14);
        assert(ll.remove(studentIY.getKey()).equals(studentIY));
        assert(ll.get(studentIY.getKey()) == null);
        assert(ll.size() == 13);
        
        
        OpenHashTable ht = new OpenHashTable();
        ht.put(studentAA1);
        ht.put(studentAA2);
        ht.put(studentJB);
        ht.put(studentRD);
        ht.put(studentAE);
        ht.put(studentJG);
        ht.put(studentYK);
        ht.put(studentRL);
        ht.put(studentRM);
        ht.put(studentYM);
        ht.put(studentFS);
        ht.put(studentKSE);
        ht.put(studentMS);
        ht.put(studentCW);
        ht.put(studentEX);
        ht.put(studentIY);
        
        System.out.println( ht.toString() );
        
        assert(ht.size() == 16);
        assert(ht.containsKey(2332));
        assert(!ht.containsKey(8));
        assert(ht.get(2332).equals(studentJB));
        assert(!ht.isEmpty());
        ht.makeEmpty();
        assert(ht.isEmpty());
        assert(ht.size() == 0);
        

    }

}
