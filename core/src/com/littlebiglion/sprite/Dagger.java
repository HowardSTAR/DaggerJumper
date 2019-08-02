package com.littlebiglion.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.littlebiglion.base.Sprite;
import com.badlogic.gdx.Game;
import com.littlebiglion.screen.GameOver;
import com.littlebiglion.screen.GameScreen;

import java.util.Iterator;

import static com.littlebiglion.screen.GameScreen.hp;
import static com.littlebiglion.sprite.Player.playerOne;

public class Dagger extends Sprite {

    protected TextureAtlas.AtlasRegion dagger;
    protected Array<Rectangle> daggerDrops;
    protected long lastDropTime;
    protected Vector3 positionV, vector2;

    protected GameScreen player;

    private Game game;


    public Dagger(TextureAtlas atlas, Game game){
        super(atlas.findRegion("dagger"));
        dagger = atlas.findRegion("dagger");
        daggerDrops = new Array<Rectangle>();
        positionV = new Vector3();
        vector2 = new Vector3(x,y, 0);
        this.game = game;
        player = new GameScreen();
    }


    protected void spawnDagger(){
        Rectangle daggerDrop = new Rectangle();
        daggerDrop.x = -10;
        daggerDrop.y = MathUtils.random(125, 415);
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
            spawnDagger();
        }

        for (int i = 0; i < 0; i++){
            spawnDagger();
            spawnDagger();
        }

        Iterator<Rectangle> iter = daggerDrops.iterator();
        while (iter.hasNext()) {
            Rectangle daggerDrop = iter.next();
            daggerDrop.x += 400 * Gdx.graphics.getDeltaTime();
            if (daggerDrop.x + 800 < 0) iter.remove();
            if (daggerDrop.overlaps(playerOne)) {
                iter.remove();

                // ХэПэ
                if(hp > 1){
                    hp--;
                }
                else {
                    hp = 3;
                    game.setScreen(new GameOver(game));
                }
            }
        }
    }
}
