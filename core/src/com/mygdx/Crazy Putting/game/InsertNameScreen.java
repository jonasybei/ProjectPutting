package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.WriterAndReader.Writer;
import com.mygdx.managers.CrazyPuttingGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InsertNameScreen extends InputAdapter implements Screen {
  private CrazyPuttingGame game;
  private Stage stage;
  private TextureAtlas atlas;
  private Skin skin;
  private Table table;
  private BitmapFont font;
  private BitmapFont headingFont;
  private Label heading;
  private Texture background;
  private boolean name = false;
  private Writer writer;


  public InsertNameScreen(CrazyPuttingGame game) {
    this.writer = new Writer();
    this.game = game;
    insertName();
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 1, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    this.background = new Texture("core/assets/golf.9.png");

    if (this.name == true) {
      this.game.showLevelScreen();
    }

    this.stage.act(delta);
    stage.getBatch().begin();
    stage.getBatch().draw(background, 0, 0, 1500, 900);
    stage.getBatch().end();
    this.stage.draw();
  }

  @Override
  public void show() {
    this.stage = new Stage();
    this.atlas = new TextureAtlas("C:\\Users\\matte.LAPTOP-FLG8V3QC\\Documents\\University Maastricht\\PROJECTS\\Project.Putting\\core\\assets\\button.pack");
    this.skin = new Skin(atlas);
    //this.skin2 = new Skin(Gdx.files.internal("core/assets/uiskin.json"));
    this.table = new Table(skin);
    this.font = new BitmapFont();
    this.headingFont = new BitmapFont(Gdx.files.internal("core/assets/fonts/font.fnt"));
    //this.area = new TextField("hello",this.skin);

    table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    TextButtonStyle textButtonStyle = new TextButtonStyle();
    textButtonStyle.up = skin.getDrawable("button_up");
    textButtonStyle.down = skin.getDrawable("button_down");
    textButtonStyle.pressedOffsetX = 1;
    textButtonStyle.pressedOffsetY = -1;
    textButtonStyle.font = this.font;
    textButtonStyle.fontColor = Color.BLACK;


    LabelStyle headingStyle = new Label.LabelStyle(this.headingFont, Color.BLACK);
    this.heading = new Label("PLEASE TYPE YOUR NAME", headingStyle);
    this.heading.setFontScale(3);


    this.table.add(heading);
    this.table.getCell(this.heading).spaceBottom(50);
    this.table.row();
    this.stage.addActor(this.table);
    Gdx.input.setInputProcessor(stage);


  }

  public void insertName() {
    final JFrame frame1 = new JFrame();
    final int FRAME_HEIGTH = 500;
    final int FRAME_WIDTH = 500;
    frame1.setSize(FRAME_WIDTH, FRAME_HEIGTH);
    frame1.setTitle("Insert your name");
    //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    final JTextField usertext = new JTextField(10);
    JLabel username = new JLabel("Username: ");
    JButton donebutton = new JButton("Done");

    class CalculateListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
        String getusername = usertext.getText();
        writer.write(getusername, "name");
        name = true;
        frame1.dispose();

      }
    }


    ActionListener listener = new CalculateListener();
    donebutton.addActionListener(listener);
    panel.add(username);
    panel.add(usertext);
    panel.add(donebutton);
    frame1.add(panel);
    frame1.setVisible(true);
    frame1.getRootPane().setDefaultButton(donebutton);

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

