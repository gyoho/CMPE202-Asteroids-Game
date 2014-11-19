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
      public static void add(int score)
      {
          value += score;
      }

## Chain of Responsibility 
