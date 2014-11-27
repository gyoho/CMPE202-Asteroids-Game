import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.awt.Color;
import java.awt.Graphics;

public class LevelCounter extends Actor implements IObserver{
    private static final Color textColor = new Color(29, 234, 59);

    public static int level;
    private String text;
    private int stringLength;

    public LevelCounter() {
        this(0);
    }

    public LevelCounter(int level) {
        text = "Level: ";
        stringLength = (text.length() + 2) * 10;

        setImage(new GreenfootImage(stringLength, 16));
        GreenfootImage image = getImage();
        image.setColor(textColor);

        this.level = level;
        updateImage();
    }
    
    public void update() {
        level = Space.level;
        updateImage();
    }

    /**
     * Make the image
     */
    private void updateImage() {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + level, 1, 12);
    }

}
