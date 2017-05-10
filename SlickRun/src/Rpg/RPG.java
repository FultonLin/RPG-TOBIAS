package Rpg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;


public class RPG extends StateBasedGame{

	public static final String gameName = "SlickRun";
	public static final int menu = 0;
	public static final int map = 1;
	
	public RPG(String gameName) {
		super(gameName);
		this.addState(new Menu(menu));
		this.addState(new Map(map));
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc,this);
		this.getState(nap).inti(gc,this);
	}
}
