package jks.yummy.game.drawing;

import static jks.yummy.game.drawing.Vars.FVars_Drawing.*;
import static jks.yummy.game.drawing.Vars.GVars_Drawing.*;


import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import jks.tools.visual.Index_PulsingType;
import jks.tools.visual.Utils_Interface;
import jks.yummy.game.drawing.Vars.FVars_Drawing;
import jks.yummy.game.drawing.Vars.GVars_Drawing;

public class DrawingSection_Draw 
{
	
	public static void drawAssit(Section_Drawing ref,float delta)
	{
		ref.render_drawAssit.begin(ShapeType.Line);
		Gdx.gl.glLineWidth(1) ;
		Index_PulsingType.show_icon_ShowTutorial.nextCycle(delta) ;

		
		if(ref.comeFromClick)
			Index_PulsingType.show_icon_ShowTutorial.drawFor_Icon(ref.currentTarget, ref.render_drawAssit,decalX,decalY);
		else
			Index_PulsingType.show_icon_ShowTutorial.drawFor_Icon(ref.currentlySelected, ref.render_drawAssit,decalX,decalY);
			
		ref.render_drawAssit.end();
		Gdx.gl.glLineWidth(GVars_Drawing.glLineWidth_Player) ;
	}
	
	public static void drawLines_Player(ShapeRenderer render_ShapeLine, ArrayList<Actor> currentLines)
	{
		render_ShapeLine.begin(ShapeType.Line);
		render_ShapeLine.setColor(lineColor);
		Gdx.gl.glLineWidth(GVars_Drawing.glLineWidth_Player) ;
		
		for(int a = 1 ; a < currentLines.size() ; a ++)
		{
			DrawingSection_Draw.drawBetween(render_ShapeLine, currentLines.get(a - 1), currentLines.get(a));
		}
		
		render_ShapeLine.end();
	}
	
	public static void drawLines_Dream(ShapeRenderer render_ShapeLine, ArrayList<Actor> currentLines)
	{
		render_ShapeLine.begin(ShapeType.Line);
		render_ShapeLine.setColor(lineColor);
		Gdx.gl.glLineWidth(GVars_Drawing.glLineWidth_Player) ;
		
		for(int a = 1 ; a < currentLines.size() ; a ++)
		{
			DrawingSection_Draw.drawBetween(render_ShapeLine, currentLines.get(a - 1), currentLines.get(a));
		}
		
		render_ShapeLine.end();
	}
	
	private static void drawBetween(ShapeRenderer render_ShapeLine,Actor actor, Actor actor2)
	{
		Vector2 from = new Vector2(actor.getX() + actor.getWidth()/2    + decalX,
								   actor.getY() + actor.getHeight()/2   + decalY) ;
		Vector2 too = new Vector2(actor2.getX() + actor2.getWidth()/2   + decalX,
								   actor2.getY() + actor2.getHeight()/2 + decalY) ;
		
		render_ShapeLine.line(from, too);
	}
	
	public static Image buildDrawImage(String imagePath, Actor toBeAddOn)
	{
		Image resultImage = new Image(Utils_Interface.buildTextureRegion(imagePath)) ; 
		resultImage.setSize(toBeAddOn.getWidth(), toBeAddOn.getWidth());
//		resultImage.setPosition(decalX, decalY);
		resultImage.setColor(1, 1, 1,startImageVisibility);
		return resultImage ; 
	}
	
	public static void endDrawGestion(Section_Drawing ref,float delta)
	{
		if(lineColor.a < deleteLinesAt)
		{
			ref.resetValues() ;
			ref.isDrawOn.add(ref.resultImage);
		}

		// Image
		ref.resultImage.setColor(1, 1, 1,ref.currentVisibilityOnResult);
		ref.currentVisibilityOnResult += delta * mulOnImageVisibility ;
		
		if(ref.currentVisibilityOnResult > nextImageAtOpacity)
		{
			if(ref.lookingAt < ref.modelList.size())
				ref.nextImage() ;
			else
			{
				ref.lookingAt = 0 ; 
				Collections.shuffle(ref.modelList);
				ref.nextImage();
			}
		}

		// Line and Button part
		if(ref.currentObjective != 0)
		{
			lineColor.a -= delta * mulOnImageVisibility ;
		}		
	}
	
}
