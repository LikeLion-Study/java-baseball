package baseball;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Application {
    static final int NUM_SIZE = 3;

    int[] targetNumbers;
    int[] userNumbers;

    public static void main(String[] args) {

        Application application = new Application();

        application.createTargetNumber(1, 9);

        System.out.println("숫자 야구 게임을 시작합니다.");

        application.getUserNumber();

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
        for (int i = 0 ; i < input.length() ; i++) {
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
}