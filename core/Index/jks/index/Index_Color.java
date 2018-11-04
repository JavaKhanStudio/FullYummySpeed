package jks.index;

import com.badlogic.gdx.graphics.Color;

public class Index_Color
{
	public static Color maxGreen = Color.GREEN.cpy().add(0, 0.3f, .3f, 0);
	public static Color midGreen = Color.GREEN.cpy().sub(0.3f, 0.3f, 0.3f, 0);
	public static Color minGreen = Color.GREEN.cpy().sub(0.6f, 0.6f, 0.6f, 0);;
	public static Color transGreen = Color.GREEN.cpy().sub(0.3f, 0.3f, 0.3f, 0.5f);
	
	public static Color maxYellow = Color.ORANGE.cpy() ;
	public static Color minYellow = Color.YELLOW.cpy() ;
	
	public static Color maxBLUE = Color.BLUE.cpy().sub(0.1f, 0.1f, 0.1f, 0) ;
	public static Color midBLUE = Color.BLUE.cpy() ;
	public static Color minBLUE = Color.BLUE.cpy() ;
}
