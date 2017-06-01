package rpg;

public class Monster implements MonsterInterface,Runnable{
	
	private Play target;
	private String name;
	private int hp;
	private int dmg;
	private int spd;
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	private int atkSpd;
	
	
	public Monster (String name, int hp, int dmg, int spd, int atkSpd, int xpos, int ypos, int height, int width){
		this.name = name;
		this.hp = hp;
		this.dmg = dmg;
		this.spd = spd;
		this.xpos = xpos;
		this.ypos = ypos;
		this.height = height;
		this.width = width;
		this.atkSpd = atkSpd;
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
	
	public int getxpos(){
		return xpos;
	}
	
	public int getypos(){
		return ypos;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getAttkSpd() {
		return atkSpd;
	}

	@Override
	public void run() {
		while(target != null){
			System.out.println("Monster is attacking");
			Item[] equip = Inventory.equip;
			for(int i = 0; i < equip.length;i++){
				
			}
			try {
				Thread.sleep(atkSpd);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Play getTarget() {
		return target;
	}

	public void setTarget(Play target) {
		this.target = target;
	}
}
