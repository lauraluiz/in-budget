package com.tacktic.inbudget.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tacktic.inbudget.InBudget;

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
        if (loadingRound || Gdx.input.isTouched()) {
            loadingRound = true;
            moveToGameScreen(1);
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
