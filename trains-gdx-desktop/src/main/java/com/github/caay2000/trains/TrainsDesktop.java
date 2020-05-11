package com.github.caay2000.trains;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class TrainsDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.height = 1200;
		config.width = 1080;


		new LwjglApplication(new SimpleTest(), config);
	}
}
