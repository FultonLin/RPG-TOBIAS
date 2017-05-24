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
	private boolean starting;
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
		Display.setInitialBackground( 0,  0,  0);
		font.drawString(425, 200, "Play Game", Color.white);
		font.drawString(425, 300, "Quit Game", Color.white);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouse = "x: " + xpos + " y: " + ypos; 
		if(xpos > 425 && xpos < 425+font.getWidth("Play Game") && ypos > 400-font.getHeight("Play Game") && ypos < 400){
			if(input.isMouseButtonDown(0)){
				sbg.enterState(1);
			}
		}
		if(xpos > 425 && xpos < 425+font.getWidth("Quit Game") && ypos > 300-font.getHeight("Quit Game") && ypos < 300){
			if(input.isMouseButtonDown(0)){
				System.exit(0);
			}
		}
		if(starting = true){
			thingy = "Resume Game";
		}
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void setStarting(boolean starting) {
		this.starting = starting;
	}
	

}
