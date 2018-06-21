package com.mygdx.MazeProject.BotAlgorithms;

import com.mygdx.MazeProject.game.Cell;

import java.util.ArrayList;

public class WallFollower implements MazeSolver {

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
    this.direction = 2;

  }

  public void createPath() {


    while (this.currentCell != this.exit) {

      this.sequenceToExit.add(currentCell);
      int curreentY = this.currentCell.getCellPos()[0];
      int currentX = this.currentCell.getCellPos()[1];

      System.out.println("currentCell pos = " + curreentY + " " + currentX);

      boolean[] currentCellWalls = getOganizedWalls(this.maze[curreentY][currentX].getCellWalls());

      System.out.print(" top = " + currentCellWalls[0]);
      System.out.print(" bottom = " + currentCellWalls[1]);
      System.out.print(" left = " + currentCellWalls[2]);
      System.out.println(" right = " + currentCellWalls[3]);


      if (currentCellWalls[2] && currentCellWalls[1] && !currentCellWalls[3]) { // left = true  , bottom = true , right = false
        System.out.println("turn right");
        turnDirrection("right");
        this.currentCell = getCellByDirection();

      } else if (currentCellWalls[2] && !currentCellWalls[1]) { // left = true , bottom = false
        System.out.println("go straight");
        this.currentCell = getCellByDirection();


      } else if (currentCellWalls[1] && currentCellWalls[2] && currentCellWalls[3]) { // bottom = true , left = true , right = true
        System.out.println("go back");
        turnDirrection("back");
        this.currentCell = getCellByDirection();


      } else if (!currentCellWalls[2]) {
        System.out.println("turn left");
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

      orgWalls[0] = walls[3];
      orgWalls[1] = walls[2];
      orgWalls[2] = walls[0];
      orgWalls[3] = walls[1];

      return orgWalls;

    } else if (this.direction == 1) {

      orgWalls[0] = walls[2];
      orgWalls[1] = walls[3];
      orgWalls[2] = walls[1];
      orgWalls[3] = walls[0];

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
    deleteDoubles();
    return this.sequenceToExit;
  }

  public void deleteDoubles() {

    ArrayList<Cell> realSequence = new ArrayList<Cell>();

    for (int i = 0; i < this.sequenceToExit.size(); i++) {

      Cell currentCell = this.sequenceToExit.get(i);
      realSequence.add(currentCell);

      for (int j = 0; j < this.sequenceToExit.size(); j++) {

        Cell currentCell2 = this.sequenceToExit.get(j);
        if (currentCell.equals(currentCell2)) {
          i = j + 1;
        }
      }
    }

    this.sequenceToExit = realSequence;

  }


}

