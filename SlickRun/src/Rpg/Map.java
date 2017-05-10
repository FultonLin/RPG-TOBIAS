package Rpg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Map extends BasicGameState{
	
	Image hero;
	int heroX = 100;
	int heroY = 100;
	
	public Map(int state){
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		hero = new Image("resources/Teemo.jpg");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(hero,heroX,heroY);
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP)){
			heroY -= 1;
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			heroY += 1;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			heroX -= 1;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
			heroX += 1;
		}	
	}

	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
