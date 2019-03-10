package com.xlip.threedtemp.Screen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.Menu.Effects.Defaults.DefaultMenuFinisher;
import com.xlip.threedtemp.Menu.Effects.Defaults.DefaultMenuOpener;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Menu.Object.MenuObject;
import com.xlip.threedtemp.Settings.Settings;

/**
 * Created by Arif on 6.08.2017.
 */

public abstract class SplashScreen extends Screen {
    MainCallBacks mainCallBacks;
    private float delaySeconds;
    private Menu splash;

    public SplashScreen(float delaySeconds, final MainCallBacks mainCallBacks) {
        super(null, null);
        this.delaySeconds = delaySeconds;

        splash = new Menu();
        MenuObject splashImage = new MenuObject(new TextureRegion(Assets.splash),new Vector2(-Settings.orto_width/2,-Settings.orto_height/2),new Vector2(Settings.orto_width, Settings.orto_height));
        splash.addMenuObject(splashImage);
        splash.setMenuOpener(new SplashMenuOpener(10));
        doInBackGround();

        splash.setMenuFinisher(new DefaultMenuFinisher(28){
            @Override
            public void onFinished() {
                super.onFinished();
                mainCallBacks.onSplashScreenFinished();
            }
        });

        setMenu(splash);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if(iTime >= delaySeconds) {
            splash.finish();
        }
    }

    public abstract void doInBackGround();


    public interface MainCallBacks{
        void onSplashScreenFinished();
    }

    private class SplashMenuOpener extends DefaultMenuOpener {

        public SplashMenuOpener(float interpolation) {
            super(interpolation);
            Vector3 viewScale = orthographicCamera.combined.getScale(new Vector3());
            setStartx(viewScale.x);
            setStarty(viewScale.y);
        }
    }


}
