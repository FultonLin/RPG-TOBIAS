package rpg;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{
	public static final String gamename = "SlickRun";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int battle = 2;
	public static final int inventory = 3;
	
	public Game(String gameName) {
		super(gameName);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		this.addState(new Battle(battle));
		this.addState(new Inventory(inventory));
	}
	 
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(battle).init(gc, this);
		this.getState(inventory).init(gc, this);
		this.enterState(menu);
	}
	
	public static void main(String[] arge){
		try {
			AppGameContainer appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(1024, 600, false);
			appgc.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
