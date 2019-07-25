package com.littlebiglion.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.littlebiglion.base.BaseScreen;
import com.littlebiglion.sprite.BackGround;
import com.littlebiglion.sprite.ButtonExit;
import com.littlebiglion.sprite.Dagger;
import com.littlebiglion.sprite.Fire;
import com.littlebiglion.sprite.StartButton;

public class Intro extends BaseScreen {

    private Game game;

    private TextureAtlas atlas, atlasfire;
    private float elapsedTime = 0f;
    private Animation animation;

    private BackGround bg;
    private Fire fire;

    private ButtonExit btnExt;
    private StartButton btnStrt;

    /**
     *
     * @param game
     */
    public Intro(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();

        atlas = new TextureAtlas("spriteAll/mySprite.atlas");

        bg = new BackGround(atlas);

        btnExt = new ButtonExit(atlas);
        btnStrt = new StartButton(atlas, game);

        atlasfire = new TextureAtlas(Gdx.files.internal("spriteFire/myFire.atlas"));
        fire = new Fire(atlasfire);
    }

    /**
     *
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if ((screenX >= 600 && screenX <= 690) && (screenY >= 350 && screenY <= 400 )) {
            btnExt.touchDown(screenX, screenY, pointer, button);
        }
        else if ((screenX >= 300 && screenX <= 390) && (screenY >= 350 && screenY <= 400 )) {
            btnStrt.touchDown(screenX, screenY, pointer, button);
        }
        return false;
    }

    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if ((screenX >= 600 && screenX <= 690) && (screenY >= 350 && screenY <= 400 )) {
            btnExt.touchUp(screenX, screenY, pointer, button);

        }

        else if ((screenX >= 300 && screenX <= 390) && (screenY >= 350 && screenY <= 400 )) {
            btnStrt.touchUp(screenX, screenY, pointer, button);
        }
        return false;
    }

    /**
     *
     * @param delta
     */
    @Override
    public void render(float delta) {
        super.render(delta);

        camera.update();

        elapsedTime += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();


        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        bg.draw(batch);

        fire.showBatsh(batch); // DONT TOUCH!!!!!! THIS IS MAGIC!!!!11!

        btnExt.draw(batch);
        btnStrt.draw(batch);

        batch.end();
    }



    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        fire.dispose();
        btnStrt.dispose();
        btnExt.dispose();
    }
}
