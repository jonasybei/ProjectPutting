package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.managers.CrazyPuttingGame;


public class DesktopLauncher {
  public static void main(String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Crazy Putting";
    config.width = 1500;
    config.height = 900;
    //config.fullscreen = true;
    new LwjglApplication(new CrazyPuttingGame(), config);
  }
}
