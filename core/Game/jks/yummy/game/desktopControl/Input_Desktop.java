package jks.yummy.game.desktopControl;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

import jks.yummy.game.GVars_Game;

public class Input_Desktop  extends InputAdapter 
{
	@Override
	public boolean keyDown (int keycode) 
	{
		switch (keycode) 
		{
			case Keys.UP : 
			case Keys.Z :
		}
		
		return false;
	}
	
	@Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) 
	{
		if(button == 1)
		{
			
		}
		return false;	
	}
	
	public static void jump()
	{
		GVars_Game.section_action.jump() ; 
	}

}
