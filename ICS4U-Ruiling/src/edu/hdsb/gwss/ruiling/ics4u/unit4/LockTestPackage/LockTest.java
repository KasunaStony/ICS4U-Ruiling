package edu.hdsb.gwss.ruiling.ics4u.unit4.LockTestPackage;

/**
 * This is a tester for all locks.
 * @since 2018-05-16
 * @version 1.0
 * @author Ruiling Ma
 */
import edu.hdsb.gwss.ruiling.ics4u.unit4.Locks.*;

public class LockTest {
    
    public static void main(String args[]){
        
        System.out.println("-----------TEST 1 MASTERLOCK------------" + "\n" +
                "-------TEST 1A DEFAULT MASTERLOCK------");        
        Lock l = new MasterLock();
        //test all methods
        l.unLock();
        assert(l.getNumberOfDigits() == 3);
        assert(l.getSerialNumber() == 1);
        assert(l.getCombo().equals("-1 -1 -1"));
        l.getCombo();
        assert(!l.isUnlocked());
        System.out.println(l.toString());
        System.out.println("---------TEST 1A PASSED---------");
        System.out.println("---------TEST 1B MASTERLOCK WITH A COMBO---------" + "\n" +
                "---TEST 1B.a MASTERLOCK WITH A COMBO OUT OF THE RANGE---");
        l = new MasterLock(70,70,70);
        //shoulde be default
        l.unLock();
        assert(l.getNumberOfDigits() == 3);
        assert(l.getSerialNumber() == 2);
        assert(l.getCombo().equals("-1 -1 -1"));
        l.getCombo();
        assert(!l.isUnlocked());
        System.out.println(l.toString());
        System.out.println("-------TEST 1B.a PASSED------");
        System.out.println("---TEST 1B.b MASTERLOCK WITH A VAILD COMBO---");
        l = new MasterLock(8,7,9);
        //with a valid combo, test all methods
        l.unLock();
        assert(l.getNumberOfDigits() == 3);
        assert(l.getSerialNumber() == 3);
        assert(l.getCombo().equals("8 7 9"));
        l.getCombo();
        assert(!l.isUnlocked());
        //use all attempts
        l.unLock(4,5,6);
        l.unLock(2,3,4,7);
        l.unLock();
        assert(!l.isUnlocked());
        System.out.println(l.toString());
        l = new MasterLock(8,7,9);
        //renew
        l.unLock(8,7,9);
        assert(l.isUnlocked());
        l.lock();
        assert(!l.isUnlocked());
        l.unLock(8,7,9);
        System.out.println(l.toString());
        System.out.println("-------TEST 1B.b PASSED-------\n" + 
                "-------TEST 1B PASSED------\n" + 
                "--------TEST 1 PASSED----------\n");
        
                
        System.out.println("-----------TEST 2 DUDLYLOCK------------" + "\n" +
                "-------TEST 2A DEFAULT DUDLYLOCK------");        
        l = new DudleyLock();
        //default 
        l.unLock();
        assert(l.getNumberOfDigits() == 3);
        assert(l.getSerialNumber() == 5);
        assert(l.getCombo().equals("-1 -1 -1"));
        l.getCombo();
        assert(!l.isUnlocked());
        System.out.println(l.toString());
        System.out.println("---------TEST 1A PASSED---------");
        System.out.println("---------TEST 1B DUDLYLOCK WITH A COMBO---------" + "\n" +
                "---TEST 2B.a DUDLYLOCK WITH A COMBO OUT OF THE RANGE---");
        l = new DudleyLock(70,70,70);
        //default
        l.unLock();
        assert(l.getNumberOfDigits() == 3);
        assert(l.getSerialNumber() == 6);
        assert(l.getCombo().equals("-1 -1 -1"));
        l.getCombo();
        assert(!l.isUnlocked());
        System.out.println(l.toString());
        System.out.println("-------TEST 2B.a PASSED------");
        System.out.println("---TEST 2B.b DUDLYLOCK WITH A VAILD COMBO---");
        //valid combo
        l = new DudleyLock(8,7,9);
        l.unLock();
        assert(l.getNumberOfDigits() == 3);
        assert(l.getSerialNumber() == 7);
        assert(l.getCombo().equals("8 7 9"));
        l.getCombo();
        assert(!l.isUnlocked());
        l.unLock(4,5,6);
        l.unLock(2,3,4,7);
        l.unLock();
        assert(!l.isUnlocked());
        System.out.println(l.toString());
        //renew
        l = new DudleyLock(8,7,9);
        l.unLock(8,7,9);
        assert(l.isUnlocked());
        l.lock();
        assert(!l.isUnlocked());
        l.unLock(8,7,9);
        System.out.println(l.toString());
        System.out.println("-------TEST 2B.b PASSED-------\n" + 
                "-------TEST 2B PASSED------\n" + 
                "--------TEST 2 PASSED----------\n");
        
        System.out.println("---------TEST 3 MASTERULOCK----------\n" +
                "----------TEST 3A MASTERULOCK WITH A DEFAULT COMBO & CHANGE COMBO--------");
        l = new MasterULock();
        //default
        assert(l.getNumberOfDigits() == 4);
        assert(!l.isUnlocked());
        System.out.println(l.getSerialNumber());
        assert(l.getSerialNumber() == 9);
        assert(l.getCombo().equals("0 0 0 0 "));
        l.getCombo();
        l.unLock(9,99,999,9999,9);
        assert(!l.isUnlocked());
        ((MasterULock)l).configure(4,5,6,7);
        //unlock
        l.unLock(0,0,0,0);
        assert(l.isUnlocked());
        //change combo
        ((MasterULock)l).configure(4,5,6,7);
        assert(l.getCombo().equals("4 5 6 7 "));
        assert(l.isUnlocked());
        l.lock();
        assert(!l.isUnlocked());
        l.unLock(4,5,6,7);
        ((MasterULock)l).configure(10,12,12,13);
        System.out.println(l.toString());
        System.out.println("---------TEST 3A PASSED----------");
        System.out.println("---------TEST 3B INITIAL MASTERULOCK WITH A NOT VAILD COMBO------");
        l = new MasterULock(10,10,10,10);
        //should be default
        assert(l.getNumberOfDigits() == 4);
        assert(l.getCombo().equals("0 0 0 0 "));
        l.getCombo();
        assert(l.getSerialNumber() == 10);
        assert(!l.isUnlocked());
        l.unLock(0,0,0,0);
        assert(l.isUnlocked());
        assert(l.getCombo().equals("0 0 0 0 "));
        System.out.println(l.toString());
        System.out.println("-----------TEST 3B PASSED----------");
        System.out.println("----------TEST 3C INITIAL MASTERLOCK WITH A VAILD COMBO---------");
        //valid cobmo
        l = new MasterULock(4,5,6,7);
        assert(l.getCombo().equals("4 5 6 7 "));
        assert(l.getSerialNumber() == 11);
        assert(l.getNumberOfDigits() == 4);
        assert(!l.isUnlocked());
        l.unLock(4,5,6,7);
        assert(l.isUnlocked());
        assert(l.getCombo().equals("4 5 6 7 "));
        System.out.println(l.toString());
        System.out.println("-----------TEST 3C PASSED----------\n" +
                "------------TEST 3 PASSED----------\n");
        
        System.out.println("---------TEST 4 ANDROIDLOCK----------\n" +
                "----------TEST 4A ANDROIDLOCK WITH A DEFAULT STATUS & CHANGE COMBO & SET BACK TO DEFAULT--------");
        l = new AndroidLock();
        //default
        assert(l.isUnlocked());
        assert(l.getCombo().equals(""));
        assert(l.getNumberOfDigits() == 3);
        l.lock();
        assert(l.isUnlocked());
        assert(l.getSerialNumber() == 12);
        System.out.println(l.toString());
        //try to change combo
        ((AndroidLock)l).configure(10,12,12,13);
        ((AndroidLock)l).configure(12,12,13); 
        //combo changed
        ((AndroidLock)l).configure(2,2,3);
        l.lock();
        assert(!l.isUnlocked());
        assert(l.getCombo().equals("2 2 3 "));
        l.getCombo();
        System.out.println(l.toString());
        l.unLock(2,2,3);
        ((AndroidLock)l).setToDefault();
        assert(l.isUnlocked());       
        System.out.println("----------TEST 4A PASSED-------------");
        System.out.println("----------TEST 4B ANDROIDLOCK INITIALED WITH INVAILD COMBO--------");
        //default/not valid
        l = new AndroidLock(10,10,10);
        assert(l.isUnlocked());
        assert(l.getCombo().equals(""));
        assert(l.getNumberOfDigits() == 3);
        l.lock();
        assert(l.isUnlocked());
        assert(l.getSerialNumber() == 13);
        System.out.println(l.toString());
        System.out.println("------------TEST 4B PASSED------------\n" +
                "-------------TEST 4 PASSED-----------");
    }   
}
