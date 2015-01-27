package com.tacktic.inbudget.sphere;

import com.badlogic.gdx.utils.Array;

public class CartRequest {
    private Array<Item> items;
    private CreateCartRequest createCartRequest = new CreateCartRequest();
    private AddToCartRequest addToCartRequest = null;

    public CartRequest(final Array<Item> items) {
        this.items = items;
        createCartRequest.load();
    }

    public Cart getCart() {
        if (addToCartRequest != null && addToCartRequest.isLoaded()) {
            return addToCartRequest.getCart();
        } else {
            load();
            return null;
        }
    }

    private void load() {
        if (createCartRequest.isLoaded() && addToCartRequest == null) {
            addToCartRequest = new AddToCartRequest(createCartRequest.getCart(), items);
            addToCartRequest.load();
        }
    }
}
