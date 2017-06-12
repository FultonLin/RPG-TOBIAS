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
import org.newdawn.slick.tiled.TiledMap;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;

public class Play extends BasicGameState{
	
	Player yuusha2;
	Map map;
	ObjectClass object; //causing null problems
	boolean quit = false;
	private TextField abc;
	private TrueTypeFont font;
	//MAP
	private TiledMap tiledmap;
	//CHARACTER
	private Animation yuusha,moveUp,moveDown,moveLeft,moveRight;
	//LOADING BAR
	private Image loading;
	int time = 0;
	int duration = 2000;
	//OBJECT
	private boolean blocked[][];
	private Image chest1,chest2,chest3,open;
	private int chestX = 0,chestY = 0;
	private boolean object1 = false;
	private boolean object2 = false;
	private boolean object3 = false;
	private boolean object4 = false;
	 
	Input input;
	
	public Play(int state) {
		yuusha2 = new Player();
		populateMap();
		map = new Map();
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		abc = new TextField(gc, gc.getDefaultFont(), 12, 500, 1000, 80);
		Font asd = new Font("Helvetica", Font.BOLD, 24);
		font = new TrueTypeFont(asd , true);
		//MAP-display
		tiledmap = new TiledMap("resources/TiledMap/rpgmap.tmx");
		input = gc.getInput();
		collisionRect();
		map.setX(0);
		map.setY(0);
		
		//OBJECT
		open = new Image("resources/chestopen.png");
		chest1 = new Image("resources/chestclose.png");
		chest2 = new Image("resources/chestclose.png");
		chest3 = new Image("resources/chestclose.png");
		
		//CHARACTER-animation movement
		Image yuushasprite1 = new Image("resources/yuushaanimation/yuushaup.png");
		Image yuushasprite2 = new Image("resources/yuushaanimation/yuushadown.png");
		Image yuushasprite3 = new Image("resources/yuushaanimation/yuushaleft.png");
		Image yuushasprite4 = new Image("resources/yuushaanimation/yuusharight.png");
		
		moveUp = getAnimation(yuushasprite1,4,1,32,40,3,200);
		moveDown = getAnimation(yuushasprite2,4,1,32,40,3,200);
		moveLeft = getAnimation(yuushasprite3,4,1,32,40,3,200);
		moveRight = getAnimation(yuushasprite4,4,1,32,40,3,200);
		yuusha = moveDown;
		
		//LOADING BAR
		loading = new Image("resources/loadingbar.jpg");
	}
	
	public void collisionRect() {
		blocked = new boolean[tiledmap.getWidth()][tiledmap.getHeight()];
		System.out.println("Dimensions: "+tiledmap.getWidth()+", "+tiledmap.getHeight());
		int objectlayer = tiledmap.getLayerIndex("ObjectLayer");
		int firstlayer = tiledmap.getLayerIndex("FirstLayer");
		int secondlayer = tiledmap.getLayerIndex("SecondLayer");
		int walllayer = tiledmap.getLayerIndex("WallLayer");
		for(int x = 0; x < tiledmap.getWidth(); x++) {
		    for(int y = 0; y < tiledmap.getHeight(); y++) {
		        int objecttileID = tiledmap.getTileId(x, y, objectlayer);
		        int firsttileID = tiledmap.getTileId(x, y, firstlayer);
		        int secondtileID = tiledmap.getTileId(x, y, secondlayer);
		        int walltileID = tiledmap.getTileId(x, y, walllayer);
//		        String value = map.getTileProperty(tileID, "blocked", "false");
//		        if(value.equals("true")) {
//		            blocked[x][y] = true;
//		            blocks.add(new Rectangle((float)x*32,(float)y*32,32,32));
//		        }
//		        if(tileID != 0){
		        if(objecttileID != 0 || firsttileID != 0 || secondtileID != 0 || walltileID != 0){
		        	System.out.println("Collision found at  "+x+", "+y);
		        	if(blocked[x][y] != true){
		        		blocked[x][y] = true;
		        	}
		        }
		    }
		}
	}
	
	public boolean checkCollision(double x, double y) {
		System.out.println("x is "+x);
//		if(x > 1600){
//			x -= 25;
//			x = (int)((x)/32)+24;
//		}else{
			x = (int)((x/32))+24;
//		}
		y = (int)((y/32))+59;
		System.out.println("Checking for Collision at "+x+", "+y);
		for(int i = 0; i < blocked.length; i++) {
			for(int j = 0; j < blocked[i].length; j++) {
				if(blocked[i][j] == true && i == x && j ==y) {
					return false;
				}
			}
		}
		return true;
	}
	
	private Animation getAnimation(Image i,int spriteX,int spriteY,int spriteWidth,int spriteHeight,int frames,int duration) {
		Animation b = new Animation(false);		
		int c = 0;
		for(int y = 0;y<spriteY;y++){
			for(int x = 0;x<spriteX;x++){
				if(c<frames){
					b.addFrame(i.getSubImage(x*spriteWidth,y*spriteHeight,spriteWidth,spriteHeight), duration);
				}	
				c++;
			}
		}
		return b;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//MAP
		tiledmap.render((int)map.getX()-32,
				(int)map.getY()-32,
				map.getMapX(),
				map.getMapY(),
				map.getMapX()+27,
				map.getMapY()+27);
		//OBJECT
		if(object1 == true){
			chest1.draw(chestX+384,chestY+385);			
		}
		if(object2 == true){
			chest2.draw(chestX+286,chestY+883);			
		}
		if(object3 == true){
			chest3.draw(chestX+1078,chestY+883);			
		}
		//CHARACTER
		yuusha.draw(385,274);
		
		//OTHER
		g.drawString("X: " + yuusha2.getYuushaX() + "\nY: " + yuusha2.getYuushaY(), 600, 10);//hero coordinates
		abc.render(gc, g);
		g.drawRect(abc.getX(), abc.getY(), abc.getWidth(), abc.getHeight());
		font.drawString(10, 10, "HP:" + yuusha2.getHp(), Color.white);
		
		//LOADING BAR-display time
		if (time<duration) {
			loading.draw(0,0,1.5f);
		}
	} 

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {		
		//CHARACTER-keyboard input
		if(input.isKeyDown(Input.KEY_UP)){
			if(checkCollision(yuusha2.getYuushaX(),yuusha2.getYuushaY()-1) == true && object3 == true){
				yuusha = moveUp;
				yuusha.update(a);
				yuusha2.setYuushaY(yuusha2.getYuushaY()-1);
				map.setY(map.getY()+1);
				chestY++;
			}else{
				abc.setText("You do not have the ability to move UP");
			}
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			if(checkCollision(yuusha2.getYuushaX(),yuusha2.getYuushaY()+1) == true){
				yuusha = moveDown;
				yuusha.update(a);
				yuusha2.setYuushaY(yuusha2.getYuushaY()+1);
				map.setY(map.getY()-1);
				chestY--;
			}
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			if(checkCollision(yuusha2.getYuushaX()-1,yuusha2.getYuushaY()) == true && object1 == true){
				yuusha = moveLeft;
				yuusha.update(a);
				yuusha2.setYuushaX(yuusha2.getYuushaX()-1);
				map.setX(map.getX()+1);
				chestX++;
			}else{
				abc.setText("You do not have the ability to move LEFT");
			}
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
			if(checkCollision(yuusha2.getYuushaX()+1,yuusha2.getYuushaY()) == true && object2 == true){
				yuusha = moveRight;
				yuusha.update(a);
				yuusha2.setYuushaX(yuusha2.getYuushaX()+1);
				map.setX(map.getX()-1);
				chestX--;
			}else{
				abc.setText("You do not have the ability to move RIGHT");
			}
		}
		if(input.isKeyDown(Input.KEY_SPACE)){
			findingInteraction();
		}
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
		// MAP-rendering with movement
		if(map.getX()<0){
			map.setMapX(map.getMapX()+1);
			map.setX(32);
		}
		if(map.getX()>32){
			map.setMapX(map.getMapX()-1);
			map.setX(0);
		}
		if(map.getY()<0){
			map.setMapY(map.getMapY()+1);
			map.setY(32);
		} 
		if(map.getY()>32){
			map.setMapY(map.getMapY()-1);
			map.setY(0);
		}
		//LOADING BAR
		time += a;
	}
	
	private void findingInteraction() {
		double newX = (int)(yuusha2.getYuushaX()/32)+24;
		double newY = (int)(yuusha2.getYuushaY()/32)+59;
		if(checkCollision(yuusha2.getYuushaX(),yuusha2.getYuushaY()-1) == false){
			double v = newX;
			double w = newY-1;
			System.out.println("Found Interaction: "+v+", "+w);
			objectInteraction(v,w);
		}
		if(checkCollision(yuusha2.getYuushaX(),yuusha2.getYuushaY()+1) == false){
			double v = newX;
			double w = newY+1;
			System.out.println("Found Interaction: "+v+", "+w);
			objectInteraction(v,w);
		}
		if(checkCollision(yuusha2.getYuushaX()-1,yuusha2.getYuushaY()) == false){
			double v = newX-1;
			double w = newY;
			System.out.println("Found Interaction: "+v+", "+w);
			objectInteraction(v,w);
		}
		if(checkCollision(yuusha2.getYuushaX()+1,yuusha2.getYuushaY()) == false){
			double v = newX+1;
			double w = newY;
			System.out.println("Found Interaction: "+v+", "+w);
			objectInteraction(v,w);
		}		
	}

	public void objectInteraction(double a, double b) {
		if(a == 24.0 && b == 62.0){
			if(object1 == false){				
				object1 = true;
				chest1 = open;
				System.out.println("Unlocked at: "+a+", "+b);
				abc.setText("Unlocked LEFT Movement");
			}else{
				System.out.println("ALREADY UNLOCKED");
			}
		}
		if(a == 21.0 && b == 77.0){
			if(object2 == false){				
				object2 = true;
				chest2 = open;
				System.out.println("Unlocked at: "+a+", "+b);
				abc.setText("Unlocked RIGHT Movement");
			}else{
				System.out.println("ALREADY UNLOCKED");
			}
		}
		if(a == 45.0 && b == 77.0){
			if(object3 == false){				
				object3 = true;
				chest3 = open;
				System.out.println("Unlocked at: "+a+", "+b);
				abc.setText("Unlocked UP Movement");
			}else{
				System.out.println("ALREADY UNLOCKED");
			}
		}
		if((a == 38.0 || a == 39.0) && (b == 55.0 || b == 56.0)){
			if(object4 == false){				
				object4 = true;
				System.out.println("Unlocked at: "+a+", "+b);
				abc.setText("Unlocked RUN ability");
			}else{
				System.out.println("ALREADY UNLOCKED");
			}
		}
	}
	
	private void populateMap() {
//		mob.add(new Monster("Chest", 10, 1, 2, 500, 200, 200, 100, 100));//example to create mob
	}

	public int getID() {
		return 1;
	}
}

