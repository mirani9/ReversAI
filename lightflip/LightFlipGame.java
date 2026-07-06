import java.util.*;

public class LightFlipGame {

    static final int N = 3;
    static int[][] grid = new int[N][N];

    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    // Flip (self + adjacent)
    static void flip(int x, int y) {
        for (int d = 0; d < 5; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                grid[nx][ny] ^= 1;
            }
        }
    }

    // Print grid (no emoji)
    static void printGrid() {
        System.out.println("\nGrid:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check if all OFF
    static boolean isAllOff() {
        for (int[] row : grid)
            for (int cell : row)
                if (cell == 1) return false;
        return true;
    }

    // Flip for temp grid (AI)
    static void flipTemp(int[][] temp, int x, int y) {
        for (int d = 0; d < 5; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                temp[nx][ny] ^= 1;
            }
        }
    }

    // Backtracking solver
    static boolean solve(int[][] temp, int index, List<String> moves) {

        if (index == N * N) {
            for (int[] row : temp)
                for (int cell : row)
                    if (cell == 1) return false;
            return true;
        }

        int x = index / N;
        int y = index % N;

        // Click
        flipTemp(temp, x, y);
        moves.add("(" + x + "," + y + ")");

        if (solve(temp, index + 1, moves)) return true;

        // Backtrack
        flipTemp(temp, x, y);
        moves.remove(moves.size() - 1);

        // Skip
        return solve(temp, index + 1, moves);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // Generate random grid
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                grid[i][j] = rand.nextInt(2);

        System.out.println("🎮 Light Flip Puzzle Game");

        while (true) {

            printGrid();

            if (isAllOff()) {
                System.out.println("🏆 You Win!");
                break;
            }

            System.out.println("\n1. Play Move");
            System.out.println("2. Get Hint (AI)");
            System.out.print("Choice: ");

            int choice = sc.nextInt();

            if (choice == 1) {

                System.out.print("Enter row (0-2): ");
                int x = sc.nextInt();

                System.out.print("Enter col (0-2): ");
                int y = sc.nextInt();

                if (x >= 0 && y >= 0 && x < N && y < N) {
                    flip(x, y);
                } else {
                    System.out.println("❌ Invalid move!");
                }

            } else if (choice == 2) {

                int[][] temp = new int[N][N];
                for (int i = 0; i < N; i++)
                    temp[i] = grid[i].clone();

                List<String> moves = new ArrayList<>();

                if (solve(temp, 0, moves)) {
                    System.out.println("💡 Hint moves: " + moves);
                } else {
                    System.out.println("No solution exists!");
                }

            } else {
                System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}