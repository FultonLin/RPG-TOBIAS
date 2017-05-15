package Rpg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Animation;

public class Play extends BasicGameState{
	
	Animation yuusha,moveUp,moveDown,moveLeft,moveRight;
	Image background;
	boolean quit = false;
	int[] animationFrame = {200,200};
	float yuushaPositionX = 0;
	float yuushaPositionY = 0;
	float startX = yuushaPositionX+100;
	float startY = yuushaPositionY+100;
	
	public Play(int state) {
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("resources/shop.png");
		Image[] walkUp = {new Image("resources/Potion1.png"), new Image("resources/Potion1.png")};
		Image[] walkDown = {new Image("resources/Potion2.png"), new Image("resources/Potion2.png")};
		Image[] walkLeft = {new Image("resources/Potion3.png"), new Image("resources/Potion3.png")};
		Image[] walkRight = {new Image("resources/Potion4.png"), new Image("resources/Potion4.png")};
		
		moveUp = new Animation(walkUp,animationFrame,false);
		moveDown = new Animation(walkDown,animationFrame,false);
		moveLeft = new Animation(walkLeft,animationFrame,false);
		moveRight = new Animation(walkRight,animationFrame,false);
		yuusha = moveDown;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(yuushaPositionX,yuushaPositionY);
		yuusha.draw(startX,startY);		
		g.drawString("X: "+yuushaPositionX+"\nY: "+yuushaPositionY,400,200);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP)){
			yuusha = moveUp;
			yuushaPositionY += a*.1f;
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			yuusha = moveDown;
			yuushaPositionY -= a*.1f;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			yuusha = moveLeft;
			yuushaPositionX += a*.1f;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
			yuusha = moveRight;
			yuushaPositionX -= a*.1f;
		}	
	}

	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}
