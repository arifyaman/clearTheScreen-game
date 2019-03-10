package com.xlip.threedtemp.Menu.Object.Font;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


/**
 * Created by Arif on 29.07.2017.
 */

public abstract class AbsNumberRenderer extends FontObject {
    private Array<FontObject> objects;

    private boolean toRight;
    protected float number;
    private String tempNumber;
    private Color color;


    private Texture atlas;
    private int x,y,tw,th;

    private Vector2 wh;
    private boolean center;
    private boolean left;
    private boolean right;
    private int ALING;

    public final static int ALING_CENTER = 1;
    public final static int ALING_LEFT = 2;
    public final static int ALING_RIGHT = 3;

    private float jumpingValue;
    private Vector2 tempV;




    public AbsNumberRenderer(Texture texture, int x, int y, int tw, int th, Vector2 position, Vector2 wh) {
        super(null,position,wh);
        this.atlas = texture;
        this.wh = wh;
        this.x = x;
        this.y = y;
        this.tw = tw;
        this.th = th;

        objects = new Array<FontObject>();

        this.toRight = true;
        this.number = 0;
        this.tempNumber ="0";
        this.jumpingValue = 50;
        this.tempV = new Vector2();
    }


    @Override
    public void draw(SpriteBatch batch,float delta) {
        Integer converted = Math.round(number);
        String str = String.valueOf(converted);
        Color existing = batch.getColor();
        if(color != null)
            batch.setColor(color);
        if(tempNumber.length() > str.length()) {
            objects.clear();
        }

        for (int i = 0; i < str.length(); i++) {

            int a = returnNumeric(str.charAt(i));
            float differenceForAlign = 0;

            if(ALING == ALING_RIGHT){
                differenceForAlign = wh.x*str.length();
            }else if(ALING == ALING_CENTER){
                differenceForAlign = (wh.x*str.length())/2;
            }

            FontObject obj =  new FontObject(new TextureRegion(atlas,x+tw*a,y,tw,th),new Vector2(getPosition()).add((wh.x*i)-differenceForAlign,jumpingValue),wh).setBalancer(jumpingValue*2);

            Vector2 gotoPos = new Vector2((getPosition().x + wh.x * i)-differenceForAlign, getPosition().y);


            obj.goToPosition(gotoPos);


                if(objects.size - 1 < i) {
                    objects.add(obj);
                }else if(str.charAt(i) != tempNumber.charAt(i)){
                    objects.removeIndex(i);
                    objects.insert(i,obj);
                }else if(str.length() > tempNumber.length()){
                    objects.removeIndex(i);
                    objects.insert(i,obj);
                }

        }

        for (FontObject f :
                objects) {
            f.draw(batch, delta);
            f.update(delta);
        }

        batch.setColor(existing);
        this.tempNumber = str;

    }


    private int returnNumeric(char i) {
        switch (i){
            case '0':
                return 0;
                
            case '1':
                return 1;
                
            case '2':
                return 2;
                
            case '3':
                return 3;
            
            case '4':
                return 4;
            
            case '5':
                return 5;
            
            case '6':
                return 6;
            
            case '7':
                return 7;
            
            case '8':
                return 8;
            
            case '9':
                return 9;
            

        }

        return 0;
    }


    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public AbsNumberRenderer setJumpingValue(float jumpingValue) {
        this.jumpingValue = jumpingValue;
        return this;
    }

    public void addToNumber(float addValue) {
        this.number += addValue;
    }

    //GETTER SETTER


    public boolean isToRight() {
        return toRight;
    }

    public void setToRight(boolean toRight) {
        this.toRight = toRight;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public AbsNumberRenderer setAling(int align){
        this.ALING = align;
        return this;
    }


}
