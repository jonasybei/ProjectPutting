package com.mygdx.MazeProject.SolverAlg;

import com.mygdx.MazeProject.game.Cell;
import com.mygdx.MazeProject.game.MazeGeneratorABalg;

import java.util.ArrayList;

public class Test {

  public static void main(String[] args) {

    MazeGeneratorABalg mazeGen = new MazeGeneratorABalg(3);

    Cell[][] maze = mazeGen.getMaze();

    WallFollower wallFollower = new WallFollower(maze);

    ArrayList<Cell> sequence = wallFollower.getSequenceToExit();


  }
}
