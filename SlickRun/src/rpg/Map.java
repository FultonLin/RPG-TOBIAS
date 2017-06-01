package rpg;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends BasicGameState {
	
	//MAP
	private TiledMap map;
	private double x,y;
	private int mapX = 11,mapY = 49;
	private Image chest;
	private boolean chestopen = false;
	private int chestX = 0,chestY = 0;
	
	private boolean blocked[][];
//	private ArrayList<Rectangle> blocks;
	//CHARACTER
	private Animation yuusha,moveUp,moveDown,moveLeft,moveRight;
	private double yuushaX,yuushaY;
	//LOADING BAR
	private Image loading;
	int time = 0;
	int duration = 2000;

	public Map(int state) {
			
	}
	Input input;
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//MAP-display
		map = new TiledMap("resources/TiledMap/rpgmap.tmx");
		input = gc.getInput();
		collisionRect();
		x = 0;
		y = 0;
	
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
		map.render((int)x-32,(int)y-32,mapX,mapY,mapX+27,mapY+27);
		chest.draw(chestX,chestY);
		//CHARACTER
		yuusha.draw(385,274);
		//LOADING BAR-display time
		if (time<duration) {
			loading.draw(0,0,1.5f);
		}	
	}
	
	public void collisionRect() {
		blocked = new boolean[map.getWidth()][map.getHeight()];
		System.out.println("Dimensions: "+map.getWidth()+", "+map.getHeight());
		int objectlayer = map.getLayerIndex("ObjectLayer");
//		int firstlayer = map.getLayerIndex("FirstLayer");
		for(int x = 0; x < map.getWidth(); x++) {
		    for(int y = 0; y < map.getHeight(); y++) {
		        int tileID = map.getTileId(x, y, objectlayer);
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
	
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
//		map.getTileId(0,0,layer);	
		
//		boolean isInCollision = false;
//		for(Rectangle ret in map.getBlocks()) {
//		    if(yuusha.bounds.intersects(ret.bounds)) {
//		        isInCollision = true;
//		    }
//		}
		//CHARACTER-keyboard input
		if(input.isKeyDown(Input.KEY_UP)){
			if(checkCollision(yuushaX,yuushaY-1) == true){
				yuusha = moveUp;
				yuusha.update(a);
				yuushaY--;
				y++;
			}
//			y += a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			if(checkCollision(yuushaX,yuushaY+1) == true){
				yuusha = moveDown;
				yuusha.update(a);
				yuushaY++;
				y--;
			}
//			y -= a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			if(checkCollision(yuushaX-1,yuushaY) == true){
				yuusha = moveLeft;
				yuusha.update(a);
				yuushaX--;
				x++;
			}
//			x += a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
			if(checkCollision(yuushaX+1,yuushaY) == true){
				yuusha = moveRight;
				yuusha.update(a);
				yuushaX++;
				x--;
			}
//			x -= a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_SPACE)){
			if(checkCollision(yuushaX,yuushaY-1)==false || checkCollision(yuushaX,yuushaY+1)==false ||
					checkCollision(yuushaX-1,yuushaY)==false || checkCollision(yuushaX+1,yuushaY)==false){
				System.out.println("UNLOCKED");
				objectInteraction();
			}
		}
		// MAP-rendering with movement
		if(x<0){
			mapX++;
			x = 32;
		}
		if(x>32){
			mapX--;
			x = 0;
		}
		if(y<0){
			mapY++;
			y = 32;
		}
		if(y>32){
			mapY--;
			y = 0;
		}
		//LOADING BAR
		time += a;
	}

	private void objectInteraction() {
		if(checkCollision(yuushaX,yuushaY) == false){
			chestopen = true;
			chestX = (int) yuushaX;
			chestY = (int) yuushaY;
		}
		
	}

	public int getID() {
		return 0;
	}
	
}



