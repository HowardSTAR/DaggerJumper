package com.littlebiglion.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class BaseScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    protected Vector3 position;
    protected Viewport viewport;
    public static BitmapFont font;
    public static Music music;

    /**
     * Задаем экран
     */

    public float widthW = 1024;
    public float heightW = 600;


    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        music = Gdx.audio.newMusic(Gdx.files.internal("music/main.mp3"));

        //  FitViewport –  поддерживает виртуальный размер экрана, всегда сохранять соотношение размера виртуального экрана
        viewport = new FitViewport(widthW,heightW, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

        Gdx.input.setInputProcessor(this);

        position = new Vector3();
        font = new BitmapFont();

    }



    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void render(float delta) {

        font.getData().setScale(2);
    }

    /**
     *
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        music.dispose();
    }
}
