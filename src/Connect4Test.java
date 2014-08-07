import static org.junit.Assert.*;
import org.junit.Test;

public class Connect4Test {

	public java.awt.Color[][] emptyBoard() {
		java.awt.Color[][] result = new java.awt.Color[6][7];
		for (int r = 0; r < result.length; r++) {
			for (int c = 0; c < result[r].length; c++) {
				result[r][c] = StdDraw.GRAY;
			}
		}	
		return result;
	}

	@Test
	public void testOpposite() {
		assertEquals(StdDraw.WHITE, Connect4.opposite(StdDraw.BLACK));
		assertEquals(StdDraw.BLACK, Connect4.opposite(StdDraw.WHITE));
	}

	@Test
	public void testDrop() {
		java.awt.Color[][] board = emptyBoard();
		Connect4.drop(board, StdDraw.BLACK, 2);
		assertEquals(StdDraw.BLACK, board[0][2]);
		Connect4.drop(board, StdDraw.WHITE, 2);
		assertEquals(StdDraw.WHITE, board[1][2]);
		assertEquals(StdDraw.BLACK, board[0][2]);
	}

	@Test
	public void testLegal() {
		java.awt.Color[][] board = emptyBoard();
		assertFalse(Connect4.legal(board, -1));		
		assertFalse(Connect4.legal(board, 7));		
		assertTrue(Connect4.legal(board, 6));		
		Connect4.drop(board, StdDraw.BLACK, 2);
		Connect4.drop(board, StdDraw.WHITE, 2);
		Connect4.drop(board, StdDraw.BLACK, 2);
		Connect4.drop(board, StdDraw.WHITE, 2);
		Connect4.drop(board, StdDraw.BLACK, 2);
		assertTrue(Connect4.legal(board, 2));
		Connect4.drop(board, StdDraw.WHITE, 2);
		assertFalse(Connect4.legal(board, 2));
	}

	@Test
	public void testFull() {
		java.awt.Color[][] board = emptyBoard();
		for (int r = 0; r < Connect4.ROWS; r++) {
			for (int c = 0; c < Connect4.COLUMNS; c++) {
				assertFalse(Connect4.full(board));
				Connect4.drop(board, StdDraw.BLACK, c);
			}
		}
		assertTrue(Connect4.full(board));
	}

	@Test
	public void testHorizontalWinner() {
		java.awt.Color[][] board = emptyBoard();
		assertEquals(StdDraw.GRAY, Connect4.winner(board));
		for (int c = 3; c < 7; c++) {
			Connect4.drop(board, StdDraw.BLACK, c);
		}
		assertEquals(StdDraw.BLACK, Connect4.winner(board));
	}
	
	@Test
	public void testVerticalWinner() {
		java.awt.Color[][] board = emptyBoard();
		for (int i = 0; i < 4; i++) {
			Connect4.drop(board, StdDraw.WHITE, 6);
		}
		assertEquals(StdDraw.WHITE, Connect4.winner(board));
	}
	
	@Test
	public void testDiagonalWinner1() {
		java.awt.Color[][] board = emptyBoard();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < i; j++) {
				Connect4.drop(board, StdDraw.WHITE, i);
			}
			Connect4.drop(board, StdDraw.BLACK, i);
		}
		assertEquals(StdDraw.BLACK, Connect4.winner(board));
	}

	@Test
	public void testDiagonalWinner2() {
		java.awt.Color[][] board = emptyBoard();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < i; j++) {
				Connect4.drop(board, StdDraw.BLACK, 5 - i);
			}
			Connect4.drop(board, StdDraw.WHITE, 5 - i);
		}
		assertEquals(StdDraw.WHITE, Connect4.winner(board));
	}
	
	@Test
	public void testUndo() {
		java.awt.Color[][] board = emptyBoard();
		Connect4.drop(board, StdDraw.BLACK, 2);
		Connect4.drop(board, StdDraw.WHITE, 2);
		Connect4.undo(board, 2);
		assertEquals(StdDraw.BLACK, board[0][2]);
		assertEquals(StdDraw.GRAY, board[1][2]);
	}

	@Test
	public void testMax0() {
		java.awt.Color[][] board = emptyBoard();
		for (int i = 0; i < 3; i++) {
			Connect4.drop(board, StdDraw.BLACK, i);
			Connect4.drop(board, StdDraw.WHITE, Connect4.COLUMNS - 1 - i);
		}
		assertEquals(0, Connect4.max(board, 0, 0));
		Connect4.drop(board, StdDraw.BLACK, 3);
		assertEquals(1, Connect4.max(board, 0, 0));
		Connect4.undo(board, 3);
		Connect4.drop(board, StdDraw.WHITE, 3);
		assertEquals(-1, Connect4.max(board, 0, 0));
	}
	
	@Test
	public void testMax1() {
		java.awt.Color[][] board = emptyBoard();
		// Black cannot win in one move
		assertEquals(0, Connect4.max(board, 1, 0));
		Connect4.drop(board, StdDraw.BLACK, 0);
		Connect4.drop(board, StdDraw.BLACK, 0);
		Connect4.drop(board, StdDraw.BLACK, 0);
		// Now black can win in one move
		assertEquals(1, Connect4.max(board, 1, 0));
	}

	@Test
	public void testMax2() {
		java.awt.Color[][] board = emptyBoard();
		for (int i = 0; i < 3; i++) {
			Connect4.drop(board, StdDraw.WHITE, i + 2);
		}
		// Black cannot prevent white from winning
		assertEquals(-1, Connect4.max(board, 2, 0));
		Connect4.drop(board, StdDraw.BLACK, 0);
		Connect4.drop(board, StdDraw.BLACK, 0);
		Connect4.drop(board, StdDraw.BLACK, 0);
		// Now black can win in one move
		assertEquals(1, Connect4.max(board, 2, 0));
	}

	@Test
	public void testMin3() {
		java.awt.Color[][] board = emptyBoard();
		for (int i = 0; i < 2; i++) {
			Connect4.drop(board, StdDraw.WHITE, i + 2);
		}
		// White can win in three moves
		assertEquals(-1, Connect4.min(board, 3, 0));
	}
	
	@Test
	public void testBestMoveForBlack() {
		java.awt.Color[][] board = emptyBoard();
		for (int i = 0; i < 3; i++) {
			Connect4.drop(board, StdDraw.WHITE, 5);
		}
		// Black has to block
		assertEquals(5, Connect4.bestMoveForBlack(board, 3));
	}

	@Test
	public void testDeepSearch() {
		java.awt.Color[][] board = emptyBoard();
		Connect4.drop(board, StdDraw.WHITE, 0);
		Connect4.drop(board, StdDraw.BLACK, 1);
		Connect4.drop(board, StdDraw.WHITE, 2);
		Connect4.drop(board, StdDraw.BLACK, 5);
		Connect4.drop(board, StdDraw.BLACK, 6);
		Connect4.drop(board, StdDraw.BLACK, 0);
		Connect4.drop(board, StdDraw.BLACK, 1);
		Connect4.drop(board, StdDraw.BLACK, 2);
		// Black 3 or 4 force a win on black's next move
		// Similarly, black 3 forces white to block at 3
		int move = Connect4.bestMoveForBlack(board, 3);
		assertTrue(move == 3 || move == 4);
		// This sequence can't be seen in a shallower search
		move = Connect4.bestMoveForBlack(board, 1);
		assertFalse(move == 3 || move == 4);
		move = Connect4.bestMoveForBlack(board, 2);
		assertFalse(move == 3 || move == 4);
	}

}
