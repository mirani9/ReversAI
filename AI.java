import java.util.List;

public class AI {
    private int depth;
    public int pruneCount = 0; // Track pruned branches

    public AI(int depth) {
        this.depth = depth;
    }

    public int[] getBestMove(Board board) {
        pruneCount = 0;
        int[] bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        List<int[]> moves = board.getValidMoves(Board.WHITE);
        for (int[] move : moves) {
            Board newBoard = new Board(board);
            newBoard.makeMove(move[0], move[1], Board.WHITE);
            int score = alphaBeta(newBoard, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int alphaBeta(Board board, int depth, int alpha, int beta, boolean maximizing) {
        List<int[]> moves = board.getValidMoves(maximizing ? Board.WHITE : Board.BLACK);

        if (depth == 0 || moves.isEmpty())
            return evaluate(board);

        if (maximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int[] move : moves) {
                Board newBoard = new Board(board);
                newBoard.makeMove(move[0], move[1], Board.WHITE);
                int eval = alphaBeta(newBoard, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    pruneCount++; // PRUNING HAPPENS HERE
                    break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int[] move : moves) {
                Board newBoard = new Board(board);
                newBoard.makeMove(move[0], move[1], Board.BLACK);
                int eval = alphaBeta(newBoard, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    pruneCount++; // PRUNING HAPPENS HERE
                    break;
                }
            }
            return minEval;
        }
    }

    // Heuristic: count difference + corner bonus
    private int evaluate(Board board) {
        int score = board.countTiles(Board.WHITE) - board.countTiles(Board.BLACK);
        // Corners are very valuable!
        int[][] corners = {{0,0},{0,7},{7,0},{7,7}};
        for (int[] c : corners) {
            if (board.grid[c[0]][c[1]] == Board.WHITE) score += 25;
            if (board.grid[c[0]][c[1]] == Board.BLACK) score -= 25;
        }
        return score;
    }
}