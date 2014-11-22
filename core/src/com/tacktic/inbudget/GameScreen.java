package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class GameScreen extends BaseScreen {
	private Array<Item> availableItems;
	private Array<Item> displayedItems;
	private Array<Item> purchasedItems;
	private long budget = 10000L;
	private long lastDropTime;
	private int spawnInterval = 1000000000;

	public GameScreen(InBudget game, Array<Item> allItems) {
		super(game);
		this.availableItems = allItems;
		this.availableItems.shuffle();
		this.displayedItems = new Array<Item>();
		this.purchasedItems = new Array<Item>();
		this.lastDropTime = TimeUtils.nanoTime();
	}

	@Override
	public void renderBatch() {
		drawBackground();
		for (Item item : displayedItems) {
			drawItem(item);
		}
		drawTopMenu(1, availableItems.size);
		drawBottomMenu(purchasedItems);
	}

	@Override
	public void renderActions() {
		moveItems();
		if (TimeUtils.nanoTime() - lastDropTime > spawnInterval) {
			spawnItem();
		}
		if (spawnInterval > 0) {
			spawnInterval -= 100000;
		}
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
