import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.*;
import java.util.ArrayList;

public class Space extends World implements ISubject{
    private Counter scoreCounter;
    private LevelCounter levelCounter;
    private int startAsteroids = 1;
    private int otherAsteroidNumber = 3;
    
    public static int level;
    public static int aNumber;
    public static int fireworkNumber;
    private boolean played = false;
    private boolean gameOver = false;
    
    // Observer pattern
    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    
    GreenfootSound bgm = new GreenfootSound("bgm.mp3");
    GreenfootSound over = new GreenfootSound("game over.mp3");
    
    public Space(int playMode) {
        super(800, 600, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        createStars(500);

        Rocket rocket = new Rocket();
        addObject(rocket, getWidth()/2 + 100, getHeight()/2);
        
        MotherShip motherShip = new MotherShip();
        addObject(motherShip, 100, 100);

        // level needed to be reset every new game
        level = 1;
        
        aNumber = 0;
        fireworkNumber = 0;
        
        levelCounter = new LevelCounter();
        scoreCounter = new Counter("Score: ", playMode);
        addObject(scoreCounter, 60, 580);
        addObject(levelCounter, 60, 550);
        
        /** call this AFTER instantiating scoreCounter!!**/
        addAsteroids(startAsteroids);
        
        ProtonWave.initializeImages();

        setPaintOrder(LevelCounter.class, Counter.class, ScoreBoard.class, Rocket.class, Firework.class, Flame.class, Particle.class, Asteroid.class);
        
        attach(levelCounter);
    }

    public void act() {
        checkANumber();
        playBgm();

        if(gameOver) {
            fireworks();
        }
    }

    /**
     * Observer pattern
     */
    public void attach(IObserver obj){
        observers.add(obj);
    }
	public void detach(IObserver obj){
	    observers.remove(obj);
	}
	public void notifyObservers() {
	    for(IObserver obj : observers) {
	        obj.update();
	    }
	}
	
	/**
	 * Need for Strategy pattern
	 * Being accessed from Asteroids:
	 * when an asteroid break into two smaller one
	 * new asteroids are instantiated
	 */
	public Counter getScoreCounter() {
	    return scoreCounter;
	}
	
    /**
     * Add a given number of asteroids to our world. Asteroids are only added into
     * the left half of the world.
     */
    private void addAsteroids(int count) {

        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth()/2);
            int y = Greenfoot.getRandomNumber(getHeight()/2);
            addObject(new Asteroid(scoreCounter, 50), x, y);
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
        if (level >= 1 && level < 8) {
            addObject(new ScoreBoard(Counter.value, gameSaying[level-1]), getWidth()/2, getHeight()/2);
        }
        else {
            addObject(new ScoreBoard(Counter.value, "Game Over"), getWidth() / 2, getHeight() / 2);
        }
    }

    public void checkANumber()
    {

        if(this.aNumber == 0) {
            GreenfootSound levelUp = new GreenfootSound("hooray.wav");
            if (!played) {
                levelUp.play();
                played = true;
            }
            if (!levelUp.isPlaying()) {
                addAsteroids(level + 1);
                ++level;
                updateLevel();
                notifyObservers();
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