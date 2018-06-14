package com.mygdx.CrazyPutting.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Ball {
  private final double size = 0.5;
  private final double G = 9.81;
  //private final double G = 15;
  private final double H = 0.0125;

  private Vector3 pos = new Vector3(); // x and y values
  private Vector3 safePosition = new Vector3();
  private Vector3 lastPos = new Vector3();
  private Vector3 velocity = new Vector3();
  private Vector3 acceleration = new Vector3();
  private double totalVelocity; //in m/s
  private double alpha;
  //private double acceleration; // in m/s2 CHANGE
  private boolean target;
  private double mu;
  private Map m;
  private float xSlope;
  private float ySlope;
  private float slopX;
  private float slopY;
  private float frictX;
  private float frictY;
  private int stopN = 0;
  private double epsilon = 0.05;

  //getNewPosition;   (DONE)
  //resetPosition     (DONE)
  //setSafePosition   (DONE)
  //setNewVelocity (RUNGA KUTTA)
  //getPosition       (DONE)
  //ResetVelocity (?) -> new shot (?) (DONE)

  public boolean isStationary() {
    if (totalVelocity <= 0.2)
      stopN++;
    else
      stopN = 0;

    return stopN > 10;
  }


  public void setInitialPosition(Vector2 position, Map m) {
    this.m = m;
    pos.x = position.x;
    pos.y = position.y;
    pos.z = (float) Terrain.compute(m.getTerrain(), pos.x, pos.y);
    lastPos.x = pos.x;
    lastPos.y = pos.y;
    lastPos.z = pos.z;
    safePosition.x = pos.x;
    safePosition.y = pos.y;
  }

  public void setNewPosition(Map m) {
    lastPos.x = pos.x;
    lastPos.y = pos.y;
    lastPos.z = pos.z;
    pos.x = (float) (pos.x + H * velocity.x);
    pos.y = (float) (pos.y + H * velocity.y);
    pos.z = (float) (Terrain.compute(m.getTerrain(), pos.x, pos.y));
  }


  public float xSlopeCalculator() {
    Vector3[] array = pointsAroundTheBall();
    xSlope = (float) ((H * G * Math.sin(Math.atan((array[0].z - array[2].z) / 2))) * 2);
    return xSlope;
  }

  public float ySlopeCalculator() {
    Vector3[] array = pointsAroundTheBall();
    ySlope = (float) -((H * G * Math.sin(Math.atan((array[1].z - array[3].z) / 2))) * 2);
    return ySlope;
  }


  public Vector3[] pointsAroundTheBall() {
    //0 = x+1,y  1 = x,y-1  2 = x-1,y  3 = x,y+1
    Vector3[] array = new Vector3[4];

    array[0] = new Vector3();
    array[1] = new Vector3();
    array[2] = new Vector3();
    array[3] = new Vector3();

    array[0].x = this.pos.x + 1;
    array[0].y = this.pos.y;
    array[0].z = (float) (Terrain.compute(this.m.getTerrain(), array[0].x, array[0].y));

    array[1].x = this.pos.x;
    array[1].y = this.pos.y - 1;
    array[1].z = (float) (Terrain.compute(this.m.getTerrain(), array[1].x, array[1].y));

    array[2].x = this.pos.x - 1;
    array[2].y = this.pos.y;
    array[2].z = (float) (Terrain.compute(this.m.getTerrain(), array[2].x, array[2].y));

    array[3].x = this.pos.x;
    array[3].y = this.pos.y + 1;
    array[3].z = (float) (Terrain.compute(this.m.getTerrain(), array[3].x, array[3].y));

    return array;
  }

  public void setSafePosition(Vector3 safe) {
    safePosition.x = safe.x;
    safePosition.y = safe.y;
    safePosition.z = safe.z;
  }

  public void resetPosition() {
    pos.x = safePosition.x;
    pos.y = safePosition.y;
    pos.z = (float) Terrain.compute(m.getTerrain(), pos.x, pos.y);


    velocity.set(0, 0, 0);
    totalVelocity = 0;

  }

  public void setVelocity(float input, float alfa) {
    totalVelocity = (input);
    velocity.x = (float) (totalVelocity * Math.cos(alfa));
    velocity.y = (float) (totalVelocity * Math.sin(alfa));
    velocity.z = 0;
  }


  // We have to update mu after each timestep, to be sureit mu=muSand or mu=muGrass /// CHECK FROM BALL CLASS
  public float setAccelerationX(float velx, float vely, float mu) {
    acceleration.x = (float) ((-G * xSlopeCalculator() - (G * mu * velx)) / (Math.sqrt(Math.pow(velx, 2) + Math.pow(vely, 2))));
    return acceleration.x;
  }

  public float setAccelerationY(float vely, float velx, float mu) {
    acceleration.y = (float) ((-G * ySlopeCalculator() - (G * mu * vely)) / (Math.sqrt(Math.pow(velx, 2) + Math.pow(vely, 2))));
    return acceleration.y;
  }


  //fourth.order runge-Kutta
  public void xRungeKutta(float mu) {
    Vector3[] array = pointsAroundTheBall();
    this.frictX = (float) (mu * G * H);
    this.slopX = (float) ((H * G * Math.sin(Math.atan((array[0].z - array[2].z) / 2))) * 2);
    if ((((velocity.x) > epsilon) && (velocity.x - frictX > epsilon)) || (velocity.x < -epsilon && velocity.x + frictX < -epsilon)) {
      float k1 = (float) H * setAccelerationX(velocity.x, velocity.y, mu);
      float k2 = (float) H * setAccelerationX(velocity.x + k1 / 3, velocity.y, mu);
      float k3 = (float) H * setAccelerationX(velocity.x - k1 / 3 + k2, velocity.y, mu);
      float k4 = (float) H * setAccelerationX(velocity.x + k1 - k2 + k3, velocity.y, mu);
      velocity.x = velocity.x + (k1 + 3 * k2 + 3 * k3 + k4) / 8;
    } else if (velocity.x > 0 && velocity.x - frictX < 0) {
      if (Math.abs(slopX) > frictX) {
        velocity.x = velocity.x - slopX;
      } else {
        velocity.x = 0;
      }
    } else if (velocity.x < 0 && velocity.x + frictX > 0) {
      if (Math.abs(slopX) > frictX) {
        velocity.x = velocity.x + slopX;
      } else {
        velocity.x = 0;
      }
    } else {
      velocity.x = 0;
    }
  }

  public void yRungeKutta(float mu) {
    Vector3[] array = pointsAroundTheBall();
    this.frictY = (float) (mu * G * H);
    this.slopY = (float) -((H * G * Math.sin(Math.atan((array[1].z - array[3].z) / 2))) * 2);
    if ((((velocity.y) > epsilon) && (velocity.y - frictY > epsilon)) || (velocity.y < -epsilon && velocity.y + frictY < -epsilon)) {

      float k1 = (float) H * setAccelerationY(velocity.y, velocity.x, mu);
      float k2 = (float) H * setAccelerationY(velocity.y + k1 / 3, velocity.x, mu);
      float k3 = (float) H * setAccelerationY(velocity.y - k1 / 3 + k2, velocity.x, mu);
      float k4 = (float) H * setAccelerationY(velocity.y + k1 - k2 + k3, velocity.x, mu);
      velocity.y = velocity.y + (k1 + 3 * k2 + 3 * k3 + k4) / 8;
    } else if (velocity.y > 0 && velocity.y - frictY < 0) {

      if (Math.abs(slopY) > frictY) {
        velocity.y = velocity.y - slopY;
      } else {
        velocity.y = 0;
      }
    } else if (velocity.y < 0 && velocity.y + frictY > 0) {

      if (Math.abs(slopY) > frictY) {
        velocity.y = velocity.y + slopY;
      } else {
        velocity.y = 0;
      }
    } else {
      velocity.y = 0;
    }
    totalVelocity = Math.sqrt(Math.pow(velocity.x, 2) + Math.pow(velocity.y, 2) + Math.pow(velocity.z, 2));
  }

  public void resetVelocity() {
    velocity.set(0, 0, 0);
    totalVelocity = 0;
  }

  public void ballIsWall(String direction) {

    if (direction.equals("horizontal")) {
      this.velocity.y = -this.velocity.y;
    } else {
      this.velocity.x = -this.velocity.x;
    }

  }

  public float getXSlope() {
    return xSlope;
  }

  public float getYSlope() {
    return ySlope;
  }

  public Vector3 getPosition() {
    return pos;
  }

  public Vector3 getNewPosition() {
    return pos;
  }

  public Vector3 getVelocity() {
    return this.velocity;
  }

}
//wi = xVelocity
//ti = x
