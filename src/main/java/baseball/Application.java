package baseball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Application {
    static final int NUM_SIZE = 3;
    static final int STATUS_SUCCESS = 1;
    static final int STATUS_FAIL = 0;

    int[] targetNumbers;
    int[] userNumbers;

    int strike;
    int ball;

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

        Random random = new Random();

        IntStream intStream = random.ints(start, end + 1).limit(NUM_SIZE);

        targetNumbers = intStream.toArray();
    }

    private void getUserNumber() {

        System.out.print("숫자를 입력해주세요 : ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().replaceAll("\\s+", "");

        validateInput(input);

        userNumbers = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            userNumbers[i] = Integer.parseInt(String.valueOf(input.charAt(i)));  // todo: 리팩토링
        }
    }

    private void validateInput(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요 !!");
            throw new IllegalArgumentException();
        }


        if (input.length() != NUM_SIZE) {
            System.out.println("1~9 사이 3개의 숫자를 입력해주세요 !!");
            throw new IllegalArgumentException();
        }
    }

    private void match() {
        ArrayList<Integer> redundants = new ArrayList<>(NUM_SIZE);

        strike = matchStrike(redundants);

        ball = matchBall(redundants);
    }

    private int matchStrike(ArrayList<Integer> redundants) {
        int strike = 0;

        for (int i = 0; i < NUM_SIZE; i++) {
            if (redundants.contains(i)) continue;

            for (int j = 0; j < NUM_SIZE; j++) {
                if (redundants.contains(j)) continue;

                if (targetNumbers[i] == userNumbers[j] && (i == j)) {
                    strike++;
                    redundants.add(j);

                    break;
                }
            }
        }

        return strike;
    }

    private int matchBall(ArrayList<Integer> redundants) {
        int ball = 0;
        ArrayList<Integer> ballRedundants = new ArrayList<>(NUM_SIZE);

        for (int i = 0; i < NUM_SIZE; i++) {
            if (redundants.contains(i)) continue;

            for (int j = 0; j < NUM_SIZE; j++) {
                if (redundants.contains(j)) continue;
                if (ballRedundants.contains(j)) continue;
                ;

                if (targetNumbers[i] == userNumbers[j]) {
                    ball++;
                    ballRedundants.add(j);

                    break;
                }
            }
        }
        return ball;
    }

    private void printResult() {
        if (ball == 0 && (strike == 0)) {
            status = STATUS_FAIL;
            System.out.println("미스");
        } else if (ball == 0) {
            status = STATUS_FAIL;
            System.out.println(strike + "스트라이크");
            if (strike == NUM_SIZE) {
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
    }
}