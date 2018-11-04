package jks.animation2d;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteModelData 
{
	HashMap<Enum_AnimState,Animation<TextureRegion>> animationList ;
	float scale ;
	
	public SpriteModelData(TextureAtlas textureAtlas, HashMap<Enum_AnimState,String> animationName, float animationSpeed, float Scale)
	{
		animationList = new HashMap<Enum_AnimState,Animation<TextureRegion>>() ;
		
		for(Enum_AnimState finalName : animationName.keySet())
		{
			animationList.put(finalName, new Animation(animationSpeed, textureAtlas.findRegions(animationName.get(finalName)))) ;
		}
	
	
		scale = Scale ; 
	}
	
	public static SpriteModelData getDoggy(float scale)
	{
		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("perso/doggy.atlas"));
		HashMap<Enum_AnimState,String> animationList = new HashMap<Enum_AnimState,String>() ; 
		animationList.put(Enum_AnimState.RUN, "Run") ; 
		animationList.put(Enum_AnimState.DEATH, "Dead") ; 
		animationList.put(Enum_AnimState.JUMP, "Jump") ; 
		animationList.put(Enum_AnimState.HURT, "Hurt") ; 
		animationList.put(Enum_AnimState.IDLE, "Idle") ; 
		animationList.put(Enum_AnimState.WALK, "Walk") ; 
		
		return new SpriteModelData(textureAtlas,animationList,0.06f,scale) ; 
	}
	
}
