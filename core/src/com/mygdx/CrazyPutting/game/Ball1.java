package com.mygdx.CrazyPutting.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Ball1 {
  private final double size = 0.5;
  private final int mass = 1;
  private final double g = 9.81; // the gravity accelertion
  private final double h = 0.0125;
  private final double gravityForce = this.mass * this.g;
  private Vector3 pos = new Vector3(); // x and y values
  private Vector3 safePosition = new Vector3();
  private Vector3 velocity = new Vector3();
  private Vector3 lastPos = new Vector3();
  private double lastz;
  private double totalVelocity; //in m/s
  private double alfa;
  private double acceleration; // in m/s2
  private boolean target;
  private int stopN = 0;
  private Map m;
  private float slopX;
  private float slopY;
  private float frictX;
  private float frictY;


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

  public void resetPosition() {
    pos.x = safePosition.x;
    pos.y = safePosition.y;
    pos.z = (float) Terrain.compute(2, pos.x, pos.y);

    velocity.set(0, 0, 0);
    totalVelocity = 0;
  }

  public void setVelocity(float input, float alfa) {
    totalVelocity = (input);

    //velocity.x=(float)(Math.abs(totalVelocity*Math.cos(alfa)));
    //velocity.y=(float)(Math.abs(totalVelocity*Math.sin(alfa)));
    velocity.x = (float) (totalVelocity * Math.cos(alfa));
    velocity.y = (float) (totalVelocity * Math.sin(alfa));
    velocity.z = 0;
  }

  public void setNewPosition(Map m) {
    lastPos.x = pos.x;
    lastPos.y = pos.y;
    lastPos.z = pos.z;
    pos.x = (float) (pos.x + h * velocity.x);
    pos.y = (float) (pos.y + h * velocity.y);
    pos.z = (float) (Terrain.compute(m.getTerrain(), pos.x, pos.y));
  }

  public boolean isStationary() {
    if (totalVelocity <= 0.5)
      stopN++;
    else
      stopN = 0;

    return stopN > 10;
  }

  public Vector3 getNewPosition() {
    return pos;
  }

  public void setNewVelocity(double mu) {

    Vector3[] array = pointsAroundTheBall();
    this.frictX = (float) (mu * g * h);
    this.frictY = (float) (mu * g * h);
    this.slopX = (float) ((h * g * Math.sin(Math.atan((array[0].z - array[2].z) / 2))) * 2);
    this.slopY = (float) -((h * g * Math.sin(Math.atan((array[1].z - array[3].z) / 2))) * 2);

    if (velocity.x > 0 && velocity.x - frictX > 0) {

      velocity.x = (float) (velocity.x - frictX);
      velocity.x = velocity.x - slopX;

    } else if (velocity.x > 0 && velocity.x - frictX < 0) {
      if (Math.abs(slopX) > frictX) {
        velocity.x = velocity.x - slopX;
      } else {
        velocity.x = 0;
      }

    } else if (velocity.x < 0 && velocity.x + frictX < 0) {

      velocity.x = (float) (velocity.x + frictX);
      velocity.x = velocity.x + slopX;

    } else if (velocity.x < 0 && velocity.x + frictX > 0) {
      if (Math.abs(slopX) > frictX) {
        velocity.x = velocity.x + slopX;
      } else {
        velocity.x = 0;
      }
    }


    if (velocity.y > 0 && velocity.y - frictY > 0) {

      velocity.y = (float) (velocity.y - frictY);
      velocity.y = velocity.y - slopY;

    } else if (velocity.y > 0 && velocity.y - frictY <= 0) {
      if (Math.abs(slopY) > frictY) {
        velocity.y = velocity.y - slopY;
      } else {
        velocity.y = 0;
      }

    } else if (velocity.y < 0 && velocity.y + frictY < 0) {

      velocity.y = (float) (velocity.y + frictY);
      velocity.y = velocity.y + slopY;

    } else if (velocity.y < 0 && velocity.y + frictY > 0) {
      if (Math.abs(slopY) > frictY) {
        velocity.y = velocity.y + slopY;
      } else {
        velocity.y = 0;
      }

    }


    //if(velocity.x > 0 || velocity.y > 0) {
    //velocity.z = (float) (velocity.z - g * h * (pos.z - lastPos.z));
    // velocity.z = (float) (velocity.z - g * h * (pos.z - lastPos.z));
    //}else{
    velocity.z = 0;
    //}
    totalVelocity = Math.sqrt(Math.pow(velocity.x, 2) + Math.pow(velocity.y, 2) + Math.pow(velocity.z, 2));

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

  public double getSize() {
    return size;
  }

  public double getMass() {
    return mass;
  }

  public Vector3 getPosition() {
    return pos;
  }

  public Vector3 getVelocity() {
    return velocity;
  }

  public double getAcceleration() {
    return acceleration;
  }

  public void resetVelocity() {
    velocity.set(0, 0, 0);
    totalVelocity = 0;
  }
}
