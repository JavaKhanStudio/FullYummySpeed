package jks.yummy.game.drawing;

import static jks.yummy.game.drawing.Vars.GVars_Drawing.*;
import static jks.yummy.game.drawing.Vars.FVars_Drawing.*;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import jks.index.Index_ActorAction;
import jks.tools.visual.Utils_Actor;
import jks.tools.visual.Utils_Interface;
import jks.yummy.game.FVars_Game;
import jks.yummy.game.GVars_Game;
import jks.yummy.game.drawing.Vars.FVars_Drawing;
import jks.yummy.game.drawing.Vars.GVars_Drawing;
import jks.yummy.game.model.DrawingModel;
import jks.yummy.game.model.PassOverButton;
import jks.yummy.game.score.Controler_Score;
import jks.yummy.game.score.Utils_Scoring;
import jks.yummy.game.sounds.Controler_Music;
import jks.yummy.game.sounds.Controler_SoundEffect;
import jks.yummy.interfaces.GVars_Interface;
import jks.yummy.sounds.effect.Index_Sound;

public class Section_Drawing extends Table
{

	public PassOverButton currentlySelected ; 
	public PassOverButton currentTarget ; 
	
	public ShapeRenderer render_ShapeLine;
	public ShapeRenderer render_drawAssit;
	
	DrawingModel currentDrawing ; 
	int currentObjective = 0; 
	
	public ArrayList<Actor> currentLines ;
	public HashMap<Vector2,PassOverButton> buttonList ; 
	
	Table isDrawOn ; 
	
	public boolean comeFromClick ; 
	public boolean currentDrawIsWon ;
	
	Image resultImage ; 
	float currentVisibilityOnResult ; 

	int soundsConteur ; 
	boolean conteurUp ; 
	
	int lookingAt = 0; 
	ArrayList<DrawingModel> modelList ;
	
	public Section_Drawing(ArrayList<DrawingModel> modelList) 
	{
		super(GVars_Interface.baseSkin) ; 
		this.setLayoutEnabled(false);
		float height = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * FVars_Game.decalDrawingY) ;
		this.setSize(height, height);
		GVars_Drawing.setDiff(height,height);
		
		this.setPosition(decalX, decalY);
		
		if(GVars_Game.runningOnAndroid)
			setComeFromClick(true);
		
		Image image = new Image(Utils_Interface.buildTextureRegion("moderneArt.png")) ; 
		image.setColor(1,1,1,0.5f);
		image.setSize(this.getWidth(), this.getHeight());
		render_ShapeLine = new ShapeRenderer() ;
		render_drawAssit = new ShapeRenderer() ;
		this.modelList = modelList ; 
		
		this.add(image);
		
		
		Controler_SoundEffect.setCurrentInstrument(Index_Sound.getPing()) ; 
		Controler_Music.play("sound/music/tune1.mp3");
	}
	

	public void nextImage() 
	{
		read(modelList.get(lookingAt)) ;
		lookingAt ++ ; 	
	}
	
	
	public void read(DrawingModel toDraw) 
	{	
		resetValues() ; 
		resetVisualValue() ;
		this.currentDrawing = toDraw ; 
		initTable() ; 
		addButtons() ; 
		
		currentTarget = buttonList.get(toDraw.drawingList.get(0)) ; 
		currentlySelected = currentTarget ; 
		currentTarget.addAction(Index_ActorAction.action_GrowingByWidth(buttonSizeAtFull,GVars_Drawing.growingSpeed)) ; 
		
		drawingAssit = true ;
		
		this.add(isDrawOn) ;
//		GVars_Game.scoring.newDrawing() ;
	}
	
	public void resetValues()
	{
		if(isDrawOn != null)
			isDrawOn.clearChildren();
			
		currentObjective = 0 ;
		currentLines = new ArrayList<Actor>() ; 
		buttonList = new HashMap<Vector2, PassOverButton>() ;	
		currentlySelected = null ; 
		currentTarget = null ; 
		drawingAssit = false ; 
	}
	
	public void resetVisualValue()
	{
		this.removeActor(resultImage) ;
		resultImage = null ;
		currentVisibilityOnResult = FVars_Drawing.startImageVisibility; 
		lineColor = new Color(1,1,1,1) ;
		currentDrawIsWon = false ; 
		new PassOverButton().setColor(Color.WHITE) ;
	}
	
	public void valueSucces(PassOverButton returningButton)
	{
		Utils_Scoring.connectionSucces(GVars_Game.section_scoring);
		
	
		if(currentlySelected != null)
			connecting(returningButton) ;
		
		
		currentObjective ++ ; 
		
		if(currentObjective >= currentDrawing.drawingList.size())
		{wonDrawing() ; return ;}
		
		if(currentDrawing.drawingList.size() == currentObjective)
			return ; 
		
		
		currentlySelected = returningButton ;
		
		currentTarget = buttonList.get(currentDrawing.drawingList.get(currentObjective)) ; 	
		
		shiftSize() ; 
		
		Controler_SoundEffect.play() ; 
	}
	
	public void shiftSize()
	{
		if(currentlySelected == null || currentTarget == null)
			return ; 
		
		if(comeFromClick)
		{
			Utils_Actor.removeAllAction(currentlySelected);
			currentlySelected.addAction(Index_ActorAction.action_ReduceByWidth(buttonSize,GVars_Drawing.growingSpeed));
			currentTarget.addAction(Index_ActorAction.action_GrowingByWidth(buttonSizeAtFull,GVars_Drawing.growingSpeed));
		}
		else
		{
			Utils_Actor.removeAllAction(currentTarget);
			currentTarget.addAction(Index_ActorAction.action_ReduceByWidth(buttonSize,GVars_Drawing.growingSpeed));
			currentlySelected.addAction(Index_ActorAction.action_GrowingByWidth(buttonSizeAtFull,GVars_Drawing.growingSpeed));
		}
		
	}
	
	void playSound() 
	{
		Controler_SoundEffect.play() ; 
	}
	
	private void wonDrawing() 
	{
		resultImage = DrawingSection_Draw.buildDrawImage(currentDrawing.imagePath, isDrawOn);
		isDrawOn.add(resultImage);
		currentDrawIsWon = true ;  
		Controler_SoundEffect.play() ; 
		Controler_Score.wonDrawing();
		Index_Sound.playCheer();
	}
	

	
	public void initTable()
	{
		isDrawOn = new Table(GVars_Interface.baseSkin) ;
		isDrawOn.setSize(this.getWidth(), this.getHeight());
		isDrawOn.setLayoutEnabled(false);	
	}
	
	public void addButtons()
	{
		Vector2 selecting ;
		for(int a = 0 ; a < currentDrawing.drawingList.size() ; a++)
		{
			selecting =  currentDrawing.drawingList.get(a) ; 
		
			if(buttonList.containsKey(selecting)) 
				continue ; 
				
			PassOverButton button = DrawingSection_Utils.buildButton(selecting, this) ;
			buttonList.put(selecting, button) ;
			isDrawOn.add(button) ;
		}
	}
	
	public boolean checkIfIsTarget(Vector2 pos)
	{
		if(currentObjective >= currentDrawing.drawingList.size())
			return false ; 
		
		return currentDrawing.drawingList.get(currentObjective).equals(pos) ; 
	}
	
	boolean checkIfStarting(Vector2 pos) 
	{return currentlySelected.refPos.equals(pos);}
	
	public void connecting(Actor endAt)
	{
		currentLines.add(currentlySelected) ;
		currentLines.add(endAt) ; 
	}
	

	public void drawTheDrawing(float delta) 
	{
		if(currentDrawing == null)
			return ;
			
		DrawingSection_Draw.drawLines_Player(render_ShapeLine, currentLines);
		
		if(drawingAssit && currentDrawing.drawingList.size() > currentObjective)
			DrawingSection_Draw.drawAssit(this,delta) ; 
		
		
		if(currentDrawIsWon)
		{
			DrawingSection_Draw.endDrawGestion(this, delta);
		}
		else
		{
		}
	}
	
	public void setComeFromClick(boolean value)
	{comeFromClick = value ;}
	

	public void gameIsWon()
	{
		resetValues() ; 
		resetVisualValue() ;
	}
}
