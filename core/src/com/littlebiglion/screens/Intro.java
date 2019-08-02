package com.littlebiglion.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Intro extends BaseScreen {

    private Sprite bg, cel, floor;
    private Texture player, dagger, daggerBack;


    @Override
    public void show() {
        super.show();
        bg = new Sprite(new Texture(Gdx.files.internal("background2.png")));
        cel = new Sprite(new Texture(Gdx.files.internal("ceiling.png")));
        floor = new Sprite(new Texture(Gdx.files.internal("floor.png")));
        player = new Texture("player.png");
        dagger = new Texture("dagger.png");
        daggerBack = new Texture("daggerBack.png");

        bg.setPosition(0,0);
        cel.setPosition(0,55);
        floor.setPosition(0,-480);

        bg.setSize(1024, 700);
        cel.setSize(1024,1024);
        floor.setSize(1024,1024);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        camera.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        posi.add(v);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        bg.draw(batch);
        cel.draw(batch);
        floor.draw(batch);

        batch.draw(player, posi.x, posi.y, 50, 50);

        for (Rectangle daggerDrop : daggerDrops) {
            batch.draw(dagger, daggerDrop.x, daggerDrop.y, 50,25);
        }
        for (Rectangle daggerDropBack : daggerDropsBack) {
            batch.draw(daggerBack, daggerDropBack.x, daggerDropBack.y, 50,25);
        }

        batch.end();

        /**
         *
         * Управление мышью
         *
         */
//		if (Gdx.input.isTouched()) {
//			pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			camera.unproject(pos);
//			playerOne.x = (int) (pos.x - 74 / 2);
//			playerOne.y = (int) (pos.y - 74 / 2);
//		}

        /**
         * Возможность урпавления с клавиатуры
         *
         * В моей игре нужно будет только двигать персонажа влево/вправо
         */

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) posi.x -= 400 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) posi.x += 400 * Gdx.graphics.getDeltaTime();

        /**
         * Границы перемещения героя
         */
        if (posi.x < 0) {
            posi.x = 0;
        }
        if (posi.x > 978) {
            posi.x = 978;
        }

        if (posi.y < 100) {
            posi.y = 100;
            v = new Vector2(x, y);
        } else if (posi.y > 455) {
            posi.y = 455;
            v = new Vector2(-x, -y);
        }

        if (TimeUtils.nanoTime() - lastDropTime > 999950000) {
            spawnDagger();
            spawnDaggerBack();
        }

        Iterator<Rectangle> iter = daggerDrops.iterator();
        Iterator<Rectangle> iterBack = daggerDropsBack.iterator();
        while (iter.hasNext()) {
            Rectangle daggerDrop = iter.next();
            daggerDrop.x += 400 * Gdx.graphics.getDeltaTime();
            if (daggerDrop.x + 800 < 0) iter.remove();
            if (daggerDrop.overlaps(playerOne)) {
                //		dropSound.play();
                iter.remove();
            }
        }
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


    @Override
    public void dispose() {
        super.dispose();
        bg.getTexture().dispose();
//        dagger.getTexture().dispose();
        cel.getTexture().dispose();
        floor.getTexture().dispose();
//        player.getTexture().dispose();
        player.dispose();
        daggerBack.dispose();
        dagger.dispose();

    }

}
