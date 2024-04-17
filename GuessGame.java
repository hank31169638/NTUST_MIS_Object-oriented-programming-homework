import java.util.Random;
import java.util.Scanner;

public class GuessGame {
    private static final Scanner sc = new Scanner(System.in);
    private static final Random random = new Random();

    private int turns;
    private int ans = random.nextInt(100) + 1;

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public int getAns() {
        return ans;
    }

    public static void main(String[] args) {
        StartGame();
    }

    public static void Init(GuessGame Game) {
        System.out.println("****** Welcome to GuessGame ******");
        System.out.println("The answer's range is between 1 to 100\n");
        System.out.print("Please set the maximum tries：");

        int temp = sc.nextInt();

        while (true) {
            if (temp < 1) {
                System.out.print("Maximum tries cannot be zero or negative, please provide a positive number：");
                temp = sc.nextInt();
            } else {
                break;
            }
        }
        // setTurns
        Game.setTurns(temp);

        System.out.println("You will have " + Game.getTurns() + " turns.");
    }

    public static boolean GameRule(GuessGame Game, int i) {

        int guess = sc.nextInt();

        if (guess < 0) {
            System.out.print("Please input a positive number： ");
            return false;
        } else if (guess < 1 || guess > 100) {
            System.out.print("Out of range. Please think a number between 1 to 100 and try again： ");
            return false;
        } else if (i == Game.getTurns() && guess!=Game.getAns()) {
            System.out.println("Oops!! No turns left. The number was " + Game.getAns() +"\n");
            // restart

        } else if (guess > Game.getAns()) {
            System.out.println("Your guess is larger than the answer");

        } else if (guess < Game.getAns()) {
            System.out.println("Your guess is smaller than the answer");

        } else {
            System.out.println("It took you " + i + " turn to guess the answer, which was " + Game.getAns() +"\n");
            // restart

        }
        return true;
    }

    public static boolean ReStart() {
        System.out.println("Do you want to play again？(Y/n)");
        String chose = sc.next();

        if (chose.equalsIgnoreCase("y")) {
            return true;
        } else if (chose.equalsIgnoreCase("n")) {
            System.exit(0);
        } else {
            System.out.println("請輸入(Y/n)");

        }
        return false;
    }

    public static void StartGame(){
        while (true) {
            GuessGame Game = new GuessGame();
            Init(Game);

            for (int i = 1; i <= Game.getTurns(); i++) {

                if (i == Game.getTurns()) {
                    System.out.print("This is your last chance! Last try：");
                } else {
                    System.out.print("You have " + (Game.getTurns() - i + 1) + " turns left, " + i + "st try：");
                }

                while (true) {
                    if (GameRule(Game, i)) {
                        break;
                    }
                }
            }
            while (true) {
                if (ReStart()) {
                    break;
                }
            }
        }
    }
}
