package com.mygdx.BotAlgorithms;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.*;

import java.util.ArrayList;

public class ShotSimulation {


  private Vector3 pos;
  private Map m;
  private float ep = 1 / (float) 4;
  private Ball rollingBall = new Ball();
  private ArrayList<TreeSpot> treesPos;
  private float ballDiameter = 0.5f;

  public ShotSimulation(int level, ArrayList<TreeSpot> treesPos) {
    this.m = MapFactory.createMap(level);
    this.treesPos = treesPos;
  }


  public Vector2 simulate(int degree, int power, Vector2 ballPosition) {

    rollingBall.setInitialPosition(ballPosition, m);
    Vector2 ballPosAfterShot = new Vector2();
    float realPower = (power / 10) * 2;
    rollingBall.setVelocity(realPower, fromDegreeToRadians(degree));


    while (rollingBall.isStationary() == false) {

      rollingBall.setNewPosition(m);
      pos = rollingBall.getNewPosition();

      if (pos.z < -0.3)
        //rollingBall.resetPosition();
        return ballPosition;

      if (pos.x > 9 || pos.x < -9 || pos.y > 9 || pos.y < -9)
        //rollingBall.resetPosition();
        return ballPosition;

      if (isBallinTree()) {
        rollingBall.resetPosition();
      }

      pos = rollingBall.getNewPosition();


      Vector3 tmpPos = rollingBall.getPosition();
      float mu = 0.5f;

      for (SandSpot s : m.getSandMap()) {
        if (s.getPosition().dst(pos.x, pos.y) < ep * s.getRadius()) {
          mu = 0.9f;
        }
      }
      rollingBall.xRungeKutta(mu);
      rollingBall.yRungeKutta(mu);


    }


    ballPosAfterShot.x = rollingBall.getPosition().x;
    ballPosAfterShot.y = rollingBall.getPosition().y;

    return ballPosAfterShot;

  }

  public float fromDegreeToRadians(float degree) {
    float radians = (float) ((degree * Math.PI) / 180);
    return radians;
  }

  public boolean isBallinTree() {
    Vector2 rollingBallPos = new Vector2();
    rollingBallPos.x = this.rollingBall.getPosition().x;
    rollingBallPos.y = this.rollingBall.getPosition().y;
    for (TreeSpot t : this.treesPos) {
      if (rollingBallPos.dst(t.getPosition()) < this.ballDiameter / 2 + 0.4) {
        return true;
      }
    }
    return false;
  }
}
