import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.util.List;

public class Rocket extends SmoothMover {
    public static final int gunReloadTime = 10;         // The minimum delay between firing the gun.
    public static final int protonReloadTime = 300;    // The minimum delay between proton wave bursts.

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
    
    // Posesses all states
    IRocketState normalState;
    IRocketState advancedState;
    IRocketState currentState;
   
    
    /**
     * Initilise this rocket.
     */
    public Rocket() {
        int a = image.getWidth();
        int b = image.getHeight();
        width = a;
        height = b;
        PIndicatorAdded = false;
        
        reloadDelayCount = 5;
        protonDelayCount = 300;
        
        // initially slowly drifting
        addForce(new Vector(13, 0.3));
        
        // instantate all states
        normalState = new NormalState(this);
        advancedState = new AdvancedState(this);
        currentState = normalState;
    }

    /**
     * Do what a rocket's gotta do. (Which is: mostly flying about, and turning,
     * accelerating and shooting when the right keys are pressed.)
     */
    public void act() {
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
    public void checkKeys() {
        if (Greenfoot.isKeyDown("up")) {
            currentState.ignite();
        }

        if (Greenfoot.isKeyDown("left")) {
            turnLeft();
        }
        
        if (Greenfoot.isKeyDown("right")) {
            turnRight();
        }
        
        if (Greenfoot.isKeyDown("space")) {
            fire();
        }
        
        if (Greenfoot.isKeyDown("a")) {
            currentState.startProtonWave();
        }
    }

    public void setState(IRocketState state) {
        this.currentState = state;
    }
    
    public IRocketState getState() {
        return currentState;
    }
    
    public IRocketState getNormalState() {
        return normalState;
    }
    
    public IRocketState getAdvancedState() {
        return advancedState;
    }
    
    /**
     * Check whether we are colliding with an asteroid.
     */
    private void checkCollision() {
        Actor a = getOneIntersectingObject(Asteroid.class);
        if (a != null) {
            Space space = (Space) getWorld();
            for (int i = 0; i < 60 + 10; i++)
            {
                getWorld().addObject(new Particle("die", 1.7), getX(), getY());
            }
            space.removeObject(this);
            space.gameOver();
        }
    }

    private void checkHud() {
        if(currentState == advancedState && !PIndicatorAdded && protonDelayCount >= protonReloadTime) {
            getWorld().addObject(new WaveDisplay(), getWorld().getWidth() / 2, getWorld(). getHeight() / getWorld().getHeight() + 20);
            PIndicatorAdded = true;
        }
    }
    
    void turnLeft() {
        setRotation(getRotation() - 5);
        double boostPos[][] = {{-2, 27}};
        for (double[] pos: boostPos) {
    
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
    
    void turnRight() {
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
    
    public void fire() {
        if (reloadDelayCount >= gunReloadTime) {           
            double bulletPos[][] = {{20, 5}, {20, -5}};
            for (double[] pos: bulletPos) {

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
    
    public void showFlame() {
        int i = Greenfoot.getRandomNumber (50);
        double thrusterPos[][] = {{-20, 0}};
        if (i >= 0) {
            for (double[] pos: thrusterPos) {
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
    
    public void setProtonDelayCount(int protonDelayCount) {
        this.protonDelayCount = protonDelayCount;
    }
    
    public int getProtonDelayCount() {
        return protonDelayCount;
    }
}