package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.MazeProject.Manager.ScreenManagerMaze;


public class DesktopLauncher {
  public static void main(String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "CrazyPutting";
    config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
    config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
    config.fullscreen = true;
    //new LwjglApplication(new ScreenManager(), config);
    //new LwjglApplication(new ScreenManagerPutting(), config);
    new LwjglApplication(new ScreenManagerMaze(), config);
  }
}
