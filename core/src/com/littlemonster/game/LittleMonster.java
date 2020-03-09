package com.littlemonster.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.io.IOException;

public class LittleMonster extends Game {
	public static final int V_WIDTH = 800, V_HEIGHT = 600;

	public OrthographicCamera camera;
	public SpriteBatch batch;
	public BitmapFont font;
	public Pet pet;
	public Skin skin;
	public Pixmap pixmap;
	public TextButton.TextButtonStyle textButtonStyle;


	public void create() {

		// Create camera for all screens to use
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);

		// Batch to draw all fonts onto
		batch = new SpriteBatch();

		font = new BitmapFont(Gdx.files.internal("font.fnt"));
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		font.getData().setScale(0.80f);

		skin = new Skin();
		pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.CLEAR);
		pixmap.fill();
		skin.add("clear", new Texture(pixmap));
		skin.add("default", font);

		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("clear", new Color(0, 0, 0, 0));
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		// Loads pet if played before
		pet = new Pet();
		try {
			pet.loadPet();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Set screen to main screen
		this.setScreen(new MainScreen(this));

	}

	public void render() {
		super.render(); //important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		this.getScreen().dispose();
	}

	public void close(){
		pet.savePet();
		Gdx.app.exit();
	}
}
