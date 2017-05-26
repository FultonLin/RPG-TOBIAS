package rpg;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.Drawable;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	 
	Image play;
	Image quit;
	public String mouse;
	private TrueTypeFont font;
	private String thingy;
	
	public Menu(int state){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Font asd = new Font("Helvetica", Font.BOLD, 36);
		font = new TrueTypeFont(asd , true);
		thingy = "Play Game";
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		gc.setShowFPS(false);
		Display.setInitialBackground( 0, 0, 0);
		font.drawString((1024/2)-(font.getWidth(thingy)/2), 200, thingy, Color.white);
		font.drawString((1024/2)-(font.getWidth("Quit Game")/2), 300, "Quit Game", Color.white);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouse = "x: " + xpos + " y: " + ypos; 
		if(xpos > 425 && xpos < 425+font.getWidth(thingy) && ypos > 400-font.getHeight(thingy) && ypos < 400){
			if(input.isMouseButtonDown(0)){
				sbg.enterState(1);
				
				thingy = "Resume Game";
			}
		}
		if(xpos > 425 && xpos < 425+font.getWidth("Quit Game") && ypos > 300-font.getHeight("Quit Game") && ypos < 300){
			if(input.isMouseButtonDown(0)){
				System.exit(0);
			}
		}

	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
