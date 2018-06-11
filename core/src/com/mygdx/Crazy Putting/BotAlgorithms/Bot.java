package com.mygdx.BotAlgorithms;

import com.badlogic.gdx.math.Vector2;

public interface Bot {
  int[] getNextShot(Vector2 ballPosition);
}
