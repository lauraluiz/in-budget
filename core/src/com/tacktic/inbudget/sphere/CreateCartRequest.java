package com.tacktic.inbudget.sphere;

import com.badlogic.gdx.Net;

class CreateCartRequest extends Request {
    private Cart result = null;

    CreateCartRequest() {
        super(Net.HttpMethods.POST, "carts", "{\"currency\" : \"EUR\"}");
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
}
