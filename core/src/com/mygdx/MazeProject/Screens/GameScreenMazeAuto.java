package com.mygdx.MazeProject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.CrazyPutting.game.*;
import com.mygdx.CrazyPutting.managers.ScreenManager;
import com.mygdx.MazeProject.BotAlgorithms.MazeBot;
import com.mygdx.MazeProject.Models3D.TransformMazeToModel3D;
import com.mygdx.MazeProject.Models3D.WallSpot;
import com.mygdx.MazeProject.game.Cell;
import com.mygdx.MazeProject.game.MazeGeneratorABalg;

import java.util.ArrayList;

public class GameScreenMazeAuto extends InputAdapter implements Screen {
  ScreenManager manager;
  ShapeRenderer renderer;
  ExtendViewport viewport;
  SpriteBatch batch;

  private Stage stage;
  private Table table;
  private BitmapFont headingFont;
  private Label scoreHeading;
  private Label powerHeading;

  private PerspectiveCamera cam;

  private ModelInstance instance;
  private ModelInstance ball;
  private ModelInstance start;
  private ModelInstance end;
  private ModelInstance arrow3D;
  private ArrayList<ModelInstance> walls;
  private TransformMazeToModel3D tmm;
  private ArrayList<WallSpot> wallSpots;
  private String directionOfHittenWall;

  private ModelBatch modelBatch;
  private Environment environment;
  private CameraInputController camController;

  private boolean loaded = false;
  private boolean ready = false;

  private Texture powerBar;
  private float power = 1;

  private float angle = 0;

  private int level;

  private Ball rollingBall;
  private Vector3 pos;

  private int state = 0;
  private float ep;

  private Map m;

  private int score;

  private float ballDiameter = 0.5f;

  private MazeGeneratorABalg mazeGen;
  private Cell[][] maze;

  private MazeBot bot;

  public GameScreenMazeAuto(ScreenManager manager, int length) {
    this.score = 0;
    this.manager = manager;
    this.powerBar = new Texture("core/assets/CrazyPutting/pwerBar.9.png");
    this.rollingBall = new Ball();
    this.level = 3;
    this.mazeGen = new MazeGeneratorABalg(length);
    this.maze = mazeGen.getMaze();
    this.tmm = new TransformMazeToModel3D(this.maze);
    this.walls = tmm.getWalls();
    this.wallSpots = tmm.getWallsSpots();
    createLevel(level);

    this.bot = new MazeBot(this.maze, 1, this.wallSpots);

  }

  @Override
  public void render(float delta) {


    if (loaded && ready) {


      if (state == 1 && rollingBall.isStationary()) {
        System.out.println("stopped");
        System.out.println("x" + this.rollingBall.getPosition().x);
        System.out.println("y" + this.rollingBall.getPosition().y);

        Vector3 tmpPos = rollingBall.getPosition();

        rollingBall.setSafePosition(tmpPos);

        if (getDistanCeToExit() < 0.5) {
          state = 2;
          manager.showWinScreen(this.score, this.level);
        }
        rollingBall.resetVelocity();
        state = 0;
      }

      if (state == 1) {
        rollingBall.setNewPosition(m);
        pos = rollingBall.getNewPosition();


        if (pos.x > 10 || pos.x < -10 || pos.y > 10 || pos.y < -10) {
          rollingBall.resetPosition();
          this.state = 0;
        }

        if (isBallInWall()) {
          this.rollingBall.ballIsWall(this.directionOfHittenWall);
        }


        pos = rollingBall.getNewPosition();
        ball.transform.setTranslation(pos.x, (float) Terrain.compute(level, pos.x, pos.y) / 2 + this.ballDiameter, pos.y);

        Vector3 tmpPos = rollingBall.getPosition();
        float mu = 0.5f;

        rollingBall.xRungeKutta(mu);
        rollingBall.yRungeKutta(mu);
      }


      Vector3 axe = new Vector3();
      axe.x = 0;
      axe.y = 1;
      axe.z = 0;
      this.arrow3D.transform.setToRotationRad(axe, convertDegreeToRadians());


      Gdx.gl.glClearColor(0f, 0.5f, 0.5f, 1f);
      Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


      //cam.position.set(ball.nodes.get(0). .getX(), sprite.getY(), 0);
      camController.update();

      modelBatch.begin(cam);
      modelBatch.render(arrow3D, environment);
      modelBatch.render(instance, environment);
      modelBatch.render(ball, environment);
      modelBatch.render(start, environment);
      modelBatch.render(end, environment);
      for (ModelInstance wall : walls) {
        modelBatch.render(wall, environment);
      }

      modelBatch.end();


    } else if (!loaded) {
      loaded = true;
      createLevel(this.level);

    }

    this.batch.begin();

    this.batch.draw(this.powerBar, Gdx.graphics.getWidth() - 100, 0, 100, Gdx.graphics.getHeight() * this.power);
    this.batch.end();

    if (Gdx.input.isKeyPressed(Input.Keys.P)) {
      manager.exitTheGame();
    }

    if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && this.state == 0) {

      Vector2 ballPos = new Vector2();
      ballPos.x = this.rollingBall.getPosition().x;
      ballPos.y = this.rollingBall.getPosition().y;

      int[] shotingInfo = this.bot.getShot(ballPos);
      rollingBall.setVelocity(shotingInfo[1], fromDegreeToRadians(shotingInfo[0]));
      this.state = 1;
      this.score++;

    }

    this.stage = new Stage();
    this.table = new Table();
    this.headingFont = new BitmapFont(Gdx.files.internal("core/assets/CrazyPutting/fonts/font.fnt"));
    table.setBounds(150, Gdx.graphics.getHeight() - 150, 100, 100);

    String yourScore = "SCORE: " + this.score;

    Label.LabelStyle headingStyle = new Label.LabelStyle(this.headingFont, Color.BLACK);
    this.scoreHeading = new Label(yourScore, headingStyle);
    this.scoreHeading.setFontScale(2);

    String yourPower = "POWER: " + Math.round(this.power * 100) + " %";

    this.powerHeading = new Label(yourPower, headingStyle);
    this.powerHeading.setFontScale(2);


    this.table.add(this.scoreHeading);
    this.table.row();
    this.table.add(this.powerHeading);
    this.stage.addActor(this.table);

    this.stage.act(delta);
    this.stage.draw();


  }


  @Override
  public void dispose() {

  }

  @Override
  public void show() {
    renderer = new ShapeRenderer();
    batch = new SpriteBatch();

    viewport = new ExtendViewport(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGTH);
    Gdx.input.setInputProcessor(this);
  }

  @Override
  public void resize(int width, int height) {
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


  private void createLevel(int t) {

    this.m = MapFactory.createMap(t);
    rollingBall.setInitialPosition(m.getStartPos(), m);

    Vector3 safePosition = new Vector3();
    safePosition.x = m.getStartPos().x;
    safePosition.y = m.getStartPos().y;
    safePosition.z = (float) Terrain.compute(this.level, safePosition.x, safePosition.y);

    rollingBall.setSafePosition(safePosition);

    modelBatch = new ModelBatch();

    cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    cam.position.set(10f, 10f, 10f);
    cam.lookAt(0, 0, 0);
    cam.near = 1f;
    cam.far = 300f;
    cam.update();

    environment = new Environment();
    environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
    environment.add(new DirectionalLight().set(Color.WHITE, 0.2f, -1f, 0.2f));

    camController = new CameraInputController(cam);
    Gdx.input.setInputProcessor(camController);

    int scale = 4;
    float ep = 1 / (float) scale;
    int tot = scale * 20;

    int k = 2;

    ArrayList<Face> faces = new ArrayList<Face>();


    //adds the surface of the minigolf island

    Color toUse = Color.GREEN;

    for (float x = -10; x < 10; x += ep) {
      for (float y = -10; y < 10; y += ep) {
        int ax = (int) (x + 10) * scale;
        int ay = (int) (y + 10) * scale;


        faces.add(new Face(
                new Vector3(x, (float) Terrain.compute(t, x, y + ep) / k, y + ep),
                new Vector3(x + ep, (float) Terrain.compute(t, x + ep, y) / k, y),
                new Vector3(x, (float) Terrain.compute(t, x, y) / k, y),
                toUse));

        faces.add(new Face(
                new Vector3(x + ep, (float) Terrain.compute(t, x + ep, y + ep) / k, y + ep),
                new Vector3(x + ep, (float) Terrain.compute(t, x + ep, y) / k, y),
                new Vector3(x, (float) Terrain.compute(t, x, y + ep) / k, y + ep),
                toUse));

      }
    }

    //adds the 'dirt' sides to the minigolf island

    for (float x = -10; x < 10; x += ep) {
      faces.add(new Face(
              new Vector3(x, (float) Terrain.compute(t, x, -10) / k, -10),
              new Vector3(x + ep, (float) Terrain.compute(t, x + ep, -10) / k, -10),
              new Vector3(x, -10, -10),
              Color.GRAY));

      faces.add(new Face(
              new Vector3(x + ep, (float) Terrain.compute(t, x + ep, -10) / k, -10),
              new Vector3(x + ep, -10, -10),
              new Vector3(x, -10, -10),
              Color.GRAY));
    }

    for (float x = -10; x < 10; x += ep) {
      faces.add(new Face(
              new Vector3(x, (float) Terrain.compute(t, x, 10) / k, 10),
              new Vector3(x, -10, 10),
              new Vector3(x + ep, (float) Terrain.compute(t, x + ep, 10) / k, 10),
              Color.GRAY));

      faces.add(new Face(
              new Vector3(x + ep, (float) Terrain.compute(t, x + ep, 10) / k, 10),
              new Vector3(x, -10, 10),
              new Vector3(x + ep, -10, 10),
              Color.GRAY));
    }

    for (float y = -10; y < 10; y += ep) {
      faces.add(new Face(
              new Vector3(-10, (float) Terrain.compute(t, -10, y) / k, y),
              new Vector3(-10, -10, y),
              new Vector3(-10, (float) Terrain.compute(t, -10, y + ep) / k, y + ep),
              Color.GRAY));

      faces.add(new Face(
              new Vector3(-10, (float) Terrain.compute(t, -10, y + ep) / k, y + ep),
              new Vector3(-10, -10, y),
              new Vector3(-10, -10, y + ep),
              Color.GRAY));
    }

    for (float y = -10; y < 10; y += ep) {
      faces.add(new Face(
              new Vector3(10, (float) Terrain.compute(t, 10, y) / k, y),
              new Vector3(10, (float) Terrain.compute(t, 10, y + ep) / k, y + ep),
              new Vector3(10, -10, y),
              Color.GRAY));

      faces.add(new Face(
              new Vector3(10, (float) Terrain.compute(t, 10, y + ep) / k, y + ep),
              new Vector3(10, -10, y + ep),
              new Vector3(10, -10, y),
              Color.GRAY));
    }


    ModelBuilder modelBuilder = new ModelBuilder();
    modelBuilder.begin();

    int attr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;

    for (Face face : faces) {

      modelBuilder.part(
              "face" + face.getID(),
              GL20.GL_TRIANGLES,
              attr,
              new Material(ColorAttribute.createDiffuse(face.getColor()))
      ).triangle(face.getA(), face.getB(), face.getC());

    }

    instance = new ModelInstance(modelBuilder.end(), 0, 0, 0);

    start = new ModelInstance(modelBuilder.createBox(0.3f, 0.1f, 0.3f, new Material(ColorAttribute.createDiffuse(Color.WHITE)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal));

    end = new ModelInstance(modelBuilder.createCylinder(0.1f, 4f, 0.1f, 10, new Material(ColorAttribute.createDiffuse(Color.WHITE)), attr));

    ball = new ModelInstance(modelBuilder.createSphere(ballDiameter, ballDiameter, ballDiameter, 20, 20, new Material(ColorAttribute.createDiffuse(Color.WHITE)), attr));

    arrow3D = new ModelInstance(modelBuilder.createArrow(0, getArrowHeight(), 0, 2, getArrowHeight(), 0, 0.1f, 0.1f, 5, GL20.GL_TRIANGLES, new Material(ColorAttribute.createDiffuse(Color.RED)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal));


    Vector2 s = m.getStartPos();
    Vector2 e = m.getEndPos();


    start.transform.setTranslation(s.x, (float) Terrain.compute(t, s.x, s.y) / k, s.y);
    end.transform.setTranslation(e.x, (float) Terrain.compute(t, e.x, s.y) / k, e.y);
    ball.transform.setTranslation(s.x, (float) Terrain.compute(t, s.x, s.y) / k + ballDiameter / 2, s.y);


    //allow the level to be rendered
    ready = true;
  }

  public float fromDegreeToRadians(float degree) {
    float radians = (float) ((degree * Math.PI) / 180);
    return radians;
  }

  public float convertDegreeToRadians() {
    float radians = (float) (this.angle * (Math.PI / 180));
    return radians;
  }


  //method to make some tests
  public float getRightAngle(float angle) {
    return Math.abs(360 - angle);
  }

  public float getArrowHeight() {
    double height = Terrain.compute(this.level, 0f, 0f);
    if (height < 0) {
      height = 0;
    }

    return (float) height;
  }


  public boolean isBallInWall() {
    Vector2 rollingBallPos = new Vector2();
    rollingBallPos.x = this.rollingBall.getPosition().x;
    rollingBallPos.y = this.rollingBall.getPosition().y;
    String direction;

    for (WallSpot wall : this.wallSpots) {
      direction = wall.getDirection();

      if (direction.equals("vertical")) {

        float leftBound = wall.getPos().x - 1;
        float rightBound = wall.getPos().x + 1;
        float upBound = wall.getPos().y + 0.1f;
        float downBound = wall.getPos().y - 0.1f;


        if (rollingBallPos.x > leftBound && rollingBallPos.x < rightBound && rollingBallPos.y > downBound && rollingBallPos.y < upBound) {
          //System.out.printf("ball is in wall");
          //System.out.println(direction);
          this.directionOfHittenWall = direction;
          return true;
        }

      } else {

        float leftBound = wall.getPos().x - 0.1f;
        float rightBound = wall.getPos().x + 0.1f;
        float upBound = wall.getPos().y + 1f;
        float downBound = wall.getPos().y - 1f;


        if (rollingBallPos.x > leftBound && rollingBallPos.x < rightBound && rollingBallPos.y > downBound && rollingBallPos.y < upBound) {
          //System.out.printf("ball is in wall");
          //System.out.println(direction);
          this.directionOfHittenWall = direction;
          return true;
        }
      }
    }
    return false;
  }

  public float[] getCellRenderPosition(int y, int x) {

    float distanceToCenter = this.maze.length / 2;
    float[] renderPos = new float[2];

    renderPos[0] = (y - distanceToCenter) * 2;
    renderPos[1] = (x - distanceToCenter) * 2;

    return renderPos;
  }

  public float getDistanCeToExit() {

    int mazeLengthIndex = this.maze.length - 1;

    Vector3 exitPos = new Vector3();
    exitPos.y = getCellRenderPosition(mazeLengthIndex, mazeLengthIndex)[0];
    exitPos.x = getCellRenderPosition(mazeLengthIndex, mazeLengthIndex)[1];
    exitPos.z = 0;

    return this.rollingBall.getPosition().dst(exitPos);
  }

}
