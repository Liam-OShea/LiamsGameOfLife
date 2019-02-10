public class Simulation {

    //TODO
    /*
    Starvation          (dies)
    OverPopulation      (dies)
    Live                (lives, no change)
    Resurrect           (comes back to life)
     */

    public static void main(String [] args){


        //Create and populate world

        Grid grid = new Grid();
        grid.populateGrid();
        grid.computeNeighborMatrix();


        int neighborMatrix [][] = grid.returnNeighborMatrix();



    }

    public void updateNeighbors(Grid grid){
    }

    public void doStarvation(Grid grid){
        Cell cell;

        for(int i = 0; i < grid.getHSize(); i++){
            for(int j = 0; j < grid.getVSize(); j++){

            }
        }

    }





}
