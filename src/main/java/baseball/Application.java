package baseball;

import baseball.model.BaseballNumber;

import java.util.Random;
import java.util.Scanner;

public class Application {
    static final int NUM_SIZE = 3;
    static final int STATUS_SUCCESS = 1;
    static final int STATUS_FAIL = 0;
    static final int STATUS_RESTART = 2;

    BaseballNumber[] targetNumbers;
    BaseballNumber[] userNumbers;

    int status;

    public static void main(String[] args) {

        Application application = new Application();

        application.createTargetNumber(1, 9);

        System.out.println("숫자 야구 게임을 시작합니다.");

        application.getUserNumber();

        application.match();

        application.printResult();

        application.checkStatus(application);

    }

    private void createTargetNumber(int start, int end) {
        targetNumbers = new BaseballNumber[NUM_SIZE];

        for (int i = 0; i < NUM_SIZE; i++) {
            targetNumbers[i] = new BaseballNumber(new Random().nextInt(end) + start);
        }

        for (BaseballNumber targetNumber: targetNumbers) {  // todo: 디버그용
            System.out.println(targetNumber.getValue());
        }
    }

    private void getUserNumber() {

        System.out.print("숫자를 입력해주세요 : ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().replaceAll("\\s+", "");

        validateInput(input);

        userNumbers = new BaseballNumber[NUM_SIZE];
        int inputNumber;
        for (int i = 0; i < NUM_SIZE; i++) {
            inputNumber = Integer.parseInt(String.valueOf(input.charAt(i))); // todo: 리팩토링
            userNumbers[i] = new BaseballNumber(inputNumber);
        }
    }

    private void validateInput(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요 !!");
            throw new IllegalArgumentException();
        }

        if (input.contains("0")) {
            System.out.println("1~9 사이 3개의 숫자를 입력해주세요 !!");
            throw new IllegalArgumentException();
        }


        if (input.length() != NUM_SIZE) {
            System.out.println("1~9 사이 3개의 숫자를 입력해주세요 !!");
            throw new IllegalArgumentException();
        }
    }

    private void match() {
        checkStrike();
        checkBall();
    }

    private void checkStrike() {
        for (int index = 0; index < NUM_SIZE; index++) {
            if ( userNumbers[index].getValue() == targetNumbers[index].getValue()) {
                userNumbers[index].setStrike(true);
                targetNumbers[index].setStrike(true);
            }
        }
    }

    private void checkBall() {
        for (int userIndex = 0; userIndex < NUM_SIZE; userIndex++) {
            if (userNumbers[userIndex].isStrike()) continue;
            for (int targetIndex = 0; targetIndex < NUM_SIZE; targetIndex++) {
                if (targetNumbers[targetIndex].isStrike()) continue;

                if (userNumbers[userIndex].getValue() == targetNumbers[targetIndex].getValue()) {
                    userNumbers[userIndex].setBall(true);
                    targetNumbers[targetIndex].setBall(true);
                    break;
                }
            }
        }
    }

    private void printResult() {
        int strike = 0;
        int ball = 0;

        for (BaseballNumber targetNumber: targetNumbers) {  // todo: strike와 ball 계산 책임  // 분리 필요
            if (targetNumber.isStrike()) {
                strike++;
                continue;
            }
            if (targetNumber.isBall()) {
                ball++;
            }
        }


        if (ball == 0 && (strike == 0)) {
            status = STATUS_FAIL;
            System.out.println("미스");
        } else if (ball == 0) {
            status = STATUS_FAIL;
            System.out.println(strike + "스트라이크");
            if (strike == NUM_SIZE) {
                status = STATUS_SUCCESS;
                System.out.println(NUM_SIZE + "개의 숫자를 모두 맞히셨습니다!");
            }
        } else if (strike == 0) {
            status = STATUS_FAIL;
            System.out.println(ball + "볼");
        } else {
            status = STATUS_FAIL;
            System.out.println(ball + "볼" + " " + strike + "스트라이크");
        }
    }

    private void checkStatus(Application application) {
        if (status == STATUS_FAIL) {
            application.getUserNumber();

            application.match();

            application.printResult();

            application.checkStatus(application);
        }
        if (status == STATUS_SUCCESS) {
            getGameOption(application);
        }
        if (status == STATUS_RESTART) {
            application.createTargetNumber(1, 9);

            System.out.println("숫자 야구 게임을 시작합니다.");

            application.getUserNumber();

            application.match();

            application.printResult();

            application.checkStatus(application);
        }
    }

    private void getGameOption(Application application) {
        int gameOption = 0;
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        try {
            gameOption = scanner.nextInt();

            if (gameOption == 1) {
                status = STATUS_RESTART;
                checkStatus(application);

            } else if (gameOption == 2) {
                return;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.out.println("1 또는 2를 입력하세요 !!");
            throw new IllegalArgumentException();
        }
    }
}