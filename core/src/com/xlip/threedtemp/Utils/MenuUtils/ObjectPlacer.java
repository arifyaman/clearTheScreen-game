package com.xlip.threedtemp.Utils.MenuUtils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Menu.Object.MenuObject;
import com.xlip.threedtemp.Objects.input.movement.InstantDragMovementController;

/**
 * Created by arif on 03.02.2018.
 */

public class ObjectPlacer implements MyInputProcessor.MyInputCallback {
    private InstantDragMovementController dragMovementController;
    private MenuObject object;
    private Vector3 vector3;
    private Vector2 vector2;

    public ObjectPlacer(MenuObject menuObject) {
        this.object = menuObject;
        vector2 = new Vector2();
        vector3 = new Vector3();
        vector3.set(menuObject.getPosition().x, menuObject.getPosition().y, 0);
        dragMovementController = new InstantDragMovementController(vector3);
        dragMovementController.setDragDeltaX(1);
        dragMovementController.setDragDeltaY(1);
        dragMovementController.setReversedX();
    }

    public void update(float delta) {
        vector3.set(dragMovementController.update(delta));
        vector2.set(vector3.x, vector3.y);
        this.object.setPosition(vector2);
        System.out.println("Current Object Position = "+object.getPosition());
    }

    @Override
    public boolean touchDown(Vector2 wCoords, Vector2 sCoors) {
        dragMovementController.touchDown(wCoords);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 wCoords, Vector2 sCoors) {
        dragMovementController.touchUp(wCoords);
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 wCoords, Vector2 sCoors) {
        dragMovementController.touchDragged(wCoords);
        return false;
    }
}
