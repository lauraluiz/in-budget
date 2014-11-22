package com.tacktic.inbudget;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class GameScreen extends BaseScreen {
	private Array<Item> availableItems;
	private Array<Item> displayedItems;
	private long lastDropTime;
	private int spawnInterval = 1000000000;

	public GameScreen(InBudget game, Array<Item> allItems) {
		super(game);
		this.availableItems = allItems;
		this.displayedItems = new Array<Item>();
		this.lastDropTime = TimeUtils.nanoTime();
	}

	@Override
	public void renderBatch() {
		draw(resources().backgroundImage(), 0, 0);
		write("Items in cue: " + availableItems.size, 100, 100);
		for (Item item : displayedItems) {
			drawItem(item);
		}
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
			item.moveDown();
			if (item.outOfScreen()) {
				iterator.remove();
			}
		}
	}
}
