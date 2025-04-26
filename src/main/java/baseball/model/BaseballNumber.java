package baseball.model;

// vo class
public class BaseballNumber {
    private final int value;

    private boolean strike = false;
    private boolean ball = false;

    public BaseballNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isStrike() {
        return strike;
    }

    public boolean isBall() {
        return ball;
    }

    public void setStrike(boolean strike) {
        this.strike = strike;
    }

    public void setBall(boolean ball) {
        this.ball = ball;
    }
}
