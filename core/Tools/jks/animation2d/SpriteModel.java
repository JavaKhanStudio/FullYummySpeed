package jks.animation2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class SpriteModel extends AnimationModel
{

	SpriteModelData ref ;
	
	public Animation<TextureRegion> currentState ; 
	TextureRegion currentFrame ; 
	public Enum_AnimState currentStateName ; 

	public SpriteModel(SpriteModelData index)
	{
		ref = index ;
	}
	

	@Override
	public void draw(Batch batch) 
	{
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		currentFrame = currentState.getKeyFrame(stateTime, false);
		batch.draw(currentFrame, position.x + (reverse ? 0 : currentFrame.getRegionWidth() * ref.scale), position.y, currentFrame.getRegionWidth() * ref.scale * (reverse ? 1 : - 1) ,currentFrame.getRegionHeight() * ref.scale ); 	
		
	
	}
	
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
	}

	@Override
	public Vector2 getPosition()
	{return position;}
	
}
