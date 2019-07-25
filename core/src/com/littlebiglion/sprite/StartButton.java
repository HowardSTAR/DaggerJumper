package com.littlebiglion.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.littlebiglion.base.ScaledTouchUpButton;
import com.littlebiglion.screen.GameScreen;
import com.littlebiglion.screen.Intro;

public class StartButton extends ScaledTouchUpButton {

    private TextureRegion startBtn;
    private Game game;

    /**
     *
     * @param atlas
     * @param game
     */
    public StartButton(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("start"));
        this.game = game;
        startBtn = atlas.findRegion("start");
    }

    /**
     *
     * @param batch
     */
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(startBtn,300,200, 90,50);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }

}
