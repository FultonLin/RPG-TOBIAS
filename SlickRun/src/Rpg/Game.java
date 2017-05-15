package Rpg;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{
	public static final String gamename = "SlickRun";
	public static final int menu = 0;
	public static final int play = 1;
	
	//this may be needed if we go turn based battle instead of world map battle
	public static final int battle = 2;
	
	public Game(String gameName) {
		super(gameName);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
	}
	 
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(play);
	}
	
	public static void main(String[] arge){
		try {
			AppGameContainer appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(750, 750, false);
			appgc.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
