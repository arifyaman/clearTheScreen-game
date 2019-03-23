package com.xlip.threedtemp.Menu.Object;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Input.Abs.Clickable;

/**
 * Created by Arif on 14.07.2017.
 */

public class MenuObject extends Clickable {
    private TextureRegion enabledTexture;
    private TextureRegion clickedTexture;
    private TextureRegion disabledTexture;
    private Vector2 position;
    private boolean finisher;
    private Color color;
    private Color temp;
    private BitmapFont bitmapFont;

    private String title;
    private float titleMarginTop;
    private float titleMarginLeft;


    private MenuObjectCallBacks menuObjectCallBacks;
    private OnClickListener onClickListener;

    public MenuObject(TextureRegion texture, Vector2 position, Vector2 wh) {
        super(wh, position);
        this.enabledTexture = texture;
        this.position = position;
        this.finisher = false;
    }


    @Override
    public void clicked() {
        if (finisher)
            menuObjectCallBacks.finish();

        if (onClickListener != null)
            onClickListener.onClick();

    }

    public void update(float delta) {

    }


    public void draw(SpriteBatch batch, float delta) {
        if (color != null) {
            temp = batch.getColor();
            batch.setColor(color);
        }
        TextureRegion t = getEnabledTexture();

        if (isDisabled()) {
            t = getDisabledTexture();
        } else {
            if (isOnIt()) {
                t = getClickedTexture();
            }
        }
        if (t == null) t = getEnabledTexture();
        batch.draw(t, getPosition().x, getPosition().y, getWh().getWidth(), getWh().getHeight());

        if (title != null) bitmapFont.draw(batch, title, getPosition().x + titleMarginLeft, getPosition().y + getWh().getHeight() + titleMarginTop);
        update(delta);

        if (color != null) {
            batch.setColor(temp);
        }
    }

    public void setTitle(String title, float titleMarginTop, float titleMarginLeft) {
        this.title = title;
        this.titleMarginLeft = titleMarginLeft;
        this.titleMarginTop = titleMarginTop;
    }


    //GETTER SETTER


    public TextureRegion getEnabledTexture() {
        return enabledTexture;
    }

    public void setEnabledTexture(TextureRegion enabledTexture) {
        this.enabledTexture = enabledTexture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        getWh().setPosition(position);
        this.position = position;
    }

    public TextureRegion getClickedTexture() {
        return clickedTexture;
    }

    public void setClickedTexture(TextureRegion clickedTexture) {
        this.clickedTexture = clickedTexture;
    }

    public TextureRegion getDisabledTexture() {
        return disabledTexture;
    }

    public void setDisabledTexture(TextureRegion disabledTexture) {
        this.disabledTexture = disabledTexture;
    }

    public MenuObjectCallBacks getMenuObjectCallBacks() {
        return menuObjectCallBacks;
    }

    public void setMenuObjectCallBacks(MenuObjectCallBacks menuObjectCallBacks) {
        this.menuObjectCallBacks = menuObjectCallBacks;
    }

    public boolean isFinisher() {
        return finisher;
    }

    public MenuObject setFinisher() {
        this.finisher = true;
        return this;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface MenuObjectCallBacks {
        boolean finish();

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setBitmapFont(BitmapFont bitmapFont) {
        this.bitmapFont = bitmapFont;
    }
}
