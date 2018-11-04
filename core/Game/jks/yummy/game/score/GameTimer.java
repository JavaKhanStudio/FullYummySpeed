package jks.yummy.game.score;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import jks.yummy.game.drawing.Vars.GVars_Drawing;
import jks.yummy.interfaces.GVars_Interface;

public class GameTimer extends Label
{

	float time = 0;
	
	int minutes = 0; 
	String millisec = "" ;
	String timerShow = "";
	DecimalFormat df = new DecimalFormat("00.##");
	
	public GameTimer()
	{
		super("",GVars_Interface.baseSkin) ;
	}
	
	public void act()
	{
		float deltaTime = Gdx.graphics.getRawDeltaTime(); //You might prefer getRawDeltaTime()
		
		time += deltaTime; //if counting down

	    minutes = ((int)time) / 60;
	    millisec = df.format(time) ;

	    timerShow = minutes + "." + millisec ; 
	    this.setText("Timer : " + timerShow);
	}

}
