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

public class DaggerBack extends Sprite {

    protected TextureRegion daggerBack;
    protected Array<Rectangle> daggerDropsBack;
    protected long lastDropTime;
    protected Vector2 positionV, vector2;


    public DaggerBack(TextureAtlas atlas){
        super(atlas.findRegion("daggerBack"));
        daggerBack = atlas.findRegion("daggerBack");
        daggerDropsBack = new Array<Rectangle>();
        playerOne = new Rectangle();
        positionV = new Vector2();
        vector2 = new Vector2(x,y);
    }


    protected void spawnDaggerBack(){
        Rectangle daggerDropBack = new Rectangle();
        daggerDropBack.x = 1024;
        daggerDropBack.y = MathUtils.random(100, 480);
        daggerDropBack.width = 1024;
        daggerDropBack.height = 700;
        daggerDropsBack.add(daggerDropBack);
        lastDropTime = TimeUtils.nanoTime();
    }



    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        positionV.add(vector2);


        for (Rectangle daggerDropBack : daggerDropsBack) {
            batch.draw(daggerBack, daggerDropBack.x, daggerDropBack.y, 50,25);
        }


        /**
         * Вылет кинажлов, пока что на рандоме
         */
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            spawnDaggerBack();
        }

        for (int i = 0; i < 0; i++){
            spawnDaggerBack();
        }

        Iterator<Rectangle> iterBack = daggerDropsBack.iterator();
        while (iterBack.hasNext()) {
            Rectangle daggerDropBack = iterBack.next();
            daggerDropBack.x -= 400 * Gdx.graphics.getDeltaTime();
            if (daggerDropBack.x + 800 < 0) iterBack.remove();
            if (daggerDropBack.overlaps(playerOne)) {
                //		dropSound.play();
                iterBack.remove();
            }
        }
    }
}
