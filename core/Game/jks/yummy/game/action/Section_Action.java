package jks.yummy.game.action;

import static jks.yummy.game.GVars_Game.batch;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import jks.animation2d.Enum_AnimState;
import jks.animation2d.SpriteModel;
import jks.animation2d.SpriteModelData;
import jks.tools2d.parallax.ParallaxBackground;
import jks.tools2d.parallax.Utils_Parralax;
import jks.yummy.game.FVars_Game;
import jks.yummy.game.GVars_Game;
import jks.yummy.game.drawing.Vars.GVars_Drawing;
import jks.yummy.game.score.FVars_Scoring;
import jks.yummy.interfaces.GVars_Interface;

public class Section_Action extends Table
{
	
	ParallaxBackground parallaxBackground ;
	OrthographicCamera worldCamera ;
	OrthographicCamera persoCamera ;
	
	SpriteModel character ; 
	
	ShapeRenderer shape ; 
	float shapeSizeX ;
	float shapeSizeY ;
	float groundLvl ; 
	float currentSpeed ; 
	
	boolean jumping = false ; 
	float currentHeight = 0 ; 
	float sinergy = 0;
	public static final float gravity = 400 ;
	public static float jumpForce = 350 ;
	float deplacementY ; 
	
	ArrayList<Obstacle> listObstacle ; 
	
	
	float distanceParcouru ;
	public static final int distanceMetric = 100 ; 
	public static final int distanceAParcourir = 500 ; 
	
	public static int obstacle_Probability = 100; 
	public static int obstacle_MinDistance ; 
	public static int obstacle_MinSpeed ; 
	public static float currentDistanceObstacle ; 
	
	ProgressBar barDistance ; 
	
	
	
	public Section_Action() 
	{
		super(GVars_Interface.baseSkin) ; 
		this.setLayoutEnabled(false);
		
		float height = Gdx.graphics.getHeight() ;
		this.setSize(Gdx.graphics.getWidth(), height);
		
		float worldWidth = Gdx.graphics.getWidth() ;
		float worldHeight = Utils_Parralax.calculateOtherDimension(true, worldWidth, this.getWidth(), this.getHeight());
		
		parallaxBackground = new ParallaxBackground();
    	parallaxBackground.addLayers(Index_BuildBackGround.createLayers_Day("day/Day.atlas", worldWidth, worldHeight,1.5f));
    	
    	
    	worldCamera = new OrthographicCamera();
    	worldCamera.setToOrtho(false,this.getWidth(), this.getHeight());
 	    worldCamera.position.add(50000,0,0) ;
    	worldCamera.update();
    	
    	persoCamera = new OrthographicCamera();
    	persoCamera.setToOrtho(false,this.getWidth(), this.getHeight());
    	persoCamera.position.add(0,0,0) ;
    	persoCamera.update();
    	
    	
    	character = new SpriteModel(SpriteModelData.getDoggy(0.85f * (this.getHeight()/500))) ; 
    	character.reverse(true);
    	character.changeAnimationState(Enum_AnimState.IDLE, true);
    	
    	groundLvl = worldHeight/6.5f;
    	Vector2 position = new Vector2(this.getWidth()/8.5f,groundLvl) ;
    	character.setPosition(position);

    	
    	shape = new ShapeRenderer() ;
    	shape.setColor( 0.5f,  0.5f, 0.5f, 0.5f);
    	
    	shapeSizeX = (float) (character.currentState.getKeyFrame(1, true).getRegionX()/6);
    	shapeSizeY = shapeSizeX/2 ; 
    	
    	barDistance = new ProgressBar(0, distanceAParcourir, 1, false, GVars_Interface.baseSkin) ;
    	barDistance.setSize(this.getWidth() - this.getWidth()/10, 100);
    	barDistance.setPosition(this.getWidth()/20, GVars_Game.section_drawing.getHeight());
	}
	
	public void drawTheAction(float delta) 
	{
		currentSpeed = GVars_Game.getCurrentSpeed() * delta ; 
		
		if(!GVars_Game.gameIsWon)
			worldCamera.position.add(currentSpeed, 0, 0);
		
		if(!GVars_Game.gameIsWon)
			persoCamera.position.add(currentSpeed, 0, 0);
	
		
		drawParallax(delta) ;
		drawCharacter(delta) ;
	}
	
	public void drawCharacter(float delta) 
	{
		persoCamera.update();
		
		batch.begin();
		batch.setProjectionMatrix(persoCamera.combined); 
		
		character.addPositionX(currentSpeed);
		distanceParcouru += currentSpeed ;
		
		barDistance.setValue(distanceParcouru/distanceMetric) ; 
		
		if(barDistance.getValue() >= barDistance.getMaxValue() && !GVars_Game.gameIsWon)
		{
			GVars_Game.gameIsWon = true ;
			GVars_Game.section_drawing.gameIsWon();
			this.add(GVars_Game.timer);
		}
		
		if(jumping) 
		{
			deplacementY = sinergy * delta ;
			if(character.getPosition().y + deplacementY > groundLvl) 
			{
				character.addPositionY(deplacementY);
				sinergy -= gravity * delta ;
			}
			else 
			{
				jumping = false ; 
				character.currentStateName = null ;
				asNewSpeed() ; 	
			}		
		}
		
		character.draw(batch);
		batch.end();
//		shape.begin(ShapeType.Filled);
//		shape.ellipse(sprite.getPosition().x + shapeSizeX/10, sprite.getPosition().y + GVars_Game.drawing.getHeight()/2 + shapeSizeY/1.5f, shapeSizeX, shapeSizeY);
//		shape.end();	
	}
	
	public void drawParallax(float delta)
	{
		batch.begin();
		parallaxBackground.act(delta) ; 
		parallaxBackground.draw(worldCamera, batch);
		batch.end();
	}

	public void asNewSpeed() 
	{
		if(character.currentStateName == Enum_AnimState.JUMP)
		{
			return ; 
		}
			
		if(GVars_Game.currentSpeed == 0) 
		{
			if(character.currentStateName != Enum_AnimState.IDLE)
				character.changeAnimationState(Enum_AnimState.IDLE, true);
		} 
		else if (GVars_Game.currentSpeed < FVars_Scoring.runningCap)
		{
			if(character.currentStateName != Enum_AnimState.WALK)
				character.changeAnimationState(Enum_AnimState.WALK, true);
		}
		else
		{
			if(character.currentStateName != Enum_AnimState.RUN)
				character.changeAnimationState(Enum_AnimState.RUN, true);
		}
	}
	
	public void jump() 
	{
		if(!jumping)
		{
			character.changeAnimationState(Enum_AnimState.JUMP, false);
			jumping = true ; 
			sinergy = jumpForce ; 
		}	
	}
}
