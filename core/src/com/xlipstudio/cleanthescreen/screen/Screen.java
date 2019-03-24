package com.xlipstudio.cleanthescreen.screen;

import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.menu.Menu;
import com.xlipstudio.cleanthescreen.menu.ProfileMenu;

public abstract class Screen extends com.xlip.threedtemp.Screen.Screen {

    public void initialized(){
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
    }

    public void initialized(Object object){
        initialized();
    }

    public void wrapReceived(Wrap wrap){
        System.out.println(wrap.getResponse().getMessage() + "  " + wrap.getResponse().getCode());
    }

    public void finishLoading(){
        ((Menu) getMenu()).setLoading(false);
    }

    public void enableLoading(){
        ((Menu) getMenu()).setLoading(true);
    }
}
