import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Modes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Modes extends World
{

    /**
     * Constructor for objects of class Modes.
     * 
     */
    public Modes()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
    }
    
    
     public void act(){
    
        if(Greenfoot.isKeyDown("e")) {
            Greenfoot.setWorld(new Space(1));
        }
        
        if(Greenfoot.isKeyDown("n")) {
            Greenfoot.setWorld(new Space(2));
        }
        
        if(Greenfoot.isKeyDown("h")) {
            Greenfoot.setWorld(new Space(3));
        } 
    }
}
