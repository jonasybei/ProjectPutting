package com.mygdx.CrazyPutting.managers;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.CrazyPutting.game.*;
import com.mygdx.MazeProject.Screens.GameScreenMazeAuto;
import com.mygdx.MazeProject.Screens.MazeLengthScreen;

public class ScreenManager extends Game {

  private GameScreen game;
  private GameScreenAuto autoGame;

  @Override
  public void create() {
    showMenuScreen();
  }

  public void showMenuScreen() {
    setScreen(new StartMenu(this));
  }

  public void showGameScreen(int level) {
    this.game = new GameScreen(this, level);
    setScreen(this.game);
  }

  public void showModeScreen() {
    setScreen(new ModeScreen(this));
  }

  public void showBotScreen() {
    setScreen(new BotScreen(this));
  }

  public void showLevelScreen() {
    setScreen(new LevelScreen(this));
  }

  public void showNameScreen() {
    setScreen(new InsertNameScreen(this));
  }

  public void showWinScreen(int score, int level) {
    setScreen(new WinScreen(this, score, level));
  }

  public void showWinScreenAuto(int score, int level) {
    setScreen(new WinScreenAuto(this, score, level));
  }


  public void showLevelScreenAuto(int alg) {
    setScreen(new LevelScreenAuto(this, alg));
  }

  public void showPauseScreen() {
    setScreen(new PauseScreen(this));
  }

  public void showPauseScreenAuto() {
    setScreen(new PauseScreenAuto(this));
  }

  public void showGameScreenAuto(int level, int alg) {
    this.autoGame = new GameScreenAuto(this, level, alg);
    setScreen(this.autoGame);
  }

  public void resumeGameScreen() {
    setScreen(this.game);
  }

  public void showGameScreenMaze(int length) {
    setScreen(new GameScreenMazeAuto(this, length));
  }

  public void showMazeLengthScreen() {
    setScreen(new MazeLengthScreen(this));
  }

  public void showChooseProjectScreen() {
    setScreen(new ChooseProjectScreen(this));
  }


  public void exitTheGame() {
    Gdx.app.exit();
  }


}
