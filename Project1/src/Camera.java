/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Geordie Wicks 185828> <gwicks>
 */

import org.newdawn.slick.SlickException;

/** Represents the camera that controls our viewpoint.
 */
public class Camera
{

    /** The unit this camera is following */
    private Player unitFollow;
    
    /** The width and height of the screen */
    /** Screen width, in pixels. */
    public final int screenwidth;
    /** Screen height, in pixels. */
    public final int screenheight;

    
    /** The camera's position in the world, in x and y coordinates. */
    private double xPos;
    private double yPos;

    
    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    
    /** Create a new World object. */
    public Camera(Player player, int screenwidth, int screenheight)
    {   
    	unitFollow = player;    	
    	this.screenwidth = screenwidth;
    	this.screenheight = screenheight;
    }

    /** Update the game camera to recentre it's viewpoint around the player 
     */
    public void update()
    throws SlickException
    {

    	
    	
        xPos = unitFollow.getxPos();
    	
      
       
        yPos = unitFollow.getyPos();
        
        

        
    }
    
    /** Returns the minimum x value on screen 
     */
    public int getMinX(){
        return (int)xPos - screenwidth/2;        
    }
    
    /** Returns the maximum x value on screen 
     */
    public int getMaxX(){
    	return (int)xPos + screenwidth/2;
    }
    
    /** Returns the minimum y value on screen 
     */
    public int getMinY(){
        return (int)yPos - screenheight/2;
    }
    
    /** Returns the maximum y value on screen 
     */
    public int getMaxY(){
    	return (int)yPos + screenheight/2;
    }

    /** Tells the camera to follow a given unit. 
     */
    public void followUnit(Object unit)
    throws SlickException
    {
        unitFollow = (Player)unit;
    }
    
}