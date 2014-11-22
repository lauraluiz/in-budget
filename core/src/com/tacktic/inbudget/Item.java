package com.tacktic.inbudget;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import io.sphere.sdk.products.ProductProjection;

import static com.tacktic.inbudget.BaseScreen.VIEWPORT_HEIGHT;
import static com.tacktic.inbudget.BaseScreen.VIEWPORT_WIDTH;
import static java.util.Locale.ENGLISH;

public class Item extends GameElement {
    private final Texture texture;
    private final Rectangle item;

    public Item(ProductProjection product, Resources resources) {
        String slug = product.getSlug().get(ENGLISH).orElse("");
        texture = resources.itemImage(slug);
        item = new Rectangle();
        item.width = texture.getWidth();
        item.height = texture.getHeight();
        item.x = MathUtils.random(0, VIEWPORT_WIDTH - item.width);
        item.y = VIEWPORT_HEIGHT;
    }

    @Override
    Texture texture() {
        return texture;
    }

    @Override
    Rectangle box() {
        return item;
    }

    public void playSound() {

    }
}
