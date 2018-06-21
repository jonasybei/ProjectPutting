package com.mygdx.MazeProject.BotAlgorithms;

import com.mygdx.MazeProject.game.Cell;
import com.mygdx.MazeProject.game.MazeGeneratorABalg;

import java.util.ArrayList;

public class Test {

  public static void main(String[] args) {

    MazeGeneratorABalg mazeGen = new MazeGeneratorABalg(5);

    Cell[][] maze = mazeGen.getMaze();

    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze.length; j++) {

        System.out.println("CEll = " + i + " " + j);

        boolean[] walls = maze[i][j].getCellWalls();
        System.out.print(" top = " + walls[0]);
        System.out.print(" bottom = " + walls[1]);
        System.out.print(" left = " + walls[2]);
        System.out.println(" right = " + walls[3]);


      }
    }

    WallFollower wallFollower = new WallFollower(maze);

    ArrayList<Cell> sequence = wallFollower.getSequenceToExit();


  }
}
