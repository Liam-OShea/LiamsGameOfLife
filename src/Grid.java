import java.util.Random;

public class Grid {

    private final int HGRIDSIZE = 100, VGRIDSIZE = 100;
    private Cell world[][];
    private int livingNeighbors[][];

    //Constructor
    public Grid() {
        world = new Cell[HGRIDSIZE][VGRIDSIZE];
        livingNeighbors = new int[HGRIDSIZE][VGRIDSIZE];
    }

    /*  TODO
        randomlyPopulateGrid
        numAdjacentAlive        DONE
     */

    public int getHSize(){return HGRIDSIZE;}
    public int getVSize(){return VGRIDSIZE;}




    public void populateGrid(){
        Random rand = new Random();
        int num;

        //Sets a rough percentage of random cells to be alive. Change number for more or less cells.
        for (int i = 0; i < HGRIDSIZE; i++){
            for(int j = 0; j < VGRIDSIZE; j++){
                num = rand.nextInt(10);
                if(num > 6){world[i][j].setAlive(1);}
            }
        }

    }

    public int getLivingNeighborsFromArray(int x, int y){
        return livingNeighbors[x][y];
    }


    public int numAdjacentAlive(Cell cell){
        int alive = 0;
        int x, y;

        //***Need to find number of cells adjacent to our cell that are alive

        //Get coords from cell
        x = cell.getX();
        y = cell.getY();

        //Get status from cells above

        for(int i = -1; i <= 1; i++){
            alive = alive + world[(x+i) % HGRIDSIZE][(y-1) % VGRIDSIZE].isAlive();
        }

        //Cells below (origin at top left)
        for(int i = -1; i <= 1; i++){
            alive += world[(x+i) % HGRIDSIZE][(y+1) % VGRIDSIZE].isAlive();
        }

        //left and right cell
        alive += world[(x-1) % HGRIDSIZE][y].isAlive();
        alive += world[(x+1) % 50][y].isAlive();

        return alive;
    }

    public void computeNeighborMatrix(){
        Cell cell;
        int alive;

        for (int i = 0; i < HGRIDSIZE; i++){
            for(int j = 0; j < VGRIDSIZE; j++){
               cell = world[i][j];
               alive = numAdjacentAlive(cell);
               livingNeighbors[i][j] = alive;
            }
        }
    }

    public int [][] returnNeighborMatrix(){
        return livingNeighbors;
    }






}
