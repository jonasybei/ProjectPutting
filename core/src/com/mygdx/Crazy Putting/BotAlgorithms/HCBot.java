package com.mygdx.BotAlgorithms;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Terrain;
import com.mygdx.game.TreeSpot;

import java.util.ArrayList;


public class HCBot implements Bot {

  private final ShotSimulation simulation;
  private final Vector2 endPos;
  private ArrayList<Shot> shots = new ArrayList<Shot>();
  private int level;


  private final int n = 10;

  public HCBot(int level, ArrayList<TreeSpot> treesPos) {
    System.out.println("Creating Hill Climb Bot");
    this.level = level;
    this.simulation = new ShotSimulation(level, treesPos);
    this.endPos = Terrain.endPos(level);
  }

  private void createShots(int n, int level, Vector2 startPos) {

    Vector2 endPos = Terrain.endPos(level);

    int directAngle = (int) startPos.angle(endPos);

    int powerInc = n / 2;
    int angleInc = n / 2;

    int range = 360; //n of degrees to fire the ball at.

    int angle;

    for (int i = 0; i <= 200; i += powerInc) {
      for (int j = 0; j <= range; j += angleInc) {
        angle = j - range / 2 + directAngle;
        shots.add(new Shot(angle, i));
      }
    }
  }


  @Override
  public int[] getNextShot(Vector2 ballPosition) {
    createShots(10, level, ballPosition);
    return simulateShots(ballPosition);
  }

  private int[] simulateShots(Vector2 startPos) {
    Vector2 ballPos;
    float dist;

    int[] best = new int[2];

    for (Shot shot : shots) {
      ballPos = simulation.simulate(shot.getAngle(), shot.getPower(), startPos);
      dist = endPos.dst(ballPos);

      if (dist < 0.7) {
        System.out.println("Hill climb found early hole in one");
        best[0] = shot.getAngle();
        best[1] = shot.getPower();
        return best;
      } else {
        shot.setDistance(dist);
      }
    }


    float min = 10000000;
    Shot bestShot = shots.get(0);


    for (Shot shot : shots) {
      if (shot.getDistance() < min) {
        bestShot = shot;
        min = shot.getDistance();
      }
    }


    best[0] = bestShot.getAngle();
    best[1] = bestShot.getPower();
    return best;
  }
}
