public class ChessBoard {
    public char[][] board;
    private int dimension;

    public ChessBoard(int dimension) {
        if (dimension < 4 || dimension > Settings.MAX_DIMENSION) {
            throw new RuntimeException("Illegal dimension!");
        } else {
            this.dimension = dimension;
            board = new char[dimension][dimension];
            for (int i = 0; i < dimension; i ++) {
                for (int j = 0; j < dimension; j ++)
                    board[i][j] = '.';
            }
            int ul = dimension / 2 - 1;
            board[ul][ul] = 'O';
            board[ul][ul + 1] = 'X';
            board[ul + 1][ul] = 'X';
            board[ul + 1][ul + 1] = 'O';

        }

    }

    public void putOnChess(int[] decision, char shape) {
        if (decision.length != 2)
            throw new RuntimeException("Wrong format of decision!");
        board[decision[0]][decision[1]] = shape;
        takeEffect(decision, shape);
    }

    private void takeEffect(int[] decision, char shape) {
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

        public boolean canMove(char shape) {
            return true;
        }

        public boolean isLegal(int[] position, char shape) {
            return true;
        }
    }
}
