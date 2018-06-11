package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
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
import com.mygdx.WriterAndReader.Writer;
import com.mygdx.managers.CrazyPuttingGame;


public class WinScreenAuto extends InputAdapter implements Screen {
  private CrazyPuttingGame game;
  private FitViewport viewport;
  private Stage stage;
  private TextureAtlas atlas;
  private Skin skin;
  private Table table;
  private TextButton menuButton;
  private TextButton BotsButton;
  private TextButton exitButton;
  private BitmapFont font;
  private BitmapFont headingFont;
  private Label heading;
  private Label scoreText;
  private int score;
  private int level;
  private Texture background;
  private Writer writer;

  public WinScreenAuto(CrazyPuttingGame game, int score, int level) {
    this.level = level;
    this.score = score;
    this.game = game;
    this.writer = new Writer();
    this.writer.write(String.valueOf(score), "score");
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 1, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    background = new Texture("core/assets/Crazy Putting/golf.9.png");

    this.stage.act(delta);
    stage.getBatch().begin();
    stage.getBatch().draw(background, 0, 0, 1500, 900);
    stage.getBatch().end();
    this.stage.draw();
  }

  @Override
  public void show() {
    this.stage = new Stage();
    this.atlas = new TextureAtlas("core/assets/Crazy Putting/button.pack");
    this.skin = new Skin(atlas);
    this.table = new Table(skin);
    this.font = new BitmapFont();
    this.headingFont = new BitmapFont(Gdx.files.internal("core/assets/Crazy Putting/fonts/font.fnt"));
    table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    TextButtonStyle textButtonStyle = new TextButtonStyle();
    textButtonStyle.up = skin.getDrawable("button_up");
    textButtonStyle.down = skin.getDrawable("button_down");
    textButtonStyle.pressedOffsetX = 1;
    textButtonStyle.pressedOffsetY = -1;
    textButtonStyle.font = this.font;
    textButtonStyle.fontColor = Color.BLACK;

    this.menuButton = new TextButton("MENU", textButtonStyle);
    this.menuButton.pad(50);
    this.menuButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        game.showMenuScreen();

      }
    });

    this.BotsButton = new TextButton("BOTS", textButtonStyle);
    this.BotsButton.pad(50);
    this.BotsButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        game.showBotScreen();
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
    this.heading = new Label("WELL DONE ", headingStyle);
    this.heading.setFontScale(3);
    String text = "YOU SCORED " + this.score;
    this.scoreText = new Label(text, headingStyle);
    this.scoreText.setFontScale(3);


    this.table.add(heading);
    this.table.getCell(this.heading).spaceBottom(20);
    this.table.row();
    this.table.add(scoreText);
    this.table.getCell(this.scoreText).spaceBottom(70);
    this.table.row();
    this.table.add(this.menuButton).width(800f);
    this.table.getCell(this.menuButton).spaceBottom(50);
    this.table.row();
    this.table.add(this.BotsButton).width(800f);
    this.table.getCell(this.BotsButton).spaceBottom(50);
    this.table.row();
    this.table.add(this.exitButton).width(800f);
    this.stage.addActor(this.table);
    Gdx.input.setInputProcessor(stage);

    ScoreConstant.scores.add(this.score);
    ScoreConstant.levels.add(this.level);
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

