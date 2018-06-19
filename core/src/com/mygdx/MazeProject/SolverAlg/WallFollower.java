package com.mygdx.MazeProject.SolverAlg;

import com.mygdx.MazeProject.game.Cell;

import java.util.ArrayList;

public class WallFollower {

  private Cell[][] maze;
  private int mazeLength;
  private ArrayList<Cell> sequenceToExit;
  private Cell exit;
  private Cell entry;
  private Cell currentCell;
  private int direction; // 0 = nord 1 = est 2 = sud 3 = west

  public WallFollower(Cell[][] maze) {
    this.maze = maze;
    this.mazeLength = this.maze.length;
    this.sequenceToExit = new ArrayList<Cell>();
    this.exit = this.maze[this.mazeLength - 1][this.mazeLength - 1];
    this.entry = this.maze[0][0];
    this.currentCell = this.entry;
    this.direction = 1;
  }

  public void createPath() {

    while (this.currentCell != this.exit) {


      this.sequenceToExit.add(currentCell);
      int curreentY = this.currentCell.getCellPos()[0];
      int currentX = this.currentCell.getCellPos()[1];
      boolean[] currentCellWalls = getOganizedWalls(this.maze[curreentY][currentX].getCellWalls());


      int counter = 0;
      for (boolean wall : currentCellWalls) {
        System.out.println(counter + " = " + wall);
        counter++;
      }


      int nextY;
      int nextX;

      if (currentCellWalls[2] && currentCellWalls[1] && !currentCellWalls[3]) {
        System.out.println("hey");
        turnDirrection("right");
        this.currentCell = getCellByDirection();

      } else if (currentCellWalls[2] && !currentCellWalls[1]) {

        this.currentCell = getCellByDirection();


      } else if (currentCellWalls[1] && currentCellWalls[2] && currentCellWalls[3]) {

        turnDirrection("back");
        this.currentCell = getCellByDirection();


      } else if (currentCellWalls[1] && currentCellWalls[3] && !currentCellWalls[2]) {
        turnDirrection("left");
        this.currentCell = getCellByDirection();


      } else if (currentCellWalls[3] && !currentCellWalls[2]) {

        turnDirrection("left");
        this.currentCell = getCellByDirection();

      }
    }
  }


  public boolean[] getOganizedWalls(boolean[] walls) {

    boolean[] orgWalls = new boolean[4];

    if (this.direction == 0) {

      orgWalls[0] = walls[1];
      orgWalls[1] = walls[0];
      orgWalls[2] = walls[3];
      orgWalls[3] = walls[2];

      return orgWalls;

    } else if (this.direction == 3) {

      orgWalls[0] = walls[2];
      orgWalls[1] = walls[3];
      orgWalls[2] = walls[1];
      orgWalls[3] = walls[0];

      return orgWalls;

    } else if (this.direction == 1) {

      orgWalls[0] = walls[3];
      orgWalls[1] = walls[2];
      orgWalls[2] = walls[0];
      orgWalls[3] = walls[1];

      return orgWalls;

    }

    return walls;

  }

  public void turnDirrection(String direction) {

    if (direction.equals("left")) {

      if (this.direction == 3) {
        this.direction = 0;
      } else {
        this.direction++;
      }

    } else if (direction.equals("right")) {


      if (this.direction == 0) {
        this.direction = 3;
      } else {
        this.direction--;
      }

    } else {
      turnDirrection("left");
      turnDirrection("left");
    }
  }

  public Cell getCellByDirection() {

    int currentY = this.currentCell.getCellPos()[0];
    int currentX = this.currentCell.getCellPos()[1];

    int nextY;
    int nextX;

    if (this.direction == 0) {

      nextY = currentY - 1;
      nextX = currentX;

    } else if (this.direction == 1) {

      nextY = currentY;
      nextX = currentX + 1;

    } else if (this.direction == 2) {

      nextY = currentY + 1;
      nextX = currentX;

    } else {

      nextY = currentY;
      nextX = currentX - 1;

    }

    return this.maze[nextY][nextX];


  }

  public ArrayList<Cell> getSequenceToExit() {
    createPath();
    return this.sequenceToExit;
  }


}

