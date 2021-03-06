package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class ResultScreen extends BaseScreen {
    private final BigDecimal budget;
    private final BigDecimal totalPrice;
    private final BigDecimal percentage;
    private boolean loadingRound;

    public ResultScreen(InBudget game, BigDecimal budget, BigDecimal totalPrice) {
        super(game);
        this.budget = budget;
        this.totalPrice = totalPrice;
        this.percentage = totalPrice.divide(budget, 3, BigDecimal.ROUND_HALF_UP);
        this.loadingRound = false;
    }

    @Override
    public void renderBatch() {
        drawResultBackground();
        write("1", Color.WHITE, 3, VIEWPORT_WIDTH/2 + 100, VIEWPORT_HEIGHT - 170);
        write("$" + String.valueOf(budget), Color.DARK_GRAY, 2, VIEWPORT_WIDTH/2 + 100, VIEWPORT_HEIGHT - 250);
        write("$" + totalPrice.toString(), Color.WHITE, 2, VIEWPORT_WIDTH/2 + 100, VIEWPORT_HEIGHT - 320);
        write("" + percentage.movePointRight(2) + "%", Color.WHITE, 2, VIEWPORT_WIDTH/2 + 100, VIEWPORT_HEIGHT - 400);

        if (Gdx.input.isTouched()) {
            loadingRound = true;
            changeRound(1).thenAccept(new Consumer<Array<Item>>() {
                @Override
                public void accept(final Array<Item> items) {
                    moveToGameScreen(items);
                }
            });

        }
    }

    @Override
    public void renderActions() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
