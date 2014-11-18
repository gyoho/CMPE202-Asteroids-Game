import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class FuelIndicator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShieldPercent extends Actor
{
    GreenfootImage image = getImage();
    private String percent = (int) Rocket.shieldAmmount + "%";
    private int length = percent.length();
    public ShieldPercent()
    {
        image.clear();
        image.setColor(new Color(78, 43, 105));
        image.scale(100, image.getHeight());
        image.fillRect(0, 0, (int) Rocket.shieldAmmount, image.getHeight());
        image.setColor(Color.RED);
        image.drawString(percent, (image.getWidth() / 2) - (percent.length() * 3), image.getHeight());
    }

    /**
     * Act - do whatever the FuelIndicator wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateImage();
        percent = (int) Rocket.shieldAmmount + "%";
    }    

    private void updateImage()
    {
        if (Rocket.shieldAmmount <= 2)
        {
            image.clear();
            image.setColor(Color.RED);
            image.drawString(percent, (image.getWidth() / 2) - (percent.length() * 3), image.getHeight());
         }
        else if(Rocket.fuelAmmount > 2)
        {
            image.clear();
            image.setColor(new Color(78, 43, 105));
            image.fillRect(0, 0, (int) Rocket.shieldAmmount, image.getHeight());
            image.setColor(Color.RED);
            image.drawString(percent, (image.getWidth() / 2) - (percent.length() * 3), image.getHeight());
        }
    }
}

