package jks.animation2d;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class SpriteModel extends AnimationModel
{

	SpriteModelData ref ;
	
	public Vector2 velocity = new Vector2();
	public Animation<TextureRegion> currentState ; 
	TextureRegion currentFrame ; 
	public Enum_AnimState currentStateName ; 
	
	Random random = new Random() ; 

	public SpriteModel(SpriteModelData index)
	{
		ref = index ;
	}
	public SpriteModel(SpriteModelData index, Vector2 putAt)
	{
		ref = index ;
		position.set(putAt) ; 
//		position.set(putAt.x * FVars_GlobalData.caseSize, putAt.y * FVars_GlobalData.caseSize);
	}
	

	@Override
	public void draw(Batch batch) 
	{
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		
//		if(currentState.isAnimationFinished(stateTime) && currentState.getPlayMode() == PlayMode.NORMAL)
//			postAnimation() ; 
			
		currentFrame = currentState.getKeyFrame(stateTime, false);
		batch.draw(currentFrame, position.x + (reverse ? 0 : currentFrame.getRegionWidth() * ref.scale), position.y, currentFrame.getRegionWidth() * ref.scale * (reverse ? 1 : - 1) ,currentFrame.getRegionHeight() * ref.scale ); 	
		
	
	}
	
//	if(lookRight && currentFrame.isFlipX())
//	{currentFrame.flip(lookRight, false);}
	
	public void changeAnimationState(Enum_AnimState state, boolean repeat)
	{
		currentState = ref.animationList.get(state) ;
		if(currentState == null)
		{
			currentState = ref.animationList.values().iterator().next() ;
			System.out.println("Impossible de trouver l'animation - " + state.toString() +  " - dans SpriteModel");
		}
		currentStateName = state  ;

		if(repeat)
			currentState.setPlayMode(PlayMode.LOOP);
		else
			currentState.setPlayMode(PlayMode.NORMAL);
		
		
		
//		if(atRandom)
//			stateTime = random.nextFloat() * currentState.getAnimationDuration() ;
	}

	@Override
	public void applyMovement() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector2 getVelocity()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2 getPosition()
	{return position;}
	
	public void postAnimation()
	{}
	
}
