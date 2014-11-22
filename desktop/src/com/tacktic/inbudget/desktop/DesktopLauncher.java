package com.tacktic.inbudget.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tacktic.inbudget.InBudget;

public class DesktopLauncher {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "In Budget!";
		config.width = 640;
		config.height = 800;
		new LwjglApplication(new InBudget(), config);
	}
}
