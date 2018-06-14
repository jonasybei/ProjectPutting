package com.mygdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class ScreenManager extends Game implements Managers {

  @Override
  public void create() {
    showChooseProjectScreen();
  }

  public void showChooseProjectScreen() {
    setScreen(new ChooseProjectScreen(this));
  }

  public void exitTheGame() {
    Gdx.app.exit();
  }

}
