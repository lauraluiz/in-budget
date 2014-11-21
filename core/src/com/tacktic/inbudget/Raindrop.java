package com.tacktic.inbudget;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import static com.tacktic.inbudget.BaseScreen.VIEWPORT_WIDTH;
import static com.tacktic.inbudget.BaseScreen.VIEWPORT_HEIGHT;

public class Raindrop extends GameElement {
    private final Texture texture;
    private final Sound sound;
    private final Rectangle raindrop;

    public Raindrop(Resources resources) {
        texture = resources.dropImage();
        sound = resources.dropSound();
        raindrop = new Rectangle();
        raindrop.width = texture.getWidth();
        raindrop.height = texture.getHeight();
        raindrop.x = MathUtils.random(0, VIEWPORT_WIDTH - raindrop.width);
        raindrop.y = VIEWPORT_HEIGHT;
    }

    @Override
    public Texture texture() {
        return texture;
    }

    @Override
    public Rectangle box() {
        return raindrop;
    }

    @Override
    public float x() {
        return raindrop.x;
    }

    @Override
    public float y() {
        return raindrop.y;
    }

    @Override
    public void x(float x) {
        raindrop.x = x;
    }

    @Override
    public void y(float y) {
        raindrop.y = y;
    }

    @Override
    public float width() {
        return raindrop.width;
    }

    @Override
    public float height() {
        return raindrop.height;
    }

    @Override
    public void fixPosition() {

    }

    public void playSound() {
        sound.play();
    }
}
