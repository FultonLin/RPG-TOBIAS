package rpg;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	String textstate;
	int time = 0;
	int duration = 15000;
	int duration2 = 3000;
	private boolean skip = false;
	private Image chest,open;
	
	public Exposition(int state){
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		input = gc.getInput();		
		Font fonttype = new Font("Helvetica",Font.ITALIC,24);
		font = new TrueTypeFont(fonttype , true);
		text = new TextField(gc,font,0,0,1024,600);
		chest = new Image("resources/chestclose.png");
		open = new Image("resources/chestopen.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {	
		text.render(gc, g);
		if(time<duration && skip == false) {
			text.setText("Five years ago...");
			if(time>2500 && time<7500){
				text.setText("Five years ago...\nThe world has been taken over by demon king.");
			}
			if(time>7500 && time<15000){
				text.setText("Five years ago...\nThe world has been taken over by demon king.\nMany warriors came together and stood against the demon army.");
			}
//			if(time>7500 && time<15000){
//				text.setText("Five years ago...\nThe world has been taken over by demon king. \nMany warriors came together and stood against the demon army."
//						+ "\nDuring the first year of the war, many warriors has fallen but the demon army was being pushed back.");
//			}
		}	
		if(skip == true){
			chest.draw((1024/2)-chest.getHeight()/2,(600/2)-chest.getWidth()/2);	
			textstate = "--press SPACE to start--";
		}else{
			textstate = "--press ENTER to skip--";
		}
		font.drawString(750,550,textstate,Color.white);		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		gc.setMinimumLogicUpdateInterval(10);
		if(input.isKeyDown(Input.KEY_ENTER)){
			text.setText("INSTRUCTIONS:");
			skip = true;
		}
		if(input.isKeyDown(Input.KEY_SPACE) && skip == true){			
			chest = open;
			text.setText("Timer: "+time/1000);
			if(time>duration2){
//				System.out.println("PLAY");
				sbg.enterState(1);		
			}
		}
		time += a;
	}

	public int getID() {
		return 4;
	}

}
