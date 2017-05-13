package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (!e.isControlDown()) {
			GUI.setControlState(false);
			System.out.println("false");
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isControlDown()) {
		    GUI.setControlState(true);
		    System.out.println("true");
		}
	}

}
