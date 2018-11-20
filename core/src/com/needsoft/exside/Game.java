package com.needsoft.exside;

import com.needsoft.exside.screens.SplashScreen;

public class Game extends com.badlogic.gdx.Game {
	
	public static final String TITLE = "ExSide";
	public static final String VERSION = "0.0.1";
	
	public static final int WINDOW_WIDTH = 720;
	public static final int WINDOW_HEIGHT = 520;
	
	
	@Override
	public void create() {
		setScreen(new SplashScreen(this));
	}
	
	@Override
	public void dispose() {
		getScreen().dispose();
	}
	
}
