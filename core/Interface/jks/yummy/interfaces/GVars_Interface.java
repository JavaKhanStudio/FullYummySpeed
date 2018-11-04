package jks.yummy.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import jks.yummy.game.drawing.Vars.GVars_Drawing;

public class GVars_Interface 
{
	public static Skin baseSkin ;
	public static Stage mainInterface ;
	
	public static void init()
	{
		mainInterface = new Stage();
		baseSkin = loadSkin ()  ;
//		baseSkin = new Skin() ;
		Gdx.input.setInputProcessor(mainInterface);
	}
	
	public static Skin loadSkin () 
	{
		Skin skin = new Skin(Gdx.files.internal("skins/orange/skin/uiskin.json"));
		skin.getFont("font").getData().setScale(1.5f);
		return skin ; 
	}

	public static void clean()
	{
		mainInterface.clear();
	}
	
	public static void reset()
	{
		mainInterface = new Stage();
		Gdx.input.setInputProcessor(mainInterface);
	}
	
	public static void render()
	{
		mainInterface.act(); 
		mainInterface.draw();
	}
	
	public static void add(Actor actor)
	{mainInterface.addActor(actor) ;}
	
}
