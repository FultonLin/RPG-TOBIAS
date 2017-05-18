package rpg;

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
	Image chestone;
	Image chesttwo;
	Image chestthree;
	Image chestfour;
	
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
		background = new Image("resources/testingmap.jpg");
		
		Image[] walkUp = {new Image("resources/yuushaup.png"), new Image("resources/yuushaup.png")};
		Image[] walkDown = {new Image("resources/yuushadown.png"), new Image("resources/yuushadown.png")};
		Image[] walkLeft = {new Image("resources/yuushaleft.png"), new Image("resources/yuushaleft.png")};
		Image[] walkRight = {new Image("resources/yuusharight.png"), new Image("resources/yuusharight.png")};
		moveUp = new Animation(walkUp,animationFrame,false);
		moveDown = new Animation(walkDown,animationFrame,false);
		moveLeft = new Animation(walkLeft,animationFrame,false);
		moveRight = new Animation(walkRight,animationFrame,false);
		yuusha = moveDown;
		
		chestone = new Image("resources/chestclose.png");
		chesttwo = new Image("resources/chestclose.png");
		chestthree = new Image("resources/chestclose.png");
		chestfour = new Image("resources/chestclose.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(yuushaX,yuushaY);
		yuusha.draw(startX,startY);		
		g.drawString("X: "+yuushaX+"\nY: "+yuushaY,600,10);//hero coordinates
		chestone.draw(yuushaX+175,yuushaY+400);
		chesttwo.draw(yuushaX+15,yuushaY+900);
		chestthree.draw(yuushaX+755,yuushaY+1030);
		chestfour.draw(yuushaX+1520,yuushaY+840);
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP) && chest3 == true){
			yuusha = moveUp;
			yuushaY += a*.2f;
			if(input.isKeyDown(Input.KEY_LSHIFT) && chest4 == true){
				yuushaY += a*.202f;
			}
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			yuusha = moveDown;
			yuushaY -= a*.2f;
			if(input.isKeyDown(Input.KEY_LSHIFT) && chest4 == true){
				yuushaY -= a*.202f;
			}
		}
		if(input.isKeyDown(Input.KEY_LEFT) && chest1 == true){
			yuusha = moveLeft;
			yuushaX += a*.2f;
			if(input.isKeyDown(Input.KEY_LSHIFT) && chest4 == true){
				yuushaX += a*.202f;
			}
		}
		if(input.isKeyDown(Input.KEY_RIGHT) && chest2 == true){
			yuusha = moveRight;
			yuushaX -= a*.2f;
			if(input.isKeyDown(Input.KEY_LSHIFT) && chest4 == true){
				yuushaX -= a*.202f;
			}
		}
		
		if(input.isKeyDown(Input.KEY_SPACE)){
			openChest(yuushaX,yuushaY);
		}		
	}

	private void openChest(float X, float Y) throws SlickException {
		if(X>-10 && X<10 && Y>-100 && Y<-50){
			if(chest1 != true){
				System.out.println("You have unlocked the LEFT movement");
			}
			chest1 = true;
			chestone = new Image("resources/chestopen.png");						
		}
		
		if(X>150 && X<180 && Y>-610 && Y<-550){
			if(chest2 != true){
				System.out.println("You have unlocked the RIGHT movement");
			}
			chest2 = true;
			chesttwo = new Image("resources/chestopen.png");
		}
		
		if(X>-600 && X<-540 && Y>-710 && Y<-665){
			if(chest3 != true){
				System.out.println("You have unlocked the UP movement");
			}
			chest3 = true;
			chestthree = new Image("resources/chestopen.png");
		}
		
		if(X>-1380 && X<-1300 && Y>-530 && Y<-470){
			if(chest4 != true){
				System.out.println("You have unlocked RUN. Hold SHIFT to move faster");
			}
			chest4 = true;
			chestfour = new Image("resources/chestopen.png");
		}
	}

	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}