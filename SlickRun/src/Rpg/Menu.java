package Rpg;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	 
	public Menu(int state){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.fillRoundRect(300,300, 200, 100, 25);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		if(xpos > 300 && xpos < 500 && ypos > 300 && ypos < 500){
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
