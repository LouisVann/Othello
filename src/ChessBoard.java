public class ChessBoard {
    public ChessPiece[][] board;
    private int dimension;

    public ChessBoard(int dimension) {
        if (dimension < 4 || dimension > Settings.MAX_DIMENSION) {
            throw new RuntimeException("Illegal dimension!");
        } else {
            this.dimension = dimension;
            board = new ChessPiece[dimension][dimension];
            int ul = dimension / 2 - 1;
            board[ul][ul] = new ChessPiece(ChessPiece.PieceColor.white);
            board[ul][ul + 1] = new ChessPiece(ChessPiece.PieceColor.black);
            board[ul + 1][ul] = new ChessPiece(ChessPiece.PieceColor.black);
            board[ul + 1][ul + 1] = new ChessPiece(ChessPiece.PieceColor.white);

        }

    }

    public void putOnChess(int row, int col, ChessPiece.PieceColor color) {
        board[row][col] = new ChessPiece(color);
        takeEffect(row, col, color);
    }

    private void takeEffect(int row, int col, ChessPiece.PieceColor color) {
        ///////////////////////////////////////////////////////////////////////--change board
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder().append("  ");
        for (int i = 0; i < dimension; i ++)
            str.append((char)(i + 97)).append(" ");
        str.append("\n");
        for (int i = 0; i < dimension; i ++) {
            str.append((char)(i + 97)).append(" ");
            for (int j = 0; j < dimension; j ++)
                str.append(board[i][j]).append(" ");
            str.append("\n");
        }
        return str.toString();
    }

    class Judge {
        public boolean isGameOver() {

            return false;
        }

        public boolean canMove(ChessPiece.PieceColor color) {
            return true;
        }

        public boolean isLegal(int[] position, ChessPiece.PieceColor color) {
            return true;
        }
    }
}
