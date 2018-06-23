package com.mygdx.MazeProject.BotAlgorithms;
import com.mygdx.MazeProject.game.Cell;

import java.util.ArrayList;


//implements MazeSolver
public class BFS implements MazeSolver{

    public Cell[][] maze;

    public BFS(Cell[][] maze) {
        this.maze = maze;
    }

  public ArrayList<Cell> exitToArray(Cell exit) {
    ArrayList<Cell> result = new ArrayList<Cell>();
    Cell currentCell = exit;

    while(currentCell!=null) {
      result.add(0, currentCell);
      currentCell = currentCell.getParent();
    }

    return result;
  }

  public Cell solveMaze() {

      for(int i = 0; i < maze.length; i++){
          for(int j = 0; j < maze[i].length; j++) {
              maze[i][j].unVisit();
          }
      }

    ArrayList<Cell> currentCells = new ArrayList<Cell>();
    currentCells.add(maze[0][0]);
    maze[0][0].visit();

    boolean done = false;
    int[] endPos = {maze.length - 1, maze.length - 1};

    while(!done){
        //check if goal is in currentCells
        for(int i = 0; i < currentCells.size(); i++) {
            int[] cellPos = currentCells.get(i).getCellPos();
            if (cellPos[0] == endPos[0] && cellPos[1] == endPos[1]) {
                return currentCells.get(i);
            }
        }
        int cellAmount = currentCells.size();

        for(int i = 0; i < cellAmount; i++) {
            Cell currentCell = currentCells.get(0);
            int[] currentCellPosition = currentCell.getCellPos();
            boolean[] cellWalls = currentCell.getCellWalls();
            maze[currentCellPosition[0]][currentCellPosition[1]].visit();

            //Add upper cell
            if(!cellWalls[0] && currentCellPosition[0] > 0 && !(maze[currentCellPosition[0] - 1][currentCellPosition[1]].isVisited())) {
                currentCells.add(maze[currentCellPosition[0] - 1][currentCellPosition[1]]);
                currentCells.get(currentCells.size() - 1).setParent(currentCell);
            }

            //Add lower cell
            if(!cellWalls[1] && ((maze.length - 1) > currentCellPosition[0]) && !(maze[currentCellPosition[0] + 1][currentCellPosition[1]].isVisited())) {
                currentCells.add(maze[currentCellPosition[0] + 1][currentCellPosition[1]]);
                currentCells.get(currentCells.size() - 1).setParent(currentCell);
            }

            //Add cell left
            if(!cellWalls[2] && currentCellPosition[1] > 0 && !(maze[currentCellPosition[0]][currentCellPosition[1] - 1].isVisited())) {
                currentCells.add(maze[currentCellPosition[0]][currentCellPosition[1] - 1]);
                currentCells.get(currentCells.size() - 1).setParent(currentCell);
            }

            //Add cell right
            if(!cellWalls[3] && (maze.length - 1) > currentCellPosition[1] && !(maze[currentCellPosition[0]][currentCellPosition[1] + 1].isVisited())) {
                currentCells.add(maze[currentCellPosition[0]][currentCellPosition[1] + 1]);
                currentCells.get(currentCells.size() - 1).setParent(currentCell);
            }
            currentCells.remove(0);
        }
    }
    return null;
  }

  public ArrayList<Cell> getSequenceToExit() {
      return exitToArray(solveMaze());
    }
  }