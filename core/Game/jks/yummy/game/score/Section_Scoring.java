package jks.yummy.game.score;

import static jks.yummy.game.score.FVars_Scoring.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import jks.yummy.game.FVars_Game;
import jks.yummy.game.GVars_Game;
import jks.yummy.interfaces.GVars_Interface; 

public class Section_Scoring extends Table
{

	Label currentPointsLabel ;
	Label currentMultLabel ;
	Label scoreTotalLabel ; 
	
	int currentPoints ;
	int currentMult ;
	int scoreTotal ; 
	
	TextButton retour ; 
	
	public Section_Scoring()
	{
		super(GVars_Interface.baseSkin) ; 
		this.setLayoutEnabled(false);
		
		float height = Gdx.graphics.getHeight() 
//				* FVars_Game.ScoreSize
				;
		this.setSize(Gdx.graphics.getWidth(), height);
		
		currentPointsLabel = new Label("",GVars_Interface.baseSkin) ;
		currentPointsLabel.setHeight(this.getHeight()/2);
		currentPointsLabel.setWidth(this.getWidth() * 0.5f);
		
		scoreTotalLabel = new Label("",GVars_Interface.baseSkin) ;
		scoreTotalLabel.setHeight(this.getHeight()/2);
		scoreTotalLabel.setWidth(this.getWidth() * 0.5f);
		scoreTotalLabel.setPosition(0, currentPointsLabel.getHeight());
		
		currentMultLabel = new Label("",GVars_Interface.baseSkin) ;
		currentMultLabel.setPosition(currentPointsLabel.getWidth(), 0);
		currentMultLabel.setHeight(this.getHeight()/2);
		currentMultLabel.setWidth(this.getWidth() * 0.5f);
		
		Label refTimer = GVars_Game.timer ; 
		refTimer.setPosition(currentPointsLabel.getWidth(), 0);
		refTimer.setHeight(this.getHeight()/2);
		refTimer.setPosition(currentPointsLabel.getWidth(), currentMultLabel.getHeight());
		refTimer.setWidth(this.getWidth() * 0.5f);
		
		setInformation() ; 

		this.add(currentPointsLabel);
		this.add(scoreTotalLabel) ;
		this.add(currentMultLabel) ;
		this.add(refTimer) ;
	}
	
	public void setInformation()
	{
		currentPointsLabel.setText("Score dessin: " + currentPoints);
		scoreTotalLabel.setText("Score Total : " + scoreTotal);
		currentMultLabel.setText("Mult : " + currentMult * mulPower);
	}
	
	
	public void newDrawing()
	{
		currentPoints = 0 ; 
		resetMult() ;
	}
	
	public void resetMult()
	{currentMult = (int) (1/mulPower) ; setInformation() ; }
	
	
}
