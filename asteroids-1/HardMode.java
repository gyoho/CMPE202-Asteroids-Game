
public class HardMode implements IRecordStrategy {
    @Override
    public void updateScore(int score) {
        Counter.value += score/2;
    }
}