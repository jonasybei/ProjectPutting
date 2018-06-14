package com.mygdx.MazeProject.Models3D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class WallsGeneratror {

  private final float wallWidth = 0.2f;
  private final float wallHeight = 4f;
  private final float wallLength = 2f;
  private final ModelBuilder modelBuilder = new ModelBuilder();

  public ModelInstance generateWall(String direction) {
    if (direction.equals("horizontal")) {
      return new ModelInstance(modelBuilder.createBox(wallLength, wallHeight, wallWidth, new Material(ColorAttribute.createDiffuse(Color.BLUE)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal));

    } else {
      return new ModelInstance(modelBuilder.createBox(wallWidth, wallHeight, wallLength, new Material(ColorAttribute.createDiffuse(Color.WHITE)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal));

    }
  }
}

