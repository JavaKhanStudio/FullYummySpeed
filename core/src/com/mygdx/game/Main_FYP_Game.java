package com.mygdx.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jks.index.Index_Image;
import jks.tools.visual.Index_PulsingType;
import jks.yummy.game.GVars_Game;
import jks.yummy.game.drawing.Vars.GVars_Drawing;
import jks.yummy.interfaces.GVars_Interface;
import jks.yummy.interfaces.accueil.Page_Accueil;
import jks.yummy.sounds.effect.Index_Sound;
import static jks.yummy.game.GVars_Game.* ; 

public class Main_FYP_Game extends ApplicationAdapter 
{

	@Override
	public void create () 
	{
		Gdx.gl.glLineWidth(GVars_Drawing.glLineWidth_Player) ;
		GVars_Interface.init();
		Index_PulsingType.init();
		Index_Image.init();
		
		if(Gdx.app.getType().equals(ApplicationType.Android))
		{
			GVars_Game.runningOnAndroid = true ;
			GVars_Drawing.setAsAndroid() ;
		}
		
		GVars_Game.batch = new SpriteBatch() ;
		GVars_Interface.add(new Page_Accueil());
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		
		float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f);
		
		
		if(section_action != null)
			section_action.drawTheAction(delta);
		
		if(timer != null && !gameIsWon)
			timer.act();
		
		GVars_Interface.render();
		
		if(section_drawing != null)
			section_drawing.drawTheDrawing(delta) ; 
		
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
	
	@Override
	public void dispose () 
	{

	}
}
