package com.mygdx.CrazyPutting.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder.VertexInfo;
import com.badlogic.gdx.math.Vector3;


class Face {

  private VertexInfo viA;
  private VertexInfo viB;
  private VertexInfo viC;

  private Vector3 p1;
  private Vector3 p2;
  private Vector3 p3;
  private int id;
  private static int ID = 0;
  private Color color;

  Face(Vector3 a, Vector3 b, Vector3 c, Color color) {
    p1 = a;
    p2 = b;
    p3 = c;

    this.color = color;

    this.id = ID;
    ID++;
  }

  public int getID() {
    return id;
  }

  public Vector3 getA() {
    return p1;
  }

  public Vector3 getB() {
    return p2;
  }

  public Vector3 getC() {
    return p3;
  }

  public Color getColor() {
    return color;
  }
}