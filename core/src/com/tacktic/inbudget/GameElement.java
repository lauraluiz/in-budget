package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import static com.tacktic.inbudget.screens.BaseScreen.VIEWPORT_WIDTH;
import static com.tacktic.inbudget.screens.BaseScreen.VIEWPORT_HEIGHT;

public abstract class GameElement {

    public abstract Texture texture();

    public abstract Rectangle box();

    public float x() {
        return box().x;
    }

    public float y() {
        return box().y;
    }

    public void x(float x) {
        box().x = x;
    }

    public void y (float y) {
        box().y = y;
    }

    public float width() {
        return box().width;
    }

    public float height() {
        return box().height;
    }

    public boolean outOfScreen() {
        return y() + height() < 0 || x() + width() < 0
                || y() > VIEWPORT_HEIGHT || x() > VIEWPORT_WIDTH;
    }

    public void setXTo(float x) {
        x(x - width() / 2);
    }

    public void setYTo(float y) {
        y(y - height() / 2);
    }

    public void moveRight() {
        x(x() + 200 * Gdx.graphics.getDeltaTime());
    }

    public void moveLeft() {
        x(x() - 200 * Gdx.graphics.getDeltaTime());
    }

    public void moveUp() {
        y(y() + 200 * Gdx.graphics.getDeltaTime());
    }

    public void moveDown() {
        y(y() - 200 * Gdx.graphics.getDeltaTime());
    }
}
