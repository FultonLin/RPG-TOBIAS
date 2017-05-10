package Rpg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;


public class RPG extends StateBasedGame{

	public static final String gameName = "SlickRun";
	public static final int map = 0;
	public static final int menu = 1;
	
	public RPG(String gameName) {
		super(gameName);
		this.addState(new Map(map));
		this.addState(new Menu(menu));
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
