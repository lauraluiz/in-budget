package com.tacktic.inbudget;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.sphere.sdk.client.JavaClient;
import io.sphere.sdk.client.JavaClientImpl;

import java.util.HashMap;
import java.util.Map;

public class InBudget extends Game {
    private Resources resources;
    private SpriteBatch batch;
    private BitmapFont font;
    private JavaClient sphere;

    public Resources resources() {
        return resources;
    }

    public SpriteBatch batch() {
        return batch;
    }

    public BitmapFont font() {
        return font;
    }

    public JavaClient sphere() {
        return sphere;
    }

    @Override
    public void create() {
        resources = new Resources();
        batch = new SpriteBatch();
        font = new BitmapFont();
        sphere = createSphereClient();
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        resources.dispose();
        batch.dispose();
        font.dispose();
    }

    private JavaClient createSphereClient() {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put("sphere.auth", "https://auth.sphere.io");
        values.put("sphere.core", "https://api.sphere.io");
        values.put("sphere.project", System.getenv("IN_BUDGET_PROJECT"));
        values.put("sphere.clientId", System.getenv("IN_BUDGET_CLIENT_ID"));
        values.put("sphere.clientSecret", System.getenv("IN_BUDGET_CLIENT_SECRET"));
        final Config config = ConfigFactory.parseMap(values);
        return new JavaClientImpl(config);
    }
}
