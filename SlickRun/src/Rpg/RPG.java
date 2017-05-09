package Rpg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;


public class RPG extends StateBasedGame{

	public static final String GameName = "SlickRun";
	public static final int Map = 0;
	public static final int Menu = 1;
	
	public RPG(String GameName) {
		super(GameName);
		this.addState(new Map(Map));
		this.addState(new Menu(Menu));
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
