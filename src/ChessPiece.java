public class ChessPiece {
    enum PieceColor {
        white, black
    }

    private PieceColor color;

    ChessPiece(PieceColor color) {
        this.color = color;
    }

    PieceColor getColor() {
        return color;
    }

    void changeColor() {
        color = color == PieceColor.white ? PieceColor.black : PieceColor.white;
    }

    char getShape() {
        return color == PieceColor.white ? 'O' : 'X';
    }
}
