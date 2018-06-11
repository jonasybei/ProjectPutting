package com.mygdx.BotAlgorithms;



import com.mygdx.game.Point;

import java.util.ArrayList;

public class Subspace {
  private Point upLeft;
  private Point upRight;
  private Point downLeft;
  private Point downRight;
  private Point middle;

  public Subspace (Point upLeft, Point upRight, Point downLeft, Point downRight) {
    if((upLeft.getX() == downLeft.getX()) && (upLeft.getY() == upRight.getY()) && (upRight.getX() == downRight.getX()) && (downLeft.getY() == downRight.getY())) {
      this.upLeft = upLeft;
      this.upRight = upRight;
      this.downLeft = downLeft;
      this.downRight = downRight;
      middle = new Point((upLeft.getX() - downRight.getX())/2, (upLeft.getY() - downRight.getY())/2);
    } else {
      System.out.println("Some point is wrong!");
    }
  }

  public ArrayList<Subspace> getSubspaces(){
      ArrayList<Subspace> result = new ArrayList<Subspace>();
      Point middleUp = new Point(middle.getX(), upLeft.getY());
      Point middleLeft = new Point(upLeft.getX(), middle.getY());
      Point middleRight = new Point(downRight.getX(), middle.getY());
      Point middleDown = new Point(middle.getX(), downRight.getY());
      result.add(new Subspace(upLeft, middleUp, middleRight, middle));
      result.add(new Subspace(middleUp, upRight, middle, middleRight));
      result.add(new Subspace(middleLeft, middle, downLeft, middleDown));
      result.add(new Subspace(middle, middleRight, middleDown, downRight));
      return result;
  }

  public ArrayList<Subspace> getSubspaces(ArrayList<Subspace> spaces) {
    ArrayList<Subspace> result = new ArrayList<Subspace>();
    for(int i = 0; i < spaces.size(); i++) {
      result.addAll(spaces.get(i).getSubspaces());
    }
    return result;
  }

  public Point getMiddle() {
    return middle;
  }

}
