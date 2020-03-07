package com.littlemonster.game;

import com.badlogic.gdx.Gdx;
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
	

	public MainScreen(final LittleMonster game) {
		// Loads game and camera from main LittleMonster class
		this.game = game;
		this.stage = new Stage(new FillViewport(LittleMonster.V_WIDTH, LittleMonster.V_HEIGHT, game.camera));
		// Allows user input for stage items
		Gdx.input.setInputProcessor(stage);

		// Set background image
		Texture bgImage = new Texture(Gdx.files.internal("MainScreen.jpg"));
		background = new Image(bgImage);
		background.setPosition(0, 0);
		stage.addActor(background);

		makeButtons();

	}

	// Method for creating all buttons on screen
	public void makeButtons(){
		// Make Skin for all buttons
		Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.CLEAR);
		pixmap.fill();
		skin.add("clear", new Texture(pixmap));
		skin.add("default", new BitmapFont());

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("clear", new Color(0, 0, 0, 0));
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		// Button for game
		TextButton gameButton = new TextButton("Play Game", skin);
		gameButton.setPosition(LittleMonster.V_WIDTH * 3 / 4, 40);
		gameButton.setWidth(60);
		gameButton.setVisible(true);
		gameButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GameScreen(game));
			}
		});

		// Button for feeding
		TextButton feedButton = new TextButton("Feed Pet", skin);

		feedButton.setPosition(LittleMonster.V_WIDTH * 2 / 4, 40);
		feedButton.setWidth(60);
		feedButton.setVisible(true);
		feedButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("Pets current food level: " + game.pet.getHunger());
				game.pet.setHunger(game.pet.getHunger() + 1);
				System.out.println("Pets hunger after feeding :" + game.pet.getHunger());
			}
		});


		stage.addActor(gameButton);
		stage.addActor(feedButton);

	}
	
	@Override
	public void render(float delta) {
		// Unneeded, TODO remove later once I know for sure its useless
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Draws all actors in stage to screen, acts if needs acting on
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

//
		game.batch.begin();
		game.batch.end();
	}


	@Override
	public void show() {
		background.addAction(alpha(0.5f));
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
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
