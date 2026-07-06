import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        AI ai = new AI(5); // depth 5 = looks 5 moves ahead
        int currentPlayer = Board.BLACK; // Human goes first

        System.out.println("=== OTHELLO AI ===");
        System.out.println("You are BLACK (B), AI is WHITE (W)");
        System.out.println("Enter moves as: row col (e.g. 2 3)");

        while (true) {
            board.printBoard();
            System.out.println("Black: " + board.countTiles(Board.BLACK) +
                               " | White: " + board.countTiles(Board.WHITE));

            List<int[]> moves = board.getValidMoves(currentPlayer);

            if (moves.isEmpty()) {
                System.out.println((currentPlayer == Board.BLACK ? "BLACK" : "WHITE") + " has no moves. Skipping turn.");
                currentPlayer = (currentPlayer == Board.BLACK) ? Board.WHITE : Board.BLACK;
                if (board.getValidMoves(currentPlayer).isEmpty()) {
                    System.out.println("\n=== GAME OVER ===");
                    int b = board.countTiles(Board.BLACK);
                    int w = board.countTiles(Board.WHITE);
                    System.out.println("Black: " + b + " | White: " + w);
                    System.out.println(b > w ? "YOU WIN!" : w > b ? "AI WINS!" : "DRAW!");
                    break;
                }
                continue;
            }

            if (currentPlayer == Board.BLACK) {
                System.out.print("Your move (row col): ");
                int r = sc.nextInt(), c = sc.nextInt();
                if (!board.isValidMove(r, c, Board.BLACK)) {
                    System.out.println("Invalid move! Try again.");
                    continue;
                }
                board.makeMove(r, c, Board.BLACK);
            } else {
                System.out.println("AI is thinking...");
                int[] move = ai.getBestMove(board);
                System.out.println("AI plays: " + move[0] + " " + move[1]);
                System.out.println("Branches pruned by Alpha-Beta: " + ai.pruneCount);
                board.makeMove(move[0], move[1], Board.WHITE);
            }

            currentPlayer = (currentPlayer == Board.BLACK) ? Board.WHITE : Board.BLACK;
        }
        sc.close();
    }
}