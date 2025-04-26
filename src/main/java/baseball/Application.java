package baseball;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");
        Scanner sc = new Scanner(System.in);

        while (true) {
            List<Integer> computer = generateRandomDigits();
            boolean correct = false;

            while (!correct) {
                System.out.print("숫자 3자리를 입력하세요 (예: 123): ");
                String input = sc.next();
                List<Integer> user = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    user.add(input.charAt(i) - '0');
                }

                int strike = 0;
                int ball = 0;

                for (int i = 0; i < 3; i++) {
                    if (user.get(i).equals(computer.get(i))) {
                        strike++;
                    } else if (computer.contains(user.get(i))) {
                        ball++;
                    }
                }

                if (strike == 0 && ball == 0) {
                    System.out.println("미스");
                } else {
                    System.out.println(strike + " 스트라이크 " + ball + " 볼");
                }

                if (strike == 3) {
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    System.out.print("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요: ");
                    int choice = sc.nextInt();
                    if (choice == 2) {
                        System.out.println("게임을 종료합니다.");
                        return;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public static List<Integer> generateRandomDigits() {
        List<Integer> digits = new ArrayList<>();
        Random random = new Random();

        while (digits.size() < 3) {
            int num = random.nextInt(9) + 1; // 1~9
            if (!digits.contains(num)) {
                digits.add(num);
            }
        }

        return digits;
    }
}
