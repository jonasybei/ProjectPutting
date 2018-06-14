package com.mygdx.MazeProject.Models3D;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.mygdx.MazeProject.game.Cell;

import java.util.ArrayList;

public class TransformMazeToModel3D {

  private Cell[][] maze;
  private ArrayList<ModelInstance> walls;
  private WallsGeneratror wallsGen;
  private ArrayList<WallSpot> wallSpots;
  private float distanceWallToPoint = 0.9f;
  private float wallHeight = 2f;
  private float distanceToCenter;


  public TransformMazeToModel3D(Cell[][] maze) {
    this.maze = maze;
    this.wallsGen = new WallsGeneratror();
    this.walls = new ArrayList<ModelInstance>();
    this.wallSpots = new ArrayList<WallSpot>();
    this.distanceToCenter = this.maze.length / 2;


  }

  public void createWalls() {

    for (int i = 0; i < this.maze.length; i++) {
      for (int j = 0; j < this.maze.length; j++) {

        float y = (i - distanceToCenter) * 2;
        float x = (j - distanceToCenter) * 2;

        boolean[] currentCellWalls = this.maze[i][j].getCellWalls();


        if (currentCellWalls[0]) {

          ModelInstance wall = wallsGen.generateWall("horizontal");
          float ya = y - distanceWallToPoint;
          float xa = x;
          wall.transform.setTranslation(xa, wallHeight, ya);
          this.walls.add(wall);

          WallSpot wallSpot = new WallSpot(ya, xa, "horizontal");
          this.wallSpots.add(wallSpot);


        }
        if (currentCellWalls[1]) {

          ModelInstance wall = wallsGen.generateWall("horizontal");
          float ya = y + distanceWallToPoint;
          float xa = x;
          wall.transform.setTranslation(xa, wallHeight, ya);
          this.walls.add(wall);

          WallSpot wallSpot = new WallSpot(ya, xa, "horizontal");
          this.wallSpots.add(wallSpot);

        }
        if (currentCellWalls[2]) {

          ModelInstance wall = wallsGen.generateWall("vertical");
          float ya = y;
          float xa = x - distanceWallToPoint;
          wall.transform.setTranslation(xa, wallHeight, ya);
          this.walls.add(wall);

          WallSpot wallSpot = new WallSpot(ya, xa, "vertical");
          this.wallSpots.add(wallSpot);

        }
        if (currentCellWalls[3]) {

          ModelInstance wall = wallsGen.generateWall("vertical");
          float ya = y;
          float xa = x + distanceWallToPoint;
          wall.transform.setTranslation(xa, wallHeight, ya);
          this.walls.add(wall);

          WallSpot wallSpot = new WallSpot(ya, xa, "vertical");
          this.wallSpots.add(wallSpot);

        }
      }

    }
  }

  public ArrayList getWalls() {
    createWalls();
    return this.walls;
  }

  public ArrayList getWallsSpots() {
    return this.wallSpots;
  }


}
