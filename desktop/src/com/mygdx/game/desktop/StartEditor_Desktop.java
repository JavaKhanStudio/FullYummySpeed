package com.mygdx.game.desktop;

import static com.mygdx.game.desktop.FVars_Desktop.baseHeight;
import static com.mygdx.game.desktop.FVars_Desktop.baseWidth;
import static com.mygdx.game.desktop.FVars_Desktop.mulSize;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.mains.Main_FYP_DrawEditor;

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
