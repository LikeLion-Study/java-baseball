package baseball.controller;

import baseball.model.BaseballGame;
import baseball.view.InputView;
import baseball.view.OutputView;

import java.util.Scanner;

public class BaseballGameController {
    private final BaseballGame game = new BaseballGame();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private static final int START = 1;
    private static final int END = 9;

    public void run() {
        game.createTargetNumber(START, END);
        outputView.printStartMessage();

        while (true) {
            game.reset();  // targetNumber의 상태 초기화 및 이전 userNumber free
            inputView.getUserNumber(game);
            game.match();
            outputView.printResult(game);  //todo:  ball, strike 연산을 view클래스에서?

            if (game.getStatus() == baseball.model.GameStatus.SUCCESS) {  // todo: 이것도 따로 함수로 빼야될 듯
                if (outputView.isRestart(game)) continue;  // todo: restart 연산을 view클래스에서?
                break;
            }
        }
    }
}
