/*
 * Name: Ruiling Ma
 * Date: Apr 2 2018
 * Version: 1.0
 * Program Name: KochsSnowflake
 * Description: Draw Koch's Snowflake with a recursive method.
 */

void setup(){
  //set size
  size(800, 800);
  //white back ground
  background(255,255,255);
  //draw black line
  stroke(0,0,0);
  //draw the snow flake
  drawKochSnowflake();
}
/*
 * Draw the snow flake, start with a equilateral triangle
 */
void drawKochSnowflake(){
  //three lines of the equilateral triangle, recurve 6 times
  drawKochCurve(150, 150 * sqrt(3), 650, 150 * sqrt(3), 6);
  drawKochCurve(400, 400 * sqrt(3), 150, 150 * sqrt(3), 6);
  drawKochCurve(650, 150 * sqrt(3), 400, 400 * sqrt(3), 6);
}
/*
 * Draw Koch Curve with the given coord and recurve depth times
 */
void drawKochCurve(float x1, float y1, float x2, float y2,int depth){
  //draw the line
  line(x1, y1, x2, y2);
  //if recured for enough times, return
  if (depth <= 1)
    return;
  else {
    
    //calculate coords for the first point on the line
    float firstX = (x1 * 2  + x2)  / 3.0;
    float firstY = (y1 * 2  + y2) / 3.0; 
    //calculate coords for the second point on the line
    float secondX = (x1 + x2 * 2) / 3.0;
    float secondY = (y1 + y2 * 2) / 3.0;
    ////calculate coords for the third point out of the line
    float thirdX = (firstX + secondX) / 2 - (firstY - secondY) * (float)Math.sqrt(3) / 2;
    float thirdY = (firstY + secondY) / 2 - (secondX - firstX) * (float)Math.sqrt(3) / 2;
    
    //erase the middle part of the line
    erase(firstX, firstY, secondX, secondY);
    //reset the color
    stroke(0,0,0);
    //draw the first line segment
    drawKochCurve(x1, y1, firstX, firstY, depth-1);
    //draw the left side of the new triangle
    drawKochCurve(firstX, firstY, thirdX, thirdY, depth-1);
    //draw the second line segment
    drawKochCurve(secondX, secondY, x2, y2, depth-1);
    //draw the right side of the new triangle
    drawKochCurve(thirdX, thirdY, secondX, secondY, depth-1);
  }
}
/*
 * Erase the given line segemnt
 */
void erase(float x1, float y1, float x2, float y2){
  //set to no stroke
  noStroke();
  //set to white
  fill(255,255,255);
  //if the points do not has the same y-coord
  if(abs(y1 - y2) > 1){
    //draw a rectangle to erase the line
    beginShape();
    vertex(x1-3, y1);
    vertex(x1+3, y1);
    vertex(x2+3, y2);
    vertex(x2-3, y2);
    vertex(x1-3, y1);  
    endShape();
  }
  else{
    //draw a parallelogram to erase the line
    beginShape();
    vertex(x1, y1-3);
    vertex(x1, y1+3);
    vertex(x2, y2+3);
    vertex(x2, y2-3);
    vertex(x1, y1-3); 
    endShape();
  }
}