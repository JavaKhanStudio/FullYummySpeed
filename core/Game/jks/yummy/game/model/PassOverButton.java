package jks.yummy.game.model;

import static jks.yummy.game.drawing.Vars.GVars_Drawing.buttonSize;
import static jks.yummy.game.drawing.Vars.GVars_Drawing.decalX;
import static jks.yummy.game.drawing.Vars.GVars_Drawing.decalY;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import jks.index.Index_Image;
import jks.yummy.game.drawing.Vars.GVars_Drawing;

public class PassOverButton extends ImageButton
{

	public Vector2 refPos ;
	public Vector2 actualPos ; 
	
	public PassOverButton(Vector2 pos) 
	{
		super(Index_Image.buttonLook);
		this.refPos = pos ;	
		actualPos = new Vector2(pos.x * GVars_Drawing.diffWidth, pos.y * GVars_Drawing.diffWidth) ; 
//		actualPos.add(GVars_Drawing.decalX, GVars_Drawing.decalY) ;
		
		this.setSize(buttonSize, buttonSize);
		this.setPosition(actualPos.x, actualPos.y);
	}
	public PassOverButton() 
	{super(Index_Image.buttonLook);}
	

}
