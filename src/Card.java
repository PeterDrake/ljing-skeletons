/** A standard playing card. */
public class Card {

	public static final int ACE = 1;

	public static final int CLUBS = 0;

	public static final int DIAMONDS = 3;

	public static final int HEARTS = 2;

	public static final int JACK = 11;

	public static final int KING = 13;

	public static final int QUEEN = 12;

	public static final int SPADES = 1;

	/** True if this card is face-up. */
	private boolean faceUp;

	/**
	 * @see #getRank()
	 */
	private int rank;

	/**
	 * @see #getSuit()
	 */
	private int suit;

	/**
	 * Newly-created cards are face-up by default.
	 * 
	 * @see getRank()
	 * @see getSuit()
	 */
	public Card(int rank, int suit) {
		// TODO You have to write this.
	}

	/**
	 * Returns the rank of this card: ACE, a number 2 to 10, JACK, QUEEN, or
	 * KING. ACE is defined as 1, and so on upward, for ease of iteration.
	 */
	public int getRank() {
		// TODO You have to write this.
		return -1;
	}

	/**
	 * Returns the suit of this card, one of CLUBS, SPADES, HEARTS, or DIAMONDS.
	 * These correspond to the ints 0 through 3 for ease of iteration.
	 */
	public int getSuit() {
		// TODO You have to write this.
		return -1;
	}

	/** Returns true if this card is face-up. */
	public boolean isFaceUp() {
		// TODO You have to write this.
		return false;
	}

	/** Returns true if this card is red. */
	public boolean isRed() {
		// TODO You have to write this.
		return false;
	}

	/** Sets the face-up status of this card. */
	public void setFaceUp(boolean faceUp) {
		// TODO You have to write this.
	}

}
