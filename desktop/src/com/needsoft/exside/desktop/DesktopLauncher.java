package com.needsoft.exside.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.needsoft.exside.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL30 = true;
		config.width = 1024;
		config.height = 600;
		config.resizable = false;
		config.title = "ExSide";
		new LwjglApplication(new Game(), config);
	}
}
