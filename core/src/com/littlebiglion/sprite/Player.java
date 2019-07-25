package com.littlebiglion.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.littlebiglion.base.Sprite;

public class Player extends Sprite {

    private TextureRegion player;
    private Vector2 positions, vk;

    /**
     * Задаем направление "прыжока" персонажа, а так же его скорость
     */
    protected int x = 0;
    protected int y = 7;


    /**
     * @param atlas
     */
    public Player(TextureAtlas atlas){
        super(atlas.findRegion("player"));
        player = atlas.findRegion("player");

        positions = new Vector2();
        vk = new Vector2(x, y);

        playerOne = new Rectangle();
        positions.x = widthW / 2 - widthP / 2;
        positions.y = 88;
        playerOne.width = widthP;
        playerOne.height = heightP;

    }

    /**
     * @param batch
     */
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
//        batch.draw(player,450,88, 60,60);
        positions.add(vk);

        batch.draw(player, positions.x, positions.y, widthP, heightP);

        /**
         * Границы перемещения героя
         */
        if (positions.x < 0) {
            positions.x = 0;
        }
        if (positions.x > 960) {
            positions.x = 960;
        }

        if (positions.y < 88) {
            positions.y = 88;
            vk = new Vector2(x, y);
        } else if (positions.y > 455) {
            positions.y = 455;
            vk = new Vector2(-x, -y);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) positions.x -= 400 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) positions.x += 400 * Gdx.graphics.getDeltaTime();
    }
}
