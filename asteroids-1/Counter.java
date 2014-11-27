import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.awt.Color;
import java.awt.Graphics;

public class Counter extends Actor {
    private static final Color textColor = new Color(29, 234, 59);

    public static int value;
    private static int target = 0;
    private String text;
    private int stringLength;
    
    public static int levelDelay;
    
    // Strategy Pattern
    private IRecordStrategy strategy;

    public Counter(String prefix, int playMode) {
        text = prefix;
        stringLength = (text.length() + 2) * 10;

        switch(playMode) {
            case 1: strategy = new EasyMode(); break;
            case 2: strategy = new NormalMode(); break;
            case 3: strategy = new HardMode(); break;
        }
        
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
    
    public void setStrategy(IRecordStrategy strategy) {
	    this.strategy = strategy;
	}
	
    public void add(int score) {
       strategy.updateScore(score);
    }

    public int getValue() {
        return target;
    }

    private void updateImage() {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + value, 1, 12);
    }

    public void setValue(int newValue) {  
        target = newValue;  
        value = newValue;  
        updateImage();  
    }  
    
}
