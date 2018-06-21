package com.mygdx.MazeProject.game;

import java.util.ArrayList;

public class Cell {

  private boolean visited;
  private int[] cellIPos; // y (row) and x (colum)
  private boolean[] cellWalls; // 0 = topWall , 1 = bottomWall , 2 = leftWall , 3 = rigthWall

  public Cell(int y, int x) {
    this.visited = false;
    this.cellWalls = new boolean[4];
    this.cellWalls[0] = true;
    this.cellWalls[1] = true;
    this.cellWalls[2] = true;
    this.cellWalls[3] = true;
    this.cellIPos = new int[2];
    this.cellIPos[0] = y;
    this.cellIPos[1] = x;
  }


  public void visit() {
    this.visited = true;
  }

  public boolean isVisited() {
    return visited;
  }

  public void unVisit() {
    this.visited = false;
  }

  public int[] getCellPos() {
    return this.cellIPos;
  }

  public ArrayList checkNeighbours(Cell[][] maze) {

    ArrayList<Cell> unVisitedNeighbours = new ArrayList<Cell>();

    if (this.cellIPos[0] >= 1 && maze[this.cellIPos[0] - 1][this.cellIPos[1]].isVisited() == false) { //check if top neighbour is unvisited
      unVisitedNeighbours.add(maze[this.cellIPos[0] - 1][this.cellIPos[1]]);
    }
    if (this.cellIPos[0] <= maze.length - 2 && maze[this.cellIPos[0] + 1][this.cellIPos[1]].isVisited() == false) { //check if bottom neighbour is unvisited
      unVisitedNeighbours.add(maze[this.cellIPos[0] + 1][this.cellIPos[1]]);
    }
    if (this.cellIPos[1] >= 1 && maze[this.cellIPos[0]][this.cellIPos[1] - 1].isVisited() == false) { //check if left neighbour is unvisited
      unVisitedNeighbours.add(maze[this.cellIPos[0]][this.cellIPos[1] - 1]);
    }
    if (this.cellIPos[1] <= maze.length - 2 && maze[this.cellIPos[0]][this.cellIPos[1] + 1].isVisited() == false) { //check if right neighbour is unvisited
      unVisitedNeighbours.add(maze[this.cellIPos[0]][this.cellIPos[1] + 1]);
    }

    return unVisitedNeighbours;
  }

  public void destroyWall(int index) {
    this.cellWalls[index] = false;
  }

  public boolean[] getCellWalls() {
    return cellWalls;
  }

}
