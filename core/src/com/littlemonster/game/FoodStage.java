package com.littlemonster.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class FoodStage extends MenuButtonsScreen {

    TextButton breadButton, chickenButton, candyButton, chocoButton;

    public FoodStage(final LittleMonster game, final MainScreen mainScreen) {
        super(game, mainScreen);
        drawScreen(StageType.FoodStage);

        // Add listener to feed button to change stage to FoodStage
        feedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainScreen.changeStage(StageType.MainStage);
            }
        });

        // BUTTONS FOR MAIN STAGE

        breadButton = new TextButton("Bread", game.transTextButtonStyle);
        breadButton.setPosition((int) ((LittleMonster.V_WIDTH / 2) - breadButton.getWidth() / 2), (int) ((LittleMonster.V_HEIGHT * 3 / 4) - breadButton.getHeight() / 2));
        breadButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet.giveFood(FoodType.Bread);
            }
        });

        // Button for feeding
        chickenButton = new TextButton("Chicken", game.textButtonStyle);
        chickenButton.setPosition((int) ((LittleMonster.V_WIDTH / 2) - chickenButton.getWidth() / 2), (int) ((LittleMonster.V_HEIGHT * 2 / 8) - chickenButton.getHeight() / 2));
        chickenButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet.giveFood(FoodType.Chicken);
            }
        });

        // Button for feeding
        candyButton = new TextButton("Candy", game.textButtonStyle);

        candyButton.setPosition((int) ((LittleMonster.V_WIDTH / 2) - candyButton.getWidth() / 2), (int) ((LittleMonster.V_HEIGHT * 3 / 8) - candyButton.getHeight() / 2));
        candyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet.giveFood(FoodType.Candy);
            }
        });

        // Button for feeding
        chocoButton = new TextButton("Chocolate", game.textButtonStyle);

        chocoButton.setPosition((int) ((LittleMonster.V_WIDTH / 2) - chocoButton.getWidth() / 2), (int) ((LittleMonster.V_HEIGHT * 4 / 8) - chocoButton.getHeight() / 2));
        chocoButton.setVisible(true);
        chocoButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet.giveFood(FoodType.Chocolate);
            }
        });

        addActor(breadButton);
        addActor(chickenButton);
        addActor(candyButton);
        addActor(chocoButton);
    }

}
