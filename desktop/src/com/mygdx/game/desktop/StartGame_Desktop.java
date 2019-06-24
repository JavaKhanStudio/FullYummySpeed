package com.mygdx.game.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.mains.Main_FYP_Game;

public class StartGame_Desktop 
{
	static final int decalY = 100 ;
	
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		

		config.width = 900; 
		config.height = 500;
		
		config.x = (int) (screenSize.getWidth()/2 - config.width/2) ; 
		config.y = decalY/3;
		
		new LwjglApplication(new Main_FYP_Game(), config);
	}
}
