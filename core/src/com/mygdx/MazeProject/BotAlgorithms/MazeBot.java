package com.mygdx.MazeProject.BotAlgorithms;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.MazeProject.Models3D.WallSpot;
import com.mygdx.MazeProject.game.Cell;

import java.util.ArrayList;

public class MazeBot {

  private Cell[][] maze;
  private MazeSolver solver;
  private ArrayList<Cell> sequenceToExit;
  private int currentIndex;
  private GreedyAlg greedy;


  public MazeBot(Cell[][] maze, int alg, ArrayList<WallSpot> wallSpots) {
    this.maze = maze;
    this.currentIndex = 0;
    this.greedy = new GreedyAlg(wallSpots);

    switch (alg) {
      case 1:
        this.solver = new WallFollower(this.maze);
        break;
      case 2:
        this.solver = new BFS(this.maze);
        break;
    }

    this.sequenceToExit = this.solver.getSequenceToExit();
  }

  public void shortenSequenceToExit() {
      for(int i = 0; i < sequenceToExit.size() - 2; i++) {
          int firstY = sequenceToExit.get(i).getCellPos()[0];
          int secondY = sequenceToExit.get(i + 1).getCellPos()[0];
          int thirdY = sequenceToExit.get(i + 2).getCellPos()[0];
          int firstX = sequenceToExit.get(i).getCellPos()[1];
          int secondX = sequenceToExit.get(i + 1).getCellPos()[1];
          int thirdX = sequenceToExit.get(i + 2).getCellPos()[1];
          if((firstY == secondY && firstY == thirdY) || (firstX == secondX && firstX == thirdX)) {
            sequenceToExit.remove(1);
            i--;
          }
      }
  }

  public int[] getShot(Vector2 ballPos) { // index 0 = angle , index 1 = power

    Cell cellToReach = this.sequenceToExit.get(currentIndex);
    this.currentIndex++;


    Vector2 cellToReachPos = new Vector2();
    cellToReachPos.y = getRenderPosition(cellToReach.getCellPos()[0], cellToReach.getCellPos()[1])[0];
    cellToReachPos.y = getRenderPosition(cellToReach.getCellPos()[0], cellToReach.getCellPos()[1])[1];

    int[] nextShot = this.greedy.getNextShot(ballPos, cellToReachPos);
    return nextShot;

  }

  public float[] getRenderPosition(int y, int x) {

    float distanceToCenter = this.maze.length / 2;
    float[] renderPos = new float[2];

    renderPos[0] = (y - distanceToCenter) * 2;
    renderPos[1] = (x - distanceToCenter) * 2;

    System.out.println("render cell position = " + renderPos[0] + " " + renderPos[1]);

    return renderPos;
  }

}
