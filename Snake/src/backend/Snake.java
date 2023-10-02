package backend;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Snake {
	
	private int[][] mapa;
	private int[] head;
	private int[] tail;
	private int snakeLength;
	private int lastMove;
	private int rows;
	private int columns;
	private int moves;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int EMPTY_SPACE = 0;
	public static final int SNAKE_SPACE = 1;
	public static final int POINT_SPACE = 2;
	private boolean gameOver;
	private Queue<int[]> snake;
	
	public Snake(int rows, int columns, int snakeRow, int snakeColumn) {
		this.rows = rows;
		this.columns = columns;
		lastMove = -1;
		snake = new ConcurrentLinkedQueue<>();
		mapa = new int[rows][columns];
		snakeLength = 1;
		mapa[snakeRow][snakeColumn] = 1;
		head = new int[] {snakeRow, snakeColumn};
		tail = head;
		snake.add(head);
		newPoint();
	}
	
	public void reiniciar(int snakeRow, int snakeColumn) {
		moves = 0;
		gameOver = false;
		lastMove = -1;
		snake = new ConcurrentLinkedQueue<>();
		mapa = new int[rows][columns];
		snakeLength = 1;
		mapa[snakeRow][snakeColumn] = 1;
		head = new int[] {snakeRow, snakeColumn};
		tail = head;
		snake.add(head);
		newPoint();
	}
	
	public boolean jogar(int move) {
		moveSnake(move);
		return gameOver;
	}
	
	public void exibir() {
		System.out.println(toString());
	}
	
	private void moveSnake(int move) {
		if (gameOver) {
			return;
		}
		moves++;
		if (!isOpposite(move)) {
			lastMove = move;
		}

		switch (lastMove) {
		case LEFT:
			novaPosicao(head[0], head[1] - 1);
			break;
		case RIGHT:
			novaPosicao(head[0], head[1] + 1);
			break;
		case UP:
			novaPosicao(head[0] - 1, head[1]);
			break;
		case DOWN:
			novaPosicao(head[0] + 1, head[1]);
			break;
		}
	}
	
	private boolean isOpposite(int move) {
		if (move == UP && lastMove == DOWN) {
			return true;
		}
		if (move == DOWN && lastMove == UP) {
			return true;
		}
		if (move == LEFT && lastMove == RIGHT) {
			return true;
		}
		if (move == RIGHT && lastMove == LEFT) {
			return true;
		}
		return false;
	}
	
	private void atualizaSnake(int row, int column) {
		head = new int[] {row(row), column(column)};
		snake.add(head);
		snake.poll();
		mapa[tail[0]][tail[1]] = 0;
		tail = snake.peek();
	}
	
	private void mapa(int row, int column, int value) {
		mapa[row(row)][column(column)] = value;
	}
	
	private int mapa(int row, int column) {
		return mapa[row(row)][column(column)];
	}
	
	private void newPoint() {
		int value = 0;
		int row;
		int column;
		do {
			row = sortear(0, rows - 1);
			column = sortear(0, columns - 1);
			value = mapa[row][column];
		} while (value != EMPTY_SPACE);
		mapa[row][column] = POINT_SPACE;
	}
	
	private void novaPosicao(int row, int column) {
		int value = mapa(row, column);
		if (value == POINT_SPACE) {
			snakeLength++;
			head = new int[] {row(row), column(column)};
			snake.add(head);
			newPoint();
		} else if (value == SNAKE_SPACE) {
			gameOver = true;
		} else {
			atualizaSnake(row, column);
		}
		mapa(row, column, SNAKE_SPACE);
	}
	
	private int row(int row) {
		if (row < 0) {
			row = rows - 1;
		} else if (row == rows) {
			row = 0;
		}
		return row;
	}
	
	private int column(int column) {
		if (column < 0) {
			column = columns - 1;
		} else if (column == columns) {
			column = 0;
		}
		return column;
	}
	
	public int[][] getMapa() {
		return mapa;
	}
	
	public int getSnakeLength() {
		return snakeLength;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	private int sortear(int min, int max) {
		return (int) (min + Math.round(Math.random() * max - min));
	}
	
	@Override
	public String toString() {
		String jogo = "";
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				jogo+=mapa[i][j];
				if (j == 14) {
					jogo+="\n";
				}
			}
		}
		return jogo;
	}
}