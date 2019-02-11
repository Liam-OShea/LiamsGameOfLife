import java.util.Random;
import java.util.concurrent.TimeUnit;
//TODO
/*
Problem lies in that the alive neighbors arent being detected properly. x and y axis issue possibly?
 */


public class SimpleVersion {

    private static int ROWS = 10, COLS = 15;
    private static int POP_FACTOR = 18; //pop factor from 1 - 20
                                        //1 -> more live cells
                                        //20 -> fewer live cells

    private static int SLEEP_MS = 250;  //Delay between each board update in milliseconds
    private static int NUM_ITERS = 300;

    //Create a board of cells, 1 is alive, 0 is dead
    static int grid [][] = new int[COLS][ROWS];
    //Identical sized grid to store number of alive neighbors for each cell
    static int aliveGrid [][] = new int [COLS][ROWS];


    public static void main(String[] args) throws InterruptedException {

        populateGrid(); //Initially populate grid

        //Simulation loop
        for(int i = 0; i < NUM_ITERS; i++) {


            updateAliveMatrix();
            doStarvation();
            displayGrid();
            displayAliveMatrix();
            doOverpopulation();
            doResurrection();
            //displayGrid();
            displayPrettyGrid();
            System.out.println("----------------");
            TimeUnit.MILLISECONDS.sleep(SLEEP_MS);





        }
    }

    public static void test(){

        populateGrid();
        updateAliveMatrix();

        //Print

        System.out.println("Cell Grid");
        displayGrid();
        System.out.println("\n\nAlive Neighbors Grid");
        displayAliveMatrix();

    }

    // ******** Rules ******************************
    /*
    Starvation          (dies if < 2 live neighbors)
    OverPopulation      (dies if > 3 live neighbors)
    Live                (lives, no change, 2 or 3 neighbors)
    Resurrect           (comes back to life if dead and exactly 3 live neighbors)
     */

    public static void doStarvation(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
               if(aliveGrid[i][j] < 2){grid[i][j] = 0;}
            }
        }
    }

    public static void doOverpopulation(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(aliveGrid[i][j] > 3){grid[i][j] = 0;}
            }
        }
    }

    public static void doResurrection(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(grid[i][j] == 0) {
                    if (aliveGrid[i][j] == 3 | aliveGrid[i][j] == 2) {
                        grid[i][j] = 1;}
                }
            }
        }
    }



    // ******** Creating and updating board ********

    public static void displayAliveMatrix(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){

                if(aliveGrid[i][j] == 0){
                    System.out.print("  ");
                } else
                    System.out.print(aliveGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Updates matrix representing number of neighbors each cell has
    public static void updateAliveMatrix(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){aliveGrid[i][j] = getNeighbors(i, j);}
        }
    }

    //This method gets the number of live neighbors from the cell at position (x, y)

    public static int getNeighbors(int xPos, int yPos){
        int neighbors = 0;
        int num;
        int ij = grid[xPos][yPos];
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                num = grid[(yPos + i + COLS) % COLS][(xPos + j + ROWS) % ROWS];
                neighbors += num;
            }
        }
        neighbors -= grid[yPos][xPos];

        return neighbors;
    }

    /* public static int getLiveNeighbors(int xpos, int ypos){
        int alive = 0;


        //Need to find number of cells adjacent to our cell that are alive

        for (int i = -1; i < 2; i++){
            for (int j = -1; j<2 ; j++){

                if(grid[(xpos + i + COLS) % COLS][(ypos + j + ROWS) % ROWS] == 1){
                    alive ++;
                }

                if (grid[xpos][ypos] == 1){
                    alive --;
                }



            }
        }
*/

       /* //Get status from three cells above
        for(int i = -1; i <= 1; i++){

            finX = xpos + i;
            finY = ypos - 1;

            //Wraparound conditions
            if(finX > ROWS -1){
                finX = finX - ROWS;
            }
            if(finX < 0){
                finX = ROWS + finX;
            }
            if(finY < 0){
                finY = COLS + finY;
            }


            //Because alive means cell is just 1, we can simply add it to the count
            alive = alive + grid[(finX)][finY];
        }

        //Three cells below (origin at top left)
        for(int i = -1; i <= 1; i++){

            finX = xpos + i;
            finY = ypos + 1;
            //wraparound conditions
            if(finX > ROWS -1){
                finX = finX - ROWS;
            }
            if(finX < 0){
                finX = ROWS - finX;
            }
            if(finY > COLS - 1){
                finY = finY - COLS;
            }
        }*/

//        //left and right cell
//        finX = xpos - 1;
//        finY = ypos;
//        if(finX < 0){finX = ROWS + finX;}
//        alive += grid[finX][finY];
//
//        finX = xpos + 1;
//        if(finX > ROWS -1){
//            finX = finX - ROWS;
//        }
//
//        alive += grid[finX][finY];


    public static void populateGrid (){
       Random rand = new Random();

       for(int i = 0; i < ROWS; i++){
           for(int j = 0; j < COLS; j++){
               if(rand.nextInt(20) > POP_FACTOR){
                   grid[i][j] = 1;
               } else{
                   grid[i][j] = 0;
               }
           }
       }

    }

    public static void displayGrid(){

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(grid[i][j] == 1) {
                    System.out.print("@ ");
                } else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void displayPrettyGrid(){
        String prettyGrid[][] = new String [ROWS][COLS];

        //Convert into nicer grid that is easier to see
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(grid[i][j] == 1){prettyGrid[i][j] = "@";}
                else if(grid[i][j] == 0){prettyGrid[i][j] = " ";}
            }
        }

        //Print pretty grid
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                System.out.print(prettyGrid[i][j]);
            }
            System.out.println();
        }
    }
}
