package jks.yummy.editeur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

import jks.yummy.game.drawing.Vars.GVars_Drawing;

/** The draw editor's mouse: a right click adds a dot where it lands, shifted by -buttonSize/2. */
public class Inputs_Editor extends InputAdapter 
{
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
}
