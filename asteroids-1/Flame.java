import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;
/**
 * Write a description of class Flame here.
 * 
 * @author (Dusty) 
 * @version (1.0)
 */
public class Flame extends SmoothMover
{
    GreenfootImage flame = getImage();
    private double scale;
    private static int rotation;
    private static int rotate;
    public static int speed;
    public Flame(int i)
    {
        scale = 15;
        //flame.clear();
        flame.scale((int) scale, (int) scale);
        //flame.setColor(new Color(255, 150, 75));
        //flame.fillOval(0, 0, flame.getWidth(), flame.getHeight());
        addForce(new Vector(returnRotation(), Rocket.mySpeed + .5));
    }

    public Flame(int i, int ii)
    {
        scale = 9;
        flame.clear();
        flame.scale((int) scale, (int) scale);
        flame.setColor(new Color(100, 100, 100));
        flame.fillOval(0, 0, flame.getWidth(), flame.getHeight());
        if (ii == 1)
        {
            addForce(new Vector( returnRotation() + 90, 2));
        }
        if (ii == 2)
        {
            addForce(new Vector( returnRotation() - 90, 2));
        }
    }

    public Flame(String item)
    {
        scale = 10;
        flame.scale((int) scale, (int) scale);

        addForce(new Vector( returnFireworkRotation(), Firework.mySpeed));
    }

    /**
     * Act - do whatever the Flame wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
        shrink();
    }

    private void shrink()
    {
        int i = Greenfoot.getRandomNumber(50);
        if (scale <= 1.5)
        {
            getWorld().removeObject(this);
        }

        if (i >= 20)
        {
            scale -= .5;
            flame.scale((int) scale, (int) scale);
        }

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
        rotate = rotation;
        return rotation;
    }

    private static int returnFireworkRotation()
    {
        if(Firework.rotation > 180)
        {
            rotation = Firework.rotation - 180;
        }
        else if(Firework.rotation <= 180)
        {
            rotation = Firework.rotation + 180;
        }
        rotate = rotation;
        return rotation;
    }

}
