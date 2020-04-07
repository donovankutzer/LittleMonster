package com.littlemonster.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class Text extends Actor {
    BitmapFont font;
    String text;

    public Text(LittleMonster game, String text) {
        super();
        this.font = game.font;
        this.text = text;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, text, this.getX(), this.getY(), 800, Align.center, true);
    }
}
