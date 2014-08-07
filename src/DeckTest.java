import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DeckTest {

	private Deck deck;

	@Before
	public void setUp() throws Exception {
		deck = new Deck();
	}

	@Test
	public void testSize() {
		assertEquals(0, deck.size());
		deck.add(new Card(5, Card.DIAMONDS));
		assertEquals(1, deck.size());
	}

	@Test
	public void testGet() {
		Card c = new Card(5, Card.DIAMONDS);
		Card d = new Card(Card.KING, Card.CLUBS);
		deck.add(c);
		deck.add(d);
		assertSame(c, deck.get(0));
		assertSame(d, deck.get(1));
	}

	@Test
	public void testTop() {
		assertNull(deck.top());
		deck.add(new Card(5, Card.DIAMONDS));
		Card c = new Card(6, Card.DIAMONDS);
		deck.add(c);
		assertSame(c, deck.top());
	}

	@Test
	public void testFill() {
		deck.fill();
		assertEquals(52, deck.size());
		// Keep track of which cards have been seen
		boolean[][] seen = new boolean[14][4];
		for (int i = 0; i < deck.size(); i++) {
			Card c = deck.get(i);
			// If a card is seen again, fail
			assertFalse(seen[c.getRank()][c.getSuit()]);
			seen[c.getRank()][c.getSuit()] = true;
		}
	}

	@Test
	public void testMove1() {
		deck.add(new Card(7, Card.SPADES));
		deck.add(new Card(Card.KING, Card.DIAMONDS));
		Deck d = new Deck();
		d.add(new Card(9, Card.HEARTS));
		deck.move(d);
		assertEquals(2, d.size());
		assertEquals(1, deck.size());
		Card c = d.get(0);
		assertEquals(9, c.getRank());
		assertEquals(Card.HEARTS, c.getSuit());
		c = d.get(1);
		assertEquals(Card.KING, c.getRank());
		assertEquals(Card.DIAMONDS, c.getSuit());
	}
	
	@Test
	public void testMoveN() {
		deck.fill();
		Deck d = new Deck();
		d.add(new Card(9, Card.HEARTS));
		Card[] before = { deck.get(49), deck.get(50), deck.get(51) };
		deck.move(d, 3);
		assertEquals(4, d.size());
		assertEquals(49, deck.size());
		Card c = d.get(0);
		assertEquals(9, c.getRank());
		assertEquals(Card.HEARTS, c.getSuit());
		for (int i = 0; i < 3; i++) {
			c = d.get(i + 1);
			assertEquals(before[i].getRank(), c.getRank());
			assertEquals(before[i].getSuit(), c.getSuit());
		}
	}

	@Test
	public void testShuffle() {
		// This test is inspired by experiments 1.1.36 and 1.1.37 in Sedgewick
		// and Wayne, Algorithms, 4th edition. It tests whether, over many
		// shuffles, each card is equally likely to end up in each position. It
		// may fail once in a great while, but it generally passes for a correct
		// shuffling algorithm and fails for common incorrect algorithms.
		Deck unshuffled = new Deck();
		unshuffled.fill();
		int[][] counts = new int[unshuffled.size()][unshuffled
				.size()];
		for (int trial = 0; trial < 5000; trial++) {
			boolean[] seen = new boolean[unshuffled.size()];
			deck = new Deck();
			deck.fill();
			deck.shuffle();
			for (int i = 0; i < unshuffled.size(); i++) {
				for (int j = 0; j < deck.size(); j++) {
					Card a = unshuffled.get(i);
					Card b = deck.get(j);
					if ((a.getRank() == b.getRank()) && (a.getSuit() == b.getSuit())) {
						counts[i][j]++;
						assertFalse(seen[i]);
						seen[i] = true;
					}
				}
			}
		}
		for (int i = 0; i < unshuffled.size(); i++) {
			for (int j = 0; j < unshuffled.size(); j++) {
				assertTrue(counts[i][j] > (5000 / 52.0) * 0.5);
				assertTrue(counts[i][j] < (5000 / 52.0) * 1.5);
			}
		}
	}

}
