package com.mygdx.MazeProject.BotAlgorithms;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.MazeProject.Models3D.WallSpot;
import com.mygdx.MazeProject.game.Cell;
import java.util.ArrayList;
import java.lang.Math;

public class MazeBot {

  private Cell[][] maze;
  private MazeSolver solver;
  private ArrayList<Cell> sequenceToExit;
  private ShotSimulation simulator;
  private int currentIndex;

  public MazeBot(Cell[][] maze, int alg, ArrayList<WallSpot> wallSpots) {
    this.maze = maze;
    this.simulator = new ShotSimulation(wallSpots);
    this.currentIndex = 0;

    switch (alg) {
      case 1:
        this.solver = new WallFollower(this.maze);
        break;
      case 2:
        this.solver = new BFS(this.maze);
        ;
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
            sequenceToExit.remove(i + 1);
            i--;
          }
      }
  }

  public int[] getShot(Vector2 ballPos) { // index 0 = angle , index 1 = power

    int[] nextShot = new int[2];

    Cell cellToReach = this.sequenceToExit.get(currentIndex);
    this.currentIndex++;

    Vector2 cellToReachPos = new Vector2();
    cellToReachPos.y = getRenderPosition(cellToReach.getCellPos()[0], cellToReach.getCellPos()[1])[0];
    cellToReachPos.y = getRenderPosition(cellToReach.getCellPos()[0], cellToReach.getCellPos()[1])[1];

    int angle = computeAngle(ballPos, cellToReachPos);
    int power = computePower(ballPos.dst(cellToReachPos));

    nextShot[0] = angle;
    nextShot[1] = power;

    return nextShot;

  }


  public int computeAngle(Vector2 start, Vector2 goal) {

    Vector2 ballPosAfterShot = simulator.simulate(0, 10, start);

    Vector2 vectorBallAfterShotDegree0 = new Vector2();
    vectorBallAfterShotDegree0.x = ballPosAfterShot.x - start.x;
    vectorBallAfterShotDegree0.y = ballPosAfterShot.y - start.y;


    Vector2 vectorFromBallGoal = new Vector2();
    vectorFromBallGoal.x = goal.x - start.x;
    vectorFromBallGoal.y = goal.y - start.y;


    double lengthStartVec = Math.sqrt((vectorBallAfterShotDegree0.x * vectorBallAfterShotDegree0.x) + (vectorBallAfterShotDegree0.y * vectorBallAfterShotDegree0.y));
    double lengthGoalVec = Math.sqrt((vectorFromBallGoal.x * vectorFromBallGoal.x) + (vectorFromBallGoal.y * vectorFromBallGoal.y));
    double startTimeGoal = (vectorBallAfterShotDegree0.x * vectorFromBallGoal.x) + (vectorBallAfterShotDegree0.y * vectorFromBallGoal.y);

    double cosAngle = startTimeGoal / (lengthGoalVec * lengthStartVec);

    int angle = (int) (Math.acos(cosAngle) * (180 / Math.PI));

    return angle;
  }

  public int computePower(float distance) {
    double acc = 0.5 * 9.81;
    double result = -acc * Math.sqrt((-2 * distance)/acc);
    return (int)result;
  }

  public float[] getRenderPosition(int y, int x) {

    float distanceToCenter = this.maze.length / 2;
    float[] renderPos = new float[2];

    renderPos[0] = (y - distanceToCenter) * 2;
    renderPos[1] = (x - distanceToCenter) * 2;

    return renderPos;
  }

}
