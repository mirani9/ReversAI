public class Board {
    public static final int SIZE = 8;
    public static final int EMPTY = 0;
    public static final int BLACK = 1; // Human
    public static final int WHITE = 2; // AI

    public int[][] grid;

    public Board() {
        grid = new int[SIZE][SIZE];
        // Starting position
        grid[3][3] = WHITE;
        grid[3][4] = BLACK;
        grid[4][3] = BLACK;
        grid[4][4] = WHITE;
    }

    // Deep copy constructor
    public Board(Board other) {
        grid = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            grid[i] = other.grid[i].clone();
    }

    public void printBoard() {
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == EMPTY) System.out.print(". ");
                else if (grid[i][j] == BLACK) System.out.print("B ");
                else System.out.print("W ");
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int row, int col, int player) {
        if (grid[row][col] != EMPTY) return false;
        int opponent = (player == BLACK) ? WHITE : BLACK;
        int[] dr = {-1,-1,-1,0,0,1,1,1};
        int[] dc = {-1,0,1,-1,1,-1,0,1};

        for (int d = 0; d < 8; d++) {
            int r = row + dr[d];
            int c = col + dc[d];
            boolean hasOpponent = false;

            while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] == opponent) {
                r += dr[d]; c += dc[d];
                hasOpponent = true;
            }
            if (hasOpponent && r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] == player)
                return true;
        }
        return false;
    }

    public void makeMove(int row, int col, int player) {
        grid[row][col] = player;
        int opponent = (player == BLACK) ? WHITE : BLACK;
        int[] dr = {-1,-1,-1,0,0,1,1,1};
        int[] dc = {-1,0,1,-1,1,-1,0,1};

        for (int d = 0; d < 8; d++) {
            int r = row + dr[d];
            int c = col + dc[d];
            java.util.List<int[]> toFlip = new java.util.ArrayList<>();

            while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] == opponent) {
                toFlip.add(new int[]{r, c});
                r += dr[d]; c += dc[d];
            }
            if (r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] == player) {
                for (int[] pos : toFlip)
                    grid[pos[0]][pos[1]] = player;
            }
        }
    }

    public java.util.List<int[]> getValidMoves(int player) {
        java.util.List<int[]> moves = new java.util.ArrayList<>();
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (isValidMove(i, j, player))
                    moves.add(new int[]{i, j});
        return moves;
    }

    public int countTiles(int player) {
        int count = 0;
        for (int[] row : grid)
            for (int cell : row)
                if (cell == player) count++;
        return count;
    }
}