import java.util.*;
import java.io.*;
import java.awt.*;

public class MapDataDrawer_Trawick
{

   private int[][] grid;

   public MapDataDrawer_Trawick(String filename, int rows, int cols){
      // initialize grid 
      grid = new int[rows][cols];
      
      //read the data from the file into the grid
      File dataFile = new File(filename);
      try {
         Scanner dataInput = new Scanner(dataFile);
         for (int i=0; i<rows; i++) {
            for (int j=0; j<cols;j++) {
               grid[i][j] = dataInput.nextInt();
            
            }
         }
         
      } catch (Exception e) { e.printStackTrace();}
   
   }
  
  /**
   * @return the min value in the entire grid
   */
   public int findMinValue() {
      int min = Integer.MAX_VALUE;
   
     // IMPLEMENT THE REST OF THIS METHOD
     /*  
     Using the smallestNumber Method from Lab8, I changed
     anywhere it uses the variable small and changed them to the variable min.
     I added another for loop because it was a multi-dimensional array and had the conditions of the first loop
     to my integer to be less than the grids first dimension and for the second loop to 
     have the integer to be less than the grids second dimension.
     I changed the array list with this.grid.
     
     */
      for (int i = 0; i < this.grid.length; i++) {
         for (int j = 0; j < this.grid[1].length; j++) {
            if ((this.grid[i][j] < min) && (this.grid[i][j] != 0)) {
               min = this.grid[i][j];
            }
         }
      }
   
            
      return min;  
   
   }
  /**
   * @return the max value in the entire grid
   */
   public int findMaxValue(){
      int max = Integer.MIN_VALUE;
     
     // I copied the method from findMinVAlue and changed the varible min to max and changing the opereator of the if condition to greater than.
      for (int i=0; i<this.grid.length; i++) {
         for (int j=0; j<this.grid[1].length; j++) {
            if ((this.grid[i][j] > max) && (this.grid[i][j] != 0)) {
               max = this.grid[i][j];
            }
         }
      }
   
      return max;  
   
   
   }
   
   /**
   * @param col the column of the grid to check
   * @return the index of the row with the lowest value in the given col for the grid
   */
   public  int indexOfMinInCol(int col){
     //Implement this method
      int lowest = 0;
      /* The use a loop to get the row and the col is given.
         if the current int in the cruurnt row is less than the int in the lowest 
         row then set lowest equal to  row
      */
      for (int row = 0; row < 479; row++) {
         if((grid[row][col] < grid[lowest][col])) {
            lowest = row;
            //System.out.println(row + "  " + grid[row][col] + "  " + grid[row + 1][col]);   
         }
            
      }
      //System.out.println(grid[lowest][col]);
      return lowest;
   
   }
  

  /**
   * Draws the grid using the given Graphics object.
   * Colors should be grayscale values 0-255, scaled based on min/max values in grid
   */
   public void drawMap(Graphics g){     
      int min = findMinValue();
      int max = findMaxValue();
   
     
      for (int i=0; i<480; i++) {
         for (int j=0; j<480; j++) {
            int c = (int) ((((double) grid[i][j] - (double) min) / ((double) max - (double)min)) * 255);// calculated grayscale value FILL THIS IN
            g.setColor(new Color(c, c, c));
            g.fillRect(j, i, 1, 1);
         }
      }
   }
   
   /**
   * Find a path from West-to-East starting at given row.
   * Choose a foward step out of 3 possible forward locations, using greedy method described in assignment.
   * @return the total change in elevation traveled from West-to-East
   */

   public int drawLowestElevPath(Graphics g, int row){
      int elevChange = 0;
         // Implement this method
      int r = row;
      int current = this.grid[r][0]; // the current position
      Random random = new Random();
         
      for(int i = 1; i < 480; i++) { 
         int top = Math.abs(current - this.grid[r - 1][i]); // the elevation change of the fwd up pos
         int mid = Math.abs(current - this.grid[r][i]); // the elevation change of the fwd pos
         int bottom = Math.abs(current - this.grid[r + 1][i]); // the elevation change of the fwd down pos  
            
               
               
               // This checks if the mid path is less than or equal to both the top and bottom path
         if((mid <= top) && (mid <= bottom)) {
            Math.abs(elevChange += mid);
            current = this.grid[r][i];
                  
         }
               //If top and bottom path are the same but less than mid path then gen. random num(0-1)
         else if((top == bottom) && (top < mid) && (bottom < mid)) { 
            int chance =  random.nextInt(1);// gen random num (0-1)
               
            if(chance == 1) { // if 0 go top path othherwise go down
               Math.abs(elevChange += top);
               r = --r;
               current = this.grid[r][i];
               
            }
            else { 
               Math.abs(elevChange += bottom);
               r = ++r; 
               current = this.grid[r][i];
               
            }
               
         }
            
               // This checks if the top path is less than or equal to both the mid and bottom path
         else if((top < mid) && (top < bottom)) {
            Math.abs(elevChange += top);
            r = --r;
            current = this.grid[r][i];
                  
         }
            
               // This checks if the bottm path is less than or equal to both the top and mid path
         else if((bottom < mid) && (bottom < top)) {
            Math.abs(elevChange += bottom);
            r = ++r; 
            current = this.grid[r][i]; 
         }
        
        
         
         g.setColor(new Color(255, 0, 0));
         g.fillRect(i, r, 1,1);
              //System.out.println( top + "  " + mid + "  " + bottom);    
      }
         
      return elevChange;
   
   
          
   }
   


   private int minOfThree(int a, int b, int c) {
      if ((a > b) && (a > c)) 
         return a;
      if ((b > a) && (b > c)) 
         return b;
      if ((c > a) && (c > b)) 
         return c;
      return 0;
   }
  
  
  
}