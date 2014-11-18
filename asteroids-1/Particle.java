import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;

/**
 * Write a description of class Particle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Particle extends SmoothMover
{
    GreenfootImage image = getImage();
    private final int LIFE = Greenfoot.getRandomNumber(200) + 30;
    private int myLife;
    private static int rotation;
    private static int rotate;
    private double speed;
    private int randomDirection;
    public Particle(double speed)
    {
        this.speed = speed;
        image.scale(Greenfoot.getRandomNumber(3) + 1, 1);
        addForce(new Vector(Greenfoot.getRandomNumber(90) + returnRotation(), speed));
        myLife = 0;
        setRotation(Rocket.Rotation);
    }

    public Particle(String death, double speed)
    {
        if (death == "die")
        {
            image.scale(2, 2);
            image.clear();
            image.setColor(new Color(255, 255, 255));
            image.fill();
            int r = Greenfoot.getRandomNumber(360);
            rotate = r;
            addForce(new Vector (r, speed));
            myLife = 0;
        }
        else if (death == "destroyed")
        {
            image.scale(1, 1);
            image.clear();
            image.setColor(new Color(155, 155, 155));
            image.fill();
            int r = Greenfoot.getRandomNumber(360);
            rotate = r;
            addForce(new Vector (r, speed));
            myLife = 100;
        }
        else if (death == "hit")
        {
            image.scale(Greenfoot.getRandomNumber(3) + 1, 1);
            image.clear();
            image.setColor(new Color(255, 255, 0));
            image.fill();
            int r = Greenfoot.getRandomNumber(360);
            rotate = r;
            addForce(new Vector (returnRotation() + randomDirection(), speed));
            myLife = 100;
            setRotation(Bullet.rotate);
        }
    }
    
    public Particle(int r, int g, int b, double speed)
    {
        image.scale(2, 2);
            image.clear();
            image.setColor(new Color(r, g, b));
            image.fill();
            int ro = Greenfoot.getRandomNumber(360);
            rotate = ro;
            addForce(new Vector (ro, speed));
            myLife = 100;
    }

    /**
     * Act - do whatever the Particle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
        myLife++;
        checkMyLife();
    }    

    private static int returnRotation()
    {
        if(Rocket.Rotation > 180)
        {
            rotation = Rocket.Rotation - 180;
        }
        else if(Rocket.Rotation <= 180)
        {
            rotation = Rocket.Rotation + 180;
        }
        if(Bullet.rotate > 180)
        {
            rotation = Bullet.rotate - 180;
        }
        else if(Bullet.rotate <= 180)
        {
            rotation = Bullet.rotate + 180;
        }
        rotate = rotation;
        return rotation;
        }
    private void checkMyLife()
    {
        if(myLife >= LIFE)
        {
            getWorld().removeObject(this);
        }
    }

    private int randomDirection()
    {
        int i = Greenfoot.getRandomNumber(100);
        if (i > 50)
        {
            randomDirection = Greenfoot.getRandomNumber(99) + 1;
        }
        else if (i <= 50)
        {
            randomDirection = -Greenfoot.getRandomNumber(99) + 1;
        }
        return randomDirection;
    }
    
}
