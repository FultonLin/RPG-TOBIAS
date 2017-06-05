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
	Object object;
	boolean quit = false;
	private TextField abc;
	private ArrayList<Monster> mob;
	private TrueTypeFont font;
	//MAP
	private TiledMap tiledmap;//this will not work
	//CHARACTER
	private Animation yuusha,moveUp,moveDown,moveLeft,moveRight;
	//LOADING BAR
	private Image loading;
	int time = 0;
	int duration = 2000;
	//OBJECT
	private boolean blocked[][];
	private Image chest;
	
	Input input;
	
	public Play(int state) {
		yuusha2 = new Player();
		mob = new ArrayList<Monster>();
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
		chest = new Image("resources/chestopen.png");
		
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
//		int firstlayer = map.getLayerIndex("FirstLayer");
		for(int x = 0; x < tiledmap.getWidth(); x++) {
		    for(int y = 0; y < tiledmap.getHeight(); y++) {
		        int tileID = tiledmap.getTileId(x, y, objectlayer);
//		        int tileID2 = map.getTileId(x, y, firstlayer);
//		        String value = map.getTileProperty(tileID, "blocked", "false");
//		        if(value.equals("true")) {
//		            blocked[x][y] = true;
//		            blocks.add(new Rectangle((float)x*32,(float)y*32,32,32));
//		        }
		        if(tileID != 0){
//		        if(tileID != 0 || tileID2 != 0){
		        	System.out.println("Object found at  "+x+", "+y);
		        	if(blocked[x][y] != true){
		        		blocked[x][y] = true;
		        	}
		        }
		    }
		}
	}
	
	public boolean checkCollision(double x, double y) {
		System.out.println("x is "+x);
		x = (int)((x)/32)+24;
		y = (int)((y)/32)+59;
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
//		g.drawString("X: " + yuusha2.getYuushaX() + "\nY: " + yuusha2.getYuushaY(), 600, 10);//hero coordinates
//		abc.render(gc, g);
//		g.drawRect(12, 500, 1000, 80);
//		font.drawString(10, 10, "HP:" + yuusha2.getHp(), Color.white);
		//MAP
//		map.render((int)x-32,(int)y-32,mapX,mapY,mapX+27,mapY+27);
		tiledmap.render((int)map.getX()-32,
				(int)map.getY()-32,
				map.getMapX(),
				map.getMapY(),
				map.getMapX()+27,
				map.getMapY()+27);
		//OBJECT
//		if(object1 == true){
//			System.out.println("CHEST IS VISIBLE");
//			chest.draw(object.getChestX(),object.getChestY());
//		}
		//CHARACTER
		yuusha.draw(385,274);
		
		//OTHER
		g.drawString("X: " + yuusha2.getYuushaX() + "\nY: " + yuusha2.getYuushaY(), 600, 10);//hero coordinates
		abc.render(gc, g);
		g.drawRect(12, 500, 1000, 80);
		font.drawString(10, 10, "HP:" + yuusha2.getHp(), Color.white);
		
		//LOADING BAR-display time
		if (time<duration) {
			loading.draw(0,0,1.5f);
		}
	} 

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {		
		//CHARACTER-keyboard input
		if(input.isKeyDown(Input.KEY_UP)){
			if(checkCollision(yuusha2.getYuushaX(),yuusha2.getYuushaY()-1) == true){
				yuusha = moveUp;
				yuusha.update(a);
//				yuushaY--;
//				y++;
				yuusha2.setYuushaY(yuusha2.getYuushaY()-1);
				map.setY(map.getY()+1);
			}
//			y += a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			if(checkCollision(yuusha2.getYuushaX(),yuusha2.getYuushaY()+1) == true){
				yuusha = moveDown;
				yuusha.update(a);
//				yuushaY++;
//				y--;
				yuusha2.setYuushaY(yuusha2.getYuushaY()+1);
				map.setY(map.getY()-1);
			}
//			y -= a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			if(checkCollision(yuusha2.getYuushaX()-1,yuusha2.getYuushaY()) == true){
				yuusha = moveLeft;
				yuusha.update(a);
//				yuushaX--;
//				x++;
				yuusha2.setYuushaX(yuusha2.getYuushaX()-1);
				map.setX(map.getX()+1);
			}
//			x += a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
			if(checkCollision(yuusha2.getYuushaX()+1,yuusha2.getYuushaY()) == true){
				yuusha = moveRight;
				yuusha.update(a);
//				yuushaX++;
//				x--;
				yuusha2.setYuushaX(yuusha2.getYuushaX()+1);
				map.setX(map.getX()-1);
			}
//			x -= a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_SPACE)){
			double newX = (int)(map.getX()/32)+24;
			double newY = (int)(map.getY()/32)+59;
			if(checkCollision(yuusha2.getYuushaX(),yuusha2.getYuushaY()-1) == false){
				double v = newX;
				double w = newY-1;
				System.out.println("Found Tile: "+v+", "+w);
				object.objectInteraction(v,w);
			}
			if(checkCollision(yuusha2.getYuushaX(),yuusha2.getYuushaY()+1) == false){
				double v = newX;
				double w = newY+1;
				System.out.println("Found Tile: "+v+", "+w);
				object.objectInteraction(v,w);
			}
			if(checkCollision(yuusha2.getYuushaX()-1,yuusha2.getYuushaY()) == false){
				double v = newX-1;
				double w = newY;
				System.out.println("Found Tile: "+v+", "+w);
				object.objectInteraction(v,w);
			}
			if(checkCollision(yuusha2.getYuushaX()+1,yuusha2.getYuushaY()) == false){
				double v = newX+1;
				double w = newY;
				System.out.println("Found Tile: "+v+", "+w);
				object.objectInteraction(v,w);
			}
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
		
		for(int i = 0; i < mob.size(); i++){
			Monster pl = mob.get(i);
			if(yuusha2.getYuushaX() > pl.getxpos() && yuusha2.getYuushaX() < pl.getxpos()+ pl.getWidth() 
			&& yuusha2.getYuushaY() > pl.getypos() && yuusha2.getYuushaY() < pl.getypos()+ pl.getHeight()){
				if(pl.getTarget() == null){
					pl.setTarget(this);
					Thread attack = new Thread(pl);
					attack.start();
				}
			}else{
				pl.setTarget(null);
			}
		}
		// MAP-rendering with movement
		if(map.getX()<0){
//			mapX++;
//			x = 32;
			map.setMapX(map.getMapX()+1);
			map.setX(32);
		}
		if(map.getX()>32){
//			mapX--;
//			x = 0;
			map.setMapX(map.getMapX()-1);
			map.setX(0);
		}
		if(map.getY()<0){
//			mapY++;
//			y = 32;
			map.setMapY(map.getMapY()+1);
			map.setY(32);
		}
		if(map.getY()>32){
//			mapY--;
//			y = 0;
			map.setMapY(map.getMapY()-1);
			map.setY(0);
		}
		//LOADING BAR
		time += a;
	}
	
	private void populateMap() {
		mob.add(new Monster("Chest", 10, 1, 2, 500, -300, -400, 100, 100));//example to create mob
	}

	public int getID() {
		return 1;
	}
}
