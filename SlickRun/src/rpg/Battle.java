package rpg;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
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

public class Battle extends BasicGameState{
	
	private TextField abc;
	private TrueTypeFont font;
	protected static ArrayList<Monster> opone;
	private int monHP;
	private Image monster;
	private boolean acceptingInput;
	private String idk;
	private String idk2;

	public Battle(int battle){
		opone = new ArrayList<Monster>();
		opone.add(new Monster("chest", 100, 10, 10));
		monHP = opone.get(0).gethp();
		acceptingInput = true;
	}
	
	@Override	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		abc = new TextField(gc, gc.getDefaultFont(), 12, 500, 1000, 80);
		Font asd = new Font("Helvetica", Font.BOLD, 24);
		font = new TrueTypeFont(asd , true);
		monster = new Image("resources/monster.png");
	} 

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		abc.render(gc, g);
		g.drawRect(abc.getX(), abc.getY(), abc.getWidth(), abc.getHeight());
		font.drawString(700, 50, opone.get(0).getName() + " Hp: " + opone.get(0).gethp() + "/" + monHP, Color.white);
		font.drawString((1024/2)-(font.getWidth(idk)/2), (600/2)-(font.getHeight(idk)), idk, Color.white);
		monster.draw(700, 80);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		if(acceptingInput){
			idk = "Attack";
			if(xpos > 512 - (font.getWidth(idk)/2) && xpos < 512 + (font.getWidth(idk)) && 
					ypos < 600 - (600/2)+(font.getHeight(idk)) && ypos > 600 - (600/2)-(font.getHeight(idk)) 
						&& input.isMousePressed(0)){
				opone.get(0).decreaseHp(Play.getYuusha().getDmg());
				System.out.println("asdasdasd");
			}
		}
	}

	@Override
	public int getID() {
		return 2;
	}
}
