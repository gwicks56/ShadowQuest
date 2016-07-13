/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Geordie Wicks 185828> <gwicks>
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
	public static final double SPEED = 0.25; // speed per millisecond 
	
	private TiledMap map; // game map
	private Player player; // the player
	private Camera camera; // the camera	
	private boolean blocked[][]; // blocked map
	
	
    /** Create a new World object. */
    public World()
    throws SlickException
    {
        map = new TiledMap("assets/map.tmx", "assets");
        player = new Player(756,684);
        camera = new Camera(player, RPG.screenwidth, RPG.screenheight);
        camera.update();        
        
        // build blocked map
        blocked = new boolean[map.getWidth()][map.getHeight()];
		for (int x = 0; x <map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				int tileID = map.getTileId(x, y, 0);
				String value = map.getTileProperty(tileID, "block", "0");
				if (value.equals("1"))
					blocked[x][y] = true;
			}
		}
    }

    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(double dir_x, double dir_y, int delta)
    throws SlickException
    {
        double offset_x = dir_x * SPEED * delta;
        double offset_y = dir_y * SPEED * delta;
        
        // check block tile
        double newx = player.getxPos() + offset_x;
        double newy = player.getyPos() + offset_y;
        int x = (int)(newx/map.getTileWidth());
        int y = (int)(newy/map.getTileHeight());
        
        //only move if tile is in map and not blocked
        if (x >= 0 && y >= 0 && x < map.getWidth() && y < map.getHeight() && !blocked[x][y]){
	        player.update(dir_x, dir_y, offset_x, offset_y);
	        camera.update();    
        }
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {    	        
    	// render map
    	int topOffsetInTiles = RPG.screenheight / map.getTileHeight() / 2;
		int leftOffsetInTiles = RPG.screenwidth / map.getTileWidth() / 2;
		double cameraX = camera.getxPos()/map.getTileWidth();
    	double cameraY = camera.getyPos()/map.getTileHeight();
    	int cameraTileX = (int) cameraX;
		int cameraTileY = (int) cameraY;
		int cameraTileOffsetX = (int) ((cameraTileX - cameraX) * map.getTileWidth());
		int cameraTileOffsetY = (int) ((cameraTileY - cameraY) * map.getTileHeight());
		int x = cameraTileOffsetX - player.getWidth()/2;
		int y = cameraTileOffsetY - player.getHeight()/2 - 6;
		int sx = cameraTileX - leftOffsetInTiles - 1;
		int sy = cameraTileY - topOffsetInTiles - 1;		
        map.render(x, y, sx, sy, 13, 11);
        
        // render player
        float centerX = RPG.screenwidth/2-player.getWidth()/2;
        float centerY = RPG.screenheight/2-player.getHeight()/2; 
    	player.getImage().draw(centerX, centerY);
    	//g.drawString("player xPos: " + camera.getxPos() + "\nplayer yPos : "+ camera.getyPos() , 400, 200);
    	//g.drawString("camera Min xPos: " + camera.getMinX() + "\nplayer Max X Pos : "+ camera.getMaxX() , 200, 200);
    	//g.drawString("camera Min yPos: " + camera.getMinY() + "\nplayer Max Y Pos : "+ camera.getMaxY() , 200, 400);
    }
}

