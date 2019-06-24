package jks.yummy.interfaces.accueil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import jks.index.Index_Drawing;
import jks.yummy.game.GVars_Game;
import jks.yummy.interfaces.GVars_Interface;

public class Page_Accueil extends Table 
{

	TextButton fruit ; 
	TextButton legume ; 
	TextButton mix ;
	TextButton hard ;
	
	public Page_Accueil()
	{
		super(GVars_Interface.baseSkin) ; 
		this.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.defaults().space(Gdx.graphics.getHeight()/10);
		float buttonSize = Gdx.graphics.getHeight()/30; 
		
		fruit = new TextButton("Fruit", GVars_Interface.baseSkin) ; 
		fruit.addListener(new InputListener() 
		{
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{GVars_Game.initGame(Index_Drawing.getFruitList()); return false ;}
		});
		fruit.pad(buttonSize,buttonSize,buttonSize,buttonSize);
		
		legume  = new TextButton("Legume", GVars_Interface.baseSkin) ; 
		legume.addListener(new InputListener() 
		{
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{GVars_Game.initGame(Index_Drawing.getLegumeList()); return false ;}
		});
		legume.pad(buttonSize,buttonSize,buttonSize,buttonSize);
		
		mix = new TextButton("Mix-up!", GVars_Interface.baseSkin) ;
		mix.addListener(new InputListener() 
		{
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
			{GVars_Game.initGame(Index_Drawing.getLegumeList()); return false ;}
		});
		mix.pad(buttonSize,buttonSize,buttonSize,buttonSize);
		
		
		
		this.add(fruit).row();
		this.add(legume).row();
		this.add(mix).row();
	}
	
	
}
