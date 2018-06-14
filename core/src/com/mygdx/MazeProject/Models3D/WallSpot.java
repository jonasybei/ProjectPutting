package com.mygdx.MazeProject.Models3D;

import com.badlogic.gdx.math.Vector2;

public class WallSpot {

  private Vector2 pos;
  private String direction;

  public WallSpot(float yPos, float xPos, String direction) {
    this.pos = new Vector2();
    this.pos.x = yPos;
    this.pos.y = xPos;

    this.direction = direction;
  }

  public Vector2 getPos() {
    return pos;
  }

  public String getDirection() {
    return direction;
  }
}
