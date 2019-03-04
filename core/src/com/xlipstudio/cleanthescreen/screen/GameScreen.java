package com.xlipstudio.cleanthescreen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Settings.Settings;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.objects.Cell;

public class GameScreen extends Screen implements MyInputProcessor.MyInputCallback {
    private static GameScreen instance = new GameScreen();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private float cellWidth;
    private float cellHeight;

    private int cellsForWidth = 15;
    private int cellsForHeight = 25;


    private Array<Cell> cells = new Array<Cell>();

    public static GameScreen getInstance() {
        return instance;
    }

    public GameScreen() {
        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer.setAutoShapeType(true);
        cellWidth = Settings.orto_width / cellsForWidth;
        cellHeight = Settings.orto_height / cellsForHeight;
        initCells();
        MyInputProcessor processor = new MyInputProcessor(this);
        processor.setMyInputCallback(this);
        Gdx.input.setInputProcessor(processor);
    }


    private void initCells() {


        for (int i = 0; i < cellsForWidth; i++) {

            for (int j = 0; j < cellsForHeight; j++) {
                Cell cell = new Cell((i + 1) * (j + 1));
                cell.setWidth(cellWidth);
                cell.setHeight(cellHeight);

                cell.setX(i * cellWidth - Settings.orto_width / 2);

                cell.setY(Settings.orto_height / 2 - j * cellHeight - cellHeight);
                cells.addAll(cell);
            }
        }

    }

    @Override
    public void render(float delta) {

        super.render(delta);
        for (Cell cell : cells
        ) {
            renderCell(cell);

        }
    }

    public void renderCell(Cell cell) {
        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight(), Color.RED, Color.RED, Color.RED, Color.RED);
        shapeRenderer.end();

    }

    @Override
    public boolean touchDown(Vector2 vector2, Vector2 vector21) {
        checkCells(vector2);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 vector2, Vector2 vector21) {
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 vector2, Vector2 vector21) {
        checkCells(vector2);
        return false;
    }

    void sendRemoveCellReq(Cell cell) {


    }

    private void checkCells(Vector2 pos) {
        for (Cell cell : cells) {
            if (cell.isIn(pos)) {
                System.out.println("Removed: " + cell.getId());
                cells.removeValue(cell, true);
            }
        }
    }

    @Override
    public void wrapReceived(Wrap wrap) {

    }


}
