package com.xlip.threedtemp.Settings;

import com.badlogic.gdx.Gdx;

/**
 * Created by Arif on 17.02.2017.
 */

public class Settings {
    public static int appwidth=480;
    public static int appheight=800;
    public static boolean splashScreenEnabled = false;

    public static float orto_width=800; //calculated after init func
    public static float orto_height=800;

    public static int x = 1000;
    public static int y = 0;

    public static void init() {
       orto_height = orto_width*Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
    }


}
