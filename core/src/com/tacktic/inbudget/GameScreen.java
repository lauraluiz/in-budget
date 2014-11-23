package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.function.Consumer;

public class GameScreen extends BaseScreen {
	private Array<Item> availableItems;
	private Array<Item> displayedItems;
	private Array<Item> purchasedItems;
	private Girl girl;
	private BigDecimal budget;
	private long lastDropTime;
	private long spawnInterval = 2000000000L;
	private boolean loadingResult;

	public GameScreen(InBudget game, Array<Item> allItems, BigDecimal budget) {
		super(game);
		this.girl = new Girl(resources());
		this.budget = budget;
		this.availableItems = allItems;
		this.availableItems.shuffle();
		this.displayedItems = new Array<Item>();
		this.purchasedItems = new Array<Item>();
		this.lastDropTime = TimeUtils.nanoTime();
		this.loadingResult = false;
		resources().gameMusic().play();
	}

	@Override
	public void renderBatch() {
		drawBackground();
		for (Item item : displayedItems) {
			drawItem(item);
		}
		drawTopMenu(1, availableItems.size, budget);
		drawGirl(girl);
		drawBottomMenu(purchasedItems);
	}

	@Override
	public void renderActions() {
		if (hasRoundFinished()) {
			if (!loadingResult) {
				loadingResult = true;
				calculatePrice(purchasedItems).thenAccept(new Consumer<BigDecimal>() {
					@Override
					public void accept(final BigDecimal totalPrice) {
						moveToResultScreen(budget, totalPrice);
					}
				});
			}
		} else {
			moveItems();
			if (TimeUtils.nanoTime() - lastDropTime > spawnInterval) {
				spawnItem();
			}
			reduceSpawnInterval();
		}
	}

	private void reduceSpawnInterval() {
		if (spawnInterval > 0) {
            spawnInterval -= 100000;
        }
	}

	private boolean hasRoundFinished() {
		return availableItems.size < 1 && displayedItems.size < 1;
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
		resources().gameMusic().stop();
	}

	private void spawnItem() {
		if (availableItems.size > 0) {
			displayedItems.add(availableItems.pop());
			lastDropTime = TimeUtils.nanoTime();
		}
	}

	private void moveItems() {
		Iterator<Item> iterator = displayedItems.iterator();
		while (iterator.hasNext()) {
			Item item = iterator.next();
			if (Gdx.input.isTouched()) {
				Vector3 touchPosition = touchPosition();
				if (item.box().contains(touchPosition.x, touchPosition.y)) {
					purchasedItems.add(item);
					resources().dropSound().play();
					iterator.remove();
				}
			}
			item.moveDown();
			if (item.outOfScreen()) {
				iterator.remove();
			}
		}
	}
}
