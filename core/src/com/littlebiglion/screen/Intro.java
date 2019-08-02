package com.littlebiglion.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.littlebiglion.base.BaseScreen;
import com.littlebiglion.sprite.BackGround;
import com.littlebiglion.sprite.ButtonExit;
import com.littlebiglion.sprite.Fire;
import com.littlebiglion.sprite.StartButton;

public class Intro extends BaseScreen {

    private Game game;

    private TextureAtlas atlas, atlasfire;
    private float elapsedTime = 0f;

    private BackGround bg;
    private Fire fire;

    private Texture tittle;

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

        tittle = new Texture(Gdx.files.internal("sprite/DaggerJumper.png"));
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
        Vector3 exit1 = new Vector3(600,350,0);
        Vector3 exit2 = new Vector3(690,400,0);
        Vector3 star1 = new Vector3(300,350,0);
        Vector3 star2 = new Vector3(390,400,0);

        camera.project(exit1);
        camera.project(exit2);
        camera.project(star1);
        camera.project(star2);

        if ((screenX >= exit1.x && screenX <= exit2.x) && (screenY >= exit1.y && screenY <= exit2.y)) {
            btnExt.touchDown(screenX, screenY, pointer, button);
        }

        else if ((screenX >= star1.x && screenX <= star2.x) && (screenY >= star1.y && screenY <= star2.y)) {
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

        Vector3 exit1 = new Vector3(600,350,0);
        Vector3 exit2 = new Vector3(690,400,0);
        Vector3 star1 = new Vector3(300,350,0);
        Vector3 star2 = new Vector3(390,400,0);

        camera.project(exit1);
        camera.project(exit2);
        camera.project(star1);
        camera.project(star2);

        if ((screenX >= exit1.x && screenX <= exit2.x) && (screenY >= exit1.y && screenY <= exit2.y)) {
            btnExt.touchUp(screenX, screenY, pointer, button);
        }

        else if ((screenX >= star1.x && screenX <= star2.x) && (screenY >= star1.y && screenY <= star2.y)) {
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

        batch.draw(tittle, 330, 280,300,200);

        btnExt.draw(batch);
        btnStrt.draw(batch);

        music.setVolume(0.5f);
        music.play();
        music.setLooping(true);

        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        fire.dispose();
        btnStrt.dispose();
        btnExt.dispose();
        music.dispose();
        tittle.dispose();
        atlas.dispose();
        atlasfire.dispose();
        batch.dispose();
    }
}
