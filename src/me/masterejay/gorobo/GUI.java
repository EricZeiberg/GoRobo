package me.masterejay.gorobo;

import javax.swing.*;
import java.awt.*;

/**
 * @author MasterEjay
 */
public class GUI extends JFrame {

	private JLabel listening;

	public GUI(){
		super("GoRobo");
		setLayout(new FlowLayout());
		listening = new JLabel("Listening...");
		add(listening);
	}
}
