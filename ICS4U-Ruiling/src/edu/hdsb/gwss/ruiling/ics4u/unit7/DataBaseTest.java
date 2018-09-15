/*
 * Name: Ruiling Ma
 * Date: June 3, 2018
 * Version: 1.0
 * Description: Data base test
 */
package edu.hdsb.gwss.ruiling.ics4u.unit7;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author maruiling
 */
import java.io.*;

public class DataBaseTest {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        //erase the file before begin
        PrintWriter writer = new PrintWriter("class_info.txt");
        writer.print("");
        writer.close();
        //new data base
        TransactionDataBase tDB = new TransactionDataBase();
        //new class record
        TransactionClassRecord tCR;
        //open the empty file
        tDB.open("class_info.txt");
        //put 100 records
        System.out.println("---------TEST SAVING NEW RECORDS--------");
        for (int i = 0; i < 100; i++) {
            tCR = new TransactionClassRecord(i, String.valueOf((char) (i + 65)), String.valueOf((char) (i + 97)), i, i, i + 1);
            //empty record, return the new record
            assert((tDB.save(tCR)).equals(tCR));
        }
        System.out.println("---------SAVING NEW RECORDS COMPLETED--------");

        //test get method
        System.out.println("-----------TEST GET METHOD-------------");
        for (int i = 0; i < 100; i++) {
            tCR = new TransactionClassRecord(i, String.valueOf((char) (i + 65)), String.valueOf((char) (i + 97)), i, i, i + 1);
            assert (tDB.get(i + 1).equals(tCR));
        }
        System.out.println("-----------TEST PASSED-------------");
        
        //test overwrite record
        System.out.println("-----------TEST OVERWRITE RECORDS-------------");
        //old records
        TransactionClassRecord old;
        for (int i = 0; i < 100; i++) {
            //old record and new record
            old = new TransactionClassRecord(i, String.valueOf((char) (i + 65)), String.valueOf((char) (i + 97)), i, i, i + 1);
            tCR = new TransactionClassRecord(i, "AAA", "BBB", i + 3, i + 3, i + 1);
            //compare
            assert (tDB.save(tCR)).equals(old);
            assert (tDB.get(i + 1).equals(tCR));
        }
        System.out.println("--------------TEST PASSED--------------");
        //test invalid data base id
        System.out.println("---------TEST CHANGING DATA BASE ID--------");
        for (int i = 0; i < 100; i++) {
            //record with invalid data base id
            tCR = new TransactionClassRecord(i, String.valueOf((char) (i + 65)), String.valueOf((char) (i + 97)), i, i, (int)(Math.random() * 100000 + 1));
            //save record
            tDB.save(tCR);
            //compare
            assert(tDB.get(i + 1).getDbID() == i+1);
        }
        System.out.println("---------TEST PASSED-----------");
        
        //test any invalid input
        System.out.println("----------TEST INVALID INPUT------------"); 
        //invalid record
        tCR = new TransactionClassRecord();
        assert(tDB.save(tCR).equals(tCR));
        //out of range 
        assert(tDB.get(10000000) == null);
        assert(tDB.get(0) == null);
        //close file
        tDB.close();
        //try to save and get
        assert(tDB.save(tCR) == null);
        assert(tDB.get(2) == null);
        System.out.println("----------TEST PASSED------------"); 

    }

}
