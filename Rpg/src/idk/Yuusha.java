package idk;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import guiPractice.components.Moving;

public class Yuusha extends Moving implements KeyListener{	

	public Yuusha(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key pressed");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
        }	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
