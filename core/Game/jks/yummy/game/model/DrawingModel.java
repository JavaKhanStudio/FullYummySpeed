package jks.yummy.game.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.lang3.SerializationUtils;

import com.badlogic.gdx.math.Vector2;

// TODO RENNOMER UNE FOIS SERIALISATION FAIT PROPREMENT
public class DrawingModel implements Serializable 
{

	private static final long serialVersionUID = 2278651095229609040L;
	
	public ArrayList<Vector2> drawingList ; 
	public String imagePath ;

	public DrawingModel(String imagePath)
	{
		this.imagePath = imagePath ;
		drawingList = new ArrayList<Vector2>() ; 
	}
	
	public void addValue(Vector2 vector)
	{drawingList.add(vector) ;}
	
	public void removeValue(Vector2 vector)
	{drawingList.remove(vector) ;}
	
	public DrawingModel(ArrayList<Vector2> drawingList)
	{this.drawingList = drawingList ;}
	
	//MOCK
	public DrawingModel()
	{
		imagePath = "DrawingImage/cherry.png" ; 
		drawingList = new ArrayList<Vector2>() ; 
		drawingList.add(new Vector2(50,50)) ;
		drawingList.add(new Vector2(50,150)) ;
		drawingList.add(new Vector2(50,250)) ;
	}
	
	public byte[] convertirEnPacket()
	{return SerializationUtils.serialize(this);}
	
	public static DrawingModel readFromPacket(byte[] source)
	{return SerializationUtils.deserialize(source) ;}
}
