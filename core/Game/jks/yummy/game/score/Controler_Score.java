package jks.yummy.game.score;

import static jks.yummy.game.GVars_Game.*;

public class Controler_Score 
{

	public static void wonDrawing()
	{
		currentSpeed +=  bonusSpeedOnWin() ;
		section_action.asNewSpeed() ;
	}
	
	public static int bonusSpeedOnWin()
	{
		return 100 ; 
	}

	public static void reset() 
	{
		currentSpeed = 0  ;	
	}
	
	
}
