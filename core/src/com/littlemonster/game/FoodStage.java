package com.littlemonster.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class FoodStage extends MenuButtonsScreen {

    TextButton breadButton, chickenButton, candyButton, chocoButton, eggButton, liverButton, broccoliButton;

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

        breadButton = new TextButton("Bread", game.transTextButtonStyle);
        breadButton.setPosition((int) ((LittleMonster.V_WIDTH / 2) - breadButton.getWidth() / 2), (int) ((LittleMonster.V_HEIGHT * 12 / 16) - breadButton.getHeight() / 2));
        breadButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet.giveFood(FoodType.Bread);
            }
        });

        broccoliButton = new TextButton("Broccoli", game.transTextButtonStyle);
        broccoliButton.setPosition((int) ((LittleMonster.V_WIDTH / 2) - broccoliButton.getWidth() / 2), (int) ((LittleMonster.V_HEIGHT * 10 / 16) - broccoliButton.getHeight() / 2));
        broccoliButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet.giveFood(FoodType.Broccoli);
            }
        });

        chickenButton = new TextButton("Chicken", game.transTextButtonStyle);
        chickenButton.setPosition((int) ((LittleMonster.V_WIDTH / 2) - chickenButton.getWidth() / 2), (int) ((LittleMonster.V_HEIGHT * 11 / 16) - chickenButton.getHeight() / 2));
        chickenButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet.giveFood(FoodType.Chicken);
            }
        });

        eggButton = new TextButton("Eggs", game.transTextButtonStyle);
        eggButton.setPosition((int) ((LittleMonster.V_WIDTH / 2) - eggButton.getWidth() / 2), (int) ((LittleMonster.V_HEIGHT * 9 / 16) - eggButton.getHeight() / 2));
        eggButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet.giveFood(FoodType.Eggs);
            }
        });

        liverButton = new TextButton("Liver", game.transTextButtonStyle);
        liverButton.setPosition((int) ((LittleMonster.V_WIDTH / 2) - liverButton.getWidth() / 2), (int) ((LittleMonster.V_HEIGHT * 8 / 16) - liverButton.getHeight() / 2));
        liverButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet.giveFood(FoodType.Chicken);
            }
        });

        chocoButton = new TextButton("Chocolate", game.transTextButtonStyle);

        chocoButton.setPosition((int) ((LittleMonster.V_WIDTH / 2) - chocoButton.getWidth() / 2), (int) ((LittleMonster.V_HEIGHT * 5 / 16) - chocoButton.getHeight() / 2));
        chocoButton.setVisible(true);
        chocoButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet.giveFood(FoodType.Chocolate);
            }
        });

        candyButton = new TextButton("Candy", game.transTextButtonStyle);

        candyButton.setPosition((int) ((LittleMonster.V_WIDTH / 2) - candyButton.getWidth() / 2), (int) ((LittleMonster.V_HEIGHT * 6 / 16) - candyButton.getHeight() / 2));
        candyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet.giveFood(FoodType.Candy);
            }
        });

        // Adds all buttons to stage
        addActor(breadButton);
        addActor(chickenButton);
        addActor(candyButton);
        addActor(chocoButton);
        addActor(broccoliButton);
        addActor(eggButton);
        addActor(liverButton);
    }

}
