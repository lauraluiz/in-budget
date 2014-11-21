package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public abstract class BaseScreen implements Screen {
    public static final int VIEWPORT_WIDTH = 640;
    public static final int VIEWPORT_HEIGHT = 960;

    private final InBudget game;
    private final OrthographicCamera camera;
    private Vector3 touchPosition;

    public BaseScreen(final InBudget game) {
        this.game = game;
        this.camera = setCamera();
        this.touchPosition = new Vector3();
    }

    public Resources resources() {
        return game.resources();
    }

    @Override
    public final void render(float delta) {
        renderBackground();
        camera.update();
        game.batch().setProjectionMatrix(camera.combined);
        game.batch().begin();
        renderBatch();
        game.batch().end();
        renderActions();
    }

    protected void renderBackground() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    abstract void renderBatch();

    abstract void renderActions();

    protected void moveToGameScreen() {
        game.setScreen(new GameScreen(game));
        dispose();
    }

    protected void moveToMainMenuScreen() {
        game.setScreen(new MainMenuScreen(game));
        dispose();
    }

    protected void write(String text, float x, float y) {
        game.font().draw(game.batch(), text, x, y);
    }

    protected void draw(GameElement element) {
        game.batch().draw(element.texture(), element.x(), element.y());
    }

    protected Vector3 touchPosition() {
        touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPosition);
        return touchPosition;
    }

    private OrthographicCamera setCamera() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        return camera;
    }
}
