package com.xlip.threedtemp.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Objects.GameObject;
import com.xlip.threedtemp.Shader.d2.MyShaderProgram;
import com.xlip.threedtemp.World.States.GameState;

import java.util.HashMap;

import static com.badlogic.gdx.Gdx.gl;


/**
 * Created by Arif on 13.07.2017.
 */

public class World implements MyInputProcessor.MyInputCallback {
    public PerspectiveCamera camera;
    public ModelBatch modelBatch;
    public Array<GameObject> objects;

    public Environment environment;

    private MyShaderProgram myShaderProgram;
    private FrameBuffer frameBuffer;
    private SpriteBatch spriteBatch;

    private GameState gameState;
    private ScreenCallbacks screenCallbacks;
    protected HashMap<Menu, String> innerMenus;

    private float frustumWidth;
    private float frustumHeight;
    private Color clearColor;
    protected ShapeRenderer shapeRenderer;


    public World() {
        environment = new Environment();
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.near = 1.5f;
        camera.far = 100;
        camera.position.set(0, 10, -10);
        camera.lookAt(0, 0, 0);
        camera.near = 0.0001f;
        camera.update();
        //setMyShaderProgram(new MyShaderProgram(Assets.defaultShaderVertex,Assets.barrelBlurFragment));

        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setAutoShapeType(true);


        this.gameState = GameState.READY;
        objects = new Array<GameObject>();
        innerMenus = new HashMap<Menu, String>();
        modelBatch = new ModelBatch();
        this.clearColor = new Color(1, 1, 1, 1);
    }

    public void render(float delta) {
        gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        if (myShaderProgram != null) {
            frameBuffer.begin();
        }
        for (GameObject g :
                objects) {
            g.update(delta);
        }

        for (GameObject g :
                objects) {
            if (g.isDisposed()) {
                objects.removeValue(g, false);
                g.dispose();
            }

        }

        drawBackGround(delta);

        camera.update();

        modelBatch.begin(camera);

        modelBatch.render(objects, environment);

        modelBatch.end();

        for (Menu menu :
                innerMenus.keySet()) {
            menu.draw(delta);
        }

        if (myShaderProgram != null) {
            frameBuffer.end();
            TextureRegion t = new TextureRegion(frameBuffer.getColorBufferTexture());
            t.flip(false, true);
            myShaderProgram.update(delta);
            spriteBatch.setShader(myShaderProgram);
            spriteBatch.begin();
            spriteBatch.draw(t, 0, 0);
            spriteBatch.end();

        }
    }

    public void drawBackGround(float delta){

    }

    public void addToInnerMenus(Menu menu, String key) {
        menu.setView(camera.combined);
        innerMenus.put(menu, key);
    }


    //region Input
    @Override
    public boolean touchDown(Vector2 wCoords, Vector2 sCoords) {

        return false;
    }


    @Override
    public boolean touchUp(Vector2 wCoords, Vector2 sCoords) {

        return false;
    }

    @Override
    public boolean touchDragged(Vector2 wCoords, Vector2 sCoords) {

        return false;
    }

    public void calculateFOVs(float distance) {
        frustumHeight = 2.0f * distance * (float) Math.tan(Math.toRadians(camera.fieldOfView * 0.5f));
        frustumWidth = camera.viewportWidth / camera.viewportHeight * frustumHeight;
    }


    //endregion

    //RAY INTERCEPTION
    public GameObject intercept(Ray ray, Vector3 interception) {
        for (GameObject gameObject : objects) {
            if (gameObject.boundingBox == null) continue;
            if (Intersector.intersectRayBounds(ray, gameObject.boundingBox, interception)) {
                return gameObject;
            }
        }
        return null;
    }


    //endregion

    public interface ScreenCallbacks {
        Menu getMenu();

    }

    public void setMyShaderProgram(MyShaderProgram myShaderProgram) {
        this.frameBuffer = new FrameBuffer.FrameBufferBuilder(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()).build();
        this.spriteBatch = new SpriteBatch();
        spriteBatch.enableBlending();
        this.myShaderProgram = myShaderProgram;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public ScreenCallbacks getScreenCallbacks() {
        return screenCallbacks;
    }

    public void setScreenCallbacks(ScreenCallbacks screenCallbacks) {
        this.screenCallbacks = screenCallbacks;
    }

    public float getFrustumWidth() {
        return frustumWidth;
    }

    public float getFrustumHeight() {
        return frustumHeight;
    }

    public Color getClearColor() {
        return clearColor;
    }

    public void setClearColor(Color clearColor) {
        this.clearColor = clearColor;
    }
}
