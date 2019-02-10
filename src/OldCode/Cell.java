public class Cell {

    private int alive;
    private int xpos, ypos;

    public Cell(){
        alive = 0;
    };

    //TODO
    /*
    senseNeighbors(Grid grid             (return number of living neighbors
     */

    public int senseNeighbors(Grid grid){
        //Need to look in all neighboring squares for live neighbors



        return 0;
    }

    public int getX(){return xpos;}
    public int getY(){return ypos;}

    public void setX(int x){xpos = x;}
    public void setY(int y){ypos = y;};

    public int isAlive(){return alive;}
    public void setAlive(int status){alive = status;}


}
