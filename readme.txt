ChessBoard.MAX_DIMENSION
multi-player extension, beyond only two players
input and output devices changeable

private static void setRoles() ---- not extendable since 固定下来的询问方式X/O
并且规则Judge也无法拓展，因为不知道规则


游戏结束的条件
1.整个棋盘满了--每个点得分都是0--不canMove
2.一方已经被吃光--不canMove
3.双方都不canMove

4.illegal已经事先检测