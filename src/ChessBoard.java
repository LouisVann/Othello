public class ChessBoard {
    private ChessPiece[][] board;
    private int dimension;

    private  int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

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
            for (int j = 0; j < dimension; j ++) {
                if (board[i][j] == null)
                    str.append(".");
                else
                    str.append(board[i][j].getShape());
                str.append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    class Judge {
        private int computePoint(int row, int col, ChessPiece.PieceColor color) {
            int point = 0;
            for (int i = 0; i < directions.length; i ++) {

            }

            return point;
        }

        public boolean isLegal(int row, int col, ChessPiece.PieceColor color) {
            if (row < 0 || row >= dimension || col < 0 || col >= dimension)
                throw new RuntimeException("Position out of the chessboard.");
            if (board[row][col] != null)
                return false;
            return computePoint(row, col, color) > 0;
        }

        public boolean isGameOver() {

            return false;
        }

        public boolean canMove(ChessPiece.PieceColor color) {
            return true;
        }


    }
}
