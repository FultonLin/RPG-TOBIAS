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
	private TiledMap tiledmap,monomap,colormap;
	//CHARACTER
	private Animation yuusha,moveUp,moveDown,moveLeft,moveRight;
	//LOADING BAR
	private Image loading1,loading2,loading3,loading4,loading5,loading6,loading7;
	int time = 0;
	int duration = 3500;
	//OBJECT
	private boolean blocked[][];
	private Image chest1,chest2,chest3,chest4,open,openmono;
	private double chestX = 0,chestY = 0;
	private boolean object1 = false;
	private boolean object2 = false;
	private boolean object3 = false;
	private boolean object4 = false;
	private boolean object5 = false;
	private boolean object6 = false;
	
	Input input;
	
	public Play(int state) {
		yuusha2 = new Player();
		map = new Map();
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		input = gc.getInput();
		abc = new TextField(gc, gc.getDefaultFont(), 12, 500, 1000, 80);
		Font asd = new Font("Helvetica", Font.BOLD, 24);
		font = new TrueTypeFont(asd , true);
		//MAP-display
		monomap = new TiledMap("resources/TiledMap/rpgmap(monocolor).tmx");
		colormap = new TiledMap("resources/TiledMap/rpgmap.tmx");
		tiledmap = monomap;
		collisionRect();
		map.setX(0);
		map.setY(0);
		
		//OBJECT
		chest1 = new Image("resources/TiledMap/chestopen.png");
		chest2 = new Image("resources/TiledMap/chestopen.png");
		chest3 = new Image("resources/TiledMap/chestopen.png");
		chest4 = new Image("resources/TiledMap/chestopen.png");
		open = new Image("resources/TiledMap/chestopen.png");
		openmono = new Image("resources/TiledMap/chestopen(mono).png");
		
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
		loading1 = new Image("resources/loadingbar/loadingbar1.jpg");
		loading2 = new Image("resources/loadingbar/loadingbar2.jpg");
		loading3 = new Image("resources/loadingbar/loadingbar3.jpg");
		loading4 = new Image("resources/loadingbar/loadingbar4.jpg");
		loading5 = new Image("resources/loadingbar/loadingbar5.jpg");
		loading6 = new Image("resources/loadingbar/loadingbar6.jpg");
		loading7 = new Image("resources/loadingbar/loadingbar7.jpg");
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
//		        	System.out.println("Collision found at  "+x+", "+y);
		        	if(blocked[x][y] != true){
		        		blocked[x][y] = true;
		        	}
		        }
		    }
		}
	}
	
	public boolean checkCollision(double x, double y) {	
		//CUSTOMIZING COLLISION
		if(x<-100){
			x -= 32;
		}else if(x<0 && x>-5 && y<13 && y>-11){
			x -= 32;
		}else if(x<6 && x>0 && y<13 && y>-11){
			x += 32;
		}else if(x<0 && x>-33 && y<13 && y>-11){
			x -= 32;
		}else if(x<33 && x>0 && y<13 && y>-11){
			x += 32;
		}else if(x<6 && x>-5 && y<10 && y>-10){
			y -= 64;
		}else if(x<-65 && y<608 && y>580){
			x -= 32;
		}else if(x<696 && x>100 && y<-79 && y>-150){
			y -= 32;
		}else if(x<20 && x>-32 && y<100 && y>70){
			y += 32;			
		}else if(x<720 && x>695){
			x += 10;		
		}else if(x<795 && x>750){
			x -= 32;
		}else if(x<1600 && x>795){
			x -= 16;			
		}else if(x>1650){
			x -= 32;
			if(x<2170 && x>2000 && y>-270){
				x -= 32;
			}else if(x>2275 && y>-270){
				x -= 32;
			}else if(x>1690 && y<-270 && y>-1150){
				x -= 16;
				if(y<-600 && y>-1150){
					y += 16;
				}
			}		
		}
		x = (int)((x/32))+24;		
		y = (int)((y/32))+59;
		System.out.println("Checking for Collision at "+x+", "+y);
		for(int i = 0; i < blocked.length; i++) {
			for(int j = 0; j < blocked[i].length; j++) {
				if(blocked[i][j] == true && i == x && j == y) {
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
			chest1.draw((int)chestX+384,(int)chestY+385);			
		}
		if(object2 == true){
			chest2.draw((int)chestX+286,(int)chestY+883);			
		}
		if(object3 == true){
			chest3.draw((int)chestX+1078,(int)chestY+883);			
		}
		if(object5 == true){
			chest4.draw((int)chestX+1407,(int)chestY+420);			
		}
		//CHARACTER
		yuusha.draw(384,274);
		
		//OTHER
		g.drawString("X: " + yuusha2.getYuushaX() + "\nY: " + yuusha2.getYuushaY(), 600, 10);//hero coordinates
		abc.render(gc, g);
		g.drawRect(abc.getX(), abc.getY(), abc.getWidth(), abc.getHeight());
		font.drawString(10, 10, "HP:" + yuusha2.getHp(), Color.white);
		
		//LOADING BAR-display time
		if(time<duration) {
			loading1.draw(0,0,1.5f);
			if(time>500 && time<1000){
				loading1 = loading2;
			}
			if(time>1000 && time<1500){
				loading1 = loading3;
			}
			if(time>1500 && time<2000){
				loading1 = loading4;
			}
			if(time>2000 && time<2500){
				loading1 = loading5;
			}
			if(time>2500 && time<3000){
				loading1 = loading6;
			}
			if(time>3000 && time<3500){
				loading1 = loading7;
			}
		}
	} 

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {		
		//CHARACTER-keyboard input
		if(input.isKeyDown(Input.KEY_UP)){
//			if(checkCollision(yuusha2.getYuushaX(),yuusha2.getYuushaY()-1) == true && object3 == true){
			if(checkCollision(yuusha2.getYuushaX(),yuusha2.getYuushaY()-1) == true){ 
				yuusha = moveUp;
				yuusha.update(a);
				if(input.isKeyDown(Input.KEY_LSHIFT) && object4 == true){
					yuusha2.setYuushaY(yuusha2.getYuushaY()-1.5);
					map.setY(map.getY()+1.5);
					chestY+=1.5;
				}else{
					yuusha2.setYuushaY(yuusha2.getYuushaY()-1);
					map.setY(map.getY()+1);
					chestY++;
				}
			}else if(object3 == false){
				abc.setText("You do not have the ability to move UP");
			}
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			if(checkCollision(yuusha2.getYuushaX(),yuusha2.getYuushaY()+1) == true){
				yuusha = moveDown;
				yuusha.update(a);
				if(input.isKeyDown(Input.KEY_LSHIFT) && object4 == true){
					yuusha2.setYuushaY(yuusha2.getYuushaY()+1.5);
					map.setY(map.getY()-1.5);
					chestY-=1.5;
				}else{
					yuusha2.setYuushaY(yuusha2.getYuushaY()+1);
					map.setY(map.getY()-1);
					chestY--;
				}
			}
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
//			if(checkCollision(yuusha2.getYuushaX()-1,yuusha2.getYuushaY()) == true && object1 == true){
			if(checkCollision(yuusha2.getYuushaX()-1,yuusha2.getYuushaY()) == true){
				yuusha = moveLeft;
				yuusha.update(a);
				if(input.isKeyDown(Input.KEY_LSHIFT) && object4 == true){
					yuusha2.setYuushaX(yuusha2.getYuushaX()-1.5);
					map.setX(map.getX()+1.5);
					chestX+=1.5;
				}else{
					yuusha2.setYuushaX(yuusha2.getYuushaX()-1);
					map.setX(map.getX()+1);
					chestX++;
				}
			}else if(object1 == false){
				abc.setText("You do not have the ability to move LEFT");
			}
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
//			if(checkCollision(yuusha2.getYuushaX()+1,yuusha2.getYuushaY()) == true && object2 == true){
			if(checkCollision(yuusha2.getYuushaX()+1,yuusha2.getYuushaY()) == true){	
				yuusha = moveRight;
				yuusha.update(a);
				if(input.isKeyDown(Input.KEY_LSHIFT) && object4 == true){
					yuusha2.setYuushaX(yuusha2.getYuushaX()+1.5);
					map.setX(map.getX()-1.5);
					chestX-=1.5;
				}else{
					yuusha2.setYuushaX(yuusha2.getYuushaX()+1);
					map.setX(map.getX()-1);
					chestX--;
				}
			}else if(object2 == false){
				abc.setText("You do not have the ability to move RIGHT");
			}
		}
		if(input.isKeyDown(Input.KEY_SPACE)){
			findingInteraction();
		}
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			sbg.enterState(0);
		}
		if(input.isKeyPressed(Input.KEY_I)){
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
				chest1 = openmono;
				System.out.println("Unlocked at: "+a+", "+b);
				abc.setText("Unlocked LEFT Movement");
			}
		}
		if(a == 21.0 && b == 77.0){
			if(object2 == false){				
				object2 = true;
				chest2 = openmono;
				System.out.println("Unlocked at: "+a+", "+b);
				abc.setText("Unlocked RIGHT Movement");
			}
		}
		if(a == 45.0 && b == 77.0){
			if(object3 == false){				
				object3 = true;
				chest3 = openmono;
				System.out.println("Unlocked at: "+a+", "+b);
				abc.setText("Unlocked UP Movement");
			}
		}
		if((a == 38.0 || a == 39.0) && (b == 55.0 || b == 56.0)){
			if(object4 == false){				
				object4 = true;
				System.out.println("Unlocked at: "+a+", "+b);
				abc.setText("Unlocked RUNNING SHOES. Hold LEFT SHIFT to RUN.");
			}
		}
		if(a == 55.0 && b == 63.0){
			if(object5 == false){				
				object5 = true;
				tiledmap = colormap;
				chest1 = open;
				chest2 = open;
				chest3 = open;
				chest4 = open;
				System.out.println("Unlocked at: "+a+", "+b);
				abc.setText("Unlocked the COLOR of the world");
			}
		}
		if(a == 58.0 && b == 28.0){
			if(object6 == false){				
				object6 = true;
				System.out.println("Unlocked at: "+a+", "+b);
				abc.setText("You hear a weird voice calling out.");
			}
		}
		if(a == 35.0 && b == 65.0){
			abc.setText("The Village of the Beginning\nCurrent Population: 1");			
		}
		if(a == 49.0 && b == 65.0){
			abc.setText("The Forest of the Beginning. Beware of hidden monsters.");			
		}
		if(a == 58.0 && b == 34.0){
			abc.setText("Graveyard for all the fallen warriors that fought against the demon king.\nThis is also where the previous fallen HERO lies.");			
		}
	}

	public int getID() {
		return 1;
	}
	public static Player getYuusha(){
		return yuusha2;
	}
}

