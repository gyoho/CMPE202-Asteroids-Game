import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HomeScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HomeScreen extends World
{

    /**
     * Constructor for objects of class HomeScreen.
     * 
     */
    public HomeScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
    }
    
    public void act(){
    
        if(Greenfoot.isKeyDown("enter"))
        Greenfoot.setWorld(new Modes());
        
        
        if(Greenfoot.isKeyDown("c"))
        Greenfoot.setWorld(new Instruction());
        
        
        
    }
}
