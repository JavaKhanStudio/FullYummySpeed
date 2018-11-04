package jks.yummy.game.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Controler_Music 
{

	public static Music currentMusic ;
	public static boolean musicMute = false ; 
	
	public static void play(String musicPath)
	{
		if(!musicMute) 
		{
			currentMusic = Gdx.audio.newMusic(Gdx.files.internal(musicPath));
			currentMusic.play();
		}
		
	}

	public static void reset() 
	{
		if(currentMusic != null)
		{
			currentMusic.stop();
			currentMusic.dispose();
			currentMusic = null ; 
		}	
	}
	
	
	
}
