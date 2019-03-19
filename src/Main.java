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
        while (true) {
            if (judge.canMove(currentPlayer.getColor())) {
                int[] decision = currentPlayer.getDecision();
                if (judge.isLegal(decision[0], decision[1], currentPlayer.getColor())) {
                    // put on a new chess onto the chessboard
                    chessBoard.putOnChess(decision[0], decision[1], currentPlayer.getColor());
                    print(chessBoard.toString());
                } else {    // invalid move
                    print("Invalid move.\nGame over." + players[(stepCount + 1) % Settings.PLAYERS_NUM].getShape() + "player wins.");
                    return;
                }
            } else {    // current player cannot move in this round
                if (! judge.canMove(players[(1 + stepCount) % Settings.PLAYERS_NUM].getColor())) {
                    // next player cannot move, either
                    print("Both players have no valid move.\nGame Over.");
                    return;
                }
                // next player can move
                print(currentPlayer.getShape() + " player has no valid move.");
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
        if (input.equals("X")) {
            players[0] = new Computer(ChessPiece.PieceColor.black);
            players[1] = new Man(ChessPiece.PieceColor.white);
        } else {
            players[0] = new Man(ChessPiece.PieceColor.black);
            players[1] = new Computer(ChessPiece.PieceColor.white);
        }
    }


    public static void print(String s) {
        Settings.output(s);
    }
}
