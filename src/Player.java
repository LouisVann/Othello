public abstract class Player {
    private ChessPiece.PieceColor color;
    private char shape;

    public Player(ChessPiece.PieceColor color) {
        this.color = color;
        shape = color == ChessPiece.PieceColor.white ? 'O' : 'X';
    }

    public ChessPiece.PieceColor getColor() {
        return color;
    }

    public char getShape() {
        return shape;
    }

    public abstract int[] getDecision();

}

class Man extends Player {
    public Man(ChessPiece.PieceColor color) {
        super(color);
    }
    @Override
    public int[] getDecision() {
        Settings.output("Enter move for " + getShape() + " (RowCol):");
        String positionString = Settings.scanner.nextLine().toLowerCase();
        while (positionString.length() != 2 || positionString.charAt(0) < 97 || positionString.charAt(0) > 96 + Settings.MAX_DIMENSION ||
                positionString.charAt(1) < 97 || positionString.charAt(1) > 96 + Settings.MAX_DIMENSION) {
            Settings.output("Illegal position! Please input again.");
            positionString = Settings.scanner.nextLine().toLowerCase();
        }
        return new int[]{positionString.charAt(0) - 97, positionString.charAt(1) - 97};
    }
}

class Computer extends Player {
    public Computer(ChessPiece.PieceColor color) {
        super(color);
    }
    @Override
    public int[] getDecision() {
        ////////////////////////////////////////////////////////////////////
        return new int[]{0, 0};
    }
}