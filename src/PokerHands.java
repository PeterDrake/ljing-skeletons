/**
 * Determines, through Monte Carlo simulation, the probability of certain Poker
 * hands.
 */
public class PokerHands {

	/**
	 * Returns an array of ints containing four copies of each of the numbers 1
	 * through 13. Note that aces are always treated as 1s in this class.
	 */
	public static int[] createDeck() {
		int[] deck = new int[52];
		int i = 0;
		for (int s = 0; s < 4; s++) {
			for (int r = 1; r <= 13; r++) {
				deck[i] = r;
				i++;
			}
		}
		return deck;
	}

	/**
	 * Returns an array of the first five elements of deck. This does not modify
	 * deck.
	 */
	public static int[] deal(int[] deck) {
		int[] result = new int[5];
		for (int i = 0; i < result.length; i++) {
			result[i] = deck[i];
		}
		return result;
	}

	/**
	 * Returns true if ranks, which is assumed to be in increasing order,
	 * contains five consecutive numbers.
	 */
	public static boolean isStraightSorted(int[] ranks) {
		// TODO You have to write this
		return false;
	}

	/**
	 * Returns true if ranks, which is not necessarily in increasing order,
	 * contains five consecutive numbers. Ranks is not modified.
	 */
	public static boolean isStraightUnsorted(int[] ranks) {
		// TODO You have to write this
		return false;
	}

	/**
	 * Returns true if ranks, which is assumed to be in increasing order,
	 * contains two pairs of identical numbers. Four of a kind would count, but
	 * three of a kind would not because the same number would have to be
	 * counted as part of two pairs.
	 */
	public static boolean isTwoPairSorted(int[] ranks) {
		// TODO You have to write this
		return false;
	}

	/**
	 * Returns true if ranks, which is not necessarily in increasing order,
	 * contains two pairs of identical numbers. Four of a kind would count, but
	 * three of a kind would not because the same number would have to be
	 * counted as part of two pairs. Ranks is not modified.
	 */
	public static boolean isTwoPairUnsorted(int[] ranks) {
		// TODO You have to write this
		return false;
	}

	public static void main(String[] args) {
		int[] deck = createDeck();
		int trials = 1000000;
		int straightUnsorted = 0;
		int straightSorted = 0;
		int twoPairUnsorted = 0;
		int twoPairSorted = 0;
		for (int t = 0; t < trials; t++) {
			shuffle(deck);
			int[] hand = deal(deck);
			if (isStraightUnsorted(hand)) {
				straightUnsorted++;
			}
			if (isTwoPairUnsorted(hand)) {
				twoPairUnsorted++;
			}
			sort(hand);
			if (isStraightSorted(hand)) {
				straightSorted++;
			}
			if (isTwoPairSorted(hand)) {
				twoPairSorted++;
			}
		}
		System.out.println("Probability of a straight (unsorted version): "
				+ (double) straightUnsorted / trials);
		System.out.println("Probability of a straight (sorted version):   "
				+ (double) straightSorted / trials);
		System.out.println("Probability of two pair (unsorted version):   "
				+ (double) twoPairUnsorted / trials);
		System.out.println("Probability of two pair (sorted version):     "
				+ (double) twoPairSorted / trials);
	}

	/** Rearranges the elements of data in a random order. */
	public static void shuffle(int[] data) {
		for (int i = data.length - 1; i > 0; i--) {
			int r = (int) (Math.random() * (i + 1));
			int temp = data[i];
			data[i] = data[r];
			data[r] = temp;
		}
	}

	/** Modifies data so that its elements are in increasing order. */
	public static void sort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			int moving = data[i];
			int j;
			for (j = i - 1; j >= 0 && data[j] > moving; j--) {
				data[j + 1] = data[j];
			}
			data[j + 1] = moving;
		}
	}

}
