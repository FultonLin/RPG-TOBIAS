package rpg;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
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
	private Item[] shown;
	private TrueTypeFont font;
	private TextField abc;
	private int counter;
	private String[] field;
	private TrueTypeFont[] field2;
	
	public Inventory(int inventory) {
		counter = 0;
		inven = new ArrayList<Item>();
		equip = new ArrayList<Item>();
		shown = new Item[3];
		field = new String[3];
		for(int i = 0; i < field.length; i++){
			field[i] = "";
		}
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		field2 = new TrueTypeFont[3];
		Font asd = new Font("Helvetica", Font.BOLD, 24);
		font = new TrueTypeFont(asd , true);
		field2[0] = new TrueTypeFont(asd, true);
		field2[1] = new TrueTypeFont(asd, true);
		field2[2] = new TrueTypeFont(asd, true);
		abc = new TextField(gc, gc.getDefaultFont(), 50, 100, 350, 450);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		createBox(gc,sbg,g,abc);
		font.drawString(500, 800, "Use", Color.white);
		field2[0].drawString(500, 100, field[0], Color.white);
		field2[1].drawString(500, 150, field[1], Color.white);
		field2[2].drawString(500, 200, field[2], Color.white);
		font.drawString(25, 25, "Inventory", Color.white);
	}
 
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		if(input.isKeyPressed(input.KEY_I)){
			sbg.enterState(1);
		}
		
		String pl = "";
		for(int j = 0; j < equip.size(); j++){
			if(equip.get(j).getType().equals("Weapon")){
				pl += equip.get(j).getType() + " name: " + equip.get(j).getName() + " Atk: " + equip.get(j).getAttack() + " Spd: " + equip.get(j).getAttackSpd() + "\n";
			}else if(equip.get(j).getType().equals("Armor")){
				pl += equip.get(j).getType() + " name: " + equip.get(j).getName() + " Def: " + equip.get(j).getdef() + "\n";
			}
		}
		abc.setText(pl);
		
		int count = 0;
		for(int j = 0; j < inven.size(); j++){
			for(int i = count; i < shown.length;){
				shown[i] = inven.get(i);
				break;
			}
			count++;
		}
		
		int placeHolder = 100;
		for(int i = 0; i < field2.length;i++){
			if(xpos > 500 && xpos < 500 + field2[i].getWidth(field[i]) && ypos < 600 - placeHolder && ypos > 600 - (placeHolder + field2[i].getHeight(field[i])) && input.isMouseButtonDown(0)){
				System.out.println(shown[i].getName());
				for(int j = 0; j < inven.size(); j++){
					if(shown[i].getName().equals(inven.get(j).getName())){
						shown[0] = inven.get(j % inven.size());
						shown[1] = inven.get((j+1) % inven.size());
						shown[2] = inven.get((j+2) % inven.size());
					}
				}
			}
			placeHolder += 50;
		}
		
		for(int i = 0; i < shown.length; i++){
			if(shown[i] != null){
				String bts = shown[i].getType();
				Item c = shown[i];
				if(bts.equals("Weapon")){
					field[i] = c.getType() + " name: " + c.getName() + " Atk: " + c.getAttack() + " Spd: " + c.getAttackSpd();
				}else if(bts.equals("Armor")){
					field[i] = c.getType() + " name: " + c.getName() + " Def: " + c.getdef();
				}else{
					field[i] = c.getType() + " name: " + c.getName() + " +" + c.getHeal() + "hp x" + c.getStackNum();
				}
			}
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

	public static void addEquip(Item equips) {
		boolean found = false;
		for(int i = 0; i < equip.size(); i++){
			if(equips.getType().equals(equip.get(i).getType())){
				found = true;
				equip.set(i,equips);
			}
		}
		if(found != true){
			equip.add(equips);
		}
	}
	public static void addInven(Item item){
		inven.add(item);
	}
}
