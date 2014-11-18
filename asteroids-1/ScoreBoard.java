import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

/**
 * The ScoreBoard is used to display results on the screen. It can display some
 * text and several numbers.
 * 
 * @author M Kolling
 * @version 1.0
 */
public class ScoreBoard extends Actor
{
    public static final float FONT_SIZE = 48.0f;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    public static int score;
    public String saying;
    
    private int rgb[] = {Greenfoot.getRandomNumber(255), Greenfoot.getRandomNumber(255), Greenfoot.getRandomNumber(255)};

    /**
     * Create a score board with dummy result for testing.
     */
    public ScoreBoard()
    {
        this(100, "Game Over");
    }

    public void act()
    {
        if(score != Counter.value)
        {
            score = Counter.value;
            makeImage(saying, "Score: ", score);
        }
        
        for(int i = 0; i < 3; i++)
        {
            if(rgb[i] <= 80)
            {
                rgb[i] = 255 - rgb[i];
            }
        }
    }

    /**
     * Create a score board for the final result.
     */
    public ScoreBoard(int score, String saying)
    {
        score = this.score;
        this.saying = saying;
        makeImage(saying, "Score: ", score);
    }

    /**
     * Make the score board image.
     */
    private void makeImage(String title, String prefix, int score)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        image.setColor(new Color(0, 0, 0, 0));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(new Color(rgb[0], rgb[1], rgb[2]));
        image.drawString(title, 30, image.getHeight()/3);
        image.drawString(prefix + score, 30, image.getHeight()/3 * 2);
        image.drawString("Level: " + Space.level, 30, 150);
        setImage(image);
    }
}
