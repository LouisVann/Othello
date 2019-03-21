import java.util.ArrayList;

public class ChessBoard {
    private ChessPiece[][] board;
    private int dimension;
    public Judge judge;

    public int getDimension() {
        return dimension;
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    private  int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public ChessBoard(int dimension) {
        judge = new Judge();

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
        //takeEffect(row, col, color);
        ArrayList<ChessPiece> pieces;
        for (int i = 0; i < directions.length; i ++) {
            pieces = new ArrayList<>();
            int thisX = row, thisY = col;
            while (judge.checkNextPiece(thisX, thisY, directions[i][0], directions[i][1], color)) {
                pieces.add(board[thisX + directions[i][0]][thisY + directions[i][1]]);
                thisX += directions[i][0];
                thisY += directions[i][1];
            }

            if (board[thisX][thisY] != null && judge.checkNextPiece(thisX, thisY, directions[i][0], directions[i][1], board[thisX][thisY].getColor()))
                for (ChessPiece piece : pieces)
                    piece.changeColor();
        }
    }

//    private void takeEffect(int row, int col, ChessPiece.PieceColor color) {
//        ///////////////////////////////////////////////////////////////////////--change board
//    }

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
        public int[] getResult() {
            int blackNum = 0, whiteNum = 0;
            for (int i = 0; i < board.length; i ++) {
                for (int j = 0; j < board[0].length; j ++) {
                    if (board[i][j] != null) {
                        if (board[i][j].getColor() == ChessPiece.PieceColor.black)
                            blackNum ++;
                        else
                            whiteNum ++;
                    }
                }
            }
            return new int[]{blackNum, whiteNum};
        }

        public int computePoint(int row, int col, ChessPiece.PieceColor color) {
            if (board[row][col] != null)
                return 0;
            int point = 0;
            for (int i = 0; i < directions.length; i ++) { // check eight directions
                int thisX = row, thisY = col, sum = 0;
                while (checkNextPiece(thisX, thisY, directions[i][0], directions[i][1], color)) {
                    sum ++;
                    thisX += directions[i][0];
                    thisY += directions[i][1];
                }
                if (board[thisX][thisY] != null && checkNextPiece(thisX, thisY, directions[i][0], directions[i][1], board[thisX][thisY].getColor()))
                    point += sum;
            }

            return point;
        }

        private boolean checkNextPiece(int thisX, int thisY, int dx, int dy, ChessPiece.PieceColor origin_color) {
            return thisX + dx >= 0 && thisX + dx < dimension
                    && thisY + dy >= 0 && thisY + dy < dimension
                    && board[thisX + dx][thisY + dy] != null
                    && board[thisX + dx][thisY + dy].getColor() != origin_color;
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

