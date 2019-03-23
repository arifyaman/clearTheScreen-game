package com.xlipstudio.cleanthescreen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.Input.Abs.Clickable;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Menu.Object.MenuObject;
import com.xlip.threedtemp.Settings.Settings;
import com.xlip.threedtemp.Utils.MenuUtils.ObjectPlacer;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.communication.request.Request;
import com.xlipstudio.cleanthescreen.communication.request.RequestType;
import com.xlipstudio.cleanthescreen.communication.sub.WrapType;

public class OpeningMenu extends Menu {

    public OpeningMenu() {
        BitmapFont font = new BitmapFont(Gdx.files.internal("obelixfnt.fnt"), new TextureRegion(Assets.fontTexture), false);
        font.setColor(Color.WHITE);
        font.getData().setScale(2);

        MenuObject profileButton = new MenuObject(Assets.button, new Vector2(-Settings.orto_width/2, 0), new Vector2(Settings.orto_width, Settings.orto_height/2));
        profileButton.setClickedTexture(Assets.button_clicked);
        profileButton.setBitmapFont(font);
        profileButton.setTitle("Profile", -300, 250);
        profileButton.setOnClickListener(new Clickable.OnClickListener() {
            @Override
            public void onClick() {
                Wrap wrap = new Wrap(WrapType.REQUEST, new Request(RequestType.GO, "PROFILE"));
                CleanTheScreenGame.getGameClient().dispatchWrap(wrap);
            }
        });


        MenuObject playButton = new MenuObject(Assets.button, new Vector2(-Settings.orto_width/2, -Settings.orto_height/2 - 1), new Vector2(Settings.orto_width, Settings.orto_height/2));
        playButton.setClickedTexture(Assets.button_clicked);
        playButton.setBitmapFont(font);
        playButton.setTitle("Play", -300, 320);
        playButton.setOnClickListener(new Clickable.OnClickListener() {
            @Override
            public void onClick() {
                Wrap wrap = new Wrap(WrapType.REQUEST, new Request(RequestType.GO, "PLAY"));
                CleanTheScreenGame.getGameClient().dispatchWrap(wrap);
            }
        });


        addMenuObject(profileButton);
        addMenuObject(playButton);

    }


}
