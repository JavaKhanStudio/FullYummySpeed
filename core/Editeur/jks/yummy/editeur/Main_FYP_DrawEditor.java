package jks.yummy.editeur;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;

import jks.index.Index_Image;
import jks.tools.visual.Index_PulsingType;
import jks.yummy.game.drawing.Section_Drawing;
import jks.yummy.game.drawing.Vars.GVars_Drawing;
import jks.yummy.game.model.DrawingModel;
import jks.yummy.interfaces.GVars_Interface;

public class Main_FYP_DrawEditor extends ApplicationAdapter 
{
	
	Section_Drawing drawingSection ; 
	Canvas canvas ;
		
	@Override
	public void create () 
	{
		Gdx.gl.glLineWidth(GVars_Drawing.glLineWidth_Player) ;
		GVars_Interface.init();
		Index_PulsingType.init();
		Index_Image.init();
		
		String doing = "pineapple";
		String subSector = "fruit/" ;
		
		canvas = new Canvas("DrawingImage/"  + subSector + doing + ".png", doing) ; 
		GVars_Interface.add(canvas);
		
		Gdx.input.setInputProcessor
		(
			new InputMultiplexer
			(
				new Inputs_Editor(),
				GVars_Interface.mainInterface
			)
		);
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f);
		canvas.draw();
		GVars_Interface.render();
	}
	
	@Override
	public void dispose () 
	{

	}
}
	
	
