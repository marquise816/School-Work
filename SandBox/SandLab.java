import java.awt.*;
import java.util.*;

public class SandLab
{
  public static void main(String[] args)
  {
    SandLab lab = new SandLab(120, 80);
    lab.run();
  }
  
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;

  // task 4 -- Add a SAND particle type
  // task 6 -- Add a WATER particle type

  
  //do not add any more fields
  private int[][] grid;
  private SandDisplay display;
  
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    names = new String[4];    // will need to modify this for tasks 4, 6
    names[EMPTY] = "Eraser";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";

    // task 4 -- Add a SAND particle type
    // task 6 -- Add a WATER particle type
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
    
    // task 1
    // Insert code to initialize the grid field to refer to a 
    // 2-dimensional array of the same dimensions. 
    grid = new int[numRows][numCols];
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
    // task 2
    // Write code to store this value representing the given tool 
    // in the corresponding position of the grid array
    grid[row][col] = tool;

  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      for(int i = 0; i < grid.length; i++) {
         for(int j = 0; j < grid[i].length; j++) {
                  if(grid[i][j] == EMPTY) {
                     display.setColor(i, j, Color.BLACK);
                  }
                  else if(grid[i][j] == METAL) {
                     display.setColor(i, j, Color.WHITE);
                  }
                  else if(grid[i][j] == SAND) {
                     display.setColor(i, j, Color.YELLOW);
                  }
                  else if(grid[i][j] == WATER) {
                     display.setColor(i, j, Color.BLUE);
                  }
               
               
               
               }
        }

     }

  //called repeatedly.
  //causes one random particle to maybe do something.
  public void step()
  {
      Random r = new Random();
      int y = r.nextInt(grid.length);
      int x = r.nextInt(grid[0].length);
      int initial = grid[y][x];
      int dir = r.nextInt(2);

    // task 5
    // This method should choose a single random valid location. 
    // (Do not use a loop.) If that location contains a sand particle 
    // and the location below it is empty, the particle should move 
    // down one row. (Metal particles will never move.)
    if((grid[y][x] == SAND ||grid[y][x] == WATER) && (grid[y + 1][x] == EMPTY)) {
         //pick rndom direction if its water
             
      
         
         if(y < grid.length - 2) { // checks if the particle is close to boundary
            if(grid[y][x] == SAND) { //checks if its water or sand
               grid[y + 1][x] = SAND;
              }
             else{
               grid[y + 1][x] = WATER; 
             }
         }
         else if(y == grid.length - 1){ // determines if particle should disapper n makes it disapper
            grid[y + 1][x] = EMPTY;
            initial = EMPTY;
         }
         
         grid[y][x] = EMPTY;
      }
            // right particle
      else if((dir == 0) && (grid[y][x] == WATER) && ( grid[y][x - 1] == EMPTY) && (grid[y][x] < grid[0].length)) {
               if(x < grid[0].length - 1) { // checks if the particle is close to boundary
                  grid[y][x - 1] = WATER;
               }
               grid[y][x] = EMPTY; 
      
      }
      // left particle
      else if((dir == 1) && (grid[y][x] == WATER) && ( grid[y][x + 1] == EMPTY) && (grid[y][x] > -1)) {
               if(x < grid[0].length - 1) { // checks if the particle is close to boundary
                  grid[y][x + 1] = WATER;
               }
               grid[y][x] = EMPTY; 
      
      }
      //if below sand is water then the sand falls
      else if((grid[y][x] == SAND) && (grid[y + 1][x] == WATER)) {
         if(y < grid.length - 2) {
            grid[y + 1][x] = SAND;
            grid[y][x] = WATER;
         }
    }
    
    // task 6
    // Modify your program so that you can also paint with water particles, 
    // which move in one of three randomly chosen directions: down, left, or right.
    
  }
  
  //do not modify
  public void run()
  {
    while (true)
    {
      for (int i = 0; i < display.getSpeed(); i++)
        step();
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }
}
