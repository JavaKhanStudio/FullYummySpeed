package jks.animation2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public abstract class AnimationModel
{
	
	protected boolean reverse ;
	protected Vector2 position = new Vector2();
	protected float stateTime ;
	
	public void update(float delta) 
	{stateTime += delta ;}
	
	public abstract void applyMovement() ;
	public abstract Vector2 getVelocity() ;
	
	public void reverse(boolean reverse)
	{this.reverse = reverse ;}
	
	public abstract void draw(Batch batch) ; 
	
	
	public Vector2 getPosition() 
	{return position ;}
	
	public void setPosition(Vector2 position) 
	{this.position = position;} 
	
	public void setPosition(float x, float y) 
	{setPositionX(x); setPositionY(y) ;} 
	
	
	public void setPositionX(float position) 
	{this.position.x = position;} 
	
	public void setPositionY(float position) 
	{this.position.y = position;} 
	
	public void addPositionX(float position) 
	{this.position.x += position;} 
	
	public void addPositionY(float position) 
	{this.position.y += position;} 
	
}
