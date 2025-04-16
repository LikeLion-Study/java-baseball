package baseball;

import java.util.Random;
import java.util.stream.IntStream;

public class Application {
    static final int NUM_SIZE = 3;

    int[] targetNumber;

    public static void main(String[] args) {

        Application application = new Application();

        application.createTargetNumber(1, 9);

        System.out.println("숫자 야구 게임을 시작합니다.");

    }

    private void createTargetNumber(int start, int end) {

        Random random = new Random();

        IntStream intStream = random.ints(start, end + 1).limit(NUM_SIZE);

        targetNumber = intStream.toArray();
    }
}