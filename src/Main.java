import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    // global variables
    private static Player[] players = new Player[Settings.PLAYERS_NUM];
    private static Scanner scanner = Settings.scanner;
    private static ChessBoard chessBoard;
    private static int stepCount = 0;
    private static long startTime = 0, endTime = 0;

    public static void main(String[] args) {
        chessBoard = new ChessBoard(askBoardDimension());
        ChessBoard.Judge judge = chessBoard.judge;

        setRoles(); // set players in the instruction of users
        print(chessBoard.toString());
        stepCount = 0;
        Player currentPlayer = players[stepCount % Settings.PLAYERS_NUM];

        startTime = System.nanoTime();

        while (true) {
            if (judge.canMove(currentPlayer.getColor())) { // current player can move in this round
                int[] decision = currentPlayer.getDecision(chessBoard); // get the new piece's position by some strategy
                if (judge.isLegal(decision[0], decision[1], currentPlayer.getColor())) {
                    chessBoard.putOnChess(decision[0], decision[1], currentPlayer.getColor()); // put on the new piece onto the chessboard
                    print(chessBoard.toString());
                } else {    // invalid move
                    gameOver(1);
                    return;
                }

            } else { // current player cannot move in this round
                if (! judge.canMove(players[(1 + stepCount) % Settings.PLAYERS_NUM].getColor())) { // next player cannot move, either
                    gameOver(0);
                    return;
                }
                // next player can move
                print(currentPlayer.getShape() + " player has no valid move.");
            }

            // this round ends, change the player to the next one
            stepCount ++;
            currentPlayer = players[stepCount % Settings.PLAYERS_NUM];
        }
        // Game is already over now, the following will never reach

    }

    private static void gameOver(int status) {
        endTime = System.nanoTime();
        int[] results = chessBoard.judge.getResult();

        switch(status) {
            case 0: // end naturally
                print("Both players have no valid move.\nGame Over.\n" +
                        "X : O = " + results[0] + " : " + results[1]);
                if (results[0] < results[1])
                    print("O player wins.");
                else if (results[0] > results[1])
                    print("X player wins.");
                else
                    print("Draw!");
                break;
            case 1: // Illegal move
                print("Invalid move.\nGame over.\n" + players[(stepCount + 1) % Settings.PLAYERS_NUM].getShape() + " player wins.");
                break;
            default:
                throw new RuntimeException("Unexpected ending status!");
        }

        StringBuilder stringBuilder = new StringBuilder();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        stringBuilder.append(dateFormat.format(new Date())).append(',');
        stringBuilder.append((int)((endTime - startTime) / 1E+9)).append(',');
        stringBuilder.append(chessBoard.getDimension()).append('*').append(chessBoard.getDimension()).append(',');
        stringBuilder.append(players[0].getName()).append(',').append(players[1].getName()).append(',');
        String resultLog = status == 0 ? results[0] + " to " + results[1] : "Human gave up";
        stringBuilder.append(resultLog);
        stringBuilder.append('\n');

        try {
            FileWriter fw = new FileWriter("Othello.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(stringBuilder.toString());
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static int askBoardDimension() {
        print("Enter the board dimension:");
        String input = scanner.nextLine();
        while (! input.matches("\\d+") || Integer.valueOf(input) < 4 || Integer.valueOf(input) > Settings.MAX_DIMENSION) {
            print("Illegal input! Try again:");
            input = scanner.nextLine();
        }
        return Integer.valueOf(input);
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


    private static void print(String s) {
        Settings.output(s);
    }
}
