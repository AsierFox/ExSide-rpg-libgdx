package com.needsoft.exside.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.needsoft.exside.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Game.WINDOW_WIDTH;
		config.height = Game.WINDOW_HEIGHT;
		config.resizable = true;
		config.title = Game.TITLE;
		new LwjglApplication(new Game(), config);
	}
}
