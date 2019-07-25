package com.littlebiglion.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.littlebiglion.base.Sprite;

import java.util.Iterator;

public class Dagger extends Sprite {

    protected TextureRegion dagger;
    protected Array<Rectangle> daggerDrops;
    protected long lastDropTime;
    protected Vector2 positionV, vector2;


    public Dagger(TextureAtlas atlas){
        super(atlas.findRegion("dagger"));
        dagger = atlas.findRegion("dagger");
        daggerDrops = new Array<Rectangle>();
        playerOne = new Rectangle();
        positionV = new Vector2();
        vector2 = new Vector2(x,y);
    }


    protected void spawnDagger(){
        Rectangle daggerDrop = new Rectangle();
        daggerDrop.x = -10;
        daggerDrop.y = MathUtils.random(100, 480);
        daggerDrop.width = 1024;
        daggerDrop.height = 600;
        daggerDrops.add(daggerDrop);
        lastDropTime = TimeUtils.nanoTime();
    }



    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        positionV.add(vector2);


        for (Rectangle daggerDrop : daggerDrops) {
            batch.draw(dagger, daggerDrop.x, daggerDrop.y, 50,25);
        }


        /**
         * Вылет кинажлов, пока что на рандоме
         */
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            spawnDagger();
        }

        for (int i = 0; i < 0; i++){
            spawnDagger();
        }

        Iterator<Rectangle> iter = daggerDrops.iterator();
        while (iter.hasNext()) {
            Rectangle daggerDrop = iter.next();
            daggerDrop.x += 400 * Gdx.graphics.getDeltaTime();
            if (daggerDrop.x + 800 < 0) iter.remove();
            if (daggerDrop.overlaps(playerOne)) {
                //		dropSound.play();
                iter.remove();
            }
        }
    }
}
