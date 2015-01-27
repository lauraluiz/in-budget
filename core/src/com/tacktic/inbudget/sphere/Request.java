package com.tacktic.inbudget.sphere;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Json;

import java.nio.charset.StandardCharsets;

public abstract class Request implements Net.HttpResponseListener {
    private static final String BASE_URL = "https://api.sphere.io/in-budget/";
    private final String method;
    private final String path;
    private final String content;
    protected final Json json;

    public Request(final String method, final String path, final String content) {
        this.method = method;
        this.path = path;
        this.content = content;
        this.json = new Json();
        json.setIgnoreUnknownFields(true);
    }

    public abstract boolean isLoaded();

    public void load() {
        Net.HttpRequest httpRequest = new Net.HttpRequest(method);
        httpRequest.setUrl(BASE_URL + path);
        httpRequest.setHeader("Authorization", "Bearer " + "eDfagRKckdjbtr8j6F9S4mdiB278B78Z");
        httpRequest.setHeader("Content-Type", StandardCharsets.UTF_8.name());
        if (content != null && !content.isEmpty()) {
            httpRequest.setContent(content);
        }
        Gdx.net.sendHttpRequest(httpRequest, this);
    }

    @Override
    public void handleHttpResponse(final Net.HttpResponse httpResponse) {
        int statusCode = httpResponse.getStatus().getStatusCode();
        if (statusCode >= 300) {
            throw new RuntimeException(statusCode + " - " + httpResponse.getResultAsString());
        }
    }

    @Override
    public void failed(final Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void cancelled() {

    }
}
