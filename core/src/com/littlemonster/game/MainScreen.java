package com.littlemonster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class MainScreen implements Screen {

	final LittleMonster game;

	private Stage stage;
	private Image background;

	Skin skin;
	Pixmap pixmap;
	TextButton.TextButtonStyle textButtonStyle;
	TextButton gameButton, feedButton, flushButton, sleepButton;
	float startTime, elapsedTime;
	

	public MainScreen(final LittleMonster game) {
		// Loads game and camera from main LittleMonster class
		this.game = game;
		this.stage = new Stage(new FillViewport(LittleMonster.V_WIDTH, LittleMonster.V_HEIGHT, game.camera));
		// Allows user input for stage items
		Gdx.input.setInputProcessor(stage);

		// Set background image
		Texture bgImage = new Texture(Gdx.files.internal("MainScreen.png"));
		background = new Image(bgImage);
		background.setPosition(0, 0);
		stage.addActor(background);


		// Draw pet
		Texture petSprite = new Texture(Gdx.files.internal("baby.png"));
		Image petActor = new Image(petSprite);
		petActor.setPosition(320, 180);
		// Creates moving animation
		petActor.addAction(forever(sequence(moveBy(0, 20), delay(0.69f), moveBy(0, -20), delay(0.69f))));
		stage.addActor(petActor);


		makeButtons();

		startTime = System.currentTimeMillis();


	}

	// Method for creating all buttons on screen
	public void makeButtons(){
		// Make Skin for all buttons
		skin = new Skin();
		pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.CLEAR);
		pixmap.fill();
		skin.add("clear", new Texture(pixmap));
		skin.add("default", game.font);

		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("clear", new Color(0, 0, 0, 0));
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		// Button for game
		gameButton = new TextButton("Play Game", skin);
		gameButton.setPosition((LittleMonster.V_WIDTH * 3 / 8) - gameButton.getWidth() / 2, 40);
		gameButton.setWidth(60);
		gameButton.setVisible(true);
		gameButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GameScreen(game));
			}
		});

		// Button for feeding
		feedButton = new TextButton("Feed Pet", skin);

		feedButton.setPosition((LittleMonster.V_WIDTH * 5 / 8) - feedButton.getWidth() / 2, 40);
		feedButton.setWidth(60);
		feedButton.setVisible(true);
		feedButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("Pets current food level: " + game.pet.getHunger());
				if (!game.pet.giveFood(FoodType.Bread))
					//TODO sad noise + sprite
				System.out.println("Pets hunger after feeding :" + game.pet.getHunger());
			}
		});

		// Button for feeding
		flushButton = new TextButton("Flush Toilet", skin);

		flushButton.setPosition((LittleMonster.V_WIDTH * 7 / 8) - flushButton.getWidth() / 2, 40);
		flushButton.setWidth(60);
		flushButton.setVisible(true);
		flushButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("Flushed!");
				game.pet.setHygiene(10);
			}
		});

		// Button for feeding
		sleepButton = new TextButton("Sleep", skin);

		sleepButton.setPosition((LittleMonster.V_WIDTH * 1 / 8) - sleepButton.getWidth() / 2, 40);
		sleepButton.setWidth(60);
		sleepButton.setVisible(true);
		sleepButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.pet.setEnergy(10);
				System.out.println("Pet fully reaster");
			}
		});

		stage.addActor(gameButton);
		stage.addActor(feedButton);
		stage.addActor(flushButton);
		stage.addActor(sleepButton);
	}

	public void checkTime(){
		elapsedTime = startTime - System.currentTimeMillis();
		//TODO
	}
	
	@Override
	public void render(float delta) {

		// Draws all actors in stage to screen, acts if needs acting on
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) game.close();

		checkTime();
	}


	@Override
	public void show() {
		
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
		
	}

	@Override
	public void pause() {
		// This gets called before any close event
		game.pet.savePet();
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
