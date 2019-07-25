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
    private Animation animation;
    private float elapsedTime = 0f;

    public Fire(TextureAtlas textureAtlas, float x, float y, float width, float height) {
        super(textureAtlas, x, y, width, height);
    }

    public Fire(TextureAtlas atlas){
        super(atlas.findRegion("spriteFire/myFire.atlas"));
        this.atlas = atlas;
       fire = atlas.findRegion("spriteFire/myFire.atlas");
//        this.animation = animations;
        animation = new Animation(1f / 6f, atlas.getRegions());
    }


    public void showBatsh(SpriteBatch batch){

        elapsedTime += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 900, 300);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 700, 300);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 500, 300);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 300, 300);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 100, 300);
    }

//    @Override
//    public void draw(SpriteBatch batch) {
//        super.draw(batch);
//        elapsedTime += Gdx.graphics.getDeltaTime();
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        elapsedTime += Gdx.graphics.getDeltaTime();
//
//        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 900, 300);
//        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 700, 300);
//        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 500, 300);
//        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 300, 300);
//        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 100, 300);
//    }
}
