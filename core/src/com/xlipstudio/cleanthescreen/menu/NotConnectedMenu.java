package com.xlipstudio.cleanthescreen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.Input.Abs.Clickable;
import com.xlip.threedtemp.Menu.Object.MenuObject;
import com.xlip.threedtemp.Settings.Settings;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.communication.request.Request;
import com.xlipstudio.cleanthescreen.communication.request.RequestType;
import com.xlipstudio.cleanthescreen.communication.sub.WrapType;

public class NotConnectedMenu extends Menu {

    public NotConnectedMenu() {
        BitmapFont font = new BitmapFont(Gdx.files.internal("obelixfnt.fnt"), new TextureRegion(Assets.fontTexture), false);

        font.getData().setScale(1.5f);
        font.setColor(Color.WHITE);
        MenuObject exitButton = new MenuObject(Assets.button, new Vector2(-Settings.orto_width / 2, -Settings.orto_height + 200), new Vector2(Settings.orto_width, Settings.orto_height / 2));
        exitButton.setClickedTexture(Assets.button_clicked);
        exitButton.setBitmapFont(font);
        exitButton.setTitle("Exit", -90, 330);
        exitButton.setOnClickListener(new Clickable.OnClickListener() {
            @Override
            public void onClick() {
                CleanTheScreenGame.exit();
            }
        });

        BitmapFont font2 = new BitmapFont(Gdx.files.internal("obelixfnt.fnt"), new TextureRegion(Assets.fontTexture), false);
        font2.setColor(Color.BLACK);
        font2.getData().setScale(1.5f);


        MenuObject information = new MenuObject(Assets.transparent, new Vector2(0, 0), new Vector2(Settings.orto_width, Settings.orto_height / 2));
        information.setBitmapFont(font2);
        information.setTitle("Please check\nyour internet\nconnection...", -360, -200);
        information.setDisabled(true);



        addMenuObject(exitButton);
        addMenuObject(information);

    }



}
