package com.mygdx.MazeProject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.CrazyPutting.managers.ScreenManager;


public class MazeLengthScreen extends InputAdapter implements Screen {
  private Stage stage;
  private TextureAtlas atlas;
  private Skin skin;
  private Table table;
  private TextButton mazeLength5Button;
  private TextButton mazeLength6Button;
  private TextButton mazeLength7Button;
  private TextButton mazeLength8Button;
  private TextButton mazeLength9Button;
  private TextButton mazeLength10Button;
  private TextButton exitButton;
  private BitmapFont font;
  private Label heading;
  private Texture background;
  private ScreenManager manager;


  public MazeLengthScreen(ScreenManager manager) {
    this.manager = manager;
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 1, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    background = new Texture("core/assets/MazeProject/backgroundPics/MazePic3.jpg");


    this.stage.act(delta);
    stage.getBatch().begin();
    stage.getBatch().draw(background, 0, 0, LwjglApplicationConfiguration.getDesktopDisplayMode().width, LwjglApplicationConfiguration.getDesktopDisplayMode().height);
    stage.getBatch().end();
    this.stage.draw();
  }

  @Override
  public void show() {
    this.stage = new Stage();
    this.atlas = new TextureAtlas();
    this.skin = new Skin(atlas);
    this.table = new Table(skin);
    this.font = new BitmapFont(Gdx.files.internal("core/assets/MazeProject/fonts/font1.fnt"));
    table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    TextButtonStyle textButtonStyle = new TextButtonStyle();
    //textButtonStyle.up = skin.getDrawable("button_up");
    //textButtonStyle.down = skin.getDrawable("button_down");
    textButtonStyle.pressedOffsetX = 1;
    textButtonStyle.pressedOffsetY = -1;
    textButtonStyle.font = this.font;
    textButtonStyle.fontColor = Color.BLACK;

    this.mazeLength5Button = new TextButton("LENGTH 5", textButtonStyle);
    this.mazeLength5Button.pad(20);
    this.mazeLength5Button.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        manager.showGameScreenMaze(5);

      }
    });

    this.mazeLength6Button = new TextButton("LENGTH 6", textButtonStyle);
    this.mazeLength6Button.pad(20);
    this.mazeLength6Button.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        manager.showGameScreenMaze(6);

      }
    });

    this.mazeLength7Button = new TextButton("LENGTH 7", textButtonStyle);
    this.mazeLength7Button.pad(20);
    this.mazeLength7Button.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        manager.showGameScreenMaze(7);

      }
    });

    this.mazeLength8Button = new TextButton("LENGTH 8", textButtonStyle);
    this.mazeLength8Button.pad(20);
    this.mazeLength8Button.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        manager.showGameScreenMaze(8);

      }
    });

    this.mazeLength9Button = new TextButton("LENGTH 9", textButtonStyle);
    this.mazeLength9Button.pad(20);
    this.mazeLength9Button.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        manager.showGameScreenMaze(9);

      }
    });

    this.mazeLength10Button = new TextButton("LENGTH 10", textButtonStyle);
    this.mazeLength10Button.pad(20);
    this.mazeLength10Button.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        manager.showGameScreenMaze(10);

      }
    });


    this.exitButton = new TextButton("EXIT", textButtonStyle);
    this.exitButton.pad(20);
    this.exitButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        manager.exitTheGame();
      }
    });


    LabelStyle headingStyle = new Label.LabelStyle(this.font, Color.BLACK);
    this.heading = new Label("MAZE PROJECT", headingStyle);
    this.heading.setFontScale(2);


    this.table.add(heading);
    this.table.getCell(this.heading).spaceBottom(100);
    this.table.row();
    this.table.add(this.mazeLength5Button).width(800f);
    this.table.getCell(this.mazeLength5Button).spaceBottom(20);
    this.table.row();
    this.table.add(this.mazeLength6Button).width(800f);
    this.table.getCell(this.mazeLength6Button).spaceBottom(20);
    this.table.row();
    this.table.add(this.mazeLength7Button).width(800f);
    this.table.getCell(this.mazeLength7Button).spaceBottom(20);
    this.table.row();
    this.table.add(this.mazeLength8Button).width(800f);
    this.table.getCell(this.mazeLength8Button).spaceBottom(20);
    this.table.row();
    this.table.add(this.mazeLength9Button).width(800f);
    this.table.getCell(this.mazeLength9Button).spaceBottom(20);
    this.table.row();
    this.table.add(this.mazeLength10Button).width(800f);
    this.table.getCell(this.mazeLength10Button).spaceBottom(20);
    this.table.row();
    this.table.add(this.exitButton).width(800f);
    this.stage.addActor(this.table);
    Gdx.input.setInputProcessor(stage);

  }


  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return true;
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void hide() {
  }

  @Override
  public void dispose() {
  }

  @Override
  public void resize(int width, int height) {
  }
}

