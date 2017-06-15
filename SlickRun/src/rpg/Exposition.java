package rpg;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Exposition extends BasicGameState{
	
	Input input;
	private TextField text;
	TrueTypeFont font;
	int time = 0;
	int duration = 3500;
	
	public Exposition(int state){
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		input = gc.getInput();		
		Font fonttype = new Font("Helvetica",Font.ITALIC,24);
		font = new TrueTypeFont(fonttype , true);
		text = new TextField(gc,font,0,0,1024,600);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		font.drawString(750,550,"--press SPACE to skip--",Color.white);
		text.render(gc, g);		
		if(time<duration) {
			text.setText(" Five years ago...");
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		if(input.isKeyDown(Input.KEY_SPACE)){
			sbg.enterState(1);
		}
		time += a;
	}

	public int getID() {
		return 4;
	}

}
