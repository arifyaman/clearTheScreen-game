package com.xlipstudio.cleanthescreen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Settings.Settings;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.communication.request.Request;
import com.xlipstudio.cleanthescreen.communication.request.RequestType;
import com.xlipstudio.cleanthescreen.communication.response.Response;
import com.xlipstudio.cleanthescreen.communication.sub.WrapType;
import com.xlipstudio.cleanthescreen.game.GameConfig;
import com.xlipstudio.cleanthescreen.objects.Cell;

import java.util.HashMap;
import java.util.Map;

public class GameScreen extends Screen implements MyInputProcessor.MyInputCallback {
    private static GameScreen instance = new GameScreen();

    public static GameScreen getInstance() {
        return instance;
    }


    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private float cellWidth = 0;
    private float cellHeight = 0;


    private float widthHeightMul = 0.6f;
    private long cellSize = 0;


    private HashMap<Long, Cell> cells = new HashMap<Long, Cell>();

    public GameScreen() {

    }

    @Override
    public void initialized() {

    }

    @Override
    public void initialized(Object object) {
        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer.setAutoShapeType(true);

        MyInputProcessor processor = new MyInputProcessor(this);
        processor.setMyInputCallback(this);
        Gdx.input.setInputProcessor(processor);
        GameConfig config = ((GameConfig) object);
        this.cellSize = config.cellSize;

        initCells();

    }

    private void initCells() {

        long cellsForWidth = Math.round(Math.pow(cellSize * widthHeightMul, 0.5));
        long cellsForHeight = Math.round(cellsForWidth / widthHeightMul);

        cellWidth = Settings.orto_width / cellsForWidth;
        cellHeight = Settings.orto_height / cellsForHeight;

        int id = 0;
        for (int i = 0; i < cellsForWidth; i++) {

            for (int j = 0; j < cellsForHeight; j++) {
                Cell cell = new Cell(id);
                cell.setWidth(cellWidth);
                cell.setHeight(cellHeight);

                cell.setX(i * cellWidth - Settings.orto_width / 2);

                cell.setY(Settings.orto_height / 2 - j * cellHeight - cellHeight);
                cells.put(cell.getId(), cell);
                id ++;
            }
        }

    }

    @Override
    public void render(float delta) {

        super.render(delta);
        for (Cell cell : cells.values()
        ) {
            renderCell(cell);

        }
    }

    public void renderCell(Cell cell) {
        if(!cell.isDestroyed()) {
            shapeRenderer.begin();
            shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight(), Color.RED, Color.RED, Color.RED, Color.RED);
            shapeRenderer.end();
        }

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
        for (Cell cell : cells.values()) {
            if (cell.isIn(pos)) {
                Wrap wrap = new Wrap(WrapType.REQUEST, new Request(RequestType.DELETE_CELL, cell.getId()));

                CleanTheScreenGame.getGameClient().dispatchWrap(wrap);
                // cells.removeValue(cell, true);
            }
        }
    }

    @Override
    public void wrapReceived(Wrap wrap) {
        if (wrap.getResponse().isResult()) {
            Response response = wrap.getResponse();
            if(response.getCode().equals("100")) {
                setClearColor(Color.BLUE);
            }else if(response.getCode().equals("-100") ) {
                setClearColor(Color.DARK_GRAY);
            }


            Object payload = response.getPayload();
            if (payload != null) {
                cells.get(((Long) payload)).setDestroyed(true);

            }
        }

    }


}