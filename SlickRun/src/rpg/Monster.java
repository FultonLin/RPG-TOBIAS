package rpg;

import java.util.ArrayList;

import org.newdawn.slick.Image;

public class Monster implements MonsterInterface,Runnable{
	
//	private Player target;
	private String name;
	private int hp;
	private int dmg; 
	private int spd;
	
	
	public Monster (String name, int hp, int dmg, int spd){
		this.name = name;
		this.hp = hp;
		this.dmg = dmg;
		this.spd = spd;
	}

	@Override
	public int gethp() {
		return hp;
	}

	@Override
	public int getdmg() {
		return dmg;
	}

	@Override
	public void decreaseHp(int dmg) {
		hp -= dmg;
	}

	@Override
	public int getSpd() {
		return spd;
	}
	
	public String getName(){
		return name;
	}

	@Override
	public void run() {
//		while(target != null){
//			System.out.println("Monster is attacking");
//			int equipped = Inventory.equip.size();
//			int totalDef = 0;
//			System.out.println(equipped);
//			for(int i = 0; i < equipped;i++){
//				totalDef += Inventory.equip.get(i).getdef();
//			}
//			if(dmg-totalDef <= 0){
//				target.decHp(1);
//			}else{
//				target.decHp(dmg-totalDef);
//			}
//			try {
//				Thread.sleep(atkSpd);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

//	public Player getTarget() {
//		return target;
//	}
//
//	public void setTarget(Player target) {
//		this.target = target;
//	}
}
