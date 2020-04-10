package com.littlemonster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuButtonsScreen extends Stage {
    LittleMonster game;
    MainScreen mainScreen;

    Texture bgImage;
    Image background;
    TextButton gameButton, feedButton, flushButton, sleepButton, statsButton;

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

        // Button for feeding
        sleepButton = new TextButton("Sleep", game.textButtonStyle);
        sleepButton.setPosition((int)((LittleMonster.V_WIDTH * .8/ 16)), 30);
        sleepButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (game.pet.isAlive()) {
                    game.pet.setSleeping(true);
                    mainScreen.changeStage(StageType.SleepStage);
                }
            }
        });

        // BUTTONS FOR MAIN STAGE

        gameButton = new TextButton("Play Game", game.textButtonStyle);
        gameButton.setPosition((int) ((LittleMonster.V_WIDTH * 3.2 / 16)), 30);
        gameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (game.pet.isAlive() && !game.pet.isSleeping())
                    mainScreen.changeStage(StageType.GameStage);
            }
        });

        // Button for feeding
        feedButton = new TextButton("Feed Pet", game.textButtonStyle);
        feedButton.setPosition((int) ((LittleMonster.V_WIDTH * 6.5 / 16)), 30);
        feedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (game.pet.isAlive() && !game.pet.isSleeping())
                    mainScreen.changeStage(StageType.FoodStage);
            }
        });


        // Button for feeding
        flushButton = new TextButton("Flush Toilet", game.textButtonStyle);
        flushButton.setPosition((int)((LittleMonster.V_WIDTH * 9.8 / 16)), 30);
        flushButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Only flushes if pet is not sleeping
                if (game.pet.isAlive() && !game.pet.isSleeping()) {
                    mainScreen.changeStage(StageType.FlushStage);
                    System.out.println("Flushed!");
                    game.pet.setHygiene(10);
                    game.pet.setPoos(0);
                    mainScreen.updateStages();
                }
            }
        });

        // Button for feeding
        statsButton = new TextButton("Stats", game.textButtonStyle);
        statsButton.setPosition((int)((LittleMonster.V_WIDTH * 13.5 / 16)), 30);
        statsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainScreen.updateStages();
                mainScreen.changeStage(StageType.StatsStage);
            }
        });



        addActor(gameButton);
        addActor(feedButton);
        addActor(flushButton);
        addActor(sleepButton);
        addActor(statsButton);

    }
}
