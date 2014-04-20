package me.masterejay.gorobo;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.microphone.MicrophoneAnalyzer;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;
import javaFlacEncoder.FLACFileWriter;

import javax.sound.sampled.AudioFileFormat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author MasterEjay
 */
public class AmbientListener{

	private static final  MicrophoneAnalyzer microphoneAnalyzer = new MicrophoneAnalyzer(AudioFileFormat.Type.WAVE);

	public static void ambientListening() throws Exception{
		Microphone mic = new Microphone(FLACFileWriter.FLAC);
		String filename = "test.wav";
		File file = new File("testfile.flac");//Name your file whatever you want
    /* User records the voice here. Microphone starts a separate thread so do whatever you want
     * in the mean time. Show a recording icon or whatever.
     */
		microphoneAnalyzer.open();
		microphoneAnalyzer.captureAudioToFile(filename);
		int THRESHOLD = 7;
		System.out.println(microphoneAnalyzer.getAudioVolume());
		boolean isSpeaking;
			if (microphoneAnalyzer.getAudioVolume() >= THRESHOLD ){
				System.out.println("Recording...");
				isSpeaking = true;
				while(isSpeaking){
					mic.captureAudioToFile(file);
					if (microphoneAnalyzer.getAudioVolume() <= THRESHOLD){
						isSpeaking = false;
						mic.close();
						System.out.println("Recording stopped.");
						Recognizer recognizer = new Recognizer(Recognizer.Languages.ENGLISH_US);//Specify your language here.
						//Although auto-detect is avalible, it is recommended you select your region for added accuracy.
						try {
							int maxNumOfResponses = 4;
							GoogleResponse response = recognizer.getRecognizedDataForFlac(file, maxNumOfResponses);
							System.out.println("Google Response: " + response.getResponse());
							System.out.println("Google is " + Double.parseDouble(response.getConfidence())*100 + "% confident in"
									+ " the reply");
							System.out.println("Other Possible responses are: ");
							for(String s: response.getOtherPossibleResponses()){
								System.out.println("\t" + s);
							}
							if (response.getResponse() != null){
								ModuleHandler.moduleExecute(response.getResponse());
							}
						} catch (Exception ex) {
							// TODO Handle how to respond if Google cannot be contacted
							System.out.println("ERROR: Google cannot be contacted");
							ex.printStackTrace();
						}

						file.deleteOnExit();//Deletes the file as it is no longer necessary.
						ambientListening();
					}
				}
			}
			else {
				Thread.sleep(100);
				ambientListening();
			}
		}
	}
