package com.littlemonster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;

public class StatsStage extends MenuButtonsScreen {
    private Texture petSprite;
    private Image petActor;
    private Text age, weight;

    public StatsStage(final LittleMonster game, final MainScreen mainScreen){
        super(game, mainScreen);
        drawScreen(StageType.MainStage);

        // Draw pet
        petSprite = new Texture(Gdx.files.internal(game.pet.getSprite()));
        petActor = new Image(petSprite);
        petActor.setPosition(300, 180, Align.bottomRight);

        age = new Text(game, "Age = " + game.pet.getAge());
        weight = new Text(game, "Weight: " + game.pet.getWeight());

        // X set to 100. Width of Text set to 800 for easy centering
        age.setPosition(100, 300);
        weight.setPosition(100, 400);

        // Creates moving animation
        petActor.addAction(forever(sequence(moveBy(0, 20), delay(0.69f), moveBy(0, -20), delay(0.69f))));
        addActor(petActor);
        addActor(age);
        addActor(weight);

        statsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainScreen.changeStage(StageType.MainStage);
            }
        });
    }

    public void update(){
        age.text = String.valueOf(super.game.pet.getAge());
        weight.text = String.valueOf(super.game.pet.getWeight());
    }
}
