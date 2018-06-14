package com.mygdx.MazeProject.Models3D;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.mygdx.MazeProject.game.Cell;

import java.util.ArrayList;

public class TransformMazeToModel3D {

  private Cell[][] maze;
  private ArrayList<ModelInstance> walls;
  private WallsGeneratror wallsGen;
  private float distanceWallToPoint = 0.4f;
  private float wallHeight = 3f;
  private float distanceToCenter;


  public TransformMazeToModel3D(Cell[][] maze) {
    this.maze = maze;
    this.wallsGen = new WallsGeneratror();
    this.walls = new ArrayList<ModelInstance>();
    this.distanceToCenter = this.maze.length / 2;


  }

  public void createWalls() {

    for (int i = 0; i < this.maze.length; i++) {
      for (int j = 0; j < this.maze.length; j++) {

        boolean[] currentCellWalls = this.maze[i][j].getCellWalls();


        if (currentCellWalls[0]) {

          ModelInstance wall = wallsGen.generateWall("horizontal");
          float y = (i - distanceWallToPoint) - distanceToCenter;
          float x = j - distanceToCenter;
          wall.transform.setTranslation(x, wallHeight, y);
          this.walls.add(wall);


        }
        if (currentCellWalls[1]) {

          ModelInstance wall = wallsGen.generateWall("horizontal");
          float y = (i + distanceWallToPoint) - distanceToCenter;
          float x = j - distanceToCenter;
          wall.transform.setTranslation(x, wallHeight, y);
          this.walls.add(wall);

        }
        if (currentCellWalls[2]) {

          ModelInstance wall = wallsGen.generateWall("vertical");
          float y = i - distanceToCenter;
          float x = (j - distanceWallToPoint) - distanceToCenter;
          wall.transform.setTranslation(x, wallHeight, y);
          this.walls.add(wall);

        }
        if (currentCellWalls[3]) {

          ModelInstance wall = wallsGen.generateWall("vertical");
          float y = i - distanceToCenter;
          float x = (j + distanceWallToPoint) - distanceToCenter;
          wall.transform.setTranslation(x, wallHeight, y);
          this.walls.add(wall);


        }
      }

    }
  }

  public ArrayList getWalls() {
    createWalls();
    return this.walls;
  }


}
