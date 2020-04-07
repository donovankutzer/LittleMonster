package com.littlemonster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

// Extends MenuButtonsScreen to save from having to draw the same button for multiple stages
public class MainStage extends MenuButtonsScreen {
    Texture petSprite;
    Texture poopSprite;
    Image petActor;
    Image poo1, poo2, poo3, poo4;

    public MainStage(final LittleMonster game, final MainScreen mainScreen) {
        super(game, mainScreen);
        drawScreen(StageType.MainStage);

        // Draw pet
        petSprite = new Texture(Gdx.files.internal(game.pet.getSprite()));
        petActor = new Image(petSprite);
        petActor.setPosition(400, 180, Align.bottomRight);

        poopSprite = new Texture(Gdx.files.internal("poop.png"));
        poo1 = new Image(poopSprite);
        poo2 = new Image(poopSprite);
        poo3 = new Image(poopSprite);
        poo4 = new Image(poopSprite);

        poo1.setPosition(600, 340, Align.bottomRight);
        poo2.setPosition(740, 340, Align.bottomRight);
        poo3.setPosition(600, 180, Align.bottomRight);
        poo4.setPosition(740, 180, Align.bottomRight);

        poo1.setVisible(false);
        poo2.setVisible(false);
        poo3.setVisible(false);
        poo4.setVisible(false);

        addActor(poo1);
        addActor(poo2);
        addActor(poo3);
        addActor(poo4);

        // Creates moving animation
        petActor.addAction(forever(sequence(moveBy(0, 20), delay(0.69f), moveBy(0, -20), delay(0.69f))));
        addActor(petActor);
    }

    public void update(){
        petActor.remove();
        petSprite = new Texture(Gdx.files.internal(game.pet.getSprite()));
        petActor = new Image(petSprite);
        petActor.setPosition(400, 180, Align.bottomRight);
        petActor.addAction(forever(sequence(moveBy(0, 20), delay(0.69f), moveBy(0, -20), delay(0.69f))));
        addActor(petActor);


        switch (game.pet.getPoos()){
            case 4: poo4.setVisible(true);
            case 3: poo3.setVisible(true);
            case 2: poo2.setVisible(true);
            case 1:
                poo1.setVisible(true);
                break;
            case 0:
                poo1.setVisible(false);
                poo2.setVisible(false);
                poo3.setVisible(false);
                poo4.setVisible(false);
                break;
        }
    }


}
