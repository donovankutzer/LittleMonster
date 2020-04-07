package com.littlemonster.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GameStage extends MenuButtonsScreen {

    TextButton appleGameButton;

    public GameStage(final LittleMonster game, final MainScreen mainScreen) {
        super(game, mainScreen);
        drawScreen(StageType.GameStage);

        // Add listener to feed button to change stage to FoodStage
        gameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainScreen.changeStage(StageType.MainStage);
            }
        });

        // BUTTONS FOR MAIN STAGE
        appleGameButton = new TextButton("Apple Catch", game.transTextButtonStyle);
        appleGameButton.setPosition((int) ((LittleMonster.V_WIDTH / 2) - appleGameButton.getWidth() / 2), (int) ((LittleMonster.V_HEIGHT * 3 / 4) - appleGameButton.getHeight() / 2));
        appleGameButton.setVisible(true);
        appleGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });


        addActor(appleGameButton);
    }

}
