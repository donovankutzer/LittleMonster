package com.littlemonster.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;

public class LittleMonster extends Game {


	public SpriteBatch batch;
	public BitmapFont font;
	public Pet pet;

	public void create() {
		batch = new SpriteBatch();
		//Use LibGDX's default Arial font.
		font = new BitmapFont();

		pet = new Pet();
		try {
			pet.loadPet();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pet.savePet();
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
