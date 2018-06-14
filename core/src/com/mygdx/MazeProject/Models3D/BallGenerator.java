package com.mygdx.MazeProject.Models3D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class BallGenerator {

  private final float ballDiameter = 0.5f;
  private final ModelBuilder modelBuilder = new ModelBuilder();
  private int attr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;

  public ModelInstance generateBall() {

    return new ModelInstance(modelBuilder.createSphere(ballDiameter, ballDiameter, ballDiameter, 20, 20, new Material(ColorAttribute.createDiffuse(Color.WHITE)), attr));
  }
}
