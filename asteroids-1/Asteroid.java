import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A rock in space
 * 
 * @author Poul Henriksen
 */
public class Asteroid extends SmoothMover
{
    /** Size of this asteroid */
    private int size;

    /** When the stability reaches 0 the asteroid will explode */
    private int stability;
    
    private Counter counter;

    public static int aSize;
    
    public Asteroid(Counter counter, int size) {
        super(new Vector(Greenfoot.getRandomNumber(360), 2));
        this.counter = counter;
        aSize = size;
        setSize(size);
    }
    
    public Asteroid(int size, Vector speed) {
        super(speed);
        setSize(size);
    }
    
    public void act() {         
        move();
        aSize = size;
    }

    /**
     * Set the size of this asteroid. Note that stability is directly
     * related to size. Smaller asteroids are less stable.
     */
    public void setSize(int size) {
        stability = size;
        this.size = size;
        GreenfootImage image = getImage();
        image.scale(size, size);
    }

    /**
     * Return the current stability of this asteroid. (If it goes down to 
     * zero, it breaks up.)
     */
    public int getStability() {
        return stability;
    }
    
    /**
     * Hit this asteroid dealing the given amount of damage.
     */
    public void hit(int damage) {
        counter.add(damage * Space.level);
        stability = stability - damage;
        if(stability <= 0) 
            breakUp ();         
    }
    
    /**
     * Break up this asteroid. If we are still big enough, this will create two
     * smaller asteroids. If we are small already, just disappear.
     */
    private void breakUp() {
        Greenfoot.playSound("Explosion.wav");
        
        if(size <= 16) 
        {
            getWorld().removeObject(this);
            Space.aNumber--;
        }
        else 
        {
            int r = getMovement().getDirection() + Greenfoot.getRandomNumber(45);
            double l = getMovement().getLength();
            Vector speed1 = new Vector(r + 60, l * 1.2);
            Vector speed2 = new Vector(r - 60, l * 1.2);        
            Asteroid a1 = new Asteroid(size/2, speed1);
            Asteroid a2 = new Asteroid(size/2, speed2);
            getWorld().addObject(a1, getX(), getY());
            getWorld().addObject(a2, getX(), getY());        
            a1.move();
            a2.move();
            Space.aNumber = Space.aNumber + 2;
            
            for (int i = 0; i < 30 + 10; i++)
            {
                //getWorld().addObject(new Particle("destroyed", 1.0), getX(), getY());
            }
        
            getWorld().removeObject(this);
            Space.aNumber--;
        }
    }
}