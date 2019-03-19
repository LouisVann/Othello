import java.util.*;

public class Main {
    public static Player[] players = new Player[Settings.PLAYERS_NUM];
    public static Scanner scanner = Settings.scanner;

    public static void main(String[] args) {
        int dimension = askBoardDimension();
        ChessBoard chessBoard = new ChessBoard(dimension);
        ChessBoard.Judge judge = chessBoard.new Judge();
        setRoles(); // 设置players
        print(chessBoard.toString());

        int stepCount = 0;
        Player currentPlayer = players[stepCount % Settings.PLAYERS_NUM];
        while (!judge.isGameOver()) {
            if (judge.canMove(currentPlayer.getShape())) {
                int[] decision = currentPlayer.getDecision();
                if (judge.isLegal(decision, currentPlayer.getShape())) {
                    // put on a new chess onto the chessboard
                    chessBoard.putOnChess(decision, currentPlayer.getShape());
                    print(chessBoard.toString());
                } else {    // invalid move
                    Settings.output("Invalid move.\nGame over." + players[(stepCount + 1) % Settings.PLAYERS_NUM].getShape() + "player wins.");
                    return;
                }
            } else {    // cannot move in this round
                Settings.output(currentPlayer.getShape() + " player has no valid move.");
            }
            stepCount ++;
            currentPlayer = players[stepCount % Settings.PLAYERS_NUM];
        }
        // Game is already over now
        ////////////////////////////////////////// 计算输赢

    }



    private static int askBoardDimension() {
//        try {
//            int a = Integer.valueOf("s");
//            System.out.println(a);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        Scanner input = new Scanner(System.in);
//        System.out.print("Enter the board dimension: ");
//
//        int dimension = input.nextInt();
        return 6;
    }

    private static String askRoles() {
        print("Computer plays (X/O):");
        String normalizedInput = scanner.nextLine();
        while (!(normalizedInput.equals("X") || normalizedInput.equals("O"))) {
            print("Illegal role setting! Please choose a role for computer between X and O:");
            normalizedInput = scanner.nextLine();
        }
        return normalizedInput;
    }

    private static void setRoles() {
        String input = askRoles();
        Player man = new Man();
        Player computer = new Computer();
        if (input.equals("X")) {
            players[0] = computer;
            players[1] = man;
        } else {
            players[0] = man;
            players[1] = computer;
        }
        players[0].setShape('X');
        players[1].setShape('O');
    }

    public static void print(String s) {
        Settings.output(s);
    }
}
