package com.littlebiglion.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.littlebiglion.base.Sprite;

public class BackGround extends Sprite {

    public TextureRegion bg;

    /**
     *
     * @param atlas
     */
    public BackGround(TextureAtlas atlas){
        super(atlas.findRegion("background"));
        bg = atlas.findRegion("background");
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(bg,0,0,1024,600);
    }
}
