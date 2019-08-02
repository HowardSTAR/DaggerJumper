package com.littlebiglion.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.littlebiglion.base.Sprite;

public class Heart extends Sprite {

    protected TextureRegion heart;

    public Heart(TextureAtlas atlas){
        super(atlas.findRegion("hp"));
        heart = atlas.findRegion("hp");
    }


    public void draw(SpriteBatch batch, int x) {
        batch.draw(heart, x, 490, 50,50);
    }
}