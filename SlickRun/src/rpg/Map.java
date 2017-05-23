package rpg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends BasicGameState {
	
	private TiledMap map;
	
//	private int x,y;

	public Map(int state) {
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = new TiledMap("resources/TiledMap/rpgmap.tmx");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		map.render(0,0);
		
//		g.fillRect(x*32,y*32,32,32);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
//		int objectLayer = map.getLayerIndex("Object");
//		
//		map.getTileId(0,0,objectLayer);
	}

	public int getID() {
		return 0;
	}
}
