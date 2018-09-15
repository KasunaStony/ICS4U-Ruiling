/*
 * Name: Ruiling Ma
 * Date: Feb 07, 2018
 * Version: 1.0
 * Description: EasterComes
 */
package edu.hdsb.gwss.ruiling.ics4u.unit1;

/**
 * Day 03 - EasterComes
 * @author maruiling
 * @version 2018-02
 */
import java.util.*;

public class EasterComes {
    
    public static void main(String args[]){
        
        //new Scanner to read user input
        Scanner input = new Scanner(System.in);
        //message
        System.out.println("This is a program that helps determine the exact date of Easter in a year. \n");
        
        System.out.print("Please enter the year: ");
        //read user input
        int year = input.nextInt();
        
        System.out.println();
        //apply the procedure
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8)/25;
        int g = (b - f + 1)/3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int j = (32 + 2*e + 2*i - h - k) % 7;
        int m = (a + 11*h + 22*j)/451;
        //the number of month
        int month = (h + j - 7*m + 114)/31;
        
        int p = (h + j - 7*m + 114) % 31;
        //the date
        int day = p + 1;
        //name of the month
        String monthString;
        //determine the name of the month
        switch (month) {
            case 1:  monthString = "January";
                     break;
            case 2:  monthString = "February";
                     break;
            case 3:  monthString = "March";
                     break;
            case 4:  monthString = "April";
                     break;
            case 5:  monthString = "May";
                     break;
            case 6:  monthString = "June";
                     break;
            case 7:  monthString = "July";
                     break;
            case 8:  monthString = "August";
                     break;
            case 9:  monthString = "September";
                     break;
            case 10: monthString = "October";
                     break;
            case 11: monthString = "November";
                     break;
            case 12: monthString = "December";
                     break;
            default: monthString = "Invalid month";
                     break;
        }
        //print the information
        System.out.println("The Easter for " + year + " is on " + monthString + " " + day + "th");
        
        
        

        
    }
    
}
