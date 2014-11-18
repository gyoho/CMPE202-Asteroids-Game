import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Firework here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Firework extends SmoothMover
{
    private double life = Greenfoot.getRandomNumber(140) + 40;

    public static int rotation;
    public static double mySpeed;

    GreenfootImage image = getImage();
    public Firework()
    {
        setRotation(getRotation() - 90);
        image.scale(image.getWidth() / 2, image.getHeight() / 2);
        addForce(new Vector(getRotation(), 2));
    }

    /**
     * Act - do whatever the Firework wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        rotation = getRotation();
        mySpeed = getSpeed();
        showFlame();
        move();
        checkLife();
        life -= .9;
    }    

    private void showFlame()
    {
        int i = Greenfoot.getRandomNumber (50);
        double thrusterPos[][] = {{-10, 1}};
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

                getWorld().addObject(new Flame("Firework"), (int) worldX, (int) worldY);
            }

        }
    }

    private void checkLife()
    {
        double speed[] = {.1, .2, .3, .4, .5, .6, .7, .8, .9, 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.9, 3};
        if (life <= 1)
        {
            for (int i = 0; i < Greenfoot.getRandomNumber(100) + 50; i++)
            {
                int r = Greenfoot.getRandomNumber(200) + 55;
                int g = Greenfoot.getRandomNumber(200) + 55;
                int b = Greenfoot.getRandomNumber(200) + 55;
                getWorld().addObject(new Particle(r, g, b, speed[Greenfoot.getRandomNumber(29)]), getX(), getY());
            }
            getWorld().removeObject(this);
            Space.fireworkNumber--;
        }
    }
}
