package com.xlip.threedtemp.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Settings.Settings;
import com.xlip.threedtemp.Shader.d2.MyShaderProgram;
import com.xlip.threedtemp.World.World;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Created by Arif on 19.07.2017.
 */

public class Screen implements Menu.ScreenCallbacks,World.ScreenCallbacks {
    private World world;
    private Menu menu;
    private MyShaderProgram myShaderProgram;
    private FrameBuffer frameBuffer;
    public SpriteBatch spriteBatch;
    public OrthographicCamera orthographicCamera;
    protected MyInputProcessor myInputProcessor;
    private Color clearColor;

    /**
     * Global time that is spent on this screen.
     */
    protected float iTime;


    public Screen() {
        orthographicCamera=new OrthographicCamera(Settings.orto_width,Settings.orto_height);
        orthographicCamera.update();

        spriteBatch = new SpriteBatch();
        spriteBatch.enableBlending();
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        this.myInputProcessor = new MyInputProcessor(this);
        this.iTime = 0;
        //Gdx.input.setInputProcessor(myInputProcessor);
        this.clearColor = new Color(1,1,1,1);
    }

    public Screen(World world, Menu menu) {
        this();
        this.world = world;

        if(menu != null)
            setMenu(menu);
    }

    public Screen(World world, Menu menu, MyShaderProgram myShaderProgram) {
        this(world,menu);
        this.myShaderProgram = myShaderProgram;
        this.frameBuffer = new FrameBuffer.FrameBufferBuilder(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()).build();
        //Pixmap.Format.RGBA8888,true
    }


    public void render(float delta) {
        gl.glClearColor(clearColor.r,clearColor.g,clearColor.b,1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        iTime += delta;
        if(myShaderProgram != null)
            frameBuffer.begin();



        if(world != null){
            world.render(delta);
        }


        if(menu != null) {
            if(menu.isFinished()) menu = null;
        }

        if(menu != null) {
            menu.update(delta);
            menu.draw(delta);
        }



        if(myShaderProgram != null) {
            frameBuffer.end();
            TextureRegion t = new TextureRegion(frameBuffer.getColorBufferTexture());
            t.flip(false,true);
            spriteBatch.setShader(myShaderProgram);
            spriteBatch.setProjectionMatrix(orthographicCamera.combined);
            spriteBatch.begin();
            spriteBatch.draw(t,-Settings.orto_width/2,-Settings.orto_height/2,Settings.orto_width,Settings.orto_height);
            spriteBatch.end();
            myShaderProgram.update(delta);
        }


    }


    @Override
    public void setMenu(Menu menu) {
        this.menu = menu;
        this.menu.setScreenCallbacks(this);
        this.menu.setView(new Matrix4(orthographicCamera.combined));
        this.myInputProcessor.setMenu(menu);
    }

    public void setWorld(World world) {
        this.world = world;
        this.myInputProcessor.setMyInputCallback(world);

    }

    public World getWorld() {
        return world;
    }

    public Menu getMenu() {
        return menu;
    }

    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }

    public Color getClearColor() {
        return clearColor;
    }

    public void setClearColor(Color clearColor) {
        this.clearColor = clearColor;
    }
}
