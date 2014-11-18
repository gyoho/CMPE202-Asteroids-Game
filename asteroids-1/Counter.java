import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.awt.Color;
import java.awt.Graphics;

/**
 * Counter that displays a text and number.
 * 
 * @author Michael Kolling
 * @version 1.0.1
 */
public class Counter extends Actor
{
    private static final Color textColor = new Color(29, 234, 59);

    public static int value;
    private static int target = 0;
    private String text;
    private int stringLength;
    
    public static int levelDelay;
    

    public Counter()
    {
        this("");
    }

    public Counter(String prefix)
    {
        text = prefix;
        stringLength = (text.length() + 2) * 10;

        setImage(new GreenfootImage(stringLength, 16));
        GreenfootImage image = getImage();
        image.setColor(textColor);

        value = 0;
        target = 0;
        levelDelay = 0;
        updateImage();
        
    }

    public void act() {
        updateImage();
        levelDelay++;
    }

    public static void add(int score)
    {
        value += score;
    }

    public int getValue()
    {
        return target;
    }

    /**
     * Make the image
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + value, 1, 12);
    }

    public void setValue(int newValue)  
    {  
        target = newValue;  
        value = newValue;  
        updateImage();  
    }  
    
}
