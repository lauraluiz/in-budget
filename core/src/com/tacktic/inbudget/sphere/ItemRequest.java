package com.tacktic.inbudget.sphere;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Array;

public class ItemRequest extends Request {
    private ItemQueryResult result = null;

    public ItemRequest(final String roundId) {
        super(Net.HttpMethods.GET, "product-projections/search?categories.id:\"" + roundId + "\"", null);
    }

    public Array<Item> getItems() {
        return result.getResults();
    }

    @Override
    public boolean isLoaded() {
        boolean hasResult = result != null;
        boolean hasImages = true;
        if (hasResult) {
            for (Item item : result.getResults()) {
                hasImages &= item.isLoaded();
            }
        }
        return hasResult && hasImages;
    }

    @Override
    public void handleHttpResponse(final Net.HttpResponse httpResponse) {
        super.handleHttpResponse(httpResponse);
        ItemQueryResult query = json.fromJson(ItemQueryResult.class, httpResponse.getResultAsString());
        for (Item item : query.getResults()) {
            item.load();
        }
        result = query;
    }

    @Override
    public void failed(final Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void cancelled() {

    }
}
