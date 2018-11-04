package jks.tools.visual;

import static jks.index.Index_Color.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import jks.tools.visual.PulsingShape;

public class Index_PulsingType 
{
;
	public static PulsingShape show_icon_ShowTutorial ;
	
	
	public static void init()
	{
		
		show_icon_ShowTutorial = new PulsingShape
		(
			new Array<Color>() 
			{{
				add(midGreen);
			    add(minGreen);
			}}, 
			1, 
			5,
			10,
			true
		) ; 
	}
	
	
}
