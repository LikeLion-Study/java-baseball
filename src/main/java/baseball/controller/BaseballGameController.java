package baseball.controller;

import baseball.model.BaseballGame;
import baseball.view.InputView;
import baseball.view.OutputView;

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
            outputView.printResult(game);

            if (game.getStatus() == baseball.model.GameStatus.SUCCESS) {
                if (outputView.isRestart(game)) continue;  // todo: 클래스 위치가 view?
                break;
            }
        }
    }
}
