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
	private TextField text,text2,text3,text4,text5;
	TrueTypeFont font;
	String textstate;
	int time = 0;
	int duration = 45000;
	private boolean skip = false;
	private boolean cheststate = false;
	private Image chest,open;
	
	public Exposition(int state){
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		input = gc.getInput();		
		Font fonttype = new Font("Helvetica",Font.ITALIC,24);
		font = new TrueTypeFont(fonttype , true);
		text = new TextField(gc,font,50,100,1024,600);
		text2 = new TextField(gc,font,50,150,1024,600);
		text3 = new TextField(gc,font,50,200,1024,600);
		text4 = new TextField(gc,font,50,250,1024,600);
		text5 = new TextField(gc,font,50,300,1024,600);
		chest = new Image("resources/chestclose.png");
		open = new Image("resources/chestopen.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {	
		g.setColor(Color.red);
		text.render(gc, g);
		text2.render(gc, g);
		text3.render(gc, g);
		text4.render(gc, g);
		text5.render(gc, g);
		g.setColor(Color.black);
		g.drawRect(text.getX(),text.getY(),text.getWidth(),text.getHeight());
		g.drawRect(text2.getX(),text2.getY(),text2.getWidth(),text2.getHeight());
		g.drawRect(text3.getX(),text3.getY(),text3.getWidth(),text3.getHeight());
		g.drawRect(text4.getX(),text4.getY(),text4.getWidth(),text4.getHeight());
		g.drawRect(text5.getX(),text5.getY(),text5.getWidth(),text5.getHeight());
		
		if(time<duration && skip == false) {
			text.setText("Five years ago...");
			if(time>1500 && time<4000){
				text.setText("Five years ago... The world has been taken over by the demon king.");
			}
			if(time>4000 && time<7500){
				text.setText("Five years ago... The world has been taken over by the demon king. Many warriors");
				text2.setText("came together and stood against the demon army.");
			}
			if(time>7500 && time<12500){
				text.setText("Five years ago... The world has been taken over by the demon king. Many warriors");
				text2.setText("came together and stood against the demon army. During the first year of the war, ");
				text3.setText("many warriors has fallen but the demon army was being pushed back.");
			}
			if(time>12500 && time<17000){
				text.setText("Five years ago... The world has been taken over by the demon king. Many warriors");
				text2.setText("came together and stood against the demon army. During the first year of the war, ");
				text3.setText("many warriors has fallen but the demon army was being pushed back. Nearing the");
				text4.setText("end of second year, the tides of the war began to turn.");
			}
			if(time>17000 && time<23000){
				text.setText("Five years ago... The world has been taken over by the demon king. Many warriors");
				text2.setText("came together and stood against the demon army. During the first year of the war, ");
				text3.setText("many warriors has fallen but the demon army was being pushed back. Nearing the");
				text4.setText("end of second year, the tides of the war began to turn. During this year, many");
				text5.setText("warriors began to lose their ability of sight and even their ability of movement.");
			}
			if(time>23000 && time<27500){
				text.setText("Three years later, the past occurance is still a mystery to the remaining human kind.");
				text2.setText("");
				text3.setText("");
				text4.setText("");
				text5.setText("");
			}
			if(time>27500 && time<36500){
				text.setText("Three years later, the past occurance is still a mystery to the remaining human kind.");
				text2.setText("Many would say that it was caused by the curse of the demon king and that he has locked");
				text3.setText("away many human abilities so humans could be suppressed in his grasp.");
			}
			if(time>36500 && time<42000){
				text.setText("Three years later, the past occurance is still a mystery to the remaining human kind.");
				text2.setText("Many would say that it was caused by the curse of the demon king and that he has locked");
				text3.setText("away many human abilities so humans could be suppressed in his grasp. Your job as the");
				text4.setText("last hero of the world is to find these abilities and defeat the demon king and his army.");
			}
			if(time>42000 && time<45000){
				text.setText("Three years later, the past occurance is still a mystery to the remaining human kind.");
				text2.setText("Many would say that it was caused by the curse of the demon king and that he has locked");
				text3.setText("away many human abilities so humans could be suppressed in his grasp. Your job as the");
				text4.setText("last hero of the world is to find these abilities and defeat the demon king and his army.");
				text5.setText("Now go YUUSHA and fight for the human race!");
			}
		}	
		if(skip == true){
			chest.draw((1024/2)-chest.getHeight()/2,(600/2)-chest.getWidth()/2+100);	
			textstate = "--press SPACE to start--";
		}else{
			textstate = "--press ENTER to skip--";
		}
		font.drawString(750,550,textstate,Color.white);		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		gc.setMinimumLogicUpdateInterval(10);
		if(input.isKeyDown(Input.KEY_ENTER)){
			text.setText("INSTRUCTIONS: ARROW UP: UP");
			text2.setText("             ARROW DOWN: DOWN");
			text3.setText("             ARROW LEFT: LEFT");
			text4.setText("             ARROW RIGHT: RIGHT");
			text5.setText("             SPACEBAR: INTERACT");
			skip = true;
		}
		if(input.isKeyDown(Input.KEY_SPACE) && skip == true){			
			chest = open;			
			if(cheststate == true){
				sbg.enterState(1);		
			}
			cheststate = true;
		}
		time += a;
	}

	public int getID() {
		return 4;
	}

}
