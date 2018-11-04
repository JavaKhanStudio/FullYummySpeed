package jks.yummy.game.desktopControl;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import jks.yummy.game.GVars_Game;
import jks.yummy.interfaces.GVars_Interface;
import jks.yummy.sounds.effect.Index_Sound;

public class Controler_Input 
{
	
	public static void initDesktopInput()
	{
		GVars_Interface.mainInterface.addListener(screenAutoDeselect()) ;
	}
		
	
	public static InputListener screenAutoDeselect()
	{
		return new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				if(button == 1) {
					rightClick() ; 
				}
				return true;
			}
			
			@Override
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
	        {
				if(button == 0)
				{
					GVars_Game.section_drawing.setComeFromClick(false);
					GVars_Game.section_drawing.shiftSize() ;
					if(!GVars_Game.section_drawing.currentDrawIsWon)
					{
						Index_Sound.playMiss(); ; 
//						Utils_Scoring.resetMult();
					}		
				}
					
			}
		} ;
	}
	
	public static void rightClick()
	{
		GVars_Game.section_action.jump();
	}
	
}
