package jks.yummy.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import jks.index.Index_Drawing;
import jks.yummy.game.action.Section_Action;
import jks.yummy.game.desktopControl.Controler_Input;
//import jks.tools2d.parallax.Utils_Parralax;
//import jks.yummy.game.action.Section_Action;
import jks.yummy.game.drawing.DrawingSection_Utils;
import jks.yummy.game.drawing.Section_Drawing;
import jks.yummy.game.model.DrawingModel;
import jks.yummy.game.score.Controler_Score;
import jks.yummy.game.score.GameTimer;
import jks.yummy.game.score.Section_Scoring;
import jks.yummy.game.sounds.Controler_Music;
import jks.yummy.game.sounds.Controler_SoundEffect;
import jks.yummy.interfaces.GVars_Interface;
import jks.yummy.interfaces.accueil.Page_Accueil;

public class GVars_Game 
{
	public static boolean runningOnAndroid = false ; 
	public static Section_Action section_action;
	public static Section_Scoring section_scoring;
	public static Section_Drawing section_drawing;
	
	public static GameTimer timer ; 
	public static SpriteBatch batch ;
	
	public static float currentSpeed ; 
	
	public static boolean gameIsWon = false ; 
	
	public static void initGame(ArrayList<DrawingModel> listToDraw)
	{
		GVars_Interface.reset();

		
		section_drawing = new Section_Drawing(listToDraw) ;
		GVars_Interface.mainInterface.addActor(section_drawing);

		section_action = new Section_Action() ;
		GVars_Interface.mainInterface.addActor(section_action);
		GVars_Interface.mainInterface.addActor(buildRetour());
		
		if(!runningOnAndroid) {
			Controler_Input.initDesktopInput();
		}
		
		timer = new GameTimer() ;
		timer.setSize(Gdx.graphics.getWidth()/2, 200);
		timer.setPosition((Gdx.graphics.getWidth() - timer.getWidth())/2, Gdx.graphics.getHeight()/2 - timer.getHeight());
//		scoring = new Section_Scoring() ;
//		scoring.setPosition(0, drawing.getHeight() + (Gdx.graphics.getHeight() * FVars_Game.decal));
//		GVars_Interface.mainInterface.addActor(scoring);
		
		startGame() ;
	}
	
	public static Actor buildRetour()
	{
		TextButton retour = new TextButton("Retour", GVars_Interface.baseSkin); 
		float buttonSize = Gdx.graphics.getWidth()/6 ; 
		retour.setSize(buttonSize, buttonSize/2);
		retour.setPosition(retour.getWidth()/2 , Gdx.graphics.getHeight() - retour.getHeight() - 50);
//		retour.setPosition(Gdx.graphics.getWidth() - retour.getWidth() - 10, Gdx.graphics.getHeight() - retour.getHeight() - 50);
		retour.addListener(new InputListener() 
		{
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				GVars_Interface.clean();
				resetGame() ; 
				GVars_Interface.add(new Page_Accueil());
				return false ;
			}
		});
		
		return retour ;
	}
	
	public static void resetGame()
	{
		section_drawing = null ; 
		section_scoring = null ; 
		section_action = null ; 
		gameIsWon = false ; 
		Controler_Music.reset() ; 
		Controler_SoundEffect.reset() ; 
		Controler_Score.reset() ; 
	}
	
	public static void startGame()
	{
		section_drawing.nextImage();
	}
	
	public static void startGame_Test()
	{
		section_drawing.read(DrawingSection_Utils.getDrawing("test"));
	}

	public static float getCurrentSpeed() 
	{
		return currentSpeed;
	}
}
