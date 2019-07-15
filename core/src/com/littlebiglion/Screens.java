package com.littlebiglion;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Screens extends Game {
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture bg, cel, floor, player;
    Rectangle playerOne;
    Vector3 pos;
    TextureRegion region;
    Vector3 v;

    @Override
    public void create () {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 700, 550);

        batch = new SpriteBatch();

        pos = new Vector3();
        v = new Vector3(0, 5,0);

        bg = new Texture("background2.png");
        cel = new Texture("ceiling.png");
        floor = new Texture("floor.png");
        player = new Texture("player.png");
        region = new TextureRegion(bg);


        playerOne = new Rectangle();
        pos.x = 700 / 2 - 74 / 2;
        pos.y = 100;
        playerOne.width = 74;
        playerOne.height = 74;

    }


    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        pos.add(v);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(region, 0, 0, 700, 550);
        batch.draw(cel, 0, 460, 700, 100);
        batch.draw(floor, 0, 0, 700, 100);
        batch.draw(player, pos.x, pos.y, 60, 60);
        batch.end();

        if (Gdx.input.isTouched()) {
            pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(pos);
            playerOne.x = (int) (pos.x - 74 / 2);
            playerOne.y = (int) (pos.y - 74 / 2);
        }

        /**
         * Возможность урпавления с клавиатуры
         *
         * В моей игре нужно будет только двигать персонажа влево/вправо
         */

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) pos.x -= 400 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) pos.x += 400 * Gdx.graphics.getDeltaTime();
//        if(Gdx.input.isKeyPressed(Input.Keys.UP)) pos.y += 500 * Gdx.graphics.getDeltaTime();
//        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) pos.y -= 500 * Gdx.graphics.getDeltaTime();

        /**
         * Границы перемещения героя
         */
        if (pos.x < 0) {
            pos.x = 0;
        }
        if (pos.x > 640) {
            pos.x = 640;
        }

        if (pos.y < 100) {
            pos.y = 100;
            v = new Vector3(0, 5, 0);
        }
       else if (pos.y > 400) {
            pos.y = 400;
            v = new Vector3(0, -5, 0);
            }
        }


    @Override
    public void dispose() {
        bg.dispose();
        cel.dispose();
        floor.dispose();
        player.dispose();
        batch.dispose();
    }
}
