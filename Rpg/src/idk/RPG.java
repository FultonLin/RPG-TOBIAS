package idk;

import guiPractice.GUIApplication;

public class RPG extends GUIApplication{

	public RPG() {
		RPG game = new RPG();
		Thread app = new Thread(game);
		app.start();
	}
	
	protected void initScreen() {
		MapScreen s = new MapScreen(getWidth(), getHeight());
		setScreen(s);
	}
}
