package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

public class MapFactory {


  public static Map createMap(int level) {
    Map m = new Map(
            createTreeMap(level),
            createSandMap(),
            level
    );

    return m;
  }

  private static ArrayList<TreeSpot> createTreeMap(int level) {
    ArrayList<TreeSpot> t = new ArrayList<TreeSpot>();
    Random ran = new Random();

    while (t.size() < 10) {
      float x = (float) ran.nextInt(18) - 10;
      float y = (float) ran.nextInt(18) - 10;

      if (Terrain.compute(level, x, y) >= 0 && (x != Terrain.startPos(level).x && y != Terrain.startPos(level).x) && (x != Terrain.endPos(level).x && y != Terrain.endPos(level).x)) {
        t.add(new TreeSpot(x, y));
      }

    }

    return t;
  }


  private static ArrayList<SandSpot> createSandMap() {
    ArrayList<SandSpot> s = new ArrayList<SandSpot>();
    s.add(new SandSpot(0, 3));
    s.add(new SandSpot(0, 3.5f, 3));
    s.add(new SandSpot(0, 4));
    return s;
  }

  private static boolean isArrayFull(float[] array) {
    for (float f : array) {
      if (f == 100) {
        return false;
      }
    }
    return true;
  }

}
