package com.mygdx.MazeProject.BotAlgorithms;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.CrazyPutting.game.Terrain;
import com.mygdx.MazeProject.Models3D.WallSpot;
import com.mygdx.MazeProject.BotAlgorithms.ShotSimulation;

import java.util.ArrayList;

public class GreedyAlg {
  private Vector2 endPos;
  private Terrain t = new Terrain();
  private ShotSimulation simulation;

  public GreedyAlg(ArrayList<WallSpot> wallSpots) {
    this.simulation = new ShotSimulation(wallSpots);
  }

  public int[] getNextShot(Vector2 ballPosition, Vector2 endPos) {

    this.endPos = endPos;

    double start = System.currentTimeMillis();

    int degree = 0;
    int power = 0;
    double distance = 1000000000;

    int rightBound = computeAngle(ballPosition, this.endPos);
    int leftBound = computeAngle(ballPosition, this.endPos);


    for (int i = 1; i < 160; i++) {
      for (int j = 1; j <= 100; j++) {
        // simulate is a method which simulated a shot in certyain terrain and outputs the position of the ball after the shot
        Vector2 ballPos = new Vector2();

        if (i <= 80) {
          Vector2 ballPosTest = simulation.simulate(leftBound, j, ballPosition);
          ballPos.x = ballPosTest.x;
          ballPos.y = ballPosTest.y;
          double simulatedDistance = getDistantsToEnd(ballPos);

          System.out.println(" degree= " + leftBound + " power= " + j + "  distance= " + simulatedDistance);

          if (simulatedDistance < distance) {
            distance = simulatedDistance;
            degree = leftBound;
            power = j;
          }

        } else {
          Vector2 ballPosTest = simulation.simulate(rightBound, j, ballPosition);
          ballPos.x = ballPosTest.x;
          ballPos.y = ballPosTest.y;
          double simulatedDistance = getDistantsToEnd(ballPos);
          System.out.println(" degree= " + rightBound + " power= " + j + "  distance= " + getDistantsToEnd(ballPos));
          if (simulatedDistance < distance) {
            distance = simulatedDistance;
            degree = rightBound;
            power = j;
          }
        }
      }
      if (i < 80) {
        leftBound = removeDegreeToAngle(leftBound);
      } else {
        rightBound = addDegreeToAngle(rightBound);
      }

    }

    int[] nextShot = {degree, power};
    System.out.println("nextShot power " + power + "  nextShot degree " + degree);
    double end = System.currentTimeMillis() - start;
    System.out.println("time " + end);

    return nextShot;
  }

  public double getDistantsToEnd(Vector2 pos) {
    //double distance = Math.abs(Math.sqrt(Math.pow((double) this.endPos.x - pos.x, 2) + Math.pow((double) this.endPos.y - pos.y, 2)));
    float distance = pos.dst(this.endPos);
    return distance;
  }

  public int computeAngle(Vector2 start, Vector2 goal) {

    Vector2 ballPosAfterShot = simulation.simulate(0, 10, start);

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


  public int addDegreeToAngle(int angle) {
    if (angle < 360) {
      return angle + 1;
    } else {
      return 1;
    }
  }

  public int removeDegreeToAngle(int angle) {
    if (angle > 1) {
      return angle - 1;
    } else {
      return 360;
    }
  }

}
