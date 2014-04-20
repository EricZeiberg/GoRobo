package me.masterejay.gorobo.utils;

import com.darkprograms.speech.synthesiser.Synthesiser;
import com.gtranslate.Audio;
import com.gtranslate.Language;
import javazoom.jl.decoder.JavaLayerException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author MasterEjay
 */
public class TTSUtil {

	public static void talk(String text){
		Audio audio = Audio.getInstance();
		InputStream sound  = null;
		try{
			sound = audio.getAudio(text, Language.ENGLISH);
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			audio.play(sound);
		}catch(JavaLayerException e){
			e.printStackTrace();
		}
	}
}
