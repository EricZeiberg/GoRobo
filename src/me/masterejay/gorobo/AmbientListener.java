package me.masterejay.gorobo;

import com.darkprograms.speech.microphone.MicrophoneAnalyzer;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;

import javax.sound.sampled.AudioFileFormat;
import java.io.File;

/**
 * @author MasterEjay
 */
public class AmbientListener{

	public static void ambientListening(){
		MicrophoneAnalyzer mic = new MicrophoneAnalyzer(AudioFileFormat.Type.WAVE);
		mic.setAudioFile(new File("AudioTestNow.wav"));
		while(true){
			mic.open();
			final int THRESHOLD = 8;
			int volume = mic.getAudioVolume();
			boolean isSpeaking = (volume > THRESHOLD);
			if(isSpeaking){
				try {
					System.out.println("RECORDING...");
					mic.captureAudioToFile(mic.getAudioFile());
					do{
						Thread.sleep(1000);//Updates every second
					}
					while(mic.getAudioVolume() > THRESHOLD);
					System.out.println("Recording Complete!");
					System.out.println("Recognizing...");
					Recognizer rec = new Recognizer(Recognizer.Languages.AUTO_DETECT);
					GoogleResponse response = rec.getRecognizedDataForWave(mic.getAudioFile(), 3);
					displayResponse(response);
					System.out.println("Looping back");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error Occured");
				}
				finally{
					mic.close();
				}
			}
		}
	}

	private static void displayResponse(GoogleResponse gr){
		if(gr.getResponse() == null){
			System.out.println((String)null);
			return;
		}
		System.out.println("Google Response: " + gr.getResponse());
		System.out.println("Google is " + Double.parseDouble(gr.getConfidence())*100 + "% confident in"
				+ " the reply");
		System.out.println("Other Possible responses are: ");
		for(String s: gr.getOtherPossibleResponses()){
			System.out.println("\t" + s);
		}
		if (gr.getResponse() != null){
			ModuleHandler.moduleExecute(gr.getResponse());
		}
	}

}