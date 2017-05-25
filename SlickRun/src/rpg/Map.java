package rpg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends BasicGameState {
		
	private TiledMap map;
		
	private double x,y;
	private int mapX,mapY;

	public Map(int state) {
			
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = new TiledMap("resources/TiledMap/rpgmap.tmx");
		
		x = 0;
		y = 0;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		map.render((int)x-32,(int)y-32,mapX,mapY,mapX+26,mapY+26);
	
		g.fillOval(380,280,40,40);
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
			y += a/3.0f;
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
//			if(map.getTileId(x,y+1,layer) == 0){
//				y++;
//			}
			y -= a/3.0f;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
//			if(map.getTileId(x-1,y,layer) == 0){
//				x--;
//			}
			x += a/3.0f;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
//			if(map.getTileId(x+1,y,layer) == 0){
//				x++;
//			}
			x -= a/3.0f;
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
	}

	public int getID() {
		return 0;
	}
	
}



