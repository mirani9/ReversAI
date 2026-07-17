/*
Project: ReversAi
Description: Othello/Reversi AI Game
Algorithm: Alpha-Beta Pruning with Heuristic Evaluation
Technologies: HTML, CSS, JavaScript
Author: Mamta Mirani
*/

const SIZE = 8;
const EMPTY = 0, BLACK = 1, WHITE = 2;
const DIRS = [[-1,-1],[-1,0],[-1,1],[0,-1],[0,1],[1,-1],[1,0],[1,1]];
const DEPTH = 5;

// Position weights — corners most valuable
const WEIGHTS = [
  [120,-20,20, 5, 5,20,-20,120],
  [-20,-40,-5,-5,-5,-5,-40,-20],
  [ 20, -5,15, 3, 3,15, -5, 20],
  [  5, -5, 3, 3, 3, 3, -5,  5],
  [  5, -5, 3, 3, 3, 3, -5,  5],
  [ 20, -5,15, 3, 3,15, -5, 20],
  [-20,-40,-5,-5,-5,-5,-40,-20],
  [120,-20,20, 5, 5,20,-20,120]
];

let nodesEvaluated = 0, pruneCount = 0;
let board = [], currentPlayer = BLACK, gameActive = true, showHints = true;

function initBoard() {
  board = Array.from({length:SIZE}, () => Array(SIZE).fill(EMPTY));
  board[3][3]=WHITE; board[3][4]=BLACK;
  board[4][3]=BLACK; board[4][4]=WHITE;
}

function countTiles(player) {
  let n = 0;
  for (let r = 0; r < SIZE; r++) for (let c = 0; c < SIZE; c++) if (board[r][c] === player) n++;
  return n;
}

function isValid(board, r, c, player) {
  if (board[r][c] !== EMPTY) return false;
  const opp = player === BLACK ? WHITE : BLACK;
  for (const [dr, dc] of DIRS) {
    let nr = r + dr, nc = c + dc, hasOpp = false;
    while (nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE && board[nr][nc] === opp) {
      nr += dr; nc += dc; hasOpp = true;
    }
    if (hasOpp && nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE && board[nr][nc] === player) return true;
  }
  return false;
}

function getValidMoves(board, player) {
  const moves = [];
  for (let r = 0; r < SIZE; r++)
    for (let c = 0; c < SIZE; c++)
      if (isValid(board, r, c, player)) moves.push([r, c]);
  return moves;
}

function applyMove(board, r, c, player) {
  const nb = board.map(row => [...row]);
  nb[r][c] = player;
  const opp = player === BLACK ? WHITE : BLACK;
  for (const [dr, dc] of DIRS) {
    let nr = r + dr, nc = c + dc, flips = [];
    while (nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE && nb[nr][nc] === opp) {
      flips.push([nr, nc]); nr += dr; nc += dc;
    }
    if (flips.length && nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE && nb[nr][nc] === player)
      for (const [fr, fc] of flips) nb[fr][fc] = player;
  }
  return nb;
}

function evaluate(board) {
  let score = 0;
  for (let r = 0; r < SIZE; r++)
    for (let c = 0; c < SIZE; c++) {
      if (board[r][c] === WHITE) score += WEIGHTS[r][c];
      else if (board[r][c] === BLACK) score -= WEIGHTS[r][c];
    }
  score += getValidMoves(board, WHITE).length * 5;
  score -= getValidMoves(board, BLACK).length * 5;
  return score;
}

function alphaBeta(board, depth, alpha, beta, maximizing) {
  nodesEvaluated++;
  const player = maximizing ? WHITE : BLACK;
  const moves = getValidMoves(board, player);
  if (depth === 0 || moves.length === 0) return evaluate(board);

  if (maximizing) {
    let maxEval = -Infinity;
    for (const [r, c] of moves) {
      const ev = alphaBeta(applyMove(board, r, c, WHITE), depth - 1, alpha, beta, false);
      maxEval = Math.max(maxEval, ev);
      alpha = Math.max(alpha, ev);
      if (beta <= alpha) { pruneCount++; break; }
    }
    return maxEval;
  } else {
    let minEval = Infinity;
    for (const [r, c] of moves) {
      const ev = alphaBeta(applyMove(board, r, c, BLACK), depth - 1, alpha, beta, true);
      minEval = Math.min(minEval, ev);
      beta = Math.min(beta, ev);
      if (beta <= alpha) { pruneCount++; break; }
    }
    return minEval;
  }
}

function getBestMove(board) {
  nodesEvaluated = 0; pruneCount = 0;
  let bestMove = null, bestScore = -Infinity;
  for (const [r, c] of getValidMoves(board, WHITE)) {
    const score = alphaBeta(applyMove(board, r, c, WHITE), DEPTH - 1, -Infinity, Infinity, false);
    if (score > bestScore) { bestScore = score; bestMove = [r, c]; }
  }
  return { move: bestMove, nodesEvaluated, pruneCount };
}

function render() {
  const boardEl = document.getElementById('board');
  boardEl.innerHTML = '';
  const hints = showHints && currentPlayer === BLACK ? getValidMoves(board, BLACK) : [];
  const hintSet = new Set(hints.map(([r, c]) => `${r},${c}`));

  for (let r = 0; r < SIZE; r++) {
    for (let c = 0; c < SIZE; c++) {
      const cell = document.createElement('div');
      cell.className = 'cell';
      if (hintSet.has(`${r},${c}`)) cell.classList.add('hint');
      if (board[r][c] !== EMPTY) {
        const tile = document.createElement('div');
        tile.className = 'tile ' + (board[r][c] === BLACK ? 'black' : 'white');
        cell.appendChild(tile);
      }
      cell.onclick = () => handleClick(r, c);
      boardEl.appendChild(cell);
    }
  }
  document.getElementById('black-score').textContent = countTiles(BLACK);
  document.getElementById('white-score').textContent = countTiles(WHITE);
}

async function handleClick(r, c) {
  if (!gameActive || currentPlayer !== BLACK) return;
  if (!isValid(board, r, c, BLACK)) return;
  board = applyMove(board, r, c, BLACK);
  currentPlayer = WHITE;
  render();
  setStatus('AI is thinking...');
  await sleep(100);
  await doAiMove();
}

async function doAiMove() {
  const result = getBestMove(board);
  document.getElementById('stat-nodes').textContent = result.nodesEvaluated.toLocaleString();
  document.getElementById('stat-pruned').textContent = result.pruneCount.toLocaleString();
  if (result.move) board = applyMove(board, result.move[0], result.move[1], WHITE);
  currentPlayer = BLACK;
  render();

  const myMoves = getValidMoves(board, BLACK);
  const aiMoves = getValidMoves(board, WHITE);
  if (myMoves.length === 0 && aiMoves.length === 0) { endGame(); return; }
  if (myMoves.length === 0) {
    setStatus('No moves for you. AI goes again...');
    currentPlayer = WHITE; await sleep(700); doAiMove(); return;
  }
  setStatus('Your turn! Click a dot to place your tile.');
}

function setStatus(msg) { document.getElementById('status').textContent = msg; }

function endGame() {
  gameActive = false;
  const b = countTiles(BLACK), w = countTiles(WHITE);
  document.getElementById('go-title').textContent = b > w ? 'YOU WIN!' : w > b ? 'AI WINS!' : 'DRAW!';
  document.getElementById('go-score').textContent = 'Black: ' + b + '  —  White: ' + w;
  document.getElementById('game-over').classList.add('show');
}

function resetGame() {
  document.getElementById('game-over').classList.remove('show');
  initBoard(); gameActive = true; currentPlayer = BLACK; showHints = true;
  document.getElementById('stat-nodes').textContent = '—';
  document.getElementById('stat-pruned').textContent = '—';
  setStatus('Your turn! Click a dot to place your tile.');
  render();
}

function toggleHints() { showHints = !showHints; render(); }
function sleep(ms) { return new Promise(r => setTimeout(r, ms)); }

initBoard();
render();
