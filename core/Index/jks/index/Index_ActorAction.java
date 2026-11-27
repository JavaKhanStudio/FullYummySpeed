package jks.index;

import com.badlogic.gdx.scenes.scene2d.Action;

public class Index_ActorAction
{

	/** Grows the actor (width and height) around its centre until its width reaches until. */
	public static Action action_GrowingByWidth(final float until, final float speed)
	{return action_ResizeByWidth(until, speed, 1);}

	/** Shrinks the actor (width and height) around its centre until its width reaches until. */
	public static Action action_ReduceByWidth(final float until, final float speed)
	{return action_ResizeByWidth(until, speed, -1);}

	/** direction 1 grows, -1 shrinks; the action removes itself once the width has passed until. */
	private static Action action_ResizeByWidth(final float until, final float speed, final int direction)
	{
		return new Action()
		{
			@Override
			public boolean act(float delta)
			{
				float currentWidth = this.getActor().getWidth();
				float step = direction * delta * speed ;

				if(direction * currentWidth < direction * until)
				{
					this.getActor().setWidth(currentWidth + step);
					this.getActor().setHeight(currentWidth + step);

					this.getActor().moveBy(-(step/2), -(step/2));
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
