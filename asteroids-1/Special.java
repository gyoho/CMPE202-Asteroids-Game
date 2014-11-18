import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A rocket that can be controlled by the arrowkeys: up, left, right.
 * The gun is fired by hitting the 'space' key. 'z' releases a proton wave.
 * 
 * @author Poul Henriksen
 * @author Michael Kolling
 * 
 * @version 1.0
 */
public class Special extends SmoothMover
{
    private final int LIFETIME = 350;
    public int life = 0;
    private GreenfootImage image = getImage();
    /**
     * Initilise this rocket.
     */
    public Special()
    {
        image.scale(image.getWidth() / 2, image.getHeight() / 2);
    }

    /**
     * Do what a rocket's gotta do. (Which is: mostly flying about, and turning,
     * accelerating and shooting when the right keys are pressed.)
     */
    public void act()
    {
        checkCollision();
        life++;
    }
    
    /**
     * Check whether we are colliding with an asteroid.
     */
    private void checkCollision() 
    {
        Actor a = getOneIntersectingObject(Rocket.class);
        if (a != null) 
        {
            Rocket.specialWeaponNumber++;
            Space.sNumber--;
            getWorld().removeObject(this);
        }
        if (life >= LIFETIME)
        {
            Space.sNumber--;
            getWorld().removeObject(this);
        }
    }
    
}