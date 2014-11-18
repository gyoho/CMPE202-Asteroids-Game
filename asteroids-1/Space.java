import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.*;

/**
 * Space. Something for rockets to fly in.
 * 
 * @author Michael Kolling
 * @version 1.1
 */
public class Space extends World
{
    private Counter scoreCounter;
    private LevelCounter levelCounter;
    private int startAsteroids = 1;
    private int otherAsteroidNumber = 3;

    public static int level = 1;
    public static int aNumber;
    public static int fireworkNumber;
    private boolean played = false;
    private boolean gameOver = false;

    GreenfootSound bgm = new GreenfootSound("bgm.mp3");
    GreenfootSound over = new GreenfootSound("game over.mp3");
    public Space() 
    {
        super(800, 600, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        createStars(500);

        Rocket rocket = new Rocket();
        addObject(rocket, getWidth()/2 + 100, getHeight()/2);

        aNumber = 0;
        fireworkNumber = 0;
        addAsteroids(startAsteroids);

        levelCounter = new LevelCounter(level);
        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 60, 580);
        addObject(levelCounter, 60, 550);
        ProtonWave.initializeImages();

        level = 1;

        setPaintOrder(LevelCounter.class, Counter.class, ScoreBoard.class, Rocket.class, Firework.class, Flame.class, Particle.class, Asteroid.class);
    }

    public void act()
    {
        checkANumber();
        playBgm();

        if(gameOver)
        {
            fireworks();
        }
    }

    /**
     * Add a given number of asteroids to our world. Asteroids are only added into
     * the left half of the world.
     */
    private void addAsteroids(int count) 
    {

        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth()/2);
            int y = Greenfoot.getRandomNumber(getHeight()/2);
            addObject(new Asteroid(), x, y);
            aNumber++;
        }

    }

    /**
     * Crete a given number of stars in space.
     */
    private void createStars(int number) 
    {
        GreenfootImage background = getBackground();             
        for(int i=0; i < number; i++) 
        {
            int x = Greenfoot.getRandomNumber( getWidth());
            int y = Greenfoot.getRandomNumber( getHeight());
            int color = Greenfoot.getRandomNumber(175) + 50;
            background.setColor(new Color(color,color,color));
            background.fillOval(x, y, 2, 2);
        }
    }

    /**
     * This method is called when the game is over to display the final score.
     */
    public void gameOver() 
    {
        String gameSaying[] = {"YOU SUCK!!", "YOU BLOW!!", "NOT BAD", "WINNER!...NOT", "GETTING GOOD", "GOOD JOB", "IMPRESSIVE"};

        gameOver = true;
        if (level >= 1 && level < 8)
        {
            addObject(new ScoreBoard(Counter.value, gameSaying[level-1]), getWidth()/2, getHeight()/2);
        }
        else
        {
            addObject(new ScoreBoard(Counter.value, "Game Over"), getWidth() / 2, getHeight() / 2);
        }

        
    }

    public void checkANumber()
    {

        if(this.aNumber == 0)
        {
            GreenfootSound levelUp = new GreenfootSound("hooray.wav");
            if (!played)
            {
                levelUp.play();
                played = true;
            }
            if (!levelUp.isPlaying())
            {
                addAsteroids(level + 1);
                level = level + 1;
                updateLevel();  
                played = false;
            }

        }
    }

    public void updateLevel()
    {
        GreenfootImage background = getBackground();
    }

    private void playBgm()
    {
        if(!bgm.isPlaying() && gameOver == false)
        {
            //bgm.setVolume(50);
            bgm.play();
        }
        if (gameOver)
        {
            bgm.stop();
            //over.setVolume(50);
            over.play();
        }
    }

    public void fireworks()
    {
        int i = Greenfoot.getRandomNumber (100);

        if (i >=97 && fireworkNumber == 0)
        {
            addObject(new Firework(), Greenfoot.getRandomNumber(getWidth()-10) + 10, getHeight() - 10);
            fireworkNumber++;
        }
    }
}