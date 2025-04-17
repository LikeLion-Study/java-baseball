package baseball.view;

import baseball.model.BaseballGame;
import baseball.model.BaseballNumber;
import baseball.model.GameStatus;
import baseball.util.Message;

import java.util.Scanner;

public class OutputView {
    public void printStartMessage() {
        System.out.println(Message.MESSAGE_START);
    }

    public void printResult(BaseballGame game) {
        int strike = 0;
        int ball = 0;

        for (BaseballNumber targetNumber : game.getTargetNumbers()) {
            if (targetNumber.isStrike()) {
                strike++;
                continue;
            }
            if (targetNumber.isBall()) {
                ball++;
            }
        }

        if (ball == 0 && strike == 0) {
            game.setStatus(GameStatus.FAIL);
            System.out.println(Message.MESSAGE_MISS);
        } else if (ball == 0) {
            game.setStatus(GameStatus.FAIL);
            System.out.println(String.format(Message.MESSAGE_STRIKE_ONLY_FORMATTED, strike));
            if (strike == game.getNumSize()) {
                game.setStatus(GameStatus.SUCCESS);
                System.out.println(String.format(Message.MESSAGE_SUCCESS_FORMATTED, BaseballGame.getNumSize()));
            }
        } else if (strike == 0) {
            game.setStatus(GameStatus.FAIL);
            System.out.println(String.format(Message.MESSAGE_BALL_ONLY_FORMATTED, ball));
        } else {
            game.setStatus(GameStatus.FAIL);
            System.out.println(String.format(Message.MESSAGE_BALL_STRIKE_FORMATTED, ball, strike));
        }
    }

    public boolean isRestart(BaseballGame game) {  // todo: 출력뿐 아니라 조건 분기도 하고 있음..
        System.out.println(Message.MESSAGE_RESTART_PROMPT);
        Scanner scanner = new Scanner(System.in);
        try {
            int option = scanner.nextInt();
            if (option == 1) {
                game.createTargetNumber(1, 9);
                game.setStatus(GameStatus.RESTART); // 의미가 있나?
                return true;
            }
            if (option == 2) return false;
        } catch (Exception e) {
            System.out.println(Message.MESSAGE_RESTART_ERROR);
        }
        throw new IllegalArgumentException();
    }
}
