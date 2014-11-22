package com.tacktic.inbudget;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import io.sphere.sdk.products.ProductProjection;

import javax.money.MonetaryAmount;

import static com.tacktic.inbudget.BaseScreen.*;
import static java.util.Locale.ENGLISH;

public class Item extends GameElement {
    private final String productId;
    private final Texture texture;
    private final Rectangle item;
    private final MonetaryAmount price;

    public Item(ProductProjection product, Resources resources) {
        String slug = product.getSlug().get(ENGLISH).orElse("");
        productId = product.getId();
        texture = resources.itemImage(slug);
        item = new Rectangle();
        item.width = texture.getWidth();
        item.height = texture.getHeight();
        item.x = MathUtils.random(VIEWPORT_MARGIN, VIEWPORT_WIDTH - item.width - VIEWPORT_MARGIN);
        item.y = VIEWPORT_HEIGHT;
        if (product.getMasterVariant().getPrices().isEmpty()) {
            throw new RuntimeException("Product without prices " + productId);
        }
        price = product.getMasterVariant().getPrices().get(0).getValue();
    }

    @Override
    Texture texture() {
        return texture;
    }

    @Override
    Rectangle box() {
        return item;
    }

    public String productId() {
        return productId;
    }

    public String price() {
        String textValue = String.valueOf(price.getNumber().intValueExact());
        return "$" + textValue;
    }

    public void playSound() {

    }
}
