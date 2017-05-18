package rpg;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	 
	Image play;
	Image quit;
	public String mouse;
	
	public Menu(int state){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		play = new Image("resources/playnow.png");
		quit = new Image("resources/exit.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		play.draw(100,100, (float) .6);
		quit.draw(100, 300);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouse = "x: " + xpos + " y: " + ypos; 
		if(xpos > 100 && xpos < 500 && ypos > 300 && ypos < 500){
			if(input.isMouseButtonDown(0)){
				sbg.enterState(1);
			}
		}
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
