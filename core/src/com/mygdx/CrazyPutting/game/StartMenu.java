package com.mygdx.CrazyPutting.game;

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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.CrazyPutting.managers.ScreenManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class StartMenu extends InputAdapter implements Screen {
  private ScreenManager game;
  private FitViewport viewport;
  private Stage stage;
  private TextureAtlas atlas;
  private Skin skin;
  private Table table;
  private TextButton playButton;
  private TextButton scoreButton;
  private TextButton exitButton;
  private BitmapFont font;
  private BitmapFont headingFont;
  private Label heading;
  private Texture background;


  public StartMenu(ScreenManager game) {
    this.game = game;
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 1, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    background = new Texture("core/assets/CrazyPutting/golfBack.jpg");


    this.stage.act(delta);
    stage.getBatch().begin();
    stage.getBatch().draw(background, 0, 0, LwjglApplicationConfiguration.getDesktopDisplayMode().width, LwjglApplicationConfiguration.getDesktopDisplayMode().height);
    stage.getBatch().end();
    this.stage.draw();
  }

  @Override
  public void show() {
    this.stage = new Stage();
    this.atlas = new TextureAtlas("core/assets/CrazyPutting/button.pack");
    this.skin = new Skin(atlas);
    this.table = new Table(skin);
    this.font = new BitmapFont();
    this.headingFont = new BitmapFont(Gdx.files.internal("core/assets/CrazyPutting/fonts/font.fnt"));
    table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    TextButtonStyle textButtonStyle = new TextButtonStyle();
    textButtonStyle.up = skin.getDrawable("button_up");
    textButtonStyle.down = skin.getDrawable("button_down");
    textButtonStyle.pressedOffsetX = 1;
    textButtonStyle.pressedOffsetY = -1;
    textButtonStyle.font = this.font;
    textButtonStyle.fontColor = Color.BLACK;

    this.playButton = new TextButton("PLAY", textButtonStyle);
    this.playButton.pad(50);
    this.playButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        game.showChooseProjectScreen();

      }
    });

    this.scoreButton = new TextButton("SCORES", textButtonStyle);
    this.scoreButton.pad(50);
    this.scoreButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        try {
          Desktop.getDesktop().open(new File("C:\\Users\\Matteo\\University Maastricht\\PROJECTS\\Project.Putting\\core\\src\\com\\mygdx\\CrazyPutting\\WriterAndReader\\ScoreInfo.txtt"));
          System.out.println("Textfile was not found.");
        } catch (IOException e) {
          System.out.println("file not found");
        }
      }
    });

    this.exitButton = new TextButton("EXIT", textButtonStyle);
    this.exitButton.pad(50);
    this.exitButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        game.exitTheGame();
      }
    });


    LabelStyle headingStyle = new Label.LabelStyle(this.headingFont, Color.BLACK);
    this.heading = new Label("CRAZY PUTTING", headingStyle);
    this.heading.setFontScale(4);


    this.table.add(heading);
    this.table.getCell(this.heading).spaceBottom(100);
    this.table.row();
    this.table.add(this.playButton).width(800f);
    this.table.getCell(this.playButton).spaceBottom(50);
    this.table.row();
    this.table.add(this.scoreButton).width(800f);
    this.table.getCell(this.scoreButton).spaceBottom(50);
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

