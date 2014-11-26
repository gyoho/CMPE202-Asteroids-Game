
public class NormalMode implements IRecordStrategy {
    @Override
    public void updateScore(int score) {
        Counter.value += score;
    }
}