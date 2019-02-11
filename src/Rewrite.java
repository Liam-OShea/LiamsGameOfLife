/*
Author: Liam O'Shea
Date: Feb 10 2018
Filename: Rewrite.java
Desc: This program uses the rules of "Conway's Game of Life" to create an
interesting simulation of cells with conditions for living and dying.
 */

import java.util.Random;

public class Rewrite {

//Static Variables
private static final int ROW = 3;
private static final int COL = 5;
private static final int POP_SET = 20; //Intensity of population from 0 - 100


public static void main(String[] args){

    //2 Arrays: One for dead/alive and one for count of alive neighbors
    int grid [][] = new int [COL][ROW];
    int aliveNeighbors [][] = new int[COL][ROW];


    populateGridAscending(grid);
    displayGridPlain(grid);

    System.out.println("\n\n");

    System.out.println(getElement(grid, 2, 1));
    System.out.println(getNeighbors(grid, 2, 1));


    //testGridOrientation();




}

    //TODO
    /*
    Methods to populate grids in different ways
        Start with Random

    Method to see how many live neighbors a cell has

    Method to populate aliveNeighbors grid with data for how many live neighbors

    Methods to implement rules
        Starvation
        Overpopulation
        Living on
        Reproduction
     */

    public static int getElement(int grid[][], int xpos, int ypos){
        return grid[ypos][xpos];
    };

    public static int getNeighbors(int grid [][], int xPos, int yPos){
        int neighbors = 0;
        int num;
        int ij = grid[xPos][yPos];
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
               num = grid[(yPos + i + COL) % COL][(xPos + j + ROW) % ROW];
               neighbors += num;
            }
        }
        neighbors -= grid[yPos][xPos];

        return neighbors;
    }

    public static void testGridOrientation(){
        int count = 0;
        for(int i = 0; i < COL; i++){
            for(int j = 0; j < ROW; j++){
                System.out.print("Num: " + count + " " + "i:" + i + " j:" + j + " *\t");
                count++;
            }
            System.out.println();
        }

        /**
         Columns as first loop, Rows as second loop. This will produce
         matrix with 0,0 at top left.
         */
    }



    public static void populateGrid(int [][] grid){
        /*
        This method populates the grid randomly based on a constant specified at
        beginning of file.
         */


        int ranNum;

        Random rand = new Random();

        //ROW, COL   i, j
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){

                //We will generate a random number and only give life to cell
                //if it is under some bound

                ranNum = rand.nextInt(100);
                if(ranNum < POP_SET){
                    grid[COL][ROW] = 1;
                } else{
                    grid[COL][ROW] = 0;
                }

            }
        }
    }

    public static void populateGridAscending(int [][] grid){
        int count = 0;
        for(int i = 0; i < COL; i++){
            for(int j = 0; j < ROW; j++){
                grid[i][j] = count;
                count++;
            }
            System.out.println();
        }
    }

    public static void displayGrid(int [][] grid){
        for(int i = 0; i < COL; i++){
            for(int j = 0; j < ROW; j++){
                if (grid[i][j] == 1){
                    System.out.print("@ ");
                } else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void displayGridPlain(int[][] grid){

        for(int i = 0; i < COL; i++){
            for(int j = 0; j < ROW; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    //******Copy and paste double for loop
    /**
    for(int i = 0; i < COL; i++){
        for(int j = 0; j < ROW; j++){

        }
     }
    */

}
