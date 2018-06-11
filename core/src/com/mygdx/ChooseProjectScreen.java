package com.mygdx;

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
import com.mygdx.Manager.ScreenManager;


public class ChooseProjectScreen extends InputAdapter implements Screen {
  private Stage stage;
  private TextureAtlas atlas;
  private Skin skin;
  private Table table;
  private TextButton projectPuttingButton;
  private TextButton projectMazeButton;
  private TextButton exitButton;
  private BitmapFont font;
  private Label heading;
  private Texture background;
  private ScreenManager manager;


  public ChooseProjectScreen(ScreenManager game) {
    this.manager = manager;
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 1, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    background = new Texture("core/assets/backgroundPics/MazePic3.jpg");


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
    this.font = new BitmapFont(Gdx.files.internal("core/assets/Fonts/font1.fnt"));
    table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    TextButtonStyle textButtonStyle = new TextButtonStyle();
    //textButtonStyle.up = skin.getDrawable("button_up");
    //textButtonStyle.down = skin.getDrawable("button_down");
    textButtonStyle.pressedOffsetX = 1;
    textButtonStyle.pressedOffsetY = -1;
    textButtonStyle.font = this.font;
    textButtonStyle.fontColor = Color.BLACK;

    this.projectPuttingButton = new TextButton("Putting project", textButtonStyle);
    this.projectPuttingButton.pad(50);
    this.projectPuttingButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {


      }
    });

    this.projectMazeButton = new TextButton("Maze project", textButtonStyle);
    this.projectMazeButton.pad(50);
    this.projectMazeButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {


      }
    });




    this.exitButton = new TextButton("EXIT", textButtonStyle);
    this.exitButton.pad(50);
    this.exitButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        manager.exitTheGame();
      }
    });


    LabelStyle headingStyle = new Label.LabelStyle(this.font, Color.BLACK);
    this.heading = new Label("CHOOSE THE PROJECT", headingStyle);
    this.heading.setFontScale(2);


    this.table.add(heading);
    this.table.getCell(this.heading).spaceBottom(100);
    this.table.row();
    this.table.add(this.projectPuttingButton).width(800f);
    this.table.getCell(this.projectPuttingButton).spaceBottom(50);
    this.table.row();
    this.table.add(this.projectMazeButton).width(800f);
    this.table.getCell(this.projectMazeButton  ).spaceBottom(50);
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

