package baseball;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

    // TODO: 클래스명과 함수명 변경 가능 (RandomNumberGenerator.generate())
    @Test
    public void 랜덤_숫자_생성_함수는_1부터_9까지의_숫자_3개를_생성한다() {
        // TODO: 실제 구현에서는 RandomNumberGenerator.generate() 등 실제 함수를 호출할 것

        List<Integer> numbers = List.of(); // 임시 코드. 구현 후 아래 코드로 변경해주세요.
        // 예: List<Integer> numbers = RandomNumberGenerator.generate();

        assertThat(numbers.size()).isEqualTo(3);

        for (Integer number : numbers) {
            assertThat(number).isBetween(1, 9);
        }

        long distinctCount = numbers.stream().distinct().count();
        assertThat(distinctCount).isEqualTo(3);
    }

    // TODO: 필요에 따라 추가 테스트 코드 작성 가능
}
