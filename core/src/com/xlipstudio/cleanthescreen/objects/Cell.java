package com.xlipstudio.cleanthescreen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Cell {
    private long id;
    private float width;
    private float height;
    private float x;
    private float y;
    private Color color;

    public Cell(long id) {
        this.id = id;
        this.color = Color.RED;
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
        if(pos.x >= this.x && pos.x <= this.x + this.width) {
            if(pos.y >= this.y && pos.y <= this.y + this.height) {
                return true;
            }
        }
        return false;

    }
}
