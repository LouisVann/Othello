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
            if (board[row][col] != null)
                return 0;
            int point = 0;
            for (int i = 0; i < directions.length; i ++) {
                int this_x = row, this_y = col, sum = 0;
                while (checkNextPiece(this_x, this_y, directions[i][0], directions[i][1], color)) {
                    sum ++;
                    this_x += directions[i][0];
                    this_y += directions[i][1];
                }
                if (checkNextPiece(this_x, this_y, directions[i][0], directions[i][1], board[this_x][this_y].getColor()))
                    point += sum;
            }

            return point;
        }

        private boolean checkNextPiece(int this_x, int this_y, int dx, int dy, ChessPiece.PieceColor origin_color) {
            return this_x + dx >= 0 && this_x + dx < dimension
                    && this_y + dy >= 0 && this_y + dy < dimension
                    && board[this_x + dx][this_y + dy] != null
                    && board[this_x + dx][this_y + dy].getColor() != origin_color;
        }

        public boolean isLegal(int row, int col, ChessPiece.PieceColor color) {
            if (row < 0 || row >= dimension || col < 0 || col >= dimension)
                throw new RuntimeException("Position out of the chessboard.");
            return computePoint(row, col, color) > 0;
        }

        public boolean canMove(ChessPiece.PieceColor color) {
            for (int i = 0; i < dimension; i ++) {
                for (int j = 0; j < dimension; j ++) {
                    if (isLegal(i, j, color))
                        return true;
                }
            }
            return false;
        }

//        public boolean isGameOver() {
//            return  canMove(ChessPiece.PieceColor.white) || canMove(ChessPiece.PieceColor.black);
//        }


    }
}
