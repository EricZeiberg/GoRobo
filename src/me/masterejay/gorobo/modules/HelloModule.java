package me.masterejay.gorobo.modules;

import me.masterejay.gorobo.utils.TTSUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MasterEjay
 */
public class HelloModule implements Module {

	@Override
	public String getDefault(){
		return "Hello";
	}

	@Override
	public List<String> getAlternatives(){
		List<String> alternatives = new ArrayList<String>();
		alternatives.add("Hello Robot");
		return alternatives;
	}

	@Override
	public String getName(){
		return "Hello Module";
	}

	@Override
	public String getDescription(){
		return "A module that says hello to you";
	}

	@Override
	public void execute(){
		TTSUtil.talk("Hello!");
	}
}
