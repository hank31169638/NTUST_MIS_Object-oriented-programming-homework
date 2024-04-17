import java.util.Scanner;

public class TicTacToe {
    public final Scanner sc = new Scanner(System.in);
    private final String[][] gameStatus = new String[3][3];
    private String player = "O";

    private int round = 0;

    public TicTacToe() {
        // init gameStatus
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.gameStatus[i][j] = " ";
            }
        }

        while (true) {
            showMenu();
            // if the number of rounds is bigger than 4,execute win function to saving the time
            if (this.round > 4) {
                checkWin();
            }
        }
    }

    public void showMenu() {
        System.out.println("[" + this.player + "的回合]");
        System.out.print("請輸入行數(1~3)：");
        int row = sc.nextInt();
        System.out.print("請輸入列數(1~3)：");
        int col = sc.nextInt();

        gameRule(row, col);
    }

    public void showPlate() {
        // draw the plate
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 1 || j == 2) {
                    System.out.print("|");
                }
                System.out.print(" " + this.gameStatus[i][j]);
            }
            System.out.println();
            for (int j = 0; j < 3; j++) {
                System.out.print("-- ");
            }
            System.out.println();
        }
    }

    public void gameRule(int row, int col) {

        if (row > 3 || row < 1 || col > 3 || col < 1) {
            System.out.println("請輸入1~3的數字!");
            return;
        }
        // Because the array indexing start from zero so --;s
        row--;
        col--;

        if (this.gameStatus[row][col].equals(" ")) {
            this.gameStatus[row][col] = this.player;
            if (this.player.equals("O")) {
                this.player = "X";
            } else {
                this.player = "O";
            }
            // if the place selected by the player is empty, show plate and let round add 1 to record the game status
            showPlate();
            this.round++;
        } else {
            System.out.println("該座標已經有值了");
        }
    }

    public void checkWin() {
        // if the grid is full, output tie
        if (this.round == 9) {
            System.out.println("平局!!");
            System.exit(0);
        }

        for (int i = 0; i < 3; i++) {
            //row
            CheckLine(this.gameStatus[i][0], this.gameStatus[i][1], this.gameStatus[i][2]);
            //column
            CheckLine(this.gameStatus[0][i], this.gameStatus[1][i], this.gameStatus[2][i]);
        }

        //check slope
        CheckLine(this.gameStatus[0][0], this.gameStatus[1][1], this.gameStatus[2][2]);
        CheckLine(this.gameStatus[0][2], this.gameStatus[1][1], this.gameStatus[2][0]);

    }

    public void CheckLine(String a, String b, String c) {
        if (a.equals(b) && b.equals(c)) {
            if (a.equals("O")) {
                System.out.println("「玩家一」勝利!!");
            } else if (a.equals("X")) {
                System.out.println("「玩家二」勝利!!");
            }
        }
    }

}

