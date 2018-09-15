/*
 * Name: Ruiling Ma
 * Date: Feb 07, 2018
 * Version: 1.0
 * Description: DistanceCalculator
 */
package edu.hdsb.gwss.ruiling.ics4u.unit1;

/**
 * Day 03 - DistanceCalculator
 * @author maruiling
 * @version 2018-02
 */
import java.util.*;

public class DistanceCalculator {
    
    public static void main(String args[]){
        //Scanner to read user input
        Scanner input = new Scanner(System.in);
        //message
        System.out.print("This program calculates the distance between two places on earth.\nIt requires entering the latitudes and longitudes. \nPlease select whether entering in degrees or radians(1 for degrees, 2 for radians): ");
        //read the selection of unit
        int selection = input.nextInt();
        //eliminate unvalued selection
        for (;;){
            if(selection == 1 || selection == 2)
                break;
            else{
                System.out.print("Please enter 1 or 2: ");
                selection = input.nextInt();
            }
        }
        
        //message for different unit
        if(selection == 1){            
            System.out.println("Entering in degrees. \nFor example: 43 degrees 40 minutes would be enter as 40.67. \nUse positive values for North latitudes and West longitudes.\nUse negative values for South latitudes and East longitudes. \n");
        }else{
            System.out.println("Entering in radians.\n");
        }
        //read the latitude and longitude of the first place
        System.out.print("Name of Place 1: ");
        String place1 = input.next();
        System.out.print("Latitude of " + place1 + ": ");
        double lat1 = input.nextDouble();
        System.out.print("Longitude of " + place1 + ": ");
        double log1 = input.nextDouble();
        System.out.println();
        //read the latitude and longitude of the second place
        System.out.print("Name of Place 2: ");
        String place2 = input.next();
        System.out.print("Latitude of " + place2 + ": ");
        double lat2 = input.nextDouble();
        System.out.print("Longitude of " + place2 + ": ");
        double log2 = input.nextDouble();
        System.out.println();
        //distance between two place
        double distance;
        //apply differnet formula for different unit
        if(selection == 1){     
            distance = 6378.8 * Math.acos(Math.sin(lat1/57.2958) * Math.sin(lat2/57.2958) + Math.cos(lat1/57.2958) * Math.cos(lat2/57.2958) * Math.cos(log2/57.2958 - log1/57.2958));
        }else{
            distance = 6378.8 * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(log2 - log1));
        }
        //print answer
        System.out.println("The distance between " + place1 + " and " + place2 + " is " + distance + "km");
            
        
    }
    
}
