package com.littlebiglion.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class ScaledTouchUpButton extends Sprite {

    public ScaledTouchUpButton(TextureRegion region) {
        super(region);
    }

    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


        return false;
    }

    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        action();

        return false;
    }

    public abstract void action();
}