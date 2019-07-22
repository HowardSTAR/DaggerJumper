package com.littlebiglion.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.littlebiglion.base.BaseScreen;

import java.util.Iterator;

public class Intro extends BaseScreen {

    private Sprite bg, cel, floor;
    private Texture player, dagger, daggerBack, dagger3, daggerBack3;
    private TextureAtlas textureAtlas, Btn;
    private Animation animation, animation2, animation3, animation4;
    private float elapsedTime = 0f;

    public static final int ON_PLAY = 1;
    public static final int ON_BACK = 2;

    private ImageButton startBtn, exitBtn;




    @Override
    public void show() {
        super.show();
        textureAtlas = new TextureAtlas(Gdx.files.internal("spriteFire/myFire.atlas"));
        Btn = new TextureAtlas(Gdx.files.internal("spriteButton/myButton.atlas"));

        startBtn = new ImageButton(new TextureRegionDrawable(textureAtlas.findRegion("spriteButton/myButton")));

        animation = new Animation(1f/6f, textureAtlas.getRegions());
        animation2 = new Animation(1f/6f, textureAtlas.getRegions());
        animation3 = new Animation(1f/6f, textureAtlas.getRegions());
        animation4 = new Animation(1f/6f, textureAtlas.getRegions());

        bg = new Sprite(new Texture(Gdx.files.internal("sprite/background2.png")));
        cel = new Sprite(new Texture(Gdx.files.internal("sprite/ceiling.png")));
        floor = new Sprite(new Texture(Gdx.files.internal("sprite/floor.png")));

        player = new Texture("player.png");
        dagger3 = new Texture("dagger3.png");
        dagger = new Texture("dagger.png");
        daggerBack = new Texture("daggerBack.png");
        daggerBack3 = new Texture("daggerBack3.png");



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

        elapsedTime += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();

        posi.add(v);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        bg.draw(batch);
        cel.draw(batch);

        // анимация огня
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime,true),900,300);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime,true),700,300);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime,true),500,300);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime,true),300,300);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime,true),100,300);


        floor.draw(batch);

        batch.draw(player, posi.x, posi.y, 50, 50);

//        batch.draw(bg, 0,0,1024,700);
//        batch.draw(bg, 0,0,1024,700);
//        batch.draw(bg, 0,0,1024,700);

        for (Rectangle daggerDrop : daggerDrops) {
            batch.draw(dagger, daggerDrop.x, daggerDrop.y, 50,25);
        }
//        for (Rectangle daggerDrop3 : daggerDrops) {
//            batch.draw(dagger, daggerDrop3.x, daggerDrop3.y, 50,25);
//        }
        for (Rectangle daggerDropBack : daggerDropsBack) {
            batch.draw(daggerBack, daggerDropBack.x, daggerDropBack.y, 50,25);
        }
//        for (Rectangle daggerDropBack3 : daggerDropsBack) {
//            batch.draw(daggerBack3, daggerDropBack3.x, daggerDropBack3.y, 250,100);
//        }

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

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            spawnDagger();
            spawnDaggerBack();
        }

        for (int i = 0; i < 0; i++){
            spawnDagger();
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
