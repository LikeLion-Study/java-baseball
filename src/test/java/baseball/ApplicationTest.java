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
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() throws Exception {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8.name()));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void 게임_시작_및_초기_프롬프트_출력_테스트() throws UnsupportedEncodingException {
        Application.main(new String[]{});

        String output = outputStream.toString(StandardCharsets.UTF_8.name());

        assertThat(output).contains("숫자 야구 게임을 시작합니다.");
        assertThat(output).contains("숫자를 입력해주세요");
    }

    @Test
    public void 승리_후_메시지_출력_테스트() throws UnsupportedEncodingException {
        Application.main(new String[]{});

        String output = outputStream.toString(StandardCharsets.UTF_8.name());

        assertThat(output).contains("3스트라이크");
        assertThat(output).contains("3개의 숫자를 모두 맞히셨습니다!");
        assertThat(output).contains("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    }

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