package jks.index;

import java.util.ArrayList;
import java.util.Collections;

import jks.yummy.game.drawing.DrawingSection_Utils;
import jks.yummy.game.model.DrawingModel;

public class Index_Drawing 
{

	public static ArrayList<DrawingModel> getTestList()
	{
		String startAt = "fruit/" ;
		ArrayList<DrawingModel> returningList = new ArrayList<DrawingModel>() ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "cerise")) ;
		return returningList ;
	}
	
	public static ArrayList<DrawingModel> getFruitList()
	{
		String startAt = "fruit/" ;
		ArrayList<DrawingModel> returningList = new ArrayList<DrawingModel>() ;
		
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "banana")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "cerise")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "citrus")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "fraise")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "melon")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "pineapple")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "poire")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "pomme")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "prune")) ;
				
		Collections.shuffle(returningList);
		
		return returningList ;
	}
	
	public static ArrayList<DrawingModel> getLegumeList()
	{
		String startAt = "legume/" ;
		ArrayList<DrawingModel> returningList = new ArrayList<DrawingModel>() ;
		
		
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "carrot")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "cornichon")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "oignion")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "poivron")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "radi")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "tomate")) ;
		returningList.add(DrawingSection_Utils.getDrawing(startAt + "truc")) ;
		
		Collections.shuffle(returningList);
		
		return returningList ; 
	}
	
	public static ArrayList<DrawingModel> getChallangeList()
	{
		ArrayList<DrawingModel> returningList = new ArrayList<DrawingModel>() ;
		returningList.addAll(getLegumeList());
		returningList.addAll(getFruitList());
		Collections.shuffle(returningList);
		
		return returningList ; 
	}
	
}
