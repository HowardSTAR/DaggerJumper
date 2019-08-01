package com.littlebiglion.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.littlebiglion.base.Sprite;

import static com.littlebiglion.screen.GameScreen.delX;
import static com.littlebiglion.screen.GameScreen.score;
import static com.littlebiglion.screen.GameScreen.scoreMul;

public class AnimatePlayer extends Sprite {

    public static Rectangle playerAnimate;
    private TextureRegion playerAni;
    private Vector3 positionP, vk;
    private float elapsedTime = 0f;


    private Game game;
    private Animation animation;


    /**
     * Задаем направление "прыжока" персонажа, а так же его скорость
     */
    protected int x = 0;
    public static int y = 7;


    /**
     * @param atlas
     */
    public AnimatePlayer(TextureAtlas atlas, Game game){
        super(atlas.findRegion("takeAll/myTake.atlas"));
        playerAni = atlas.findRegion("takeAll/myTake.atlas");
        animation = new Animation(1f / 9f, atlas.getRegions());


        positionP = new Vector3();
        vk = new Vector3(x, y, 0);


        positionP.x = widthW / 2 - 60 / 2;
        positionP.y = 125;
        playerAnimate = new Rectangle(0,0,50,50);

        this.game = game;
    }

    /**
     * @param batch
     */
    @Override
    public void draw(SpriteBatch batch) {
        positionP.add(vk);
        playerAnimate.x = positionP.x;
        playerAnimate.y = positionP.y;
        playerAnimate.width += vk.x;

        elapsedTime += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), playerAnimate.x, playerAnimate.y, playerAnimate.width, playerAnimate.height);

        
        /**
         * Границы перемещения героя
         */
        if (playerAnimate.x < 0) {
            playerAnimate.x = 0;
        }
        if (playerAnimate.x > 960) {
            playerAnimate.x = 960;
        }

        if (playerAnimate.y <= 125) {
            playerAnimate.y = 125;
            vk = new Vector3(x, y, 0);
            score = (score + 1) * scoreMul;
        } else if (playerAnimate.y > 415) {
            playerAnimate.y = 415;
            vk = new Vector3(-x, -y, 0);
            score = (score + 1) * scoreMul;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) positionP.x -= (400 + delX) * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) positionP.x += (400 + delX) * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}
