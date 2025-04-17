package baseball.view;

import baseball.model.BaseballGame;
import baseball.util.Message;
import baseball.util.Validator;

import java.util.Scanner;

public class InputView {
    public void getUserNumber(BaseballGame baseballGame) {
        System.out.print(Message.MESSAGE_INPUT_PROMPT);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().replaceAll("\\s+", "");

        Validator.inputValidate(input);

        baseballGame.createUserNumbers(input);
    }
}
