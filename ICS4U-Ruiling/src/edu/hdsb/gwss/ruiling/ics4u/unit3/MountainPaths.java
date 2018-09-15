/*
 * Name: Ruiling Ma
 * Date: Apr. 2, 2018
 * Version: 1.0
 * Description: Read a set of topographic (land elevation) data 
                into a 2D array and compute paths through the 
                mountains as well as visualize them.
 */
package edu.hdsb.gwss.ruiling.ics4u.unit3;

import java.awt.*;
import java.util.*;
import java.io.*;

public class MountainPaths {

    /**
     * Mount Paths
     */
    public static void main( String[] args ) throws Exception {

        // ***********************************
        // TASK 1:  read data into a 2D Array
        // 
        System.out.println( "TASK 1: READ DATA" );
        //read data
        int[][] data = read( "Colorado.844x480.dat" );

        // ***********************************
        // Construct DrawingPanel, and get its Graphics context
        //
        DrawingPanel panel = new DrawingPanel( data[0].length, data.length );
        Graphics g = panel.getGraphics();

        // ***********************************
        // TASK 2:  find HIGHEST & LOWEST elevation; for GRAY SCALE
        //
        System.out.println( "TASK 2: HIGHEST / LOWEST ELEVATION" );
        //find min data point
        int min = findMinValue( data );
        System.out.println( "\tMin: " + min );
        //find max data point
        int max = findMaxValue( data );
        System.out.println( "\tMax: " + max );

        // ***********************************
        // TASK 3:  Draw The Map
        //
        System.out.println( "TASK 3: DRAW MAP" );
        //draw map
        drawMap( g, data );

        // ***********************************
        // TASK 4A:  implement indexOfMinInCol
        //
        System.out.println( "TASK 4A: INDEX OF MIN IN COL 0" );
        //find the index of the min in col 0
        int minRow = indexOfMinInCol( data, 0 );
        System.out.println( "\tRow with lowest Col 0 Value: " + minRow );

        // ***********************************
        // TASK 4B:  use minRow as starting point to draw path
        //
        System.out.println( "TASK 4B: PATH from LOWEST STARTING ELEVATION" );
        //draw one path with color red, start from the min point at col 0
        g.setColor( Color.RED );
        //record the change of elevation on the road
        int totalChange = drawLowestElevPath( g, data, minRow, 0); //
        System.out.println( "\tLowest-Elevation-Change Path starting at row " + minRow + " gives total change of: " + totalChange );

        // ***********************************
        // TASK 5:  determine the BEST path
        //
        g.setColor( Color.RED );
        //find the best path 
        int bestRow = indexOfLowestElevPath( g, data );

        // ***********************************
        // TASK 6:  draw the best path
        //
        System.out.println( "TASK 6: DRAW BEST PATH" );
        //drawMap.drawMap(g); //use this to get rid of all red lines
        g.setColor( Color.GREEN ); //set brush to green for drawing best path
        totalChange = drawLowestElevPath( g, data, bestRow, 0 );//get the total change in the path
        System.out.println( "\tThe Lowest-Elevation-Change Path starts at row: " + bestRow + " and gives a total change of: " + totalChange );

    }

    /**
     * This method reads a 2D data set from the specified file. The Graphics'
     * industry standard is width by height (width x height), while programmers
     * use rows x cols / (height x width).
     *
     * @param fileName the name of the file
     * @return a 2D array (rows x cols) of the data from the file read
     */
    public static int[][] read( String fileName ) throws FileNotFoundException, IOException {
        //number of rows
        int row = 0;
        //bufferedreader to read file
        BufferedReader reader = new BufferedReader(new FileReader(new File("./TestData/mountain.paths/" + fileName)));
        //stringtokenzier 
        StringTokenizer st;
        //count the number of rows
        while((reader.readLine()) != null){         
            row ++;
        }
        //initial the data array
        int[][] data = new int[row][];
        //re-read the file
        reader = new BufferedReader(new FileReader(new File("./TestData/mountain.paths/" + fileName)));
        //count the number of data in one row
        for(int i = 0; i < row; i++){
            st = new StringTokenizer(reader.readLine());
            //count the number of points
            data[i] = new int[st.countTokens()];
        }
        //re-read the data
        reader = new BufferedReader(new FileReader(new File("./TestData/mountain.paths/" + fileName)));
        //read the data into the array
        for(int i = 0; i < data.length; i++){
            st = new StringTokenizer(reader.readLine());
            for(int j = 0; j < data[i].length; j++){
                //read data
                data[i][j] = Integer.parseInt(st.nextToken());               
            }
        }
        //return data array
        return data;
    }

    /**
     * @param grid a 2D array from which you want to find the smallest value
     * @return the smallest value in the given 2D array
     */
    public static int findMinValue( int[][] grid ) {
        //the min value, set it to be the first element in the grid
        int min = grid[0][0];
        //search the array to find the min value
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                //if min is larger than the current data point, change
                if(min > grid[i][j])
                    min = grid[i][j];
            }
        }
        //return the min value
        return min;

    }

    /**
     * @param grid a 2D array from which you want to find the largest value
     * @return the largest value in the given 2D array
     */
    public static int findMaxValue( int[][] grid ) {
        //the max value, set it to be the first element in the grid
        int max = grid[0][0];
        //search the array to find the max value
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                //if max is smaller than the current data point, change
                if(max < grid[i][j])
                    max = grid[i][j];
            }
        }
        //return the max value
        return max;

    }

    /**
     * Given a 2D array of elevation data create a image of size rows x cols,
     * drawing a 1x1 rectangle for each value in the array whose color is set to
     * a a scaled gray value (0-255). Note: to scale the values in the array to
     * 0-255 you must find the min and max values in the original data first.
     *
     * @param g a Graphics context to use
     * @param data a 2D array of the data
     */
    public static void drawMap( Graphics g, int[][] data ) {
        //the min point in the array
        int min = findMinValue(data);
        //calculate the gray scale
        double grayscale = 255.0/(findMaxValue(data) - findMinValue(data));
        //color
        int c;
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                //calculate the color of the point
                c = (int)((data[i][j] - min) * grayscale);
                //set color
                g.setColor(new Color(c,c,c));
                //draw the rectangle
                g.fillRect(j, i, 1, 1);
            }
        }
    }

    /**
     * Scan a single column of a 2D array and return the index of the row that
     * contains the smallest value
     *
     * @param grid a 2D array
     * @param col the column in the 2D array to process
     * @return the index of smallest value from grid at the given col
     */
    public static int indexOfMinInCol( int[][] grid, int col ) {
        //row index of the min point
        int index = 0;
        //search the given col
        for(int i = 0; i < grid.length; i++){
            //if the current data point is smaller than the index data point, change
            if(grid[i][col] < grid[index][col])
                index = i;
        }
        //return index
        return index;
    }

    /**
     * Find the minimum elevation-change route from West-to-East in the given
     * grid, from the given starting row, and draw it using the given graphics
     * context
     *
     * @param g - the graphics context to use
     * @param data - the 2D array of elevation values
     * @param row - the starting row for traversing to find the min path
     * @param col - the starting col for traversing to find the min path
     * @return total elevation of the route
     */
    public static int drawLowestElevPath( Graphics g, int[][] data, int row, int col) {
        //draw the rectangle
        g.fillRect(col, row, 1, 1);
        //if reach the last col
        if(col >= data[0].length - 1){
            //no elevation change if it is the last col, return 0
            return 0;
        }
        //if not at the first row or last row
        else if ((row - 1 >= 0)&&(row + 1) < 480){
            //upRightChange: the elevation change between current point and the up right point
            int upRightChange = Math.abs(data[row-1][col+1] - data[row][col]);
            //frontChange: the elevation change between current point and the front point
            int frontChange = Math.abs(data[row][col+1] - data[row][col]);
            //downRightChange: the elevation change between current point and the down right point
            int downRightChange = Math.abs(data[row+1][col+1] - data[row][col]);
            //always go front if the front change is smaller or equal to the other two changes
            if(frontChange <= upRightChange && frontChange <= downRightChange){
                //return the change in the elevation plus the change start at front point
                return frontChange + drawLowestElevPath(g, data, row, col+1);                
            }//if the front is not a opition, compare upRight and downRight
            //if upRight change is smaller than the down right change
            else if(upRightChange < downRightChange){
                //return the change in the elevation plus the change start at up right point
                return upRightChange + drawLowestElevPath(g, data, row-1, col+1);
            }
            //if downRight change is smaller than the upRight change
            else if(upRightChange > downRightChange){
                //return the change in the elevation plus the change start at down right point
                return downRightChange + drawLowestElevPath(g, data, row+1, col+1);
            }
            //if downRight change is equal to upRight change
            else{
                //randomly choose a point to go
                if(Math.random() * 2 < 1){
                    //return the change in elevation plus the change start at up right point
                    return upRightChange + drawLowestElevPath(g, data, row-1, col+1);
                }else{
                    //return the change in elevation plus the change start at down right point
                    return downRightChange + drawLowestElevPath(g, data, row+1, col+1);
                }                   
            }             
        }
        //if at the first row
        else if(row - 1 < 0){
            //frontChange: the elevation change between current point and the front point
            int frontChange = Math.abs(data[row][col+1] - data[row][col]);
            //downRightChange: the elevation change between current point and the down right point
            int downRightChange = Math.abs(data[row+1][col+1] - data[row][col]);
            //if front change is smaller or equal to down right change, go front
            if(frontChange <= downRightChange)
                //return the change in elevation plus the change starts from front point
                return frontChange + drawLowestElevPath(g, data, row, col+1);
            //else go down right
            else
                //return the change in elevation plus the change starts from down right point
                return downRightChange + drawLowestElevPath(g, data, row+1, col+1);
        }
        //if at the last row
        else{
            //upRightChange: the elevation change between current point and the up right point
            int upRightChange = Math.abs(data[row-1][col+1] - data[row][col]);
            //frontChange: the elevation change between current point and the front point
            int frontChange = Math.abs(data[row][col+1] - data[row][col]);
            //if front change is smaller or equal to up right change, go front
            if(frontChange <= upRightChange)
                //return the change in elevation plus the change starts from front point
                return frontChange + drawLowestElevPath(g, data, row, col+1);
            else
                //return the change in elevation plus the change starts from up right point
                return upRightChange + drawLowestElevPath(g, data, row-1, col+1);
        }
    }

    /**
     * Generate all west-to-east paths, find the one with the lowest total
     * elevation change, and return the index of the row that path starts on.
     *
     * @param g - the graphics context to use
     * @param data- the 2D array of elevation values
     * @return the index of the row where the lowest elevation-change path
     * starts.
     */
    public static int indexOfLowestElevPath( Graphics g, int[][] data ) {
        // the row index of the lowest elevation path, set to 0 at first
        int index = 0;
        //the current elevation, set to the first path to begin
        int currentElevation = drawLowestElevPath(g, data, 0, 0);
        //the lowest elevation, set to the first path to begin
        int  lowest = currentElevation;
        //run though the array to find the best path
        for(int i = 0; i < data.length; i ++){
            //set the current elevation
            currentElevation = drawLowestElevPath(g, data, i, 0);
            //if the lowest elevation is larger than the current, change
            if(lowest > currentElevation){
                lowest = currentElevation;
                //change index of the lowest elevation path
                index = i;
            }
        }
        //return index
        return index;
    }
}
