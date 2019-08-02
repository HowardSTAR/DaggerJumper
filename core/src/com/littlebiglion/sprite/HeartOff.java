package com.littlebiglion.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.littlebiglion.base.Sprite;

public class HeartOff extends Sprite {

    protected TextureRegion heartOff;

    public HeartOff(TextureAtlas atlas){
        super(atlas.findRegion("hp_off"));
        heartOff = atlas.findRegion("hp_off");
    }

    public void draw(SpriteBatch batch, int x) {
        batch.draw(heartOff, x, 490, 50,50);
    }
}