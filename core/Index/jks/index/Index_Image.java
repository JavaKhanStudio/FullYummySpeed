package jks.index;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import jks.tools.visual.Utils_Interface;

public class Index_Image 
{
	
	public static TextureRegionDrawable buttonLook ;
	public static final String standarPath = "Interface/"; 
	
	
	public static void init()
	{
		buttonLook = Utils_Interface.buildDrawingRegionTexture(standarPath + "buttonStars2.png") ;
	}

}
