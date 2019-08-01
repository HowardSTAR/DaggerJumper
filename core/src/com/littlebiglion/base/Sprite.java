package com.littlebiglion.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Sprite extends BaseScreen{

    protected TextureRegion regions;

    protected float x, y, width, height;
    protected SpriteBatch batch;


    /**
     *
     * @param region
     */
    public Sprite(TextureRegion region) {
        this.regions = region;
    }

    public Sprite() {
    }


    /**
     *
     * @param batch
     */
    public void draw(SpriteBatch batch){
        batch.draw(regions, x, y, 0, 0,width,height,1,1,0);
    }


}
