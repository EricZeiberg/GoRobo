package me.masterejay.gorobo;

import javax.swing.*;

/**
 * @author MasterEjay
 */
public class Main {

	static String TRIGGER = "Robot";

	public static void main(String[] args){
		GUI goRobo = new GUI();
		goRobo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		goRobo.setSize(275, 180);
		goRobo.setVisible(true);
		ModuleHandler.buildModules();
		try{
			AmbientListener.ambientListening();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static String getTrigger(){
		return TRIGGER;
	}
}
