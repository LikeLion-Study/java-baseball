package baseball;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import baseball.model.BaseballGame;
import baseball.model.BaseballNumber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

    @Test
    public void 랜덤_숫자_생성_함수는_1부터_9까지의_숫자_3개를_생성한다() {

        BaseballGame baseballGame = new BaseballGame();
        baseballGame.createTargetNumber(1,9);
        BaseballNumber[] numbers = baseballGame.getTargetNumbers();

        assertThat(numbers.length).isEqualTo(3);

        for (BaseballNumber number : numbers) {
            assertThat(number.getValue()).isBetween(1, 9);
        }

//        long distinctCount = numbers.stream().distinct().count();
//        assertThat(distinctCount).isEqualTo(3);
    }
}
