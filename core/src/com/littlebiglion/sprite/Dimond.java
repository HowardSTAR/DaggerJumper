package com.littlebiglion.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.littlebiglion.base.Sprite;

import java.util.Iterator;

import static com.littlebiglion.screen.GameScreen.delX;
import static com.littlebiglion.screen.GameScreen.hp;
import static com.littlebiglion.screen.GameScreen.s;
import static com.littlebiglion.screen.GameScreen.scoreMul;
import static com.littlebiglion.sprite.Player.playerOne;

public class Dimond extends Sprite {

    private long time = 1000000000;

    protected TextureRegion dimond;
    protected Array<Rectangle> dimondDrops;
    protected long lastDropTime;
    protected Vector3 positionV, vector2;
    private Sound sound;

    public Dimond(TextureAtlas atlas) {
        super(atlas.findRegion("dimond"));
        dimond = atlas.findRegion("dimond");
        sound = Gdx.audio.newSound(Gdx.files.internal("sound/diamond.wav"));
        dimondDrops = new Array<Rectangle>();
        positionV = new Vector3();
        vector2 = new Vector3(x, y, 0);
    }

    protected void spawnDimond() {
        Rectangle dimondDrop = new Rectangle();
        dimondDrop.x = 1200;
        dimondDrop.y = MathUtils.random(125, 415);
        dimondDrops.add(dimondDrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public  void draw(SpriteBatch batch) {
        super.draw(batch);

        positionV.add(vector2);

        for (Rectangle dimondDrop : dimondDrops) {
            batch.draw(dimond, dimondDrop.x, dimondDrop.y, 50, 35);
        }

        float temp = TimeUtils.nanoTime() - lastDropTime;
        for (int u = 0; u < Math.random() * (20 - 15 + 1) + 15; u++) {  // Вылет кристала
            temp -= time;
        }
        if (temp > 1000000000) {
            spawnDimond();
        }

        for (int i = 0; i < 0; i++) {
            spawnDimond();
        }

        Iterator<Rectangle> iter = dimondDrops.iterator();
        while (iter.hasNext()) {
            Rectangle dimondDrop = iter.next();

            dimondDrop.x -= 200 * Gdx.graphics.getDeltaTime();

            if (dimondDrop.x + 800 < 0) iter.remove();
            if (dimondDrop.overlaps(playerOne)) {
                iter.remove();

                sound.play();
                lootBox();
            }
        }
    }

    private void lootBox() {
        float LB = (float) Math.random();
        System.out.println(LB + " random");
        if (LB < 0.35) {
            if (delX <= 100) {
                delX += 50;
                Player.y++; // увелечение скорости прыжка
                System.out.println("1");
            } else mul();
        } else if (LB < 0.65) {
            if (hp < 3) {
                hp++;
                System.out.println("3");
            } else {
                mul();
            }
        } else {
            mul();
        }
    }

    private void mul(){
        if(s < 10) {
            scoreMul = (float) (scoreMul + 0.002);
            s++;
        }
        System.out.println("2");
    }
}
