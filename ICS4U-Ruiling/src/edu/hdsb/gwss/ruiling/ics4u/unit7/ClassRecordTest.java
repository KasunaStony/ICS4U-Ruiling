/*
 * Name: Ruiling Ma
 * Date: June 3, 2018
 * Version: 1.0
 * Description: Test record class
 */
package edu.hdsb.gwss.ruiling.ics4u.unit7;

/**
 *
 * @author maruiling
 */
import java.io.*;
public class ClassRecordTest {
    
    public static void main(String args[]) throws FileNotFoundException, IOException{
        //erase file
        PrintWriter writer = new PrintWriter("Record.txt");
        writer.print("");
        writer.close();
        //new transaction
        TransactionClassRecord t = new TransactionClassRecord(34.5, "AAA", "BBB", 89, 90);
        //random access file
        RandomAccessFile raf = new RandomAccessFile("Record.txt", "rw");
        //start
        raf.seek(0);
        //write all information
        raf.writeDouble(t.getAmount());
        raf.writeChars(t.getParticular());
        raf.writeChars(t.getCurrency());
        raf.writeInt(t.getEntryNumber());
        raf.writeInt(t.getPR());
        //a record
        TransactionClassRecord cRecord = new TransactionClassRecord();
        //get information
        raf.seek(0);
        cRecord.setAmount(raf.readDouble());
        char arrayOfChar[] = new char[cRecord.getPARTICULAR_SIZE()];
        for (int i = 0; i < arrayOfChar.length; i++) {
            arrayOfChar[i] = raf.readChar();
        }
        cRecord.setParticular(arrayOfChar);
        arrayOfChar = new char[cRecord.getCURRENCY_SIZE()];
        for (int i = 0; i < arrayOfChar.length; i++) {
            arrayOfChar[i] = raf.readChar();
        }
        cRecord.setCurrency(arrayOfChar);
        cRecord.setEntryNumber(raf.readInt());
        cRecord.setPR(raf.readInt());
        //compare
        assert(cRecord.equals(t));
    }
    
}
