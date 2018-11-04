package jks.yummy.sounds.effect;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Index_Sound 
{
	public static final String path = "sound/" ;
	public static final String pathPiano = path + "piano/piano_" ; 
	public static final String pathPing = path + "ping/ping" ; 
	public static final String ext = ".wav" ; 
	
	public static Sound cheer ; 
	public static Sound miss ; 
	
	
	public static ArrayList<Sound> getPiano() 
	{
		ArrayList<Sound> piano = new ArrayList<Sound>() ; 
		for(int a = 64; a >= 40 ; a --) 
		{
			piano.add(Gdx.audio.newSound(Gdx.files.internal(pathPiano + a + ext)));
		}
		return piano ; 
	}
	
	public static ArrayList<Sound> getPing() 
	{
		ArrayList<Sound> ping = new ArrayList<Sound>() ; 
		ping.add(Gdx.audio.newSound(Gdx.files.internal(pathPing + 3 + ext))) ; 
		ping.add(Gdx.audio.newSound(Gdx.files.internal(pathPing + 3 + ext))) ; 
		return ping ; 
	}
	
	public static void playCheer()
	{
		if(cheer == null)
			cheer = Gdx.audio.newSound(Gdx.files.internal("sound/cheer.wav")) ; 
		
		cheer.play(0.5f, 1.2f, 1) ; 
	}
	
	public static void playMiss()
	{
		if(miss == null)
			miss = Gdx.audio.newSound(Gdx.files.internal("sound/miss.wav")) ; 
		
		miss.play(0.8f, 1.2f, 1) ; 
	}
}
