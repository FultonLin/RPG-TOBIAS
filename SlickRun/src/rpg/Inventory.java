package rpg;

import java.util.ArrayList;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Inventory extends BasicGameState{

	private ArrayList<Item> inventory;
	private Item[] Equip;
	
	public Inventory(int inventory) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		inventory = new ArrayList<Item>();
		Equip = new Item[4]; 
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		TrueTypeFont asd = new TrueTypeFont(Font.TRUETYPE_FONT, true);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_I)){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sbg.enterState(1);
		}
	}

	@Override
	public int getID() {
		return 3;
	}
	
	public void addItem(Item entity){
		inventory.add(entity);
	}
}
