package com.mygdx.CrazyPutting.game;

import com.badlogic.gdx.math.Vector2;

//This class computes the terrain with the use of functions. Those can be found here:
//https://docs.google.com/document/d/1ZUv9NAnBHs384mxa-9tkk39cExyGSKAK1exx1yJGvn0/edit?usp=sharing
//All terrains are meant to have a range of -10 to 10 for x, y, and z!


public class Terrain {
  public static double compute(int terrain, double x, double y) {
    int a, b, i, j;
    float c, d;
    Matrix[][] coefficient;
    Matrix current;
    float position = 0;
    if (terrain == 1) {
      //(e^x-e^y)/2000 - 5*e^(-x^2-y^2)
      return (((Math.exp(x) - Math.exp(y)) / 2000) - (5 * Math.exp(-Math.pow(x, 2) - Math.pow(y, 2))));
    } else if (terrain == 2) {
      //(x^2+y^3)/100 + 6*e^(-x^2-y^2)
      return ((Math.pow(x, 2) + Math.pow(y, 3)) / 100 + 6 * Math.exp(-Math.pow(x, 2) - Math.pow(y, 2)));
    } else if (terrain == 3) {
      //abs(-x^3-y^3+5(-e^(-(x-2)^2-(y+2)^2)*10^2.5))-e^(-x^2-y^2)*10^2.8
      //return null;
      return 1;
    } else if (terrain == 4) {
      //abs(x^2+y^3)/100+abs(6*e^(-x^2-y^2))
      return (Math.abs(Math.pow(x, 2) + Math.pow(y, 3)) / 100 + Math.abs(6 * Math.exp(-Math.pow(x, 2) - Math.pow(y, 2))));
    } else if (terrain == 5) {
      //-sin(y/5)+1.5*e^(-x^2-y^2)+0.5
      return (-Math.sin(y / 5) + 1.5 * Math.exp(-Math.pow(x, 2) - Math.pow(y, 2)) + 0.5);
    } else if (terrain == 6) {
      //abs(x/y)/2
      a = (int) (x + 10) / 4;
      b = (int) (y + 10) / 4;
      c = (float) ((x + 10) % 4) / 4;
      d = (float) ((y + 10) % 4) / 4;
      if (a == 5) a = 4;
      if (b == 5) b = 4;
      Spline.makeTerrain(a, b);
      coefficient = Spline.getTotalMatrix();
      current = coefficient[a][b];
      for (i = 0; i < 4; i++) {
        for (j = 0; j < 4; j++) {
          position = position + (float) (current.getCoefficient(i, j) * Math.pow(c, i) * Math.pow(d, j));
        }
      }
      return position;
    }
    return 1;
    //return null;
  }


  public static Vector2 startPos(int terrain) {
    switch (terrain) {
      case (1):
        return new Vector2(6, 0);
      case (2):
        return new Vector2(-8, 3);
      case (3):
        return new Vector2(-7, -7);
      case (4):
        return new Vector2(6, 4);
      case (5):
        return new Vector2(-5, -5);
      case (6):
        return new Vector2(-8, -8);
      default:
        return new Vector2(0, 0);
    }

  }

  public static Vector2 endPos(int terrain) {
    switch (terrain) {
      case (1):
        return new Vector2(-6, 2);
      case (2):
        return new Vector2(8, -1);
      case (3):
        return new Vector2(7, 8);
      case (4):
        return new Vector2(-6, -4);
      case (5):
        return new Vector2(0, 0);
      case (6):
        return new Vector2(7, 4);
      default:
        return new Vector2(0, 0);
    }

  }

//	public static SandSpot getSand(int terrain) {
//		switch (terrain) {
//			case (1): return new SandSpot();
//			default: return new Vector2(0,0);
//		}
//
//	}
}
