/** A deck or pile of cards. */
public class Deck {

	/** The cards in this deck. */
	private Card[] cards;

	/** @see #size() */
	private int size;

	/** A new deck is initially empty. */
	public Deck() {
		// TODO You have to write this.
	}

	/** Adds card to the top of this deck. */
	public void add(Card card) {
		// TODO You have to write this.
	}

	/** Adds all cards in a standard deck. */
	public void fill() {
		// TODO You have to write this.
	}

	/**
	 * Returns the ith card in this deck, where card 0 is the one on the bottom.
	 * Assumes the deck is not empty.
	 */
	public Card get(int i) {
		// TODO You have to write this.
		return null;
	}

	/** Moves one card from the top of this deck to the top of that deck. */
	public void move(Deck that) {
		// TODO You have to write this.
	}

	/**
	 * Moves the top n cards from the top of this deck to the top of that deck.
	 * They maintain their order, so that the card that used to be on top of
	 * this becomes the top of that.
	 */
	public void move(Deck that, int n) {
		// TODO You have to write this.
	}

	/**
	 * Randomly reorders the cards in this deck.
	 */
	public void shuffle() {
		// TODO You have to write this.
	}

	/** Returns the number of cards in this deck. */
	public int size() {
		// TODO You have to write this.
		return -1;
	}

	/**
	 * Returns the top card on this deck (but does not modify the deck).
	 * 
	 * @return null if this deck is empty.
	 */
	public Card top() {
		// TODO You have to write this.
		return null;
	}

}
