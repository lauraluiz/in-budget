package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import java.util.function.Consumer;

public class MainMenuScreen extends BaseScreen {
    private boolean loadingRound;

    public MainMenuScreen(InBudget game) {
        super(game);
        loadingRound = false;
    }

    @Override
    public void renderBatch() {
        write("In Budget!", Color.BLACK, 2, VIEWPORT_WIDTH/2 - 100, VIEWPORT_HEIGHT/2);
        write("Tap anywhere to begin", Color.BLACK, 1, VIEWPORT_WIDTH/2 - 100, VIEWPORT_HEIGHT/2 - 100);
    }

    @Override
    public void renderActions() {
        if (Gdx.input.isTouched() && !loadingRound) {
            loadingRound = true;
            changeRound(1).thenAccept(new Consumer<Array<Item>>() {
                @Override
                public void accept(final Array<Item> items) {
                    moveToGameScreen(items);
                }
            });
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
