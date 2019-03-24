package com.xlipstudio.cleanthescreen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.Utils.Lerp;

public class Cell {
    private long id;
    private float width;
    private float height;
    private float x;
    private float y;
    private Color color;
    private boolean destroyed;
    private Texture texture;
    private Lerp xDestroyer;
    private Lerp yDestroyer;


    public Cell(long id,  float x, float y, float width, float height) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = Color.RED;
        this.texture = Assets.cell;
        this.xDestroyer = new Lerp(0, width, 8);
        this.yDestroyer = new Lerp(0, height, 8);
    }


    public void render(float delta, SpriteBatch batch) {
        Color color = batch.getColor();
        batch.setColor(this.color);
        batch.begin();

        float consumeX = destroyed ? xDestroyer.getValue(delta) : 0;
        float consumeY = destroyed ? yDestroyer.getValue(delta) : 0;
        if(destroyed){
            System.out.println(consumeY + "  asd");
        }

        batch.draw(texture, x + consumeX/2  , y + consumeY/2  , width - consumeX, height - consumeY);
        batch.end();
        batch.setColor(color);
    }


    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public long getId() {
        return id;
    }

    public boolean isIn(Vector2 pos) {
        if (pos.x >= this.x && pos.x <= this.x + this.width) {
            if (pos.y >= this.y && pos.y <= this.y + this.height) {
                return true;
            }
        }
        return false;

    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
