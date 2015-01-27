package com.tacktic.inbudget.sphere;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.tacktic.inbudget.GameElement;

import static com.tacktic.inbudget.screens.BaseScreen.VIEWPORT_HEIGHT;
import static com.tacktic.inbudget.screens.BaseScreen.VIEWPORT_MARGIN;
import static com.tacktic.inbudget.screens.BaseScreen.VIEWPORT_WIDTH;

public class Item extends GameElement implements Net.HttpResponseListener, Json.Serializable {
    private String id;
    private String imageUrl;
    private Texture texture;
    private Rectangle item;
    private Money price;

    public Item() {

    }

    public boolean isLoaded() {
        return texture != null;
    }

    public void load() {
        Net.HttpRequest requestImage = new Net.HttpRequest(Net.HttpMethods.GET);
        requestImage.setUrl(imageUrl);
        Gdx.net.sendHttpRequest(requestImage, this);
    }

    public String productId() {
        return id;
    }

    public Money price() {
        return price;
    }

    @Override
    public Texture texture() {
        return texture;
    }

    @Override
    public Rectangle box() {
        return item;
    }

    @Override
    public void write(final Json json) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void read(final Json json, final JsonValue jsonData) {
        JsonValue variant = jsonData.get("masterVariant");
        this.id = jsonData.get("id").asString();
        this.imageUrl = variant.get("images").get(0).get("url").asString();
        this.price = json.fromJson(Money.class, variant.get("prices").get(0).get("value").toString());
        this.item = new Rectangle();
    }

    @Override
    public void handleHttpResponse(final Net.HttpResponse httpResponse) {
        String fileName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1, imageUrl.length());
        final FileHandle fileHandle = Gdx.files.external(fileName);
        fileHandle.write(httpResponse.getResultAsStream(), false);
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                texture = new Texture(fileHandle);
                setItemDimensions(texture.getWidth(), texture.getHeight());
            }
        });
    }

    @Override
    public void failed(final Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void cancelled() {

    }

    public void dispose() {
        texture.dispose();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", price=" + price +
                '}';
    }

    private void setItemDimensions(final int width, final int length) {
        this.item.setWidth(width);
        this.item.setHeight(length);
        this.item.setX(MathUtils.random(VIEWPORT_MARGIN, VIEWPORT_WIDTH - width - VIEWPORT_MARGIN));
        this.item.setY(VIEWPORT_HEIGHT);
    }
}
