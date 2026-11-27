package com.mygdx.game.desktop;

import static com.mygdx.game.desktop.FVars_Desktop.baseHeight;
import static com.mygdx.game.desktop.FVars_Desktop.baseWidth;
import static com.mygdx.game.desktop.FVars_Desktop.mulSize;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.mains.Main_FYP_DrawEditor;

public class StartEditor_Desktop 
{
	public static void main (String[] arg) 
	{
		Platform_Desktop.preferX11OnLinux();
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode((int) (baseWidth * mulSize), (int) (baseHeight * mulSize));
		
		new Lwjgl3Application(new Main_FYP_DrawEditor(), config);
	}
}
