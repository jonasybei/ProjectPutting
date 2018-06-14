package com.mygdx.CrazyPutting.BotAlgorithms;

public class Shot {
  private int angle;
  private int power;
  private float distance;

  public Shot(int angle, int power) {
    this.angle = angle;
    this.power = power;
  }

  public int getAngle() {
    return angle;
  }

  public int getPower() {
    return power;
  }

  public void setDistance(float distance) {
    this.distance = distance;
  }

  public float getDistance() {
    return distance;
  }
}
