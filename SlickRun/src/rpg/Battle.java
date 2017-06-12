package rpg;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Battle extends BasicGameState{
	
	private TextField abc;
	private TextField ab;
	private TrueTypeFont font;
	protected static ArrayList<Monster> opone;
	private int monHP;
	private Image beezlebub,slime,bat,chest;

	public Battle(int battle){
		opone = new ArrayList<Monster>();
		opone.add(new Monster("chest", 100, 10, 10));
		monHP = opone.get(0).gethp();
	}
	
	@Override	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		abc = new TextField(gc, gc.getDefaultFont(), 12, 500, 1000, 80);
		Font asd = new Font("Helvetica", Font.BOLD, 24);
		font = new TrueTypeFont(asd , true);
		ab = new TextField(gc, gc.getDefaultFont(), 800, 100, 100, 50);
		chest = new Image("resources/chestclose.png");
		ab.setText(opone.get(0).getName() + "HP:" + opone.get(0).gethp() + "/" + monHP);
	} 

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		abc.render(gc, g);
		g.drawRect(abc.getX(), abc.getY(), abc.getWidth(), abc.getHeight());
		ab.render(gc, g);
		chest.draw(300, 100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		
	}

	@Override
	public int getID() {
		return 2;
	}
}

