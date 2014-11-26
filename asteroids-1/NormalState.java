
public class NormalState implements IRocketState {    
    Rocket rocket;
    public NormalState(Rocket rocket) {
        this.rocket = rocket;
    }
    
    // define the concrete methods level 1-2
    @Override
    public void ignite() {
        // check the level here
        if(Space.level >= 3) {
            rocket.setState(rocket.getAdvancedState());
        }
        
        // rocket flies slowly initially
        rocket.addForce (new Vector(rocket.getRotation(), 0.1));
        rocket.showFlame();
    }
    
    @Override
    public void startProtonWave() {
        // no proton wave equipped
    }
}
