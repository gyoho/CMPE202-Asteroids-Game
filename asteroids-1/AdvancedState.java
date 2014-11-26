
public class AdvancedState implements IRocketState {
    Rocket rocket;
    public AdvancedState(Rocket rocket) {
        this.rocket = rocket;
    }
    
    // define the concrete methods after level 3
    @Override
    public void ignite() {
        // rocket flies faster
        rocket.addForce (new Vector(rocket.getRotation(), 0.3));
        rocket.showFlame();
    }
    
    @Override
    public void startProtonWave() {
        if (rocket.getProtonDelayCount() >= Rocket.protonReloadTime) {
            ProtonWave wave = new ProtonWave();
            rocket.getWorld().addObject (wave, rocket.getX(), rocket.getY());
            rocket.getWorld().removeObjects(rocket.getWorld().getObjects(WaveDisplay.class));
            rocket.setProtonDelayCount(0);
            Rocket.PIndicatorAdded = false;
        }
    }
}
