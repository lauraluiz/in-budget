package com.tacktic.inbudget;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.sphere.sdk.categories.Category;
import io.sphere.sdk.categories.queries.CategoryQuery;
import io.sphere.sdk.client.JavaClient;
import io.sphere.sdk.client.JavaClientImpl;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.products.search.ProductProjectionSearch;
import io.sphere.sdk.queries.PagedQueryResult;
import io.sphere.sdk.queries.Query;
import io.sphere.sdk.search.FilterExpression;
import io.sphere.sdk.search.PagedSearchResult;
import io.sphere.sdk.search.SearchDsl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static io.sphere.sdk.products.ProductProjectionType.CURRENT;

public class InBudget extends Game {
    private Resources resources;
    private SpriteBatch batch;
    private BitmapFont font;
    private JavaClient sphere;
    private CompletableFuture<List<Category>> rounds;
    private CompletableFuture<Array<Item>> roundItems;

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

    public void changeRound(final int round) {
        roundItems = fetchRound(round);
    }

    public CompletableFuture<List<Category>> rounds() {
        return rounds;
    }

    public CompletableFuture<Array<Item>> roundItems() {
        return roundItems;
    }

    public CompletableFuture<Array<Item>> fetchRound(final int round) {
        return rounds.thenCompose(new Function<List<Category>, CompletableFuture<Array<Item>>>() {
            @Override
            public CompletableFuture<Array<Item>> apply(final List<Category> categories) {
                if (categories.size() < round) {
                    throw new RuntimeException("Requested round " + round + ", only " + categories.size());
                }
                return fetchItems(categories.get(round - 1));
            }
        });
    }

    @Override
    public void create() {
        resources = new Resources();
        batch = new SpriteBatch();
        font = new BitmapFont();
        sphere = createSphereClient();
        rounds = fetchRounds();
        changeRound(1);
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
        sphere.close();
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

    private CompletableFuture<List<Category>> fetchRounds() {
        final Query<Category> query = new CategoryQuery().withLimit(50);
        return sphere.execute(query).thenApply(new Function<PagedQueryResult<Category>, List<Category>> () {
            @Override
            public List<Category> apply(final PagedQueryResult<Category> result) {
                if (result.getTotal() < 0) {
                    throw new RuntimeException("No categories found!");
                }
                return result.getResults();
            }
        });
    }

    private CompletableFuture<Array<Item>> fetchItems(final Category round) {
        final FilterExpression<ProductProjection> filter = FilterExpression.of("categories.id:\"" + round.getId() + "\"");
        final SearchDsl<ProductProjection> search = new ProductProjectionSearch(CURRENT).plusFilterQuery(filter);
        return sphere.execute(search.withLimit(100)).thenApply(new Function<PagedSearchResult<ProductProjection>, Array<Item>>() {
            @Override
            public Array<Item> apply(final PagedSearchResult<ProductProjection> result) {
                Array<Item> items = new Array<Item>();
                for (ProductProjection product : result.getResults()) {
                    items.add(new Item(product, resources()));
                }
                return items;
            }
        });
    }
}
