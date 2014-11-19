# Design Patterns

## Composite
    In World class addObject(Actor object, int x, int y)
    Each class extending Actor implements its own act method

## Observer
  1. In class LevelCounter: Keep watching the changes in level, and update it

    public void act() {
        if (Space.level != level) {
            level = Space.level;
            updateImage();
        }
    }

  2. In Class Counter:

    public void act() {
        updateImage();
        ...
    }

    #### Workflow
    Bullet:
    private void checkAsteroidHit() {
      ...
      asteroid.hit(damage);
    }

    Asteroid:
    public void hit(int damage) {
        Counter.add(damage * Space.level);
        ...
    }

    Counter:
    public static void add(int score) {
        value += score;
    }

    private void updateImage() {
        ...
        image.drawString(text + ...);
    }

## Chain of Responsibility
  1. In class Bullet:
    private void checkAsteroidHit() {
        Asteroid asteroid = (Asteroid) getOneIntersectingObject(Asteroid.class);
        ...
    }

    // Behind the scene
    Traverse through the array of asteroids and check
    if one of them is intersected by asking every asteroid
    if it is closed enough to the object

  2. In class ProtonWave:
    private void checkCollision() {
        int range = getImage().getWidth() / 2;
        List<Asteroid> asteroids = getObjectsInRange(range, Asteroid.class);

        for (Asteroid a : asteroids) {
            a.hit (DAMAGE);
        }
    }

    // Behind the scene
    Traverse through the array of asteroids and check
    if some of them are in the given range. Return the asteroids
    in the range for take further action.
