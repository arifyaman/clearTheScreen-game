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

public class WaitingPlayerMenu extends Menu {

    public WaitingPlayerMenu() {
        BitmapFont font = new BitmapFont(Gdx.files.internal("obelixfnt.fnt"), new TextureRegion(Assets.fontTexture), false);
        font.setColor(Color.WHITE);
        font.getData().setScale(1.5f);

        MenuObject profileButton = new MenuObject(Assets.button, new Vector2(-Settings.orto_width / 2, -Settings.orto_height + 200), new Vector2(Settings.orto_width, Settings.orto_height / 2));
        profileButton.setClickedTexture(Assets.button_clicked);
        profileButton.setBitmapFont(font);
        profileButton.setTitle("Go Back", -90, 280);
        profileButton.setOnClickListener(new Clickable.OnClickListener() {
            @Override
            public void onClick() {
                Wrap wrap = new Wrap(WrapType.REQUEST, new Request(RequestType.EXIT,null));
                CleanTheScreenGame.getGameClient().dispatchWrap(wrap);
                setLoading(true);
            }
        });


        MenuObject information = new MenuObject(Assets.transparent, new Vector2(0, 0), new Vector2(Settings.orto_width, Settings.orto_height / 2));
        information.setBitmapFont(font);
        information.setTitle("Please wait\n\nFinding\nplayer...", -360, -120);
        information.setDisabled(true);



        addMenuObject(profileButton);
        addMenuObject(information);

    }



}
