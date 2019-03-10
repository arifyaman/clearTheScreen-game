package com.xlip.threedtemp.Objects.Model;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ConeShapeBuilder;


/**
 * Created by Yaman on 12.12.2015.
 */
public abstract class DefaultModels extends com.badlogic.gdx.graphics.g3d.Model {

    public static com.badlogic.gdx.graphics.g3d.Model model;
    ModelBuilder mb;


    public DefaultModels() {
        mb = new ModelBuilder();
    }

    public void init() {
        Material material = new Material();
        material.set(new ColorAttribute(ColorAttribute.createDiffuse(Color.RED)));
        material.set(new ColorAttribute(ColorAttribute.createSpecular(Color.RED)));
        material.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, 1));

        mb.begin();

        float wh = 1f;

        mb.node().id = "plane";
        mb.part("plane", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates, material)
                .rect(-wh, wh, 0,
                        wh, wh, 0,
                        wh, -wh, 0,
                        -wh, -wh, 0,
                        -1, 1, 0);

        mb.node().id = "box";
        mb.part("box", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates, material)
                .box(1,1,1);


        mb.node().id = "cone";
        ConeShapeBuilder.build(mb.part("cone", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates, material),
                1,1,1,30);



        mb.node().id = "patch";
        mb.part("patch", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates, material)
                .patch(0, wh, wh,
                        0, wh, -wh,
                        0, -wh, -wh,
                        0, -wh, wh,
                        0, 1, -1, 2, 1);


        addModelTheseParts(mb);
      /*  wh=10f;
        mb.node().id="duvar";
        mb.part("duvar", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal| VertexAttributes.Usage.TextureCoordinates,material)
                .rect(wh,wh,0,
                        wh,-wh,0,
                        -wh,-wh,0,
                        -wh,wh,0,
                        0,0,-1);*/

        model = mb.end();
    }

    public abstract void addModelTheseParts(ModelBuilder modelBuilder);


}
