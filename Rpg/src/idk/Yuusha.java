package idk;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import guiPractice.Screen;
import guiPractice.components.Action;
import guiPractice.components.Graphic;
import guiPractice.components.Visible;

public class Yuusha extends Entity implements KeyListener{	
	
	public Action action;
	private Graphic yuusha;

	public Yuusha(int width, int height) {
		super(width, height);
	}
	
	public void initObjects(ArrayList<Visible> viewObjects) {
		yuusha = new Graphic(450,40,.4,"resources/sampleImages/Teemo.jpg");
		viewObjects.add(yuusha);
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key pressed");
            yuusha.setX(yuusha.getX()+10);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
            yuusha.setX(yuusha.getX()-10);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Up key pressed");
            yuusha.setX(yuusha.getY()+10);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key pressed");
            yuusha.setX(yuusha.getY()-10);
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
