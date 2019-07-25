package com.littlebiglion;

import com.badlogic.gdx.Game;
import com.littlebiglion.screen.Intro;

public class DaggerJumper extends Game {

	@Override
	public void create () {
		setScreen(new Intro(this));
	}

}