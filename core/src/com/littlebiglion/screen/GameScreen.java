package com.littlebiglion.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.littlebiglion.base.BaseScreen;
import com.littlebiglion.sprite.BackGround;
import com.littlebiglion.sprite.Dagger;
import com.littlebiglion.sprite.DaggerBack;
import com.littlebiglion.sprite.Fire;
import com.littlebiglion.sprite.Player;

public class GameScreen extends BaseScreen {

    private TextureAtlas atlas, atlasfire;
    private float elapsedTime = 0f;
    private Animation animation;

    private BackGround bg;
    private Player player;
    private Dagger dagger;
    private DaggerBack daggerBack;

    private Fire fire;


    @Override
    public void show() {
        super.show();

        atlas = new TextureAtlas("spriteAll/mySprite.atlas");

        bg = new BackGround(atlas);

        player = new Player(atlas);

        dagger = new Dagger(atlas);
        daggerBack = new DaggerBack(atlas);

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
        return super.touchDown(screenX, screenY, pointer, button);
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
        return super.touchUp(screenX, screenY, pointer, button);
    }

    /**
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
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
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        bg.draw(batch);

        fire.showBatsh(batch); // DONT TOUCH!!!!!! THIS IS MAGIC!!!!11!

        dagger.draw(batch);
        player.draw(batch);
        daggerBack.draw(batch);

        batch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        fire.dispose();
        player.dispose();
        dagger.dispose();
    }

}
