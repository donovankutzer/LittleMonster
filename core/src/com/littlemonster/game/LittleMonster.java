package com.littlemonster.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;

public class LittleMonster extends Game {
	public static final int V_WIDTH = 800, V_HEIGHT = 600;

	public OrthographicCamera camera;
	public SpriteBatch batch;
	public BitmapFont font;
	public Pet pet;


	public void create() {

		// Create camera for all screens to use
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);

		// Batch to draw all fonts onto
		batch = new SpriteBatch();
		// Use LibGDX's default Arial font.
		// TODO CHANGE TO COOL FONT
		font = new BitmapFont();

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
	}
}
