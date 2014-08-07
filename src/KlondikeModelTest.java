import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KlondikeModelTest {

	private KlondikeModel model;
	
	@Before
	public void setUp() throws Exception {
		model = new KlondikeModel();
	}

	@Test
	public void testSuccessor() {
		Card c = new Card(Card.QUEEN, Card.CLUBS);
		Card d = new Card(Card.KING, Card.HEARTS);
		assertTrue(model.successor(c, d));
		assertFalse(model.successor(d, c));
		assertFalse(model.successor(c, null));
		assertTrue(model.successor(d, null));
	}

	@Test
	public void testKlondikeMove() {
		Deck deck = new Deck();
		// Deck is the source pile
		Card c = new Card(10, Card.SPADES);
		c.setFaceUp(false);
		deck.add(c);
		c = new Card(Card.JACK, Card.HEARTS);
		c.setFaceUp(false);
		deck.add(c);
		deck.add(new Card(6, Card.HEARTS));
		deck.add(new Card(5, Card.SPADES));
		deck.add(new Card(4, Card.DIAMONDS));
		deck.add(new Card(3, Card.CLUBS));
		// Destination is the other pile
		Deck destination = new Deck();
		c = new Card(Card.KING, Card.HEARTS);
		c.setFaceUp(false);
		destination.add(c);
		destination.add(new Card(8, Card.DIAMONDS));
		destination.add(new Card(7, Card.SPADES));
		destination.add(new Card(6, Card.DIAMONDS));
		// It is illegal to move from destination to deck
		model.klondikeMove(destination, deck);
		assertEquals(6, deck.size());
		assertEquals(4, destination.size());
		// Moving from deck to destination should have the desired effect
		model.klondikeMove(deck, destination);
		assertEquals(3, deck.size());
		assertEquals(7, destination.size());
		for (int i = 0; i < 6; i++) {
			assertEquals(3 + i, destination.get(destination.size() - 1 - i).getRank());
		}
	}
	
	@Test
	public void testMoveKingToEmptyDeck() {
		Deck deck = new Deck();
		// A 7 can't be moved to an empty pile
		deck.add(new Card(7, Card.DIAMONDS));
		Deck destination = new Deck();
		model.klondikeMove(deck, destination);
		assertEquals(1, deck.size());
		// ...but a king can
		deck.get(0).setFaceUp(false);
		deck.add(new Card(Card.KING, Card.HEARTS));
		model.klondikeMove(deck, destination);
		assertEquals(1, deck.size());
		assertEquals(1, destination.size());
	}

	@Test
	public void testDrawNextCard() {
		// There are 23 cards left in the deck after the initial deal
		for (int i = 1; i <= 23; i++) {
			model.drawNextCard();
			assertEquals(23 - i, model.getDrawPile().size());
			assertEquals(1 + i, model.getDiscardPile().size());
		}
		// Trying to draw another card should have no effect
		model.drawNextCard();
		assertEquals(0, model.getDrawPile().size());
		assertEquals(24, model.getDiscardPile().size());
	}
	
	@Test
	public void testMoveToFoundation() {
		Deck source = new Deck();
		source.add(new Card(7, Card.SPADES));
		source.top().setFaceUp(false);
		source.add(new Card(2, Card.HEARTS));
		source.add(new Card(Card.ACE, Card.HEARTS));
		// This illegal move should have no effect
		model.moveToFoundation(source, Card.CLUBS);
		// These should work
		model.moveToFoundation(source, Card.HEARTS);
		model.moveToFoundation(source, Card.HEARTS);
		// This should not
		model.moveToFoundation(source, Card.SPADES);
		assertEquals(1, source.size());
		assertTrue(source.top().isFaceUp());
	}

	@Test
	public void testMoveToTableau() {
		// Arrange the tableau to make some moves possible
		Deck garbage = new Deck();
		model.getTableau(6).move(garbage);
		model.getTableau(6).add(new Card(6, Card.SPADES));
		model.getTableau(5).move(garbage);
		model.getTableau(5).add(new Card(Card.QUEEN, Card.CLUBS));
		model.getTableau(5).add(new Card(4, Card.CLUBS));
		model.getTableau(5).add(new Card(3, Card.HEARTS));
		model.getDiscardPile().add(new Card(5, Card.DIAMONDS));
		// Moving from the draw pile to tableau pile 5 should have no effect
		model.moveToTableau(model.getDiscardPile(), 5);
		assertEquals(2, model.getDiscardPile().size());
		// Moving to pile 6 should work
		model.moveToTableau(model.getDiscardPile(), 6);
		assertEquals(1, model.getDiscardPile().size());
		// Now a move from tableau pile 5 to 6 should move two cards
		model.moveToTableau(model.getTableau(5), 6);
		assertEquals(10, model.getTableau(6).size());
		assertEquals(6, model.getTableau(5).size());
	}
	

}
