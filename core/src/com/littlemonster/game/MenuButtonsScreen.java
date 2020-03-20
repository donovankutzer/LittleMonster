package com.littlemonster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class MenuButtonsScreen extends Stage {
    LittleMonster game;
    MainScreen mainScreen;

    Texture bgImage;
    Image background;
    TextButton gameButton, feedButton, flushButton, sleepButton;

    public MenuButtonsScreen(LittleMonster game, MainScreen mainScreen) {
        this.game = game;
        this.mainScreen = mainScreen;
    }


    public void drawScreen(StageType stage){
        setBg(stage);
        drawMenuButtons();
    }


    public void setBg(StageType stage){
        switch(stage){
            case MainStage:
                bgImage = new Texture(Gdx.files.internal("MainScreen.png"));
                break;
            case FoodStage:
            case GameStage:
                bgImage = new Texture(Gdx.files.internal("MenuStage.png"));
                break;
            case SleepStage:
                bgImage = new Texture(Gdx.files.internal("SleepStage.png"));
        }
        background = new Image(bgImage);
        background.setPosition(0, 0);
        addActor(background);
    }

    public void drawMenuButtons() {

        // BUTTONS FOR MAIN STAGE

        gameButton = new TextButton("Play Game", game.textButtonStyle);
        gameButton.setPosition((int) ((LittleMonster.V_WIDTH * 3 / 8) - gameButton.getWidth() / 2), 30);
        gameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainScreen.changeStage(StageType.GameStage);
            }
        });

        // Button for feeding
        feedButton = new TextButton("Feed Pet", game.textButtonStyle);
        feedButton.setPosition((int) ((LittleMonster.V_WIDTH * 5 / 8) - feedButton.getWidth() / 2), 30);


        // Button for feeding
        flushButton = new TextButton("Flush Toilet", game.textButtonStyle);
        flushButton.setPosition((LittleMonster.V_WIDTH * 7 / 8) - flushButton.getWidth() / 2, 30);
        flushButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Flushed!");
                game.pet.setHygiene(10);
            }
        });

        // Button for feeding
        sleepButton = new TextButton("Sleep", game.textButtonStyle);
        sleepButton.setPosition((LittleMonster.V_WIDTH * 1 / 8) - sleepButton.getWidth() / 2, 30);
        sleepButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainScreen.changeStage(StageType.SleepStage);
            }
        });

        addActor(gameButton);
        addActor(feedButton);
        addActor(flushButton);
        addActor(sleepButton);

    }
}
