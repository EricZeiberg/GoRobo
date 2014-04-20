package me.masterejay.gorobo;

import me.masterejay.gorobo.modules.HelloModule;
import me.masterejay.gorobo.modules.Module;
import me.masterejay.gorobo.utils.StringUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MasterEjay
 */
public class ModuleHandler {

	static List<Class<? extends Module>> modules = new ArrayList<>();

	public static void buildModules(){
		modules.add(HelloModule.class);
	}


	public static void moduleExecute(String rawInput){
		String input = StringUtils.stripString(rawInput);
		for (Class<? extends Module> clazz :modules){
			try{
				Module m = clazz.newInstance();
				if (input.equalsIgnoreCase(m.getDefault()) || m.getAlternatives().contains(input)){
				   m.execute();
				}
			}catch(InstantiationException | IllegalAccessException e){
				e.printStackTrace();
			}
		}
	}
}
