package baseball;

import java.util.Random;
import java.util.stream.IntStream;

public class Application {

    int[] targetNumber;

    public static void main(String[] args) {

        Application application = new Application();

        application.createTargetNumber(1, 9, 3);

    }

    private void createTargetNumber(int start, int end, int size) {

        Random random = new Random();

        IntStream intStream = random.ints(start, end + 1).limit(size);

        targetNumber = intStream.toArray();
    }
}