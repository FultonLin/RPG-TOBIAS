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
	Image chest;
	int[] animationFrame = {200,200};
	float yuushaX = 0;
	float yuushaY = 0;
	float startX = yuushaX+157;
	float startY = yuushaY+250;
	boolean quit = false;
	boolean chest1 = false;
	boolean chest2 = false;
	boolean chest3 = false;
	boolean chest4 = false;
	
	public Play(int state) {
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Image[] walkUp = {new Image("resources/yuushaup.png"), new Image("resources/yuushaup.png")};
		Image[] walkDown = {new Image("resources/yuushadown.png"), new Image("resources/yuushadown.png")};
		Image[] walkLeft = {new Image("resources/yuushaleft.png"), new Image("resources/yuushaleft.png")};
		Image[] walkRight = {new Image("resources/yuusharight.png"), new Image("resources/yuusharight.png")};
		moveUp = new Animation(walkUp,animationFrame,false);
		moveDown = new Animation(walkDown,animationFrame,false);
		moveLeft = new Animation(walkLeft,animationFrame,false);
		moveRight = new Animation(walkRight,animationFrame,false);
		yuusha = moveDown;
		
		background = new Image("resources/testingmap.jpg");
		chest = new Image("resources/chestclose.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		yuusha.draw(startX,startY);		
		g.drawString("X: "+yuushaX+"\nY: "+yuushaY,600,10);//hero coordinates
		background.draw(yuushaX,yuushaY);
		chest.draw(yuushaX+100,yuushaY+120);
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP) && chest2 == true){
			yuusha = moveUp;
			yuushaY += a*.2f;
			if(input.isKeyDown(Input.KEY_LSHIFT) && chest4 == true){
				yuushaY += a*.4f;
			}
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			yuusha = moveDown;
			yuushaY -= a*.2f;
			if(input.isKeyDown(Input.KEY_LSHIFT) && chest4 == true){
				yuushaY -= a*.4f;
			}
		}
		if(input.isKeyDown(Input.KEY_LEFT) && chest3 == true){
			yuusha = moveLeft;
			yuushaX += a*.2f;
			if(input.isKeyDown(Input.KEY_LSHIFT) && chest4 == true){
				yuushaX += a*.4f;
			}
		}
		if(input.isKeyDown(Input.KEY_RIGHT) && chest1 == true){
			yuusha = moveRight;
			yuushaX -= a*.2f;
			if(input.isKeyDown(Input.KEY_LSHIFT) && chest4 == true){
				yuushaX -= a*.4f;
			}
		}
		
		if(input.isKeyDown(Input.KEY_SPACE)){
			openChest(yuushaX,yuushaY);
			chest = new Image("resources/chestopen.png");
		}		
	}

	private void openChest(float X, float Y) {
		if(X>100 && X<120 && Y>100 && Y<120){
			chest1 = true;
			System.out.println("You have unlocked the RIGHT movement");
		}
		
		if(X>100 && X<120 && Y>100 && Y<120){
			chest2 = true;
			System.out.println("You have unlocked the UP movement");
		}
		
		if(X>100 && X<120 && Y>100 && Y<120){
			chest3 = true;
			System.out.println("You have unlocked the LEFT movement");
		}
		
		if(X>100 && X<120 && Y>100 && Y<120){
			chest4 = true;
			System.out.println("You have unlocked RUN. Hold SHIFT to move faster");
		}
	}

	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}
