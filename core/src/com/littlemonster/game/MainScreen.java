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
import jdk.tools.jaotc.Main;

enum StageType{
	FoodStage, MainStage, GameStage
}
public class MainScreen implements Screen {

	final LittleMonster game;

	Stage stage;
	MainStage mainStage;

	//TODO Change to proper stages. This is just to allow game to run
	FoodStage foodStage;
	MainStage gameStage;

	long startTime, elapsedTime;
	

	public MainScreen(final LittleMonster game) {
		// Loads game and camera from main LittleMonster class
		this.game = game;


		mainStage = new MainStage(game, this);
		foodStage = new FoodStage(game, this);
		stage = mainStage;


		// Allows user input for stage items
		Gdx.input.setInputProcessor(stage);

		startTime = System.currentTimeMillis();
	}

	public void changeStage(StageType stage){
		switch(stage){
			case MainStage:
				this.stage = mainStage;
				break;
			case FoodStage:
				this.stage = foodStage;
				break;
			case GameStage:
				this.stage = gameStage;
				break;
		}
		Gdx.input.setInputProcessor(this.stage);
	}

	public void checkTime(){
		elapsedTime = startTime - System.currentTimeMillis();
		//TODO
	}
	
	@Override
	public void render(float delta) {

		// Draws Stage and acts if needed
		stage.act();
		stage.draw();

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) game.close();

		checkTime();
	}


	@Override
	public void show() {
		
	}

	@Override
	public void resize(int width, int height) {
		mainStage.getViewport().update(width, height, false);

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
		mainStage.dispose();
	}

}
