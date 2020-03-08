package com.littlemonster.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.littlemonster.game.LittleMonster;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Little Monster";
		config.width = 800;
		config.height = 600;
		config.forceExit = false;
		new LwjglApplication(new LittleMonster(), config);
	}
}
