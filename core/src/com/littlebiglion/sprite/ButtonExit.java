package com.littlebiglion.sprite;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.littlebiglion.base.ScaledTouchUpButton;

public class ButtonExit extends ScaledTouchUpButton {

    public TextureRegion exitBtn;
    public int x = 630;
    public int y = 200;


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
        batch.draw(exitBtn, x, y, 90, 50);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }

}
