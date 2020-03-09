package com.littlemonster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;

// Extends MenuButtonsScreen to save from having to draw the same button for multiple stages
public class MainStage extends MenuButtonsScreen {
    Texture petSprite;
    Image petActor;

    public MainStage(final LittleMonster game, final MainScreen mainScreen){
        super(game, mainScreen);

        // Draw pet
        petSprite = new Texture(Gdx.files.internal("baby.png"));
        petActor = new Image(petSprite);
        petActor.setPosition(320, 180);
        // Creates moving animation
        petActor.addAction(forever(sequence(moveBy(0, 20), delay(0.69f), moveBy(0, -20), delay(0.69f))));
        addActor(petActor);

        // Add listener to feed button to change stage to FoodStage
        feedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainScreen.changeStage(StageType.FoodStage);
            }
        });

    }


}
