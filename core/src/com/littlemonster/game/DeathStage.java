package com.littlemonster.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;


public class DeathStage extends Stage {
    LittleMonster game;
    MainScreen mainScreen;

    Texture bgImage;
    Image background;
    TextButton restartButton;

    Text age, weight;

    public DeathStage(final LittleMonster game, final MainScreen mainScreen) {
        this.game = game;
        this.mainScreen = mainScreen;
        bgImage = new Texture("MainScreen.png");
        background = new Image(bgImage);
        background.setPosition(0, 0);
        addActor(background);

        age = new Text(game, "Age = " + game.pet.getAge());
        weight = new Text(game, "Weight: " + game.pet.getWeight());

        // X set to 0. Width of Text set to 800 for easy centering
        age.setPosition(0, 300);
        weight.setPosition(0, 400);

        restartButton = new TextButton("Restart", game.textButtonStyle);
        restartButton.setPosition((int) (LittleMonster.V_WIDTH / 2) , 200, Align.center);
        restartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.pet = new Pet();
                game.pet.updateStats(0);
                mainScreen.changeStage(StageType.MainStage);
            }
        });

        addActor(age);
        addActor(weight);
        addActor(restartButton);
    }

}
