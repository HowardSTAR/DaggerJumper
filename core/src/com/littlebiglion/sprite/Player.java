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

public class Player extends Sprite {

    public static Rectangle playerOne;
    private TextureRegion player;
    private Vector3 positionP, vk;
    private float elapsedTime = 0f;
    private Animation animation;

    private int f = 0;

    private Game game;


    /**
     * Задаем направление "прыжока" персонажа, а так же его скорость
     */
    protected int x = 0;
    public static int y = 7;


    //TODO сделать анимацию прыжка игрока и анимацию взятия кристала

    /**
     * @param atlas
     * @param game
     */
    public Player(TextureAtlas atlas, Game game){
        super(atlas.findRegion("player"));
        player = atlas.findRegion("player");
//        animation = new Animation(1f / 3f, atlas.getRegions());


        positionP = new Vector3();
        vk = new Vector3(x, y, 0);


        positionP.x = widthW / 2 - 60 / 2;
        positionP.y = 125;
        playerOne = new Rectangle(0,0,60,60);

        this.game = game;
    }


    /**
     * @param batch
     */
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        positionP.add(vk);
        playerOne.x = positionP.x;
        playerOne.y = positionP.y;

        //TODO реализовать отражение при перемещении влево/вправо

        batch.draw(player, playerOne.x, playerOne.y, 0,0, playerOne.width, playerOne.height,1,1, 0);

        /**
         * Границы перемещения героя
         */
        if (playerOne.x < 0) {
            playerOne.x = 0;
        }
        if (playerOne.x > 960) {
            playerOne.x = 960;
        }

        if (playerOne.y <= 125) {
            playerOne.y = 125;
            vk = new Vector3(x, y, 0);
            score = (score + 1) * scoreMul;
        }
        else if (playerOne.y > 272 && playerOne.y < 280){
            playerOne.y = 280;
            player.flip(false,true);
        }
        else if (playerOne.y >= 415) {
            playerOne.y = 415;
            vk = new Vector3(-x, -y, 0);
            score = (score + 1) * scoreMul;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            positionP.x -= (400 + delX) * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            positionP.x += (400 + delX) * Gdx.graphics.getDeltaTime();
        }
    }


    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}
