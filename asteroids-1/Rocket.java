import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.util.List;

/**
 * A rocket that can be controlled by the arrowkeys: up, left, right.
 * The gun is fired by hitting the 'space' key. 'z' releases a proton wave.
 * 
 * @author Poul Henriksen
 * @author Michael Kolling
 * 
 * @version 1.0
 */
public class Rocket extends SmoothMover
{
    private static final int gunReloadTime = 10;         // The minimum delay between firing the gun.
    private static final int protonReloadTime = 500;    // The minimum delay between proton wave bursts.

    private int reloadDelayCount;               // How long ago we fired the gun the last time.
    private int protonDelayCount;               // How long ago we fired the proton wave the last time.

    private GreenfootImage rocket = new GreenfootImage("rocket.png");    
    private GreenfootImage image = getImage();

    public static int xLoc;
    public static int yLoc;
    public static int Rotation;
    public static double mySpeed;

    public static int width;
    public static int height;

    public static boolean PIndicatorAdded;
    public static boolean bWeaponActivated;
    /**
     * Initilise this rocket.
     */
    public Rocket()
    {
        int a = image.getWidth();
        int b = image.getHeight();
        width = a;
        height = b;
        PIndicatorAdded = false;
        
        reloadDelayCount = 5;
        protonDelayCount = 300;
        
        addForce(new Vector(13, 0.3)); // initially slowly drifting
    }

    /**
     * Do what a rocket's gotta do. (Which is: mostly flying about, and turning,
     * accelerating and shooting when the right keys are pressed.)
     */
    public void act()
    {
        move();
        checkKeys();

        reloadDelayCount++;
        protonDelayCount++;

        xLoc = getX();
        yLoc = getY();
        Rotation = getRotation();
        mySpeed = getSpeed();

        checkHud();
        checkCollision();
    }

    /**
     * Check whether there are any key pressed and react to them.
     */
    private void checkKeys() 
    {
        ignite(Greenfoot.isKeyDown("up"));

        if (Greenfoot.isKeyDown("left")) 
        {
            setRotation(getRotation() - 5);
            double boostPos[][] = {{-2, 27}};
            for (double[] pos: boostPos)
            {

                double x = pos[0];
                double y = pos[1];

                double dir = calculateDirection(x, y);
                double dist = calculateMagnitude(x, y);

                dir += getRotation();

                double worldX = getX() + calculateX(dir, dist);
                double worldY = getY() + calculateY(dir, dist);

                getWorld().addObject(new Flame(1, 2), (int) worldX, (int) worldY);

            }
            
        }
        if (Greenfoot.isKeyDown("right")) 
        {
            setRotation(getRotation() + 5);

            double boostPos[][] = {{-2, -27}};
            for (double[] pos: boostPos)
            {

                double x = pos[0];
                double y = pos[1];

                double dir = calculateDirection(x, y);
                double dist = calculateMagnitude(x, y);

                dir += getRotation();

                double worldX = getX() + calculateX(dir, dist);
                double worldY = getY() + calculateY(dir, dist);

                getWorld().addObject(new Flame(1, 1), (int) worldX, (int) worldY);

            }

        }
        if (Greenfoot.isKeyDown("space")) 
        {
            fire();
        }
        if (Greenfoot.isKeyDown("a")) 
        {
            startProtonWave();
        }
    }

    /**
     * Check whether we are colliding with an asteroid.
     */
    private void checkCollision() 
    {
        Actor a = getOneIntersectingObject(Asteroid.class);
        if (a != null) 
        {
            Space space = (Space) getWorld();
            for (int i = 0; i < 60 + 10; i++)
            {
                getWorld().addObject(new Particle("die", 1.7), getX(), getY());
            }
            space.removeObject(this);
            space.gameOver();
        }
    }

    /**
     * Should the rocket be ignited?
     */
    private void ignite(boolean boosterOn) 
    {
        if (boosterOn)
        {
            addForce (new Vector(getRotation(), 0.3));
            showFlame();
        }
    }

    /**
     * Fire a bullet if the gun is ready.
     */
    private void fire() 
    {
        if (reloadDelayCount >= gunReloadTime) 
        {           
            double bulletPos[][] = {{20, 5}, {20, -5}};
            for (double[] pos: bulletPos)
            {

                double x = pos[0];
                double y = pos[1];

                double dir = calculateDirection(x, y);
                double dist = calculateMagnitude(x, y);

                dir += getRotation();

                double worldX = getX() + calculateX(dir, dist);
                double worldY = getY() + calculateY(dir, dist);

                Bullet bullet = new Bullet (getMovement().copy(), getRotation());
                getWorld().addObject(bullet, (int) worldX, (int) worldY);
                bullet.move();
                reloadDelayCount = 0;
            }
        }
    }

    /**
     * Release a proton wave (if it is loaded).
     */
    private void startProtonWave() 
    {
        if (Space.level > 2 && protonDelayCount >= protonReloadTime) 
        {
            ProtonWave wave = new ProtonWave();
            getWorld().addObject (wave, getX(), getY());
            getWorld().removeObjects(getWorld().getObjects(WaveDisplay.class));
            protonDelayCount = 0;
            PIndicatorAdded = false;
        }
    }

    private void checkHud()
    {

        if(!PIndicatorAdded && protonDelayCount >= protonReloadTime)
        {
            getWorld().addObject(new WaveDisplay(), getWorld().getWidth() / 2, getWorld(). getHeight() / getWorld().getHeight() + 20);
            PIndicatorAdded = true;
        }
    }

    private void showFlame()
    {
        int i = Greenfoot.getRandomNumber (50);
        double thrusterPos[][] = {{-20, 0}};
        if (i >= 0)
        {
            for (double[] pos: thrusterPos)
            {
                double x = pos[0];
                double y = pos[1];

                double dir = calculateDirection(x, y);
                double dist = calculateMagnitude(x, y);

                dir += getRotation();

                double worldX = getX() + calculateX(dir, dist);
                double worldY = getY() + calculateY(dir, dist);

                getWorld().addObject(new Flame(1), (int) worldX, (int) worldY);
            }

        }
    }

}