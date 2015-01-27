package com.tacktic.inbudget.sphere;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Array;

class AddToCartRequest extends Request {
    private Cart result = null;

    AddToCartRequest(final Cart cart, final Array<Item> items) {
        super(Net.HttpMethods.POST, "carts/" + cart.getId(), updateActions(cart, items));
    }

    public Cart getCart() {
        return result;
    }

    @Override
    public boolean isLoaded() {
        return result != null;
    }

    @Override
    public void handleHttpResponse(final Net.HttpResponse httpResponse) {
        super.handleHttpResponse(httpResponse);
        result = json.fromJson(Cart.class, httpResponse.getResultAsString());
        System.out.println(result);
    }

    @Override
    public void failed(final Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void cancelled() {

    }

    private static String updateActions(final Cart cart, final Array<Item> items) {
        String actions = "";
        for (Item item : items) {
            actions += action(item) + ",";
        }
        if (!actions.isEmpty()) {
            actions = actions.substring(0, actions.length() - 1);
        }
        return "{\"version\" : " + cart.getVersion() + ", \"actions\" : [" + actions + "]}";
    }

    private static String action(Item item) {
        return "{\"action\" : \"addLineItem\", \"productId\" : \"" + item.productId() + "\", \"variantId\" : 1, \"quantity\" : 1}";
    }
}
