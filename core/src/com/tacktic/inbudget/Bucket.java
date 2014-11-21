package com.tacktic.inbudget;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import static com.tacktic.inbudget.BaseScreen.VIEWPORT_WIDTH;

public class Bucket extends GameElement {
    private final Texture texture;
    private final Rectangle bucket;

    public Bucket(Resources resources) {
        this.texture = resources.bucketImage();
        bucket = new Rectangle();
        bucket.width = texture.getWidth();
        bucket.height = texture.getHeight();
        bucket.x = VIEWPORT_WIDTH / 2 - bucket.width / 2;
        bucket.y = 20;
    }

    @Override
    public Texture texture() {
        return texture;
    }

    @Override
    public Rectangle box() {
        return bucket;
    }

    @Override
    public float x() {
        return bucket.x;
    }

    @Override
    public float y() {
        return bucket.y;
    }

    @Override
    public void x(float x) {
        bucket.x = x;
    }

    @Override
    public void y(float y) {
        bucket.y = y;
    }

    @Override
    public float width() {
        return bucket.width;
    }

    @Override
    public float height() {
        return bucket.height;
    }

    @Override
    public void fixPosition() {
        if (x() < 0) {
            x(0);
        }
        if (x() > VIEWPORT_WIDTH - width()) {
            x(VIEWPORT_WIDTH - width());
        }
    }
}
