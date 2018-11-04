package jks.tools.visual;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Utils_Actor
{
	
	
	public static void removeAllAction(Actor actor) 
	{
		if(actor != null)
		{
			for(Action action : actor.getActions())
				actor.removeAction(action);
		}
		
	}

}
