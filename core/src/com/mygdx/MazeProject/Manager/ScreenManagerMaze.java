package com.mygdx.MazeProject.Manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.MazeProject.Screens.GameScreen;
import com.mygdx.MazeProject.Screens.StartMenu;

public class ScreenManagerMaze extends Game {

  @Override
  public void create() {
    showMenuScreen();
  }

  public void showMenuScreen() {
    setScreen(new StartMenu(this));
  }

  public void showGameScreen() {
    setScreen(new GameScreen(this));
  }

  public void exitTheGame() {
    Gdx.app.exit();
    ;
  }
}
