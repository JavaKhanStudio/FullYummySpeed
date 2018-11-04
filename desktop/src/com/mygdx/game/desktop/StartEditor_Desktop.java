package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import static com.mygdx.game.desktop.FVars_Desktop.* ;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main_FYP_Game;

import jks.yummy.editeur.Main_FYP_DrawEditor;

public class StartEditor_Desktop 
{
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) (baseWidth * mulSize); 
		config.height = (int) (baseHeight * mulSize);
		
		new LwjglApplication(new Main_FYP_DrawEditor(), config);
	}
}
