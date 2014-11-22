package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.math.BigDecimal;
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

    public CompletableFuture<BigDecimal> calculatePrice(Array<Item> items) {
        return game.fetchTotalPrice(items);
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
        game.setScreen(new GameScreen(game, items, new BigDecimal(2500)));
        dispose();
    }

    protected void moveToMainMenuScreen() {
        game.setScreen(new MainMenuScreen(game));
        dispose();
    }

    protected void moveToResultScreen(BigDecimal budget, BigDecimal totalPrice) {
        game.setScreen(new ResultScreen(game, budget, totalPrice));
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

    protected void drawGirl(Girl girl) {
        game.batch().draw(girl.nextFrame(), -20, 0);
    }

    protected void drawItem(Item item) {
        draw(item);
        draw(resources().priceTagImage(), item.x() - 12, item.y() - 20);
        write(item.price(), Color.BLACK, 1, item.x(), item.y() + 10);
    }

    protected void drawBackground() {
        draw(resources().backgroundImage(), 0, 0);
    }

    protected void drawResultBackground() {
        draw(resources().resultBackgroundImage(), 0, 0);
    }

    protected void drawTopMenu(int round, int itemsLeft, BigDecimal budget) {
        Texture texture = resources().topMenuImage();
        draw(texture, (VIEWPORT_WIDTH - texture.getWidth())/2, VIEWPORT_HEIGHT - texture.getHeight());
        write(String.valueOf(round), Color.DARK_GRAY, 2, 155, VIEWPORT_HEIGHT - 22);
        write(String.valueOf(itemsLeft), Color.OLIVE, 2, VIEWPORT_WIDTH / 2 - 25, VIEWPORT_HEIGHT - 35);
        write("$" + budget.toPlainString(), Color.DARK_GRAY, 2, VIEWPORT_WIDTH / 2 + 100, VIEWPORT_HEIGHT - 22);
    }

    protected void drawBottomMenu(Array<Item> purchasedItems) {
        Texture texture = resources().bottomMenuImage();
        draw(texture, VIEWPORT_WIDTH - texture.getWidth(), 0);
        int i = 0;
        for (Item item : purchasedItems) {
            draw(item.texture(), 132 + 100 * i, 22);
            i++;
        }
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
