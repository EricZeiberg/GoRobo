package me.masterejay.gorobo.utils;

import me.masterejay.gorobo.Main;

/**
 * @author MasterEjay
 */
public class StringUtils {

	public static String stripString(String input){
		String returned = input;
		returned.replace(".", "");
		returned.replace(Main.getTrigger(), "");
		returned.toLowerCase();
		return returned;
	}
}
