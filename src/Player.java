public abstract class Player {
    private ChessPiece.PieceColor color;
    private char shape;
    private String name;

    Player(ChessPiece.PieceColor color) {
        this.color = color;
        shape = color == ChessPiece.PieceColor.white ? 'O' : 'X';
    }

    ChessPiece.PieceColor getColor() {
        return color;
    }

    char getShape() {
        return shape;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public abstract int[] getDecision(ChessBoard chessBoard);

}

class Man extends Player {
    Man(ChessPiece.PieceColor color) {
        super(color);
        setName("human");
    }

    @Override
    public int[] getDecision(ChessBoard chessBoard) {
        Settings.output("Enter move for " + getShape() + " (RowCol):");
        String positionString = Settings.scanner.nextLine().toLowerCase();

        while (positionString.length() != 2 || positionString.charAt(0) < 'a' || positionString.charAt(0) > 'a' - 1 + Settings.MAX_DIMENSION ||
                positionString.charAt(1) < 'a' || positionString.charAt(1) > 'a' - 1 + Settings.MAX_DIMENSION) {
            Settings.output("Illegal position! Please input position again.");
            positionString = Settings.scanner.nextLine().toLowerCase();
        }

        return new int[]{positionString.charAt(0) - 'a', positionString.charAt(1) - 'a'};
    }
}

class Computer extends Player {
    Computer(ChessPiece.PieceColor color) {
        super(color);
        setName("computer");
    }

    @Override
    public int[] getDecision(ChessBoard chessBoard) {
        ////////////////////////////////////////////////////////////////////
        int returnX = 0, returnY = 0, maxPoint = 0;
        ChessPiece[][] board = chessBoard.getBoard();
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (chessBoard.judge.computePoint(i, j, getColor()) > maxPoint) {
                    maxPoint = chessBoard.judge.computePoint(i, j, getColor());
                    returnX = i;
                    returnY = j;
                }
            }
        }

        Settings.output("Computer places " + getShape() + " at "
                + (char)(returnX + 'a') + (char)(returnY + 'a') + ".");

        return new int[]{returnX, returnY};
    }
}
