package jks.yummy.editeur;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import jks.yummy.game.FVars_Game;
import jks.yummy.game.drawing.DrawingSection_Draw;
import jks.yummy.game.drawing.Vars.GVars_Drawing;
import jks.yummy.game.model.DrawingModel;
import jks.yummy.game.model.PassOverButton;
import jks.yummy.interfaces.GVars_Interface;

public class Canvas extends Table
{

	public static DrawingModel drawModel ;
	public static PassOverButton lastAdd ;
	public static HashMap<Vector2,PassOverButton> buttonList ; 
	public static Table objectTable ;
	
	public static ArrayList<Actor> currentLines ;
	
	public TextButton convert;
	
	public ShapeRenderer render_ShapeLine;
	
	public Canvas(String imagePath, final String saveName)
	{
		this.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * FVars_Game.drawingSize);
		this.setLayoutEnabled(false);
		
		Image ref = DrawingSection_Draw.buildDrawImage(imagePath, this) ; 
		drawModel = new DrawingModel(imagePath) ; 
		buttonList = new HashMap<Vector2, PassOverButton>() ;
		objectTable = this ;
		
		convert = new TextButton("Convert", GVars_Interface.baseSkin) ;
		convert.setSize(200, 50);
		convert.setPosition(100, Gdx.graphics.getHeight() - 200);
		convert.addListener(new InputListener()
		{
			@Override
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
	        {
				FileOutputStream fout;
				try 
				{
					fout = new FileOutputStream("./" + saveName);
					ObjectOutputStream oos = new ObjectOutputStream(fout);
					oos.writeObject(drawModel.convertirEnPacket());
					System.exit(1);
				} 
				catch (FileNotFoundException e) 
				{e.printStackTrace();} 
				catch (IOException e) 
				{e.printStackTrace();}
				
				return true ; 
	        }

		});
		
		render_ShapeLine = new ShapeRenderer() ;
		currentLines = new ArrayList<Actor>() ;
		
		GVars_Drawing.lineColor = Color.WHITE ;
		this.add(convert) ;
		this.add(ref);
 	}
	
	public void draw()
	{
		DrawingSection_Draw.drawLines_Player(render_ShapeLine, currentLines);
	}

	public static void addNewButton(final int posX, final int posY) 
	{
		final Vector2 vector = new Vector2(posX,posY) ;
		drawModel.addValue(vector);
		final PassOverButton pass = new PassOverButton(vector) ;
		pass.addListener(new InputListener()
		{
			@Override
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
	        {
				drawModel.addValue(vector);
				currentLines.add(pass) ;
				return true ; 
	        }
		});
	      
		objectTable.add(pass);
		currentLines.add(pass) ;
		lastAdd = pass ;
	}
	
	
	
	
	
}
