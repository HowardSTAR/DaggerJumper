package com.littlebiglion.base;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public abstract class BaseScreen extends ApplicationAdapter implements Screen, InputProcessor {


    protected SpriteBatch batch;
    protected Rectangle playerOne;
    protected Vector2 posi;
    protected OrthographicCamera camera;
    protected Vector2 v;
    protected Array<Rectangle> daggerDrops;
    protected Array<Rectangle> daggerDropsBack;
    protected long lastDropTime;


    Viewport viewport;

    /**
     * Задаем направление "прыжока" персонажа, а так же его скорость
     */
    protected int x = 0;
    protected int y = 7;


    @Override
    public void show() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();

        //  FitViewport –  поддерживает виртуальный размер экрана, всегда сохранять соотношение размера виртуального экрана
        viewport = new FitViewport(1024,600,camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

        Gdx.input.setInputProcessor(this);

        posi = new Vector2();
        v = new Vector2(x, y);

        playerOne = new Rectangle();
        posi.x = 700 / 2 - 74 / 2;
        posi.y = 100;
        playerOne.width = 74;
        playerOne.height = 74;

        daggerDropsBack = new Array<Rectangle>();
        daggerDrops = new Array<Rectangle>();

        spawnDagger();
        spawnDaggerBack();
    }

    protected void spawnDagger(){
        Rectangle daggerDrop = new Rectangle();
        Rectangle daggerDrop3 = new Rectangle();
        daggerDrop.x = -10;
        daggerDrop.y = MathUtils.random(100, 480);
        daggerDrop.width = 1024;
        daggerDrop.height = 700;
        daggerDrops.add(daggerDrop);

        daggerDrop3.x = -10;
        daggerDrop3.y = MathUtils.random(100, 400);
        daggerDrop3.width = 1024;
        daggerDrop3.height = 700;
        daggerDrops.add(daggerDrop3);
        lastDropTime = TimeUtils.nanoTime();
    }

    protected void spawnDaggerBack(){
        Rectangle daggerDropBack = new Rectangle();
        daggerDropBack.x = 1024;
        daggerDropBack.y = MathUtils.random(100, 480);
        daggerDropBack.width = 1024;
        daggerDropBack.height = 700;
        daggerDropsBack.add(daggerDropBack);

        Rectangle daggerDropBack3= new Rectangle();
        daggerDropBack3.x = 1024;
        daggerDropBack3.y = MathUtils.random(100, 400);
        daggerDropBack3.width = 1024;
        daggerDropBack3.height = 700;
        daggerDropsBack.add(daggerDropBack3);
        lastDropTime = TimeUtils.nanoTime();
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

    }

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
    }
}
