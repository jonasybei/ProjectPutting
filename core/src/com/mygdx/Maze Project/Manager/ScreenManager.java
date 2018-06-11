package com.mygdx.Manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.Screens.StartMenu;

public class ScreenManager extends Game {

  @Override
  public void create() {
    showMenuScreen();
  }

  public void showMenuScreen() {
    setScreen(new StartMenu(this));
  }

  public void exitTheGame(){
    Gdx.app.exit();;
  }
}
