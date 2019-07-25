package com.littlebiglion.base;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class Sprite extends BaseScreen{

    protected TextureRegion regions;
    protected TextureAtlas textureAtlas;

    protected float x, y, width, height;
    protected OrthographicCamera camera;
    protected SpriteBatch batch;

    /**
     *
     * @param region
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Sprite(TextureRegion region, float x, float y, float width, float height){
        this.regions = region;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     *
     * @param textureAtlas
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Sprite(TextureAtlas textureAtlas, float x, float y, float width, float height){
        this.textureAtlas = textureAtlas;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     *
     * @param region
     */
    public Sprite(TextureRegion region) {
        this.regions = region;
    }

    protected Sprite() {
    }

    /**
     *
     * @param batch
     */
    public void draw(SpriteBatch batch){
        batch.draw(regions, x, y, width, height);
    }

}
