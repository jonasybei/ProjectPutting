package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class TreeSpot {
  private Vector2 position = new Vector2(); //x and y values
  private final int radius = 2;

  public TreeSpot(float aX, float aY) {
    this.position.x = aX;
    this.position.y = aY;
  }

  public Vector2 getPosition() {
    return position;
  }

  public int getRadius() {
    return radius;
  }
}
