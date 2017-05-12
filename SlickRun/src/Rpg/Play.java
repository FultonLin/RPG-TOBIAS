package Rpg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import idk.Animation;

public class Play extends BasicGameState{
	Image hero;
	int heroX = 100;
	int heroY = 100;
	Animation yuusha,moveUp,moveDown,moveLeft,moveRight;
	int[] animationFrame = {200,200};
	double yuushaPositionX = 0;
	double yuushaPositionY = 0;
	double shiftX = yuushaPositionX+100;
	double shiftY = yuushaPositionY+100;
	
	 
	public Play(int state) {
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		hero = new Image("resources/Teemo.jpg");
		Image[] walkUp = {new Image(""), new Image("")};
		Image[] walkDown = {new Image(""), new Image("")};
		Image[] walkLeft = {new Image(""), new Image("")};
		Image[] walkRight = {new Image(""), new Image("")};
		
		movingUp = new Animation(walkUp,animationFrame,false);
		movingDown = new Animation(walkDown,animationFrame,false);
		movingLeft = new Animation(walkLeft,animationFrame,false);
		movingRight = new Animation(walkRight,animationFrame,false);
		hero = movingDown;
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
		return 1;
	}
}
