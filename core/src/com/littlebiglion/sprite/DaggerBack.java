package com.littlebiglion.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.littlebiglion.base.Sprite;
import com.littlebiglion.screen.GameOver;
import com.littlebiglion.screen.GameScreen;

import java.util.Iterator;

import static com.littlebiglion.screen.GameScreen.delX;
import static com.littlebiglion.screen.GameScreen.hp;
import static com.littlebiglion.sprite.Player.playerOne;

public class DaggerBack extends Sprite {

    protected TextureRegion daggerBack;
    protected Array<Rectangle> daggerDropsBack;
    protected long lastDropTime;
    protected Vector2 positionV, vector2;
    private Game game;
    private Sound soundD, soundG;
    private Dimond dimond;

    protected GameScreen player;


    public DaggerBack(TextureAtlas atlas, Game game){
        super(atlas.findRegion("daggerBack"));
        daggerBack = atlas.findRegion("daggerBack");
        soundD = Gdx.audio.newSound(Gdx.files.internal("sound/dagger.wav"));
        soundG = Gdx.audio.newSound(Gdx.files.internal("sound/gameover.wav"));
        daggerDropsBack = new Array<Rectangle>();
        positionV = new Vector2();
        vector2 = new Vector2(x,y);
        this.game = game;

        player = new GameScreen();
    }


    protected void spawnDaggerBack(){
        Rectangle daggerDropBack = new Rectangle();
        daggerDropBack.x = 1200;
        daggerDropBack.y = MathUtils.random(125, 415);
        daggerDropsBack.add(daggerDropBack);
        lastDropTime = TimeUtils.nanoTime();
    }



    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        positionV.add(vector2);

        for (Rectangle daggerDropBack : daggerDropsBack) {
            batch.draw(daggerBack, daggerDropBack.x , daggerDropBack.y, 70,15);
        }


        /**
         * Вылет кинажлов, пока что на рандоме
         */
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            spawnDaggerBack();
            spawnDaggerBack();
        }

        for (int i = 0; i < 0; i++){
            spawnDaggerBack();
        }

        Iterator<Rectangle> iterBack = daggerDropsBack.iterator();
        while (iterBack.hasNext()) {
            Rectangle daggerDropBack = iterBack.next();
            daggerDropBack.x -= (400 + delX * 2) * Gdx.graphics.getDeltaTime();
            if (daggerDropBack.x + 800 < 0) iterBack.remove();
            if (daggerDropBack.overlaps(playerOne)) {
                iterBack.remove();

                soundD.play();

                // ХэПэ
                if(hp > 1){
                    hp--;
                }
                else {
                    hp = 3;
                    soundG.play();
                    game.setScreen(new GameOver(game));
                }
            }
        }
    }
}
