package baseball.util;

import baseball.model.BaseballGame;

public class Validator {
    public static void inputValidate(String input) {
        try {
            Integer.parseInt(input);
            if (input.contains("0") || input.length() != BaseballGame.getNumSize()) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.out.println(Message.MESSAGE_INPUT_ERROR);
            throw new IllegalArgumentException();
        }
    }
}
