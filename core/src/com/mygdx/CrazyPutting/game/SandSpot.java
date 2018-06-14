package com.mygdx.CrazyPutting.game;

import com.badlogic.gdx.math.Vector2;

public class SandSpot {
  private Vector2 position;
  private float radius;

  SandSpot(float aX, float aY, float r) {
    position = new Vector2(aX, aY);
    radius = r;
  }

  SandSpot(float aX, float aY) {
    position = new Vector2(aX, aY);
    radius = 2;
  }

  public Vector2 getPosition() {
    return position;
  }

  public float getRadius() {
    return radius;
  }
}
