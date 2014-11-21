package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class GameScreen extends BaseScreen {
	private Bucket bucket;
	private Array<Raindrop> raindrops;
	private long lastDropTime;
	private int spawnInterval = 1000000000;
	private int dropsGathered = 0;

	public GameScreen(InBudget game) {
		super(game);
		resources().playBackgroundMusic();
		bucket = new Bucket(resources());
		raindrops = new Array<Raindrop>();
		spawnRaindrop();
	}

	@Override
	public void renderBatch() {
		write("Drops collected " + dropsGathered, 0, 480);
		draw(bucket);
		for (Raindrop raindrop : raindrops) {
			draw(raindrop);
		}
	}

	@Override
	public void renderActions() {
		moveBucket();
		moveDrops();
		if (TimeUtils.nanoTime() - lastDropTime > spawnInterval) {
			spawnRaindrop();
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

	private void spawnRaindrop() {
		raindrops.add(new Raindrop(resources()));
		lastDropTime = TimeUtils.nanoTime();
	}

	private void moveBucket() {
		if (Gdx.input.isTouched()) {
			bucket.setXTo(touchPosition().x);
		} else {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				bucket.moveLeft();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				bucket.moveRight();
			}
		}
		bucket.fixPosition();
	}

	private void moveDrops() {
		Iterator<Raindrop> iterator = raindrops.iterator();
		while (iterator.hasNext()) {
			Raindrop raindrop = iterator.next();
			raindrop.moveDown();
			raindrop.fixPosition();
			if (raindrop.outOfScreen()) {
				iterator.remove();
			}
			if (raindrop.box().overlaps(bucket.box())) {
				dropsGathered++;
				raindrop.playSound();
				iterator.remove();
			}
		}
	}
}
