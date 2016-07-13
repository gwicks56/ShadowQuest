/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Geordie Wicks 185828> <gwicks>
 */


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Player {
	private Image image, left, right;
	
	// player's position in the world
	private double xPos; 
	private double yPos;
		
	public Player(double xPos, double yPos) throws SlickException {
		this.xPos = xPos;
		this.yPos = yPos;
		
		image = new Image("assets/units/player.png");
		right = image;
		left = image.getFlippedCopy(true, false);				
	}
	//get image
	public Image getImage(){
		return image;
	}

	public double getxPos() {
		return xPos;
	}

	public double getyPos() {
		return yPos;
	}
	
	public int getWidth(){
		return image.getWidth();
	}
	
	public int getHeight(){
		return image.getHeight();
	}
	
	/**
	 * Update player position
	 * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param offset_x the offset to move to in the x axis
     * @param offset_y the offset to move to in the y axis
	 */
	public void update(double dir_x, double dir_y, double offset_x, double offset_y){
		if (dir_x == -1){ // move left
			image = left;
		}
		else if (dir_x == 1){ // move right
			image = right;
		}
		
		xPos += offset_x;
		yPos += offset_y;
	}
	
}
