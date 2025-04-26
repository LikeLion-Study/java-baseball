package baseball.model;

import java.util.Random;

public class BaseballGame {
    private static final int NUM_SIZE = 3;

    BaseballNumber[] targetNumbers;
    BaseballNumber[] userNumbers;

    GameStatus status;

    public void createTargetNumber(int start, int end) {
        targetNumbers = new BaseballNumber[NUM_SIZE];
        for (int i = 0; i < NUM_SIZE; i++) {
            targetNumbers[i] = new BaseballNumber(new Random().nextInt(end) + start);
        }
    }

    public void createUserNumbers(String input) {
        userNumbers = new BaseballNumber[NUM_SIZE];
        for (int i = 0; i < NUM_SIZE; i++) {
            int inputNumber = Integer.parseInt(String.valueOf(input.charAt(i)));
            userNumbers[i] = new BaseballNumber(inputNumber);
        }
    }


    public void match() {
        checkStrike();
        checkBall();
    }

    private void checkStrike() {
        for (int index = 0; index < NUM_SIZE; index++) {
            if (userNumbers[index].getValue() == targetNumbers[index].getValue()) {
                userNumbers[index].setStrike(true);
                targetNumbers[index].setStrike(true);
            }
        }
    }

    private void checkBall() {
        for (int userIndex = 0; userIndex < NUM_SIZE; userIndex++) {
            if (userNumbers[userIndex].isStrike()) continue;
            for (int targetIndex = 0; targetIndex < NUM_SIZE; targetIndex++) {
                if (targetNumbers[targetIndex].isBall()) continue;
                if (userNumbers[userIndex].getValue() == targetNumbers[targetIndex].getValue()) {
                    userNumbers[userIndex].setBall(true);
                    targetNumbers[targetIndex].setBall(true);
                    break;
                }
            }
        }
    }

    public void reset() {
        for (BaseballNumber targetNumber: targetNumbers) {
            targetNumber.setStrike(false);
            targetNumber.setBall(false);
        }

        userNumbers = null;
    }

    public BaseballNumber[] getTargetNumbers() {
        return targetNumbers;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public static int getNumSize() {
        return NUM_SIZE;
    }
}
