package com.littlebiglion.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.littlebiglion.base.ScaledTouchUpButton;
import com.littlebiglion.screen.GameScreen;

public class ReturnButton extends ScaledTouchUpButton {

    private TextureRegion returnBtn;
    private Game game;
    private int x = 290;
    private int y = 200;

    /**
     *
     * @param atlas
     * @param game
     */
    public ReturnButton(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("return"));
        this.game = game;
        returnBtn = atlas.findRegion("return");
    }

    /**
     *
     * @param batch
     */
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(returnBtn,x,y, 90 ,50);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
    }
}
