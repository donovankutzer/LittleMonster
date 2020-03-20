package com.littlemonster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;


enum StageType {
    FoodStage, MainStage, GameStage, SleepStage
}

public class MainScreen implements Screen {

    final LittleMonster game;

    private Stage stage;
    private MainStage mainStage;
    private FoodStage foodStage;
    private GameStage gameStage;
    private SleepStage sleepStage;
    private ShapeRenderer petStats;


    public MainScreen(final LittleMonster game) {
        // Loads game and camera from main LittleMonster class
        this.game = game;

        // Creates all stages
        mainStage = new MainStage(game, this);
        foodStage = new FoodStage(game, this);
        gameStage = new GameStage(game, this);
        sleepStage = new SleepStage(game, this);

        petStats = new ShapeRenderer();

        stage = mainStage;

        // Allows user input for stage items
        Gdx.input.setInputProcessor(stage);
    }

    public void changeStage(StageType stage) {
        switch (stage) {
            case MainStage:
                this.stage = mainStage;
                break;
            case FoodStage:
                this.stage = foodStage;
                break;
            case GameStage:
                this.stage = gameStage;
                break;
            case SleepStage:
                this.stage = sleepStage;
                break;
        }
        Gdx.input.setInputProcessor(this.stage);
    }


    public void checkTime() {
        game.currentTime = System.currentTimeMillis();
        game.elapsedTime = game.currentTime - game.startTime;
        // If 1 minute has passed
        if (game.elapsedTime / 1000 > 60) {
            game.pet.updateStats((int) (game.elapsedTime / 1000 / 60));
            // Resets elapsed time to 0
            game.startTime = game.currentTime;
        }
    }

    @Override
    public void render(float delta) {

        // Draws Stage and acts if needed
        stage.act();
        stage.draw();

        // Displays text above pet stats
        game.batch.begin();
        game.font.draw(game.batch, "Hunger", 40, 550);
        game.font.draw(game.batch, "Energy", 240, 550);
        game.font.draw(game.batch, "Happiness", 440, 550);
        game.font.draw(game.batch, "Hygiene", 640, 550);
        game.batch.end();

        // Display pet stat bars
        petStats.begin(ShapeRenderer.ShapeType.Filled);
        petStats.setColor(Color.BLACK);
        petStats.rect(75 - 35, 525 - 4, 70, 8);
        petStats.rect(75 - 35, 525 - 4, game.pet.getHunger() * 70 / 10, 8, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);

        petStats.rect(275 - 35, 525 - 4, 70, 8);
        petStats.rect(275 - 35, 525 - 4, game.pet.getEnergy() * 70 / 10, 8, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);

        petStats.rect(475 - 35, 525 - 4, 70, 8);
        petStats.rect(475 - 35, 525 - 4, game.pet.getHappiness() * 70 / 10, 8, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);

        petStats.rect(675 - 35, 525 - 4, 70, 8);
        petStats.rect(675 - 35, 525 - 4, game.pet.getHygiene() * 70 / 10, 8, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);

        petStats.end();

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
        foodStage.dispose();
        gameStage.dispose();
    }

}
