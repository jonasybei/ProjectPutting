package com.mygdx.MazeProject.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeGeneratorABalg {

  //http://weblog.jamisbuck.org/2011/1/17/maze-generation-aldous-broder-algorithm
  private Cell[][] maze;
  private int visitedCellsNum;
  private Cell currentCell; // 0 = y (row) , 1 = x (colum)
  private Stack<Cell> visitedCells;


  public MazeGeneratorABalg(int height) {
    this.visitedCells = new Stack<Cell>();
    this.maze = new Cell[height][height];
    this.visitedCellsNum = 0;

    for (int i = 0; i < this.maze.length; i++) {
      for (int j = 0; j < this.maze.length; j++) {
        this.maze[i][j] = new Cell(i, j);
      }
    }

    this.currentCell = this.maze[0][0];
    this.maze[0][0].visit();
    this.visitedCellsNum++;

  }


  public void generateMaze() {

    int totalcells = this.maze.length * this.maze.length;
    Random r = new Random();


    while (this.visitedCellsNum != totalcells) {


      int currentY = this.currentCell.getCellPos()[0];
      int currentX = this.currentCell.getCellPos()[1];

      System.out.println("current cell pos = " + currentY + "  " + currentX);

      ArrayList<Cell> unvisitedNeighbours = this.maze[currentY][currentX].checkNeighbours(this.maze);
      int unvisitedNeigSize = unvisitedNeighbours.size();


      if (unvisitedNeigSize > 0) {

        int randomNum;

        randomNum = r.nextInt(unvisitedNeigSize);


        Cell nextCell = unvisitedNeighbours.get(randomNum);
        int nextCellY = nextCell.getCellPos()[0];
        int nextCellX = nextCell.getCellPos()[1];

        System.out.println("next cell pos = " + nextCellY + "  " + nextCellX);

        if (findOutNeigPos(nextCell).equals("top")) {

          System.out.println("destroy top");

          this.maze[currentY][currentX].destroyWall(0);
          this.maze[nextCellY][nextCellX].destroyWall(1);

        } else if (findOutNeigPos(nextCell).equals("bottom")) {

          System.out.println("destroy bottom");
          this.maze[currentY][currentX].destroyWall(1);
          this.maze[nextCellY][nextCellX].destroyWall(0);

        } else if (findOutNeigPos(nextCell).equals("left")) {

          System.out.println("destroy left");
          this.maze[currentY][currentX].destroyWall(2);
          this.maze[nextCellY][nextCellX].destroyWall(3);

        } else if (findOutNeigPos(nextCell).equals("right")) {

          System.out.println("destroy right");
          this.maze[currentY][currentX].destroyWall(3);
          this.maze[nextCellY][nextCellX].destroyWall(2);

        }

        this.visitedCells.push(this.currentCell);
        this.currentCell = nextCell;
        this.maze[currentCell.getCellPos()[0]][currentCell.getCellPos()[1]].visit();
        this.visitedCellsNum++;

      } else {
        System.out.println("hey");
        this.currentCell = this.visitedCells.pop();
      }

    }
  }

  public Cell[][] getMaze() {
    generateMaze();
    return this.maze;
  }

  public String findOutNeigPos(Cell neigbour) {
    if (neigbour.getCellPos()[0] == this.currentCell.getCellPos()[0] - 1) {

      return "top";

    } else if (neigbour.getCellPos()[0] == this.currentCell.getCellPos()[0] + 1) {

      return "bottom";

    } else if (neigbour.getCellPos()[1] == this.currentCell.getCellPos()[1] - 1) {

      return "left";

    } else {

      return "right";
    }
  }


}
