package rpg;

public class ObjectClass {
	
//	private Image chest;
	private boolean object1 = false;
	private boolean object2 = false;
	private boolean object3 = false;
	private boolean object4 = false;
	private int chestX = 0,chestY = 0;

//	public void objectInteraction(double a, double b) {
//		if(a == 24.0 && b == 62.0){
//			object1 = true;
//			chestX = (int) a*10;
//			chestY = (int) b*10;
//			System.out.println("Unlocked at: "+a+", "+b);
//		}
//		if(a == 21.0 && b == 77.0){
//			object2 = true;
//			chestX = (int) a;
//			chestY = (int) b;
//			System.out.println("Unlocked at: "+a+", "+b);
//		}
//		if(a == 45.0 && b == 77.0){
//			object3 = true;
//			chestX = (int) a;
//			chestY = (int) b;
//			System.out.println("Unlocked at: "+a+", "+b);
//		}
//		if((a == 38.0 || a == 39.0) && (b == 55.0 || b == 56.0)){
//			object4 = true;
//			System.out.println("Unlocked Run");
//		}
//	}
	public int getChestX(){
		return chestX;
	}
	public int getChestY(){
		return chestY;
	}
	public boolean getObjectState(){
		return object1;
	}
	public void setObjectState(boolean object){
		object1 = object;
	}
	
}
