package jks.yummy.editeur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import jks.yummy.game.drawing.Vars.GVars_Drawing;

public class Inputs_Editor implements InputProcessor 
{

	@Override
	public boolean keyDown(int keycode) 
	{
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) 
	{
		
		if(button == 1)
		{
			Canvas.addNewButton(
					(int) (screenX - GVars_Drawing.buttonSize/2),
					(int) (Gdx.graphics.getHeight() - screenY - GVars_Drawing.buttonSize/2)) ;
			return true;
		}
		
		return false ; 
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{return false;}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) 
	{return false;}

	@Override
	public boolean mouseMoved(int screenX, int screenY) 
	{return false;}

	@Override
	public boolean scrolled(int amount) 
	{return false;}
	
	  
		
}
