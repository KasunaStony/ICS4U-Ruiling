/*
 * Name: Ruiling Ma
 * Date: Mar 5 2018
 * Version: 1.0
 * Program Name: Merica
 * Description: Draw a USA map and states that predominantly vote for 
 *              the Republican presidential candidate (red) or the 
 *              Democratic presidential candidate (blue).
 */
 
import java.util.Scanner;
import java.util.StringTokenizer;
//A String to store all the election result files
final static String[] FILE_NAMES_ELECTIONS = {
  "USA1960.txt", "USA1964.txt", "USA1968.txt", "USA1972.txt", "USA1976.txt", 
  "USA1980.txt", "USA1984.txt", "USA1988.txt", "USA1992.txt", "USA1996.txt", 
  "USA2000.txt", "USA2004.txt", "USA2008.txt", "USA2012.txt", "USA2016.txt"
};

//Name of the current election result file
String fileName;
//An array to store election results
String[][] electionResults;
//Store minimum and maximum longitude and latitude values
double maxLog, minLog, maxLat, minLat;
//A 3D array to store the coordinates of each subregion; each 2D array stores the data of one subregion
Float[][][] coord;
//Store the name of each subregion 
String[] nameOfSubregions;
//The number of states
int numberOfStates;

/*
 * SETUP; CALLED ONCE @ STARTUP
 */
void setup() {
  //set the size of the window
  size( 1200, 600 );
  //draw white lines and points
  stroke(255,255,255);
  //white background
  background(255,255,255);
  // DEFAULT MAP
  fileName = FILE_NAMES_ELECTIONS[0];
  // ELECTION RESULTS
  electionResult();
  //read the coordinates file
  readMapData(); 
}

/*
 * ELECTION RESULTS
 * - parse election file INTO electionResults[][] 2D-Array, or data structure of your choice.
 */
void electionResult( ) {
  try {
    // ELECTION RESULT DATA
    Scanner data = new Scanner( new File( dataPath("") + "/" + fileName ) );
    //initialize numberOfStates as 0
    numberOfStates = 0;
    //skip the first line as a description of the file
    data.nextLine();
    //count the number of lines in the file, which is equal to the number of states
    while(data.hasNextLine()){
      numberOfStates++;
      //read the line
      data.nextLine();
    }
    //re-read the file
    data = new Scanner( new File( dataPath("") + "/" + fileName ) );
    //skip the first line
    data.nextLine();
    //initialize the election results array with each row represent a state
    electionResults = new String[numberOfStates][4];  
    //a StringTokenizer to be used in the loop
    StringTokenizer st;
    //a loop to initialize electionResults
    for(int row = 0; row < numberOfStates; row++){
      //initialize StringTokenizer to tokenize each line by comma
      st = new StringTokenizer(data.nextLine(), ",");
      for(int col = 0; col < electionResults[row].length; col++){
        //read the election results into the array
        electionResults[row][col] = st.nextToken();
      }      
    }
    //close scanner
    data.close();    
  }
  catch (Exception e ) {
    e.printStackTrace();
  }  
}

/*
 * draw method to continually draw the state
 */
void draw(){
    drawState();
}

/*
 * read the coordinates of the map
 */
void readMapData() {
  // MAP
  try {
    // USA MAP DATA
    Scanner data = new Scanner( new File( dataPath("") + "/USA.txt" ) );
    //StringTokenizer to read the max and min coordinates
    StringTokenizer st = new StringTokenizer(data.nextLine());
    //min coordinates
    minLog = Double.parseDouble(st.nextToken());
    minLat = Double.parseDouble(st.nextToken());
    //read next line
    st = new StringTokenizer(data.nextLine());
    //max coordinates
    maxLog = Double.parseDouble(st.nextToken());
    maxLat = Double.parseDouble(st.nextToken());
    //read the number of subregions
    int numberOfSubregions = data.nextInt();
    //skip the end of this line
    data.nextLine();
    //skip the blank line
    data.nextLine();
    //initialize coord array by numberOfSubregions(104) 2D arraies
    coord  = new Float[numberOfSubregions][][];
    //initialize nameOfSubregions array
    nameOfSubregions = new String[numberOfSubregions];
    //number of coordinates
    int numberOfCoord;
    //read data for each region
    for(int i = 0; i < numberOfSubregions; i++){
      //read the name of the subregion
      nameOfSubregions[i] = data.nextLine();
      //skip the line "USA"
      data.nextLine();
      //read the number of coordinates
      numberOfCoord = Integer.parseInt(data.nextLine());
      //initial coord array
      coord[i] = new Float[numberOfCoord][2];
      //read each coordinate
      for(int j = 0; j < numberOfCoord; j++){
        //a new stringtokenizer to sepeate log and lat
        st = new StringTokenizer(data.nextLine());
        for(int a = 0; a < 2; a++){
          //read each log and lat
          coord[i][j][a] = Float.parseFloat(st.nextToken());
        }     
      }//skip the blank line
      data.nextLine();
    }      
    data.close();
  }
  catch (Exception e ) {
    e.printStackTrace();
  }
}

/*
 * Draw State
 */
void drawState( ) {
  //the strech in length
  int lengthStrech = (int)(1200/abs((float)(minLog - maxLog)));
  //the strech in width
  int widthStrech = (int)(600/abs((float)(minLat - maxLat)));
  //color for each subregion
  String[][] Color = new String[numberOfStates][4];
  //the RGB value
  double R, G, B;
  for(int i = 0; i < numberOfStates; i++){
    //R is the red portion, first election result
    R = Double.parseDouble(electionResults[i][1])/(Double.parseDouble(electionResults[i][1])+Double.parseDouble(electionResults[i][2])+
    Double.parseDouble(electionResults[i][3]))* 255;
    //B is the blue portion, second election result
    B = Double.parseDouble(electionResults[i][2])/(Double.parseDouble(electionResults[i][1])+Double.parseDouble(electionResults[i][2])+
    Double.parseDouble(electionResults[i][3]))* 255;
    //green portion, third election result
    G = Double.parseDouble(electionResults[i][3])/(Double.parseDouble(electionResults[i][1])+Double.parseDouble(electionResults[i][2])+
    Double.parseDouble(electionResults[i][3])) * 255;
    //copy the name of states and the color
    Color[i][0] = electionResults[i][0];
    Color[i][1] = String.valueOf(R);
    Color[i][2] = String.valueOf(G);
    Color[i][3] = String.valueOf(B);
  }
  //draw each state
  for(int i = 0; i < 104; i++){
      beginShape();
      //draw each point
      for(int j = 0; j < coord[i].length;j++){
        vertex((((coord[i][j][0])-(float)(minLog))*lengthStrech) , (((float)(maxLat)-(coord[i][j][1]))*widthStrech));     
      }
      //if the name of subregion match the name of state, color it
      for(int c = 0; c < numberOfStates; c++){
          if(nameOfSubregions[i].equalsIgnoreCase(Color[c][0])){
            fill(round(Float.parseFloat(Color[c][1])), round(Float.parseFloat(Color[c][2])),round(Float.parseFloat(Color[c][3])));
          }
        }     
      endShape();      
    }
}

/*
 * Detect the pressed key
 */
void keyPressed() {
  if ( key >= 'a' && key <= ( 'a' + FILE_NAMES_ELECTIONS.length - 1 )  ) {
    fileName = FILE_NAMES_ELECTIONS[key-'a'];
    println( fileName );
    electionResult();
  }
}