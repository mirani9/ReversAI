# ReversAi

A browser-based implementation of the classic Othello (Reversi) board game featuring an AI opponent powered by Alpha-Beta Pruning and heuristic board evaluation.

## Project Overview

ReversAi delivers a polished, browser-playable Othello experience with a strong AI opponent that uses classic search and evaluation techniques. The UI is clean, responsive, and designed for easy gameplay.

## Features

- Classic Othello/Reversi gameplay
- Human player as Black (●)
- AI opponent as White (○)
- Alpha-Beta Pruning for efficient search
- Heuristic board evaluation with positional weights
- Turn status, score tracking, legal move hints
- Game over detection with result overlay

## Technologies Used

- HTML
- CSS
- JavaScript

## AI Algorithm

The AI uses a Minimax search with Alpha-Beta Pruning and a heuristic evaluation function to select strong moves.

## Alpha-Beta Pruning

Alpha-Beta Pruning reduces the number of evaluated nodes in the game tree by eliminating branches that cannot affect the final decision. This makes the AI search faster while preserving the result of Minimax.

## Heuristic Evaluation Strategy

The evaluation combines positional weights for board squares with mobility considerations. Corners are highly valued, edges are weighted positively, and the number of legal moves for each player influences the score.

## Game Rules

Players alternate placing pieces on an 8x8 board. Any opponent discs surrounded by the new piece and another friendly piece are flipped. The game ends when neither player can move.

## Project Structure

- `index.html` — page structure and asset references
- `styles.css` — all styling and layout rules
- `script.js` — game logic, rendering, AI, and interaction
- `README.md` — project documentation
- `LICENSE` — MIT license
- `.gitignore` — project ignore rules
- `favicon.png` — project favicon
- `screenshots/game.png` — placeholder screenshot

## Installation & Running

1. Open the project folder in VS Code.
2. Use Live Server or another local server extension.
3. Open `index.html` in the browser.

## Screenshots

![ReversAi Screenshot](screenshots/game.png)

## Live Demo

Live demo link placeholder: `https://your-live-demo-url.com`

## Future Improvements

- Add sound effects
- Support two-player mode
- Add difficulty levels
- Add move history and undo
- Add mobile-specific controls

## License

This project is licensed under the MIT License.

## Author

Mamta Mirani
