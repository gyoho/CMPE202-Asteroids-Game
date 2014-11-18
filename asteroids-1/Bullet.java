import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
/**
 * A bullet that can hit asteroids.
 * 
 * @author Poul Henriksen
 */
public class Bullet extends SmoothMover
{
    /** The damage this bullet will deal */
    private static final int damage = 16;

    /** A bullet looses one life each act, and will disappear when life = 0 */
    private int life = 50;
    
    public static int xPos;
    public static int yPos;
    public static int rotate;

    private GreenfootSound shoot = new GreenfootSound("EnergyGun.wav");

    public Bullet()
    {
    }

    public Bullet(Vector speed, int rotation)
    {
        super(speed);
        setRotation(rotation);
        addForce(new Vector(rotation, 15));
        //shoot.setVolume(90);
        shoot.play();
    }

    /**
     * The bullet will damage asteroids if it hits them.
     */
    public void act()
    {
        xPos = getX();
        yPos = getY();
        rotate = getRotation();
        if(life <= 0) {
            getWorld().removeObject(this);
        } 
        else {
            life--;
            move();
            checkAsteroidHit();
        } 
    }

    /**
     * Check whether we have hit an asteroid.
     */
    private void checkAsteroidHit()
    {
        Asteroid asteroid = (Asteroid) getOneIntersectingObject(Asteroid.class);
        if (asteroid != null){
            for (int i = 0; i < Greenfoot.getRandomNumber(10) + 1 ; i++)
            {
                getWorld().addObject(new Particle("hit" ,getSpeed() / 15), getX(), getY());
            }
            getWorld().removeObject(this);
            asteroid.hit(damage);
        }
    }
}