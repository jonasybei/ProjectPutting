package com.mygdx.managers;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.*;

public class CrazyPuttingGame extends Game {

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

  public void exitTheGame() {
    Gdx.app.exit();
  }


}
