package com.littlemonster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;


public class GameScreen implements Screen {
	final LittleMonster game;
	final long time = 25000;

	private Texture dropImage;
	private Texture bucketImage;
	private Texture backgroundImage;
	
	OrthographicCamera camera;
	Rectangle bucket;
	Array<Rectangle> raindrops;
	long lastDropTime;
	int dropsGathered;
	long startTime, curTime, remainingTime;



	public GameScreen(final LittleMonster game) {
		this.game = game;
		camera = game.camera;

		startTime = System.currentTimeMillis();

		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture(Gdx.files.internal("apple.png"));
		bucketImage = new Texture(Gdx.files.internal("baby.png"));
		backgroundImage = new Texture(Gdx.files.internal("MainScreen.png"));

		// create a Rectangle to logically represent the bucket
		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;

		// create the raindrops array and spawn the first raindrop
		raindrops = new Array<>();
		spawnRaindrop();
	}

	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800 - 64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}

	@Override
	public void render(float delta) {
		remainingTime = (time - (System.currentTimeMillis() - startTime)) / 1000;

		if (remainingTime <= 0){
			giveRewards();
		}

		// tell the camera to update its matrices.
		camera.update();

		// begin a new batch and draw the bucket and
		// all drops
		game.batch.begin();
		game.batch.draw(backgroundImage, 0, 0, LittleMonster.V_WIDTH, LittleMonster.V_HEIGHT);
		game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, 480);
		game.batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height);
		for (Rectangle raindrop : raindrops) {
			game.batch.draw(dropImage, raindrop.x, raindrop.y, raindrop.width, raindrop.height);
		}
		game.font.draw(game.batch, Long.toString(remainingTime), 300, 480);
		game.batch.end();

		// process user input
		if (Gdx.input.isTouched()) {
			bucket.x = Gdx.input.getX() - 64 / 2;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			bucket.x -= 250 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			bucket.x += 250 * Gdx.graphics.getDeltaTime();

		// make sure the bucket stays within the screen bounds
		if (bucket.x < 0)
			bucket.x = 0;
		if (bucket.x > 800 - 64)
			bucket.x = 800 - 64;

		// check if we need to create a new raindrop
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnRaindrop();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we increase the
		// value our drops counter and add a sound effect.
		Iterator<Rectangle> iter = raindrops.iterator();
		while (iter.hasNext()) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0)
				iter.remove();
			if (raindrop.overlaps(bucket)) {
				dropsGathered++;
				iter.remove();
			}
		}
	}

	public void giveRewards(){
		System.out.println(dropsGathered);
		game.setScreen(new MainScreen(game));
		if (dropsGathered < 10){
			game.pet.giveHappiness(1);
			game.pet.giveWeight(-1);
		}
		else if (dropsGathered < 20){
			game.pet.giveHappiness(2);
			game.pet.giveWeight(-2);
		}
		else if (dropsGathered < 30){
			game.pet.giveHappiness(3);
			game.pet.giveWeight(-3);
		}
		else if (dropsGathered < 40){
			game.pet.giveHappiness(4);
			game.pet.giveWeight(-4);
		}
		else {
			game.pet.giveHappiness(5);
			game.pet.giveWeight(-5);
		}

		if (game.pet.getWeight() < 5)
			game.pet.setWeight(5);

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown

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
		dropImage.dispose();
		bucketImage.dispose();
		backgroundImage.dispose();

	}

}
