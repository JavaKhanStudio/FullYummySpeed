package jks.yummy.game.sounds;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Sound;

public class Controler_SoundEffect 
{

	public static ArrayList<Sound> currentInstrument ;
	public static int soundsConteur = 0 ; 
	public static boolean conteurUp = false ; 
	
	public static void setCurrentInstrument(ArrayList<Sound> soundList) 
	{currentInstrument = soundList  ;}

	public static void play() 
	{
		currentInstrument.get(soundsConteur).play(0.1f, 2f, 1f) ;
		
		if(soundsConteur + 1 > currentInstrument.size() - 1 || soundsConteur == 0)
		{conteurUp = !conteurUp ;}
		
		if(conteurUp)
			soundsConteur += 1 ;
		else
			soundsConteur -= 1 ;	
	}

	public static void reset() 
	{
		currentInstrument = null ; 
		soundsConteur = 0 ; 
		conteurUp = false ; 
	}

}
