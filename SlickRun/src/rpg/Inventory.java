package rpg;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Inventory extends BasicGameState{

	protected static ArrayList<Item> inven;
	protected static ArrayList<Item> equip;
	private ArrayList<TextField> fields;
	private TrueTypeFont font;
	private TextField abc;
	private TextField abcd;
	private TextField abcde;
	
	private final int armor = 10;
	private final int weapon = 20;
	private final int potion = 30;
	
	public Inventory(int inventory) {
		fields = new ArrayList<TextField>();
		inven = new ArrayList<Item>();
		equip = new ArrayList<Item>();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Font asd = new Font("Helvetica", Font.BOLD, 36);
		font = new TrueTypeFont(asd , true);
		abc = new TextField(gc, gc.getDefaultFont(), 50, 100, 300, 450);
		abcd = new TextField(gc,gc.getDefaultFont(), 500, 350, 500, 200);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		createBox(gc,sbg,g,abc);
		createBox(gc,sbg,g,abcd);
		g.drawRect(500, 50, 500, 250);
		font.drawString(25, 25, "Inventory", Color.white);
		for(int i = 0; i < fields.size();i++){
			fields.get(i).render(gc, g);
		}
	}
 
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyPressed(input.KEY_E)){
			sbg.enterState(1);
		}
		String pl = "";
		for(int j = 0; j < equip.size(); j++){
			pl += equip.get(j).getName();
		}
		abc.setText(pl);
		for(int i = 0; i < inven.size(); i++){
			int pldr = 50;
			abcde = new TextField(gc,gc.getDefaultFont(), 500, (pldr*i)+50, 500, 30);
			fields.add(abcde);
		}
	}
	
	public void createBox(GameContainer gc, StateBasedGame sbg, Graphics g,TextField pl){
		abc.render(gc, g);
		g.drawRect(pl.getX(), pl.getY(), pl.getWidth(), pl.getHeight());
	}

	@Override
	public int getID() {
		return 3;
	}

	public ArrayList<Item> getEquip() {
		return equip;
	}

	public void addEquip(Item equip) {
		for(int i = 0; i < Inventory.equip.size(); i++){
			if(equip.getType() == Inventory.equip.get(i).getType()){
				Inventory.equip.set(i,equip);
			}else{
				Inventory.equip.add(equip);
			}
		}
	}
	
	public static void addItem(Item equipment){
		inven.add(equipment);
	}
}
