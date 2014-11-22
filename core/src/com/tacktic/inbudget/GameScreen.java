package com.tacktic.inbudget;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class GameScreen extends BaseScreen {
	private Array<Item> items;
	private long lastDropTime;
	private int spawnInterval = 1000000000;

	public GameScreen(InBudget game) {
		super(game);
		resources().playBackgroundMusic();
		items = new Array<Item>();
	}

	@Override
	public void renderBatch() {
		for (Item item : items) {
			draw(item);
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
		if (roundItems().size > 0) {
			items.add(roundItems().pop());
			lastDropTime = TimeUtils.nanoTime();
		}
	}

	private void moveItems() {
		Iterator<Item> iterator = items.iterator();
		while (iterator.hasNext()) {
			Item item = iterator.next();
			item.moveDown();
			if (item.outOfScreen()) {
				iterator.remove();
			}
		}
	}
}
