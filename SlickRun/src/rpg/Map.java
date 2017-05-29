package rpg;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends BasicGameState {
	
	//MAP
	private TiledMap map;
	private double x,y;
	private int mapX = 11,mapY = 49;
	//CHARACTER
	private Animation yuusha,moveUp,moveDown,moveLeft,moveRight;
	//LOADING BAR
	private Image loading;
	int time = 0;
	int duration = 2000;

	public Map(int state) {
			
	}
 
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//MAP-display
		map = new TiledMap("resources/TiledMap/rpgmap.tmx");
		x = 0;
		y = 0;
		
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
		//CHARACTER
		yuusha.draw(385,274);
		//LOADING BAR-display time
		if (time<duration) {
			loading.draw(0,0,1.5f);
		}	
	}

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
//		int layer = map.getLayerIndex("Tile Layer 1");
//		
//		map.getTileId(0,0,layer);
		//CHARACTER-keyboard input
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP)){
//			if(map.getTileId(x,y-1,layer) == 0){
//				y--;
//			}
			yuusha = moveUp;
			yuusha.update(a);
			y += a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
//			if(map.getTileId(x,y+1,layer) == 0){
//				y++;
//			}
			yuusha = moveDown;
			yuusha.update(a);
			y -= a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
//			if(map.getTileId(x-1,y,layer) == 0){
//				x--;
//			}
			yuusha = moveLeft;
			yuusha.update(a);
			x += a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
//			if(map.getTileId(x+1,y,layer) == 0){
//				x++;
//			}
			yuusha = moveRight;
			yuusha.update(a);
			x -= a/4.0f;
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

	public int getID() {
		return 0;
	}
	
}



