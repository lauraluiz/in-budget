package com.tacktic.inbudget;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InBudget extends Game {
    private Resources resources;
    private SpriteBatch batch;
    private BitmapFont font;

    public Resources resources() {
        return resources;
    }

    public SpriteBatch batch() {
        return batch;
    }

    public BitmapFont font() {
        return font;
    }

    @Override
    public void create() {
        resources = new Resources();
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        resources.dispose();
        batch.dispose();
        font.dispose();
    }
}
