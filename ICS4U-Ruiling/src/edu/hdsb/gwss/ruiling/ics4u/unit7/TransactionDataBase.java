/*
 * Name: Ruiling Ma
 * Date: June 3, 2018
 * Version: 1.0
 * Description: Data base for transaction records
 */
package edu.hdsb.gwss.ruiling.ics4u.unit7;

/**
 *
 * @author maruiling
 */
import java.io.*;

public class TransactionDataBase {
    //random access file
    private RandomAccessFile data;
    /**
     * Default constructor.
     */
    public TransactionDataBase() {

    }
    /**
     * Open a random access file
     * @param file the file name
     * @throws FileNotFoundException 
     */
    public void open(String file) throws FileNotFoundException {
        data = new RandomAccessFile(file, "rw");
    }
    /**
     * Close the random access file
     * @throws IOException 
     */
    public void close() throws IOException {
        data.close();
        data = null;      
    }
    /**
     * Save a new record, if the data base id is new or not in the range, 
     * add to the end of the file, return the new record, if id is in the record, 
     * overwrite the current record, return the old record. 
     * @param t the new record to be saved
     * @return the new record if add at end, old record if overwrite
     * @throws IOException 
     */
    public TransactionClassRecord save(TransactionClassRecord t) throws IOException {
        
        //if the file is null
        if (data == null) {
            System.out.println("Please open the file first. ");
            return null;
        }
        //if the record is invalid
        if(!t.isValid()){
            System.out.println("Not a valid record. ");
            return t;
        }
        //if the data base id is in the range
        if(data.length()/t.getRECORD_SIZE() >= t.getDbID()){
            //seek to the begining of the record
            data.seek((t.getDbID() - 1) * t.RECORD_SIZE);
        }else{
            //if the id is -1 or too big, seek to the end of the file
            data.seek(data.length());
            //set the new id
            t.setDbID(data.length()/t.getRECORD_SIZE() + 1);
        }
        
        //attemp to get the old record
        TransactionClassRecord old = this.get(t.getDbID());
        //return to the current position
        data.seek((t.getDbID() - 1) * t.RECORD_SIZE);
        //write new record
        data.writeDouble(t.getAmount());
        data.writeChars(t.getParticular());
        data.writeChars(t.getCurrency());
        data.writeInt(t.getEntryNumber());
        data.writeInt(t.getPR());
        
        //if old is not null
        if (old != null) {
            //return the old record
            return old;
        } else {
            //no old, return new record
            return t;
        }
    }
    /**
     * Get the record at the id
     * @param dbID the data base id
     * @return the record at this id
     * @throws IOException 
     */
    public TransactionClassRecord get(long dbID) throws IOException {
        //the class record
        TransactionClassRecord cRecord = new TransactionClassRecord(0, "", "", 0, 0, -1);
        //if the file is null
        if (data == null) {
            System.out.println("Please open the file first. ");
            return null;
        }
        //if out of the range
        if ((dbID - 1) * cRecord.RECORD_SIZE >= data.length() || dbID - 1 < 0) {
            System.out.println("No such id");
            return null;
        }
        
        //seek to the id
        data.seek((dbID - 1) * cRecord.RECORD_SIZE);
        //read the record
        cRecord.setAmount(data.readDouble());
        char arrayOfChar[] = new char[cRecord.getPARTICULAR_SIZE()];
        for (int i = 0; i < arrayOfChar.length; i++) {
            arrayOfChar[i] = data.readChar();
        }
        cRecord.setParticular(arrayOfChar);
        arrayOfChar = new char[cRecord.getCURRENCY_SIZE()];
        for (int i = 0; i < arrayOfChar.length; i++) {
            arrayOfChar[i] = data.readChar();
        }
        cRecord.setCurrency(arrayOfChar);
        cRecord.setEntryNumber(data.readInt());
        cRecord.setPR(data.readInt());
        
        cRecord.setDbID(dbID);
        //return the record
        return cRecord;

    }
    /**
     * Get a string of every records in the data base.
     * @return information of records
     * @throws IOException 
     */
    public String showData() throws IOException {
        
        String str = "";
        for (int i = 1; i <= data.length() / 62; i++) {
            str += this.get(i).toString() + "\n" + "----------\n";

        }
        return str;
    }
}
