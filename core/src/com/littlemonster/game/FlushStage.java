package com.littlemonster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class FlushStage extends MenuButtonsScreen {
    Texture flushTexture;
    Image flush;

    public FlushStage(final LittleMonster game, final MainScreen mainScreen){
        super(game, mainScreen);
        drawScreen(StageType.MainStage);

        // Import water texture
        flushTexture = new Texture(Gdx.files.internal("flush.png"));
        flush = new Image(flushTexture);
        flush.setPosition(800, 100, Align.bottomLeft);
        addActor(flush);

    }

    public void flush(){
        flush.setPosition(800, 100);
        flush.addAction(sequence(repeat(800/20 + (int)flush.getWidth()/20, sequence(moveBy(-20, 0), delay(0.075f))), run(new Runnable(){
            public void run(){
                mainScreen.changeStage(StageType.MainStage);
            }
        })));
    }
}
