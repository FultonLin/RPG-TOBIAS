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
	private String[] field;
	private TrueTypeFont field2;
	private TrueTypeFont font;
	private TextField abc;
	private Item shown1;
	private Item shown2;
	private Item shown3;
	private Item temp;
	
	public Inventory(int inventory) {
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
		Font asd = new Font("Helvetica", Font.BOLD, 24);
		font = new TrueTypeFont(asd , true);
		Font abd = new Font("Helvetica", Font.BOLD, 24);
		field2 = new TrueTypeFont(asd, true);
		abc = new TextField(gc, gc.getDefaultFont(), 50, 100, 350, 450);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		createBox(gc,sbg,g,abc);
		font.drawString(500, 800, "Use", Color.white);
		field2.drawString(500, 100, field[0], Color.white);
		field2.drawString(500, 150, field[1], Color.white);
		field2.drawString(500, 200, field[2], Color.white);
		font.drawString(25, 25, "Inventory", Color.white);
	}
 
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		if(input.isKeyPressed(Input.KEY_I)){
			sbg.enterState(1);
		}
		
		String pl = "";
		for(int j = 0; j < equip.size(); j++){
			if(equip.get(j).getType().equals("Weapon")){
				pl += equip.get(j).getType() + " name: " + equip.get(j).getName() + " Atk: " + equip.get(j).getAttack() + "\n";
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
		if(xpos > 500 && xpos < 500 + field2.getWidth(field[1]) &&
				ypos < 600 - placeHolder && ypos > 600 - (placeHolder + field2.getHeight(field[1])) 
					&& input.isMousePressed(0)){
			for(int j = 0; j < inven.size(); j++){
				if(shown2.getName().equals(inven.get(j).getName())){
					shown[0] = inven.get(j % inven.size());
					shown[1] = inven.get((j+1) % inven.size());
					shown[2] = inven.get((j+2) % inven.size());
				}
			}
		}else if(xpos > 500 && xpos < 500 + field2.getWidth(field[2]) &&
				ypos < 600 - placeHolder && ypos > 600 - (placeHolder + field2.getHeight(field[2])) 
					&& input.isMousePressed(0)){
			for(int j = 0; j < inven.size(); j++){
				if(shown3.getName().equals(inven.get(j).getName())){
					shown[0] = inven.get(j % inven.size());
					shown[1] = inven.get((j+1) % inven.size());
					shown[2] = inven.get((j+2) % inven.size());
				}
			}
		}
		placeHolder += 50;
		
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
			int armorCount = 0;
			if(equips.getType().equals(equip.get(i).getType()) && !(equip.get(i).getType().equals("Armor"))){
				found = true;
				equip.set(i,equips);
			}else if(equips.getType().equals("Armor")){
				armorCount++;
			}else if(armorCount <= 3){
				equip.add(equips);
			}else if(armorCount > 3){
				equip.set(0, equips);
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
