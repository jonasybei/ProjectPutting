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
import com.mygdx.CrazyPutting.WriterAndReader.Writer;
import com.mygdx.CrazyPutting.managers.ScreenManagerPutting;


public class BotScreen extends InputAdapter implements Screen {
  private ScreenManagerPutting game;
  private Stage stage;
  private TextureAtlas atlas;
  private Skin skin;
  private Table table;
  private TextButton alg1;
  private TextButton alg2;
  private TextButton alg3;
  private TextButton alg4;
  private TextButton back;
  private BitmapFont font;
  private BitmapFont headingFont;
  private Label heading;
  private Texture background;
  private Writer writer;


  public BotScreen(ScreenManagerPutting game) {
    this.game = game;
    this.writer = new Writer();
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

    this.alg1 = new TextButton("Optimized Greedy-Brute Force algorithm", textButtonStyle);
    this.alg1.pad(30);
    this.alg1.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        writer.write("Optimized Greedy-Brute Force algorithm", "name");
        game.showLevelScreenAuto(1);


      }
    });

    this.alg2 = new TextButton("Greedy-Brute Force algorithm", textButtonStyle);
    this.alg2.pad(30);
    this.alg2.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        writer.write("Greedy-Brute Force algorithm", "name");
        game.showLevelScreenAuto(2);

      }
    });

    this.alg3 = new TextButton("Hill climb Algorithm ", textButtonStyle);
    this.alg3.pad(30);
    this.alg3.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        writer.write("Hill climb Algorithm ", "name");
        game.showLevelScreenAuto(3);
      }
    });

    this.alg4 = new TextButton("Divide and conquer", textButtonStyle);
    this.alg4.pad(30);
    this.alg4.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        writer.write("Divide and conquer", "name");
        game.showLevelScreenAuto(4);
      }
    });


    this.back = new TextButton("BACK", textButtonStyle);
    this.back.pad(30);
    this.back.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {

        game.showModeScreen();
      }
    });


    LabelStyle headingStyle = new Label.LabelStyle(this.headingFont, Color.BLACK);
    this.heading = new Label("CRAZY PUTTING", headingStyle);
    this.heading.setFontScale(3);


    this.table.add(heading);
    this.table.getCell(this.heading).spaceBottom(100);
    this.table.row();
    this.table.add(this.alg1).width(800f);
    this.table.getCell(this.alg1).spaceBottom(50);
    this.table.row();
    this.table.add(this.alg2).width(800f);
    this.table.getCell(this.alg2).spaceBottom(50);
    this.table.row();
    this.table.add(this.alg3).width(800f);
    this.table.getCell(this.alg3).spaceBottom(50);
    this.table.row();
    this.table.add(this.alg4).width(800f);
    this.table.getCell(this.alg4).spaceBottom(50);
    this.table.row();
    this.table.add(this.back).width(800f);
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

