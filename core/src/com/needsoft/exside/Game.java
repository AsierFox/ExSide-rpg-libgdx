package com.needsoft.exside;

import com.badlogic.gdx.Gdx;
import com.needsoft.exside.screens.PlayingScreen;

public class Game extends com.badlogic.gdx.Game {
	
	@Override
	public void create() {
		setScreen(new PlayingScreen());
	}
	
}
