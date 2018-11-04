package jks.index;

import com.badlogic.gdx.scenes.scene2d.Action;

public class Index_ActorAction 
{

	
	public static Action action_GrowingByWidth(final float Until, final float speed) 
	{
		return new Action() 
		{
			float until = Until ; 
			@Override
			public boolean act(float delta) 
			{
				float currentWidth = this.getActor().getWidth(); 
				float currentSpeed = delta * speed ;
				float currentIncrease = currentWidth + (delta * speed) ;
				
				if(currentWidth < until) 
				{
					this.getActor().setWidth(currentIncrease);
					this.getActor().setHeight(currentIncrease);
					
					this.getActor().moveBy(-(currentSpeed/2), -(currentSpeed/2));
				}
				else
				{
					this.getActor().removeAction(this);
				}
				return false;
			}
			
		} ; 
	}
	
	public static Action action_ReduceByWidth(final float Until, final float speed) 
	{
		return new Action() 
		{
			float until = Until ; 
			@Override
			public boolean act(float delta) 
			{
				float currentWidth = this.getActor().getWidth(); 
				float currentSpeed = delta * speed ;
				float currentIncrease = currentWidth - (delta * speed) ;
				
				if(currentWidth > until) 
				{
					this.getActor().setWidth(currentIncrease);
					this.getActor().setHeight(currentIncrease);
					
					this.getActor().moveBy((currentSpeed/2), (currentSpeed/2));
				}
				else
				{
					this.getActor().removeAction(this);
				}
				return false;
			}
			
		} ; 
	}
	
	
}
