package com.needsoft.exside;

import com.needsoft.exside.screens.SplashScreen;
import com.needsoft.exside.screens.LoadingScreen;

public class Game extends com.badlogic.gdx.Game {
	
	public static final String TITLE = "ExSide";
	public static final String VERSION = "0.0.1";
	
	public static final int WINDOW_WIDTH = 720;
	public static final int WINDOW_HEIGHT = 520;
	
	// TODO Have here the common game screens, to not create instances every screen changes
	// (ie: menu, settings, loading...), and do belongsToGame.setScreen(belongsToGame.menuScreen)
	
	@Override
	public void create() {
		setScreen(new LoadingScreen(this));
	}
	
	@Override
	public void dispose() {
		getScreen().dispose();
	}
	
}
