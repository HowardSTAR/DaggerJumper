package com.littlebiglion.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.littlebiglion.base.Sprite;

public class Fire extends Sprite {

    private TextureAtlas atlas;
    private TextureRegion fire;
    private Animation animation, animation2, animation3, animation4, animation5;
    private float elapsedTime = 0f;

    public Fire(TextureAtlas atlas){
        super(atlas.findRegion("1"));
        this.atlas = atlas;
        fire = atlas.findRegion("1");
        animation = new Animation(1.1f / 6f, atlas.getRegions());
        animation2 = new Animation(1.2f / 6f, atlas.getRegions());
        animation3 = new Animation(1.3f / 6f, atlas.getRegions());
        animation4 = new Animation(1.4f / 6f, atlas.getRegions());
        animation5 = new Animation(1.5f / 6f, atlas.getRegions());
    }


    public void showBatsh(SpriteBatch batch){

        elapsedTime += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 900, 280);
        batch.draw((TextureRegion) animation2.getKeyFrame(elapsedTime, true), 700, 280);
        batch.draw((TextureRegion) animation3.getKeyFrame(elapsedTime, true), 500, 280);
        batch.draw((TextureRegion) animation4.getKeyFrame(elapsedTime, true), 300, 280);
        batch.draw((TextureRegion) animation5.getKeyFrame(elapsedTime, true), 100, 280);
    }

}
