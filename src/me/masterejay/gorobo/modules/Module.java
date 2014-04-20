package me.masterejay.gorobo.modules;

import java.util.List;

/**
 * @author MasterEjay
 */
public interface Module {

	public String getDefault();
	public List<String> getAlternatives();
	public String getName();
	public String getDescription();
	public void execute();
}
