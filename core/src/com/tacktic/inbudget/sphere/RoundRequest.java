package com.tacktic.inbudget.sphere;

import com.badlogic.gdx.Net;

public class RoundRequest extends Request {
    private RoundQueryResult result = null;

    public RoundRequest() {
        super(Net.HttpMethods.GET, "categories", null);
    }

    /**
     * Get the round corresponding to the given round number.
     * This method assumes the result has been fetched.
     * @param roundNumber the number of the requested round.
     * @return the requested round object.
     */
    public Round getRound(final int roundNumber) {
        for (Round round : result.getResults()) {
            if (round.getExternalId() == roundNumber) {
                return round;
            }
        }
        throw new RuntimeException("Not found requested round " + roundNumber);
    }

    @Override
    public boolean isLoaded() {
        return result != null;
    }

    @Override
    public void handleHttpResponse(final Net.HttpResponse httpResponse) {
        super.handleHttpResponse(httpResponse);
        result = json.fromJson(RoundQueryResult.class, httpResponse.getResultAsString());
   }

    @Override
    public void failed(final Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void cancelled() {

    }
}
