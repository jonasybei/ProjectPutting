package com.mygdx.MazeProject;

import com.mygdx.MazeProject.game.Cell;

import java.util.ArrayList;

public class WallFollower {

    private Cell[][] maze;
    private int mazeLength;
    private ArrayList<Cell> sequenceToExit;
    private Cell exit;
    private Cell entry;
    private Cell currentCell;
    private int direction; // 0 = nord 1 = sud 2 = west 3 = est

    public WallFollower(Cell[][] maze){
        this.maze = maze;
        this.mazeLength = this.maze.length;
        this.sequenceToExit = new ArrayList<Cell>();
        this.exit = this.maze[this.mazeLength][this.mazeLength];
        this.entry = this.maze[0][0];
        this.currentCell = this.entry;
        this.direction = 1;
    }

    public void createPath(){

        while(this.currentCell != this.exit){

            this.sequenceToExit.add(currentCell);
            int curreentY = this.currentCell.getCellPos()[0];
            int currentX = this.currentCell.getCellPos()[1];
            boolean[] currentCellWalls = getOganizedWalls(this.maze[curreentY][currentX].getCellWalls());

            int nextY;
            int nextX;

            if(currentCellWalls[2] && currentCellWalls[1] && !currentCellWalls[3]){




            }


        }


    }


    public boolean[] getOganizedWalls(boolean[] walls){

        boolean[] orgWalls = new boolean[4];

        if(this.direction == 0){

            orgWalls[0] = walls[1];
            orgWalls[1] = walls[0];
            orgWalls[2] = walls[3];
            orgWalls[3] = walls[2];

            return orgWalls;

        }else if(this.direction == 2){

            orgWalls[0] = walls[2];
            orgWalls[1] = walls[3];
            orgWalls[2] = walls[1];
            orgWalls[3] = walls[0];

            return orgWalls;

        }else if(this.direction == 3){

            orgWalls[0] = walls[3];
            orgWalls[1] = walls[2];
            orgWalls[2] = walls[0];
            orgWalls[3] = walls[1];

            return orgWalls;

        }

        return walls;

    }

    public void turnDirrection(String direction){

        if(direction.equals("left")){

            if(this.direction == 3){
                this.direction = 0;
            }else{
                this.direction++;
            }

        }else if(direction.equals("right")){


            if(this.direction == 0){
                this.direction = 3;
            }else{
                this.direction--;
            }

        }else{
            turnDirrection("left");
            turnDirrection("left");
        }
    }

    public ArrayList<Cell> getSequenceToExit(){
        createPath();
        return this.sequenceToExit;
    }


}

