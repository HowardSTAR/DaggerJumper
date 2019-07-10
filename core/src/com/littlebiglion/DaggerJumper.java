package com.littlebiglion;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DaggerJumper extends ApplicationAdapter {
	SpriteBatch batch;
	Texture bg, cel, flor;
	TextureRegion region;

	@Override
	public void create () {

		batch = new SpriteBatch();
		bg = new Texture("background2.png");
		cel = new Texture("ceiling.png");
		flor = new Texture("floor.png");
		region = new TextureRegion(bg);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(region, 0, 0, 700,550);
		batch.draw(cel, 0, 460, 700,100);
		batch.draw(flor, 0, 0, 700,100);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		cel.dispose();
		bg.dispose();
		flor.dispose();
	}
}
