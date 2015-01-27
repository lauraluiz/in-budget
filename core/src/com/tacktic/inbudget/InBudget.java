package com.tacktic.inbudget;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.tacktic.inbudget.screens.MainMenuScreen;
import com.tacktic.inbudget.sphere.*;

public class InBudget extends Game {
    private Resources resources;
    private SpriteBatch batch;
    private BitmapFont font;
    private RoundRequest roundRequest = new RoundRequest();
    private CartRequest cartRequest = null;
    private ArrayMap<Integer, ItemRequest> roundItems = new ArrayMap<Integer, ItemRequest>();

    @Override
    public void create() {
        resources = new Resources();
        batch = new SpriteBatch();
        font = new BitmapFont();
        roundRequest.load();
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
        for (ItemRequest itemRequest : roundItems.values().toArray()) {
            for (Item item : itemRequest.getItems()) {
                item.dispose();
            }
        }
    }

    public Resources resources() {
        return resources;
    }

    public SpriteBatch batch() {
        return batch;
    }

    public BitmapFont font() {
        return font;
    }

    public Array<Item> fetchRound(final int roundNumber) {
        if (roundRequest.isLoaded()) {
            ItemRequest itemRequest = fetchItems(roundRequest.getRound(roundNumber));
            if (itemRequest.isLoaded()) {
                return itemRequest.getItems();
            }
        }
        return null;
    }

    public Money fetchTotalPrice(final Array<Item> items) {
        if (cartRequest != null) {
            Cart cart = cartRequest.getCart();
            if (cart != null) {
                return cart.getTotalPrice();
            }
        } else {
            cartRequest = new CartRequest(items);
        }
        return null;
    }

    private ItemRequest fetchItems(final Round round) {
        if (roundItems.containsKey(round.getExternalId())) {
            return roundItems.get(round.getExternalId());
        } else {
            ItemRequest access = new ItemRequest(round.getId());
            roundItems.put(round.getExternalId(), access);
            access.load();
            return access;
        }
    }
}
