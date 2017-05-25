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
		
	private TiledMap map;
	private double x,y;
	private int mapX = 11,mapY = 49;
	
	private Animation yuusha,moveUp,moveDown,moveLeft,moveRight;
	private int[] animationFrame = {200,200};
	
	private Image loading;
	int time = 0;
	int duration = 2000;

	public Map(int state) {
			
	}
 
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = new TiledMap("resources/TiledMap/rpgmap.tmx");
		
		x = 0;
		y = 0;
		
		Image[] walkUp = {new Image("resources/TiledMap/yuusha32/yuushaup.png"), new Image("resources/TiledMap/yuusha32/yuushaup.png")};
		Image[] walkDown = {new Image("resources/TiledMap/yuusha32/yuushadown.png"), new Image("resources/TiledMap/yuusha32/yuushadown.png")};
		Image[] walkLeft = {new Image("resources/TiledMap/yuusha32/yuushaleft.png"), new Image("resources/TiledMap/yuusha32/yuushaleft.png")};
		Image[] walkRight = {new Image("resources/TiledMap/yuusha32/yuusharight.png"), new Image("resources/TiledMap/yuusha32/yuusharight.png")};
		moveUp = new Animation(walkUp,animationFrame,false);
		moveDown = new Animation(walkDown,animationFrame,false);
		moveLeft = new Animation(walkLeft,animationFrame,false);
		moveRight = new Animation(walkRight,animationFrame,false);
		yuusha = moveDown;
		
		loading = new Image("resources/loadingbar.jpg");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		map.render((int)x-32,(int)y-32,mapX,mapY,mapX+27,mapY+27);
		yuusha.draw(380,280);
		if (time<duration) {
			loading.draw(0,0,1.5f);
		}	
	}

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
//		int layer = map.getLayerIndex("Tile Layer 1");
//		
//		map.getTileId(0,0,layer);
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP)){
//			if(map.getTileId(x,y-1,layer) == 0){
//				y--;
//			}
			yuusha = moveUp;
			y += a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
//			if(map.getTileId(x,y+1,layer) == 0){
//				y++;
//			}
			yuusha = moveDown;
			y -= a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
//			if(map.getTileId(x-1,y,layer) == 0){
//				x--;
//			}
			yuusha = moveLeft;
			x += a/4.0f;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
//			if(map.getTileId(x+1,y,layer) == 0){
//				x++;
//			}
			yuusha = moveRight;
			x -= a/4.0f;
		}
		//rendering map with movement
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
		
		time += a;
	}

	public int getID() {
		return 0;
	}
	
}



