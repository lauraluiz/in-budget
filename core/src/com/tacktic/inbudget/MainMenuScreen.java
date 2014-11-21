package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;

public class MainMenuScreen extends BaseScreen {

    public MainMenuScreen(InBudget game) {
        super(game);
    }

    @Override
    public void renderBatch() {
        write("Welcome to In Budget!", 100, 150);
        write("Tap anywhere to begin", 100, 100);
    }

    @Override
    public void renderActions() {
        if (Gdx.input.isTouched()) {
            moveToGameScreen();
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
