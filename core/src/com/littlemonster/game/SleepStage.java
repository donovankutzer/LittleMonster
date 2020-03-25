package com.littlemonster.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import javafx.scene.control.TextFormatter;

public class SleepStage extends MenuButtonsScreen {

    public SleepStage(final LittleMonster game, final MainScreen mainScreen) {
        super(game, mainScreen);
        drawScreen(StageType.SleepStage);

        // Add listener to feed button to change stage to FoodStage
        sleepButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet.setSleeping(false);
                mainScreen.changeStage(StageType.MainStage);
            }
        });
    }

}
