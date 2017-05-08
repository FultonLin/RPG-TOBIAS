package idk;

import java.util.ArrayList;

import guiPractice.Screen;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class MapScreen extends Screen implements Runnable{

	private Entity[][] grid;
	private TextLabel label;
	
	public MapScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initObjects(ArrayList<Visible> ViewObjects) {
//		ViewObjects.add(new Yuusha(getHeight(), getWidth());
	}

	private void changeText(String s) {
		try {
			label.setText(s);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
