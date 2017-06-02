package rpg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;

public class Play extends BasicGameState{
	
	Player yuusha;
	boolean quit = false;
	private TextField abc;
	private ArrayList<Monster> mob;
	private TrueTypeFont font;
	
	
	public Play(int state) {
		yuusha = new Player();
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		abc = new TextField(gc, gc.getDefaultFont(), 12, 500, 1000, 80);
		abc.setText("You can walk down");
		mob = new ArrayList<Monster>();
		populateMap();
		Font asd = new Font("Helvetica", Font.BOLD, 24);
		font = new TrueTypeFont(asd , true);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("X: " + yuusha.getYuushaX() + "\nY: " + yuusha.getYuushaY(), 600, 10);//hero coordinates
		abc.render(gc, g);
		g.drawRect(12, 500, 1000, 80);
		font.drawString(10, 10, "HP:" + yuusha.getHp(), Color.white);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			sbg.enterState(0);
		}
		if(input.isKeyDown(Input.KEY_I)){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sbg.enterState(3);
		}
		
		for(int i = 0; i < mob.size(); i++){
			Monster pl = mob.get(i);
			if(yuusha.getYuushaX() > pl.getxpos() && yuusha.getYuushaX() < pl.getxpos()+ pl.getWidth() 
			&& yuusha.getYuushaY() > pl.getypos() && yuusha.getYuushaY() < pl.getypos()+ pl.getHeight()){
				if(pl.getTarget() == null){
					pl.setTarget(this);
					Thread attack = new Thread(pl);
					attack.start();
				}
			}else{
				pl.setTarget(null);
			}
		}
	}
	
	private void populateMap() {
		mob.add(new Monster("Chest", 10, 1, 2, 500, -300, -400, 100, 100));//example to create mob
	}

	public int getID() {
		return 1;
	}
}
