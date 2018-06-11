package com.mygdx.BotAlgorithms;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Terrain;
import com.mygdx.game.TreeSpot;

import java.util.ArrayList;

public class GreedyBTalg implements Bot {
  private Vector2 endPos;
  private Terrain t = new Terrain();
  private ShotSimulation simulation;

  public GreedyBTalg(int terrainNumber, ArrayList<TreeSpot> treesPos) {
    this.endPos = this.t.endPos(terrainNumber);
    this.simulation = new ShotSimulation(terrainNumber, treesPos);
  }

  public int[] getNextShot(Vector2 ballPosition) {

    double start = System.currentTimeMillis();

    int degree = 0;
    int power = 0;
    double distance = 1000000000;

    for (int i = 0; i < 360; i++) {
      for (int j = 0; j < 100; j++) {
        // simulate is a method which simulated a shot in certyain terrain and outputs the position of the ball after the shot
        Vector2 ballPos = simulation.simulate(i, j, ballPosition);


        if (getDistantsToEnd(ballPos) < distance) {
          distance = getDistantsToEnd(ballPos);
          degree = i;
          power = j;
        }
      }
    }

    int[] nextShot = {degree, power};
    System.out.println("nextShot power " + power + "  nextShot degree " + degree);

    double end = System.currentTimeMillis() - start;

    System.out.printf("time " + end);

    return nextShot;
  }

  public double getDistantsToEnd(Vector2 pos) {
    double distance = Math.abs(Math.sqrt(Math.pow((double) this.endPos.x - pos.x, 2) + Math.pow((double) this.endPos.y - pos.y, 2)));
    return distance;
  }


}
