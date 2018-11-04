package jks.yummy.game.score;

import static jks.yummy.game.score.FVars_Scoring.baseValue;
import static jks.yummy.game.score.FVars_Scoring.mulPower;

import jks.yummy.game.GVars_Game;

import static jks.yummy.game.GVars_Game.* ;

public class Utils_Scoring 
{

	public static void connectionSucces(Section_Scoring ref)
	{
		if(ref != null)
		{
			int pointValue = (int) (baseValue * (ref.currentMult * mulPower)) ; 
			ref.currentPoints += pointValue ;
			ref.scoreTotal += pointValue ;
			ref.currentMult ++ ;
			ref.setInformation();
		}	
	}
	
	public static void mistake(Section_Scoring ref)
	{
		ref.currentMult = 1 ;
		ref.setInformation();
	}

	public static void act() 
	{
		timer.act();
	}

	public static void resetMult() 
	{
//		GVars_Game.scoring.resetMult();
		
	}
	
	
}
