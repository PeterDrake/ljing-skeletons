

/** Model of the Questions game. */
public class QuestionsModel {

	/** The root of the tree of knowledge. */
	public BinaryNode root;

	/** Current node in the decision tree. */
	private BinaryNode currentNode;

	/** A new Game has two questions and three leaves. */
	public QuestionsModel() {
		// TODO You have to write this.
	}

	/** True if the current node is a leaf. */
	public boolean atLeaf() {
		// TODO You have to write this.
	}

	/** Updates the current node to the left or right child, respectively, if answer is true or false. */
	public void descend(boolean answer) {
		// TODO You have to write this.
	}

	/** Returns the current node. */
	public BinaryNode getCurrentNode() {
		// TODO You have to write this.
		return null;
	}

	/** Returns the question asked of the user for learning after losing a game. */
	public String getLearningQuestion(String correct) {
		// TODO You have to write this.
	}
	
	/** Returns the questions asked at the current node. */
	public String getQuestion() {
		// TODO You have to write this.
	}

	/** Returns the root of this game's decision tree. */
	public BinaryNode getRoot() {
		// TODO You have to write this.
		return null;
	}

	/**
	 * Replaces the key of the current node (a leaf) with question and gives it two
	 * children. The left child's key is correct. The right child's key is
	 * this node's old key.
	 * 
	 * This is a "delegate" method that simply calls the version from Node.
	 */
	public void learn(String correct, String question) {
		// TODO You have to write this.
	}

	/** Resets the current node to be the root of the decision tree. */
	public void reset() {
		// TODO You have to write this.
	}

	@Override
	public String toString() {
		// TODO You have to write this.
		return null;
	}

}

