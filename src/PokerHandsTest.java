import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PokerHandsTest {

	@Test
	public void testIsStraightSorted1() {
		assertTrue(PokerHands.isStraightSorted(new int[] {1, 2, 3, 4, 5}));
	}
	
	@Test
	public void testIsStraightSorted2() {
		assertTrue(PokerHands.isStraightSorted(new int[] {5, 6, 7, 8, 9}));
	}
	
	@Test
	public void testIsStraightSorted3() {
		assertTrue(PokerHands.isStraightSorted(new int[] {9, 10, 11, 12, 13}));
	}
	
	@Test
	public void testIsStraightSorted4() {
		assertFalse(PokerHands.isStraightSorted(new int[] {1, 10, 11, 12, 13}));
	}
	
	@Test
	public void testIsStraightSorted5() {
		assertFalse(PokerHands.isStraightSorted(new int[] {2, 3, 5, 6, 7}));
	}
	
	@Test
	public void testIsStraightSorted6() {
		assertFalse(PokerHands.isStraightSorted(new int[] {4, 5, 5, 6, 7}));
	}

	@Test
	public void testIsStraightUnsorted1() {
		assertTrue(PokerHands.isStraightUnsorted(new int[] {2, 3, 1, 5, 4}));
	}
	
	@Test
	public void testIsStraightUnsorted2() {
		assertTrue(PokerHands.isStraightUnsorted(new int[] {13, 12, 11, 10, 9}));
	}
	
	@Test
	public void testIsStraightUnsorted3() {
		assertTrue(PokerHands.isStraightUnsorted(new int[] {5, 6, 7, 8, 9}));
	}
	
	@Test
	public void testIsStraightUnsorted4() {
		assertFalse(PokerHands.isStraightUnsorted(new int[] {10, 11, 12, 13, 1}));
	}
	
	@Test
	public void testIsStraightUnsorted5() {
		assertFalse(PokerHands.isStraightUnsorted(new int[] {2, 7, 3, 5, 6}));
	}
	
	@Test
	public void testIsStraightUnsorted6() {
		assertFalse(PokerHands.isStraightUnsorted(new int[] {3, 4, 5, 6, 3}));
	}

	// TODO You have to write some tests for the isTwoPair methods
	
	@Test
	public void testShuffle() {
		// This test is inspired by experiments 1.1.36 and 1.1.37 in Sedgewick
		// and Wayne, Algorithms, 4th edition. It tests whether, over many
		// shuffles, each element is equally likely to end up in each position. It
		// may fail once in a great while, but it generally passes for a correct
		// shuffling algorithm and fails for common incorrect algorithms.
		int[][] counts = new int[14][52];
		for (int trial = 0; trial < 5000; trial++) {
			int[] deck = PokerHands.createDeck();
			PokerHands.shuffle(deck);
			for (int rank = 1; rank <= 13; rank++) {
				for (int j = 0; j < deck.length; j++) {
					if (deck[j] == rank) {
						counts[rank][j]++;
					}
				}
			}
		}
		for (int rank = 1; rank <= 13; rank++) {
			for (int j = 0; j < 52; j++) {
				assertTrue(counts[rank][j] > (5000 / 13.0) * 0.5);
				assertTrue(counts[rank][j] < (5000 / 13.0) * 1.5);
			}
		}
	}

	@Test
	public void testSort1() {
		int[] hand = {5, 2, 4, 3, 1};
		PokerHands.sort(hand);
		assertArrayEquals(new int[] {1, 2, 3, 4, 5}, hand);
	}
	
	@Test
	public void testSort2() {
		int[] hand = new int[] {6, 7, 8, 9, 10};
		PokerHands.sort(hand);
		assertArrayEquals(new int[] {6, 7, 8, 9, 10}, hand);
	}
	
	@Test
	public void testSort3() {
		int[] hand = new int[] {13, 12, 11, 10, 9};
		PokerHands.sort(hand);
		assertArrayEquals(new int[] {9, 10, 11, 12, 13}, hand);
	}

}
