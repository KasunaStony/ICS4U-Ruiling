/*
 * Name: Ruiling Ma
 * Date: June 3, 2018
 * Version: 1.0
 * Description: Class record for transaction
 */
package edu.hdsb.gwss.ruiling.ics4u.unit7;

import java.util.Objects;

/**
 *
 * @author maruiling
 */
public class TransactionClassRecord {
    
    //private variable
    private double amount;
    private String particular;
    private String currency;
    private int entryNumber;
    private int PR;
    //the id in the data base
    private long dbID;
    
    //record size
    protected final int RECORD_SIZE = 62;
    private final int PARTICULAR_SIZE = 20;
    private final int CURRENCY_SIZE = 3;
    
    /**
     * Default Constructor.
     */
    public TransactionClassRecord() {
        
        this.amount = 0;
        this.setParticular("");
        this.setCurrency("");
        this.entryNumber = -1;
        this.PR = -1;
        this.dbID = -1;       
    }
    /**
     * Constructor.
     * @param amount
     * @param particular
     * @param currency
     * @param entryNumber
     * @param PR
     */
    public TransactionClassRecord(double amount, String particular, String currency, int entryNumber, int PR) {
        this.amount = amount;
        this.setParticular(particular);
        this.setCurrency(currency);
        this.entryNumber = entryNumber;
        this.PR = PR;
        this.dbID = -1;
    }
    
    
    
    /**
     * Constructor.
     * @param amount
     * @param particular
     * @param currency
     * @param entryNumber
     * @param PR
     * @param id 
     */
    public TransactionClassRecord(double amount, String particular, String currency, int entryNumber, int PR, int id) {
        //set everything
        this(amount, particular, currency, entryNumber, PR);
        this.dbID = id;
    }
    //getters and setters
    public double getAmount() {
        return amount;
    }

    public long getDbID() {
        return dbID;
    }

    public int getPARTICULAR_SIZE() {
        return PARTICULAR_SIZE;
    }

    public void setDbID(long dbID) {
        this.dbID = dbID;
    }

    public int getCURRENCY_SIZE() {
        return CURRENCY_SIZE;
    }

    public String getParticular() {
        return particular;
    }

    public String getCurrency() {
        return currency;
    }

    public int getEntryNumber() {
        return entryNumber;
    }

    public int getPR() {
        return PR;
    }

    public int getRECORD_SIZE() {
        return RECORD_SIZE;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    //particular setter with a string
    public final void setParticular(String particular) {
        //append empty characters
        StringBuilder temp = new StringBuilder();
        if (particular != null) {
            temp.append(particular.trim());
        } else {
            temp.append("TBD");
        }
        // trucates or pads the string
        temp.setLength(PARTICULAR_SIZE);
        this.particular = temp.toString();

    }
    //set particular with a array of chars
    public void setParticular(char[] data) {
        
        if (data.length != 0) {
            String str = "";
            for (int i = 0; i < data.length; i++) {
                str += data[i];
            }

            this.particular = str;
        }else{
            this.setParticular("");
        }
    }
    
    //set currency with an array of chars
    public void setCurrency(char[] data) {

        if (data.length != 0) {
            String str = "";
            for (int i = 0; i < data.length; i++) {
                str += data[i];
            }

            this.currency = str;
        }else{
            this.setCurrency("");
        }
    }
    
    //set currency with a string
    public final void setCurrency(String currency) {
        StringBuilder temp = new StringBuilder();

        if (currency != null) {
            temp.append(currency.trim());
        } else {
            temp.append("*");
        }

        // trucates or pads the string
        temp.setLength(CURRENCY_SIZE);
        this.currency = temp.toString();
    }
    
    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }

    public void setPR(int PR) {
        this.PR = PR;
    }
    
    /**
     * Override toString
     * @return the information for the transaction.
     */
    @Override
    public String toString(){
        if(this == null){
            return "Empty transaction. ";
        }
        return"amount: " + this.amount + "\n" + "pa: " + this.particular + "\ncurrecy: " + this.currency + "\nEN: " + this.entryNumber + "\nPR: " + this.PR;
    }
    
    /**
     * Override equals
     * @param obj the object to be compare
     * @return true if the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransactionClassRecord other = (TransactionClassRecord) obj;
        if (Double.doubleToLongBits(this.amount) != Double.doubleToLongBits(other.amount)) {
            System.out.println("amount");
            return false;
        }
        if (this.entryNumber != other.entryNumber) {
            System.out.println("EN");
            return false;
        }
        if (this.PR != other.PR) {
            System.out.println("pr");
            return false;
        }
        if (!Objects.equals(this.particular, other.particular)) {
            System.out.println("pa");
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            System.out.println("curr");
            return false;
        }
        return true;
    }
    
    /**
     * Check if the transaction is a valid one.
     * @return true if it has valid entry number and PR, false otherwise.
     */
    public boolean isValid(){      
        return (this.entryNumber >=0 && this.PR >= 0);
    }

}
