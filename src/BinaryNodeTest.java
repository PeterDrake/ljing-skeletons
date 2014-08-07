

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class BinaryNodeTest {

	private BinaryNode a;
	
	private BinaryNode b;
	
	private BinaryNode c;
	
	private BinaryNode d;
	
	private BinaryNode e;
	
	@Before
	public void setUp() {
		a = new BinaryNode("a");
		b = new BinaryNode("b");
		c = new BinaryNode("c");
		d = new BinaryNode("d");
		e = new BinaryNode("e");
		a.setLeft(b);
		b.setLeft(c);
		b.setRight(d);
		a.setRight(e);
	}

	@Test
	public void testChildGetters() {
		assertSame(b, a.getLeft());
		assertSame(e, a.getRight());
	}
	
	@Test
	public void testIsLeaf() {
		assertFalse(a.isLeaf());
		assertFalse(b.isLeaf());
		assertTrue(c.isLeaf());
		assertTrue(d.isLeaf());
		assertTrue(e.isLeaf());
	}
	
	@Test
	public void testHeight() {
		assertEquals(2, a.height());
		assertEquals(1, b.height());
		assertEquals(0, c.height());
		assertEquals(0, d.height());
		assertEquals(0, e.height());
	}
	
	@Test
	public void testToString() {
		assertEquals("a\n  b\n    c\n    d\n  e\n", a.toString());
	}

	@Test
	public void testAlternateConstructor() {
		BinaryNode root = new BinaryNode("Does it have a motor?",
				new BinaryNode("Does it store information?",
						new BinaryNode("a hard drive"),
						new BinaryNode("a car")),
				new BinaryNode("a giraffe"));
		assertEquals("Does it have a motor?\n  Does it store information?\n    a hard drive\n    a car\n  a giraffe\n", root.toString());
	}
	
	@Test
	public void testLearn() {
		BinaryNode root = new BinaryNode("Does it have a motor?",
				new BinaryNode("Does it store information?",
						new BinaryNode("a hard drive"),
						new BinaryNode("a car")),
				new BinaryNode("a giraffe"));
		root.getRight().learn("a whale", "Does it live in the ocean?");
		assertEquals("Does it have a motor?\n  Does it store information?\n    a hard drive\n    a car\n  Does it live in the ocean?\n    a whale\n    a giraffe\n", root.toString());				
	}

}
