package jks.yummy.game.drawing.Vars;

import static jks.yummy.game.drawing.Vars.FVars_Drawing.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import jks.yummy.game.FVars_Game;


public class GVars_Drawing 
{

	public static float buttonSize = 20; 
	public static float buttonSizeAtFull = 40; 
	public static float growingSpeed = 40;
	public static Vector2 buttonSizeAtFullVector ; 
	
	public static float glLineWidth_Player = 6.4f ; 
	public static float glLineWidth_Dream = 3.4f ; 
	public static boolean drawingAssit = true ;  
	
	public static float decalX = 0 ;  
	public static float decalY = 0 ; 
	public static Vector2 buttonSizeVector ;
	
	public static final float androidMulti = 3.0f ;  

	public static float diffWidth = 1;
	public static float diffHeight = 1;
	
	public static Color lineColor ; 
	
	public static void setDiff(float width, float height)
	{
		diffWidth = width / (refSizeWidth * refMulSize) ;
		diffHeight = height / (refSizeHeight * refMulSize) ;
			
		decalX = Gdx.graphics.getWidth() - Gdx.graphics.getHeight() ; 
		decalY = (Gdx.graphics.getHeight() - height)/2 ; 
		buttonSizeVector = new Vector2(buttonSize,buttonSize) ;
		buttonSizeAtFullVector = new Vector2(buttonSizeAtFull,buttonSizeAtFull) ;
	}
	
	
	public static void setAsAndroid()
	{
		buttonSize *= androidMulti/2 ; 
		buttonSizeAtFull *= androidMulti ;
		growingSpeed *= androidMulti * 1.5f ; 
		buttonSizeVector = new Vector2(buttonSize,buttonSize) ;
	}
	
	
	public static float getDrawingSectionHeight()
	{return Gdx.graphics.getHeight() 
//			* FVars_Game.drawingSize 
			;}


	
}
