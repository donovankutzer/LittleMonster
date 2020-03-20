package com.littlemonster.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
	public TextButton.TextButtonStyle textButtonStyle, transTextButtonStyle;
	public long startTime, currentTime, elapsedTime;
	TextureAtlas buttonAtlas;


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

		pixmap = new Pixmap(1,  1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.BLACK);
		pixmap.fill();
		skin.add("clear", new Texture(pixmap));
		skin.add("default", font);

		transTextButtonStyle = new TextButton.TextButtonStyle();
		transTextButtonStyle.up = skin.newDrawable("clear", new Color(0, 0, 0, 0f));
		transTextButtonStyle.font = skin.getFont("default");
		skin.add("default", transTextButtonStyle);


		buttonAtlas = new TextureAtlas(Gdx.files.internal("skin/neon-ui.atlas"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.newDrawable(skin.getDrawable("button"), 69/255f,180/255f, 106/255f, 1f);
		textButtonStyle.over = skin.newDrawable(skin.getDrawable("button-over"), 69/255f,180/255f, 106/255f, 1f);
		textButtonStyle.down = skin.newDrawable(skin.getDrawable("button-pressed"), 69 / 255f, 180 / 255f, 106 / 255f, 1f);


		// Loads pet if played before
		pet = new Pet();
		try {
			pet.loadPet();
		} catch (IOException e) {
			e.printStackTrace();
		}

		startTime = System.currentTimeMillis();

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
