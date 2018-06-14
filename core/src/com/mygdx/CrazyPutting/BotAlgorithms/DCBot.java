package com.mygdx.CrazyPutting.BotAlgorithms;


import com.mygdx.CrazyPutting.game.Point;
import com.mygdx.CrazyPutting.game.Terrain;

import java.util.ArrayList;

public class DCBot {

  //index of terrain
  private int terrainIndex;

  public DCBot(int terrainIndex) {
    this.terrainIndex = terrainIndex;
  }

  public Shot[] compute(Point start, Point goal) {
    //if we can reach the goal in one shot, go for it!
    if (canReach(start, goal)) {
      shoot(start, goal);
    } else {
      //Start the algorithm.
      //For now, the bot only shoots twice. This should be enough.
      boolean done = false;
      //Specify the points!
      Point upLeft = new Point(-10, 10);
      Point upRight = new Point(10, 10);
      Point downLeft = new Point(-10, -10);
      Point downRight = new Point(-10, 10);
      Subspace terrainSpace = new Subspace(upLeft, upRight, downLeft, downRight);
      ArrayList<Subspace> subspaces = terrainSpace.getSubspaces();
      while (!done) {

      }
    }
    return null;
  }

  //computes the power required from start to finish, this number is between 0 and 100
  public int computePower(Point start, Point goal) {
    //???
    return 0;
  }


  public void shoot(Point start, Point goal) {
    //???
  }

  public double computeDistance(Point start, Point goal) {
    double x = goal.getX() - start.getX();
    double y = goal.getY() - start.getY();
    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
  }

  public boolean canReach(Point point, Point goal) {

    //Amount of times we need to check. Since since the density of the drawn points is equal to the length, we take as many steps as needed.
    int steps = (int) Math.ceil(computeDistance(point, goal));

    for (int i = 1; i < steps; i++) {
      double t = i / steps;
      if (Terrain.compute(terrainIndex, (point.getX() + t * (goal.getX() - point.getX())), (point.getY() + t * (goal.getY() - point.getY()))) < 0) {

        return false;
      }
    }
    return true;
  }

}
