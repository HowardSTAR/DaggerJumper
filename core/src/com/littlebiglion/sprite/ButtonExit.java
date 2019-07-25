package com.littlebiglion.sprite;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.littlebiglion.base.ScaledTouchUpButton;

public class ButtonExit extends ScaledTouchUpButton {

    public TextureRegion exitBtn;

    /**
     *
     * @param atlas
     */
    public ButtonExit(TextureAtlas atlas){
        super(atlas.findRegion("exit"));
        exitBtn = atlas.findRegion("exit");
    }

    /**
     *
     * @param batch
     */
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(exitBtn, 600,200, 90, 50);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }

}
