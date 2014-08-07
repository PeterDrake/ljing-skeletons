import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MastermindModelTest {

	private MastermindModel game;

	@Before
	public void setUp() throws Exception {
		game = new MastermindModel("royr");
	}

	/**
	 * This one is pretty simple. Just count the number of matches between the
	 * guess and the correct answer (which was passed to the Game constructor).
	 */
	@Test
	public void testGetNumberOfBlackPegs() {
		assertEquals(4, game.getNumberOfBlackPegs("royr"));
		assertEquals(2, game.getNumberOfBlackPegs("bogr"));
		assertEquals(1, game.getNumberOfBlackPegs("rroy"));
	}

	/**
	 * This tests the counts method.
	 */
	@Test
	public void testCounts() {
		assertArrayEquals(new int[] {2, 1, 1, 0, 0, 0}, game.counts("royr"));
		assertArrayEquals(new int[] {0, 1, 0, 2, 0, 1}, game.counts("govg"));
	}

	/**
	 * This is the difficult one. It is much easier if you first sort the guess
	 * and the correct answer. Be sure to subtract the number of black pegs
	 * before returning the result.
	 */
	@Test
	public void testGetNumberOfWhitePegs() {
		assertEquals(0, game.getNumberOfWhitePegs("royr"));
		assertEquals(2, game.getNumberOfWhitePegs("brgy"));
		assertEquals(3, game.getNumberOfWhitePegs("rroy"));
		game = new MastermindModel("rooo");
		assertEquals(1, game.getNumberOfWhitePegs("grrr"));
		game = new MastermindModel("oorr");
		assertEquals(2, game.getNumberOfWhitePegs("bboo"));
		game = new MastermindModel("gory");
		assertEquals(1, game.getNumberOfWhitePegs("oogb"));
	}
	
	/**
	 * This tests some getters and the guess method, which stores a guess.
	 */
	@Test
	public void testGuess() {
		assertEquals(0, game.getNumberOfGuessesMade());
		game.guess("ygbv");
		assertEquals(1, game.getNumberOfGuessesMade());
		game.guess("ovgg");
		assertEquals(2, game.getNumberOfGuessesMade());
		assertEquals("ygbv", game.getGuess(0));
		assertEquals("ovgg", game.getGuess(1));
	}

	/**
	 * The game is won if the last guess was correct.
	 */
	@Test
	public void testIsWon() {
		assertFalse(game.isWon());
		// The String correct is built in pieces so that it will .equals(), but
		// not ==, the correct code
		String correct = "ro";
		correct += "yr";
		game.guess(correct);
		assertTrue(game.isWon());
	}

	/**
	 * The game is lost if there have been 10 guesses.
	 */
	@Test
	public void testIsLost() {
		assertFalse(game.isLost());
		for (int i = 0; i < 9; i++) {
			game.guess("bvrg");
		}
		assertFalse(game.isLost());
		game.guess("bvrg");
		assertTrue(game.isLost());
	}

	/**
	 * If the 10th guess is correct, the game is still won.
	 */
	@Test
	public void testWinSupersedesLoss() {
		assertFalse(game.isLost());
		for (int i = 0; i < 9; i++) {
			game.guess("bvrg");
		}
		assertFalse(game.isLost());
		game.guess("royr");
		assertFalse(game.isLost());
		assertTrue(game.isWon());
	}

	/**
	 * This tests another getter.
	 */
	@Test
	public void testGetCorrect() {
		assertEquals("royr", game.getCorrect());
	}

	/**
	 * The zero-argument constructor should construct a random 4-character
	 * correct answer, using the letters r, o, y, g, b, and v.
	 */
	@Test
	public void testRandomCorrect() {
		assertFalse(new MastermindModel().getCorrect().equals(new MastermindModel().getCorrect()));
	}

}
