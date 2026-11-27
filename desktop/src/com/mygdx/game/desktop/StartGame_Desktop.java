package com.mygdx.game.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.mains.Main_FYP_Game;

public class StartGame_Desktop 
{
	static final int decalY = 100 ;
	
	public static void main (String[] arg) 
	{
		Platform_Desktop.preferX11OnLinux();
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		DisplayMode screen = Lwjgl3ApplicationConfiguration.getDisplayMode(); 
		
		int width = 900; 
		int height = 500;
		config.setWindowedMode(width, height);
		
		// Centred horizontally, near the top, as the LWJGL2 launcher placed it.
		config.setWindowPosition(screen.width/2 - width/2, decalY/3);
		
		new Lwjgl3Application(new Main_FYP_Game(), config);
	}
}
