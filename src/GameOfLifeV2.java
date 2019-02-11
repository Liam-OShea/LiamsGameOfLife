import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameOfLifeV2 {

    private static int xCells = 20;
    private static int yCells = 20;
    private static int gens = 100;
    private static int msSleep = 300;
    private static int grid [][] = new int [xCells][yCells];
    private static int alive [][] = new int [xCells][yCells];

    public static void main(String[] args) throws InterruptedException {
        populateGridRandom();
        displayGrid();

        for(int i = 0; i < gens; i++){
            updateAliveMatrix();
            doStarvation();
            doOverpopulation();
            doResurrection();

            System.out.println("\n ---------- \n");

            displayPrettyGrid();
            TimeUnit.MILLISECONDS.sleep(msSleep);
        }




    }


    //Game rules

    public static void doStarvation(){
        int i, j;

        for (j = 0; j < yCells; j++){
            for (i = 0; i < xCells; i++){
                if(alive[i][j] < 2){
                    grid[i][j] = 0;
                }
            }
        }
    }

    public static void doOverpopulation(){
        int i, j;

        for (j = 0; j < yCells; j++){
            for (i = 0; i < xCells; i++){
                if(alive[i][j] > 3){
                    grid[i][j] = 0;
                }
            }
        }
    }

    public static void doResurrection(){
        int i, j;

        for (j = 0; j < yCells; j++){
            for (i = 0; i < xCells; i++){
                if(grid[i][j] == 0 && alive[i][j] == 3){
                    grid[i][j] = 1;
                }
            }
        }
    }

    //Cell Methods

    //This method gets number of alive neighbors from 8 adjacent cells

    public static int adjAlive(int xPos, int yPos){
        int alive = 0;
        int i, j, finX, finY;

        for (j = -1; j < 2; j++){
            for (i = -1; i < 2; i++){
                finX = (xPos + i + xCells) % xCells;
                finY = (yPos + j + yCells) % yCells;
                alive += grid[finX][finY];
            }
        }

        alive -= grid[xPos][yPos];

        return alive;
    }

    public static void updateAliveMatrix(){
        int i, j;

        for (j = 0; j < yCells; j++){
            for (i = 0; i < xCells; i++){
                alive[i][j] = adjAlive(i, j);
            }
        }
    }

    public static void displayAliveMatrix(){
        int i, j;

        for (j = 0; j < yCells; j++){
            for (i = 0; i < xCells; i++){
                System.out.print(alive[i][j] + " ");
            }
            System.out.println();
        }
    }






    //Grid Methods

    public static void populateGridAscending(){
        int n = 0;
        int i, j;

        for (j = 0; j < yCells; j++){
            for (i = 0; i < xCells; i++){
                grid[i][j] = n;
                n++;
            }
        }
    }

    public static void populateGridRandom(){
        Random rand = new Random();
        int i, j;

        for (j = 0; j < yCells; j++){
            for (i = 0; i < xCells; i++){
                grid[i][j] = rand.nextInt(2);
            }
        }
    }

    public static void displayGrid(){
        int i, j;

        for (j = 0; j < yCells; j++){
            for (i = 0; i < xCells; i++){
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void displayPrettyGrid() {
        int i, j;

        for (j = 0; j < yCells; j++) {
            for (i = 0; i < xCells; i++) {
                if (grid[i][j] == 1) {
                    System.out.print("@ ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

}
