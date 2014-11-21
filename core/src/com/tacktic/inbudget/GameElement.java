package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import static com.tacktic.inbudget.BaseScreen.VIEWPORT_WIDTH;
import static com.tacktic.inbudget.BaseScreen.VIEWPORT_HEIGHT;

public abstract class GameElement {
    abstract Texture texture();

    abstract Rectangle box();

    abstract float x();

    abstract float y();

    abstract float width();

    abstract float height();

    abstract void x(float x);

    abstract void y (float y);

    abstract void fixPosition();

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
