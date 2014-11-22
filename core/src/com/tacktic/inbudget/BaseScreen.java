package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.concurrent.CompletableFuture;

public abstract class BaseScreen implements Screen {
    public static final int VIEWPORT_WIDTH = 640;
    public static final int VIEWPORT_HEIGHT = 800;
    public static final int VIEWPORT_MARGIN = 70;

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

    public CompletableFuture<Array<Item>> changeRound(int round) {
        return game.fetchRound(round);
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
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    abstract void renderBatch();

    abstract void renderActions();

    protected void moveToGameScreen(Array<Item> items) {
        game.setScreen(new GameScreen(game, items));
        dispose();
    }

    protected void moveToMainMenuScreen() {
        game.setScreen(new MainMenuScreen(game));
        dispose();
    }

    protected void write(String text, Color color, float size, float x, float y) {
        game.font().setColor(color);
        game.font().setScale(size);
        game.font().draw(game.batch(), text, x, y);
    }

    protected void draw(Texture texture, float x, float y) {
        game.batch().draw(texture, x, y);
    }

    protected void draw(GameElement element) {
        game.batch().draw(element.texture(), element.x(), element.y());
    }

    protected void drawItem(Item item) {
        draw(item);
        write(item.price(), Color.BLACK, 1, item.x(), item.y());
    }

    protected void drawBackground() {
        draw(resources().backgroundImage(), 0, 0);
    }

    protected void drawTopMenu(int itemsLeft) {
        Texture texture = resources().topMenuImage();
        draw(texture, (VIEWPORT_WIDTH - texture.getWidth())/2, VIEWPORT_HEIGHT - texture.getHeight());
        write(String.valueOf(itemsLeft), Color.OLIVE, 2, VIEWPORT_WIDTH / 2 - 25, VIEWPORT_HEIGHT - 35);
    }

    protected void drawBottomMenu() {
        Texture texture = resources().bottomMenuImage();
        draw(texture, VIEWPORT_WIDTH - texture.getWidth(), 0);
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
