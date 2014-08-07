import static org.junit.Assert.*;
import org.junit.Test;

public class CardTest {

	@Test
	public void testGetRank() {
		assertEquals(3, new Card(3, Card.HEARTS).getRank());
	}

	@Test
	public void testGetSuit() {
		assertEquals(Card.HEARTS, new Card(3, Card.HEARTS).getSuit());
	}

	@Test
	public void testSetFaceUp() {
		Card c = new Card(7, Card.SPADES);
		assertTrue(c.isFaceUp());
		c.setFaceUp(false);
		assertFalse(c.isFaceUp());
		c.setFaceUp(true);
		assertTrue(c.isFaceUp());
	}

	@Test
	public void testIsRed() {
		assertFalse(new Card(4, Card.CLUBS).isRed());
		assertFalse(new Card(4, Card.SPADES).isRed());
		assertTrue(new Card(4, Card.HEARTS).isRed());
		assertTrue(new Card(4, Card.DIAMONDS).isRed());
	}

}
