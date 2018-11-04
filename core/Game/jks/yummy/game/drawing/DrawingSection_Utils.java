package jks.yummy.game.drawing;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import jks.yummy.game.GVars_Game;
import jks.yummy.game.model.DrawingModel;
import jks.yummy.game.model.PassOverButton;
import jks.yummy.game.score.Utils_Scoring;
import jks.yummy.interfaces.GVars_Interface;


public class DrawingSection_Utils 
{

	public static PassOverButton buildButton(final Vector2 pos, final Section_Drawing ref)
	{
		final PassOverButton returningButton = new PassOverButton(pos) ;
		
		returningButton.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				if(ref.checkIfStarting(pos))
					ref.setComeFromClick(true);
				
				ref.shiftSize() ;
	            
				return true;
	        }
			 
			@Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) 
		    {
//				if(pointer == -1)
//					return ; 
				
				if(ref.checkIfIsTarget(pos) && ref.comeFromClick)
				{ref.valueSucces(returningButton) ;}	
				else 
				{
					if(!ref.checkIfStarting(pos) && !GVars_Game.runningOnAndroid)
					{
						ref.setComeFromClick(false);
						Utils_Scoring.resetMult();
						ref.shiftSize() ;
					}
				}		
			}

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) 
            {
               
            }
          
		});
		  

		return returningButton ;
	}
	
	public static DrawingModel getDrawing(String name)
	{
		DrawingModel returingValue = null; 
		InputStream file = Gdx.files.internal("DrawingData/" + name).read() ; 
	    InputStream buffer = new BufferedInputStream(file);
	    try 
	    {
			ObjectInput input = new ObjectInputStream (buffer);
			byte[] list = (byte[]) input.readObject();
			returingValue = DrawingModel.readFromPacket(list) ; 
		}
	    catch (IOException e) 
	    {e.printStackTrace();} 
	    catch (ClassNotFoundException e)
	    {e.printStackTrace();}
	    
	    return returingValue ; 
	}
	
}
