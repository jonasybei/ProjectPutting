package com.mygdx.MazeProject.BotAlgorithms;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.CrazyPutting.game.Ball;
import com.mygdx.CrazyPutting.game.Map;
import com.mygdx.CrazyPutting.game.MapFactory;
import com.mygdx.CrazyPutting.game.SandSpot;
import com.mygdx.MazeProject.Models3D.WallSpot;

import java.util.ArrayList;

public class ShotSimulation {
  private Vector3 pos;
  private Map m;
  private float ep = 1 / (float) 4;
  private Ball rollingBall = new Ball();
  private ArrayList<WallSpot> wallSpots;
  private float ballDiameter = 0.5f;
  private String directionOfHittenWall;

  public ShotSimulation(ArrayList<WallSpot> wallSpots) {
    this.m = MapFactory.createMap(3);
    this.wallSpots = wallSpots;
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

      if (isBallInWall()) {
        this.rollingBall.ballIsWall(this.directionOfHittenWall);
        //rollingBall.resetPosition();
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

  public boolean isBallInWall() {
    Vector2 rollingBallPos = new Vector2();
    rollingBallPos.x = this.rollingBall.getPosition().x;
    rollingBallPos.y = this.rollingBall.getPosition().y;
    String direction;

    for (WallSpot wall : this.wallSpots) {
      direction = wall.getDirection();

      if (direction.equals("vertical")) {

        float leftBound = wall.getPos().x - 1;
        float rightBound = wall.getPos().x + 1;
        float upBound = wall.getPos().y + 0.1f;
        float downBound = wall.getPos().y - 0.1f;


        if (rollingBallPos.x > leftBound && rollingBallPos.x < rightBound && rollingBallPos.y > downBound && rollingBallPos.y < upBound) {
          //System.out.printf("ball is in wall");
          //System.out.println(direction);
          this.directionOfHittenWall = direction;
          return true;
        }

      } else {

        float leftBound = wall.getPos().x - 0.1f;
        float rightBound = wall.getPos().x + 0.1f;
        float upBound = wall.getPos().y + 1f;
        float downBound = wall.getPos().y - 1f;


        if (rollingBallPos.x > leftBound && rollingBallPos.x < rightBound && rollingBallPos.y > downBound && rollingBallPos.y < upBound) {
          //System.out.printf("ball is in wall");
          //System.out.println(direction);
          this.directionOfHittenWall = direction;
          return true;
        }
      }
    }
    return false;
  }
}
