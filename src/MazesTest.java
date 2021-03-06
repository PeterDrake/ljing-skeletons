import static org.junit.Assert.*;

import org.junit.Test;

public class MazesTest {

	@Test
	public void testContains() {
		int[][] list = { { 1, 2 }, { 3, 4 }, { 4, 3 } };
		assertTrue(Mazes.contains(new int[] { 3, 4 }, list, list.length));
		assertFalse(Mazes.contains(new int[] { 2, 1 }, list, list.length));
		assertFalse(Mazes.contains(new int[] { 4, 3 }, list, 2));
	}

	@Test
	public void testRemove() {
		int[][] list = { { 1, 2 }, { 3, 4 }, { 4, 3 }, { 5, 8 } };
		// Remove 3, 4 from the whole list
		Mazes.remove(new int[] { 3, 4 }, list, list.length);
		assertEquals("[[1, 2], [5, 8], [4, 3], [5, 8]]",
				java.util.Arrays.deepToString(list));
		// Remove 1, 2 from the first three elements of list
		Mazes.remove(new int[] { 1, 2 }, list, 3);
		assertEquals("[[4, 3], [5, 8], [4, 3], [5, 8]]",
				java.util.Arrays.deepToString(list));
	}

	@Test
	public void testChooseRandomlyFrom() {
		int[][] list = { { 1, 2 }, { 3, 4 }, { 4, 3 }, { 5, 8 } };
		// Choose a random element many times
		int[] counters = new int[4];
		for (int i = 0; i < 600; i++) {
			int[] pair = Mazes.chooseRandomlyFrom(list, 3);
			for (int j = 0; j < list.length; j++) {
				// We can use == here to compare arrays because we're not asking
				// if they have the same contents; we're asking if they're THE
				// SAME ARRAY
				if (pair == list[j]) {
					counters[j]++;
				}
			}
		}
		// Each of the first three items should be chosen about a third of the
		// time
		for (int i = 0; i < 3; i++) {
			assertTrue(counters[i] > 150);
			assertTrue(counters[i] < 250);
		}
		// The last item should never be chosen
		assertEquals(0, counters[3]);
		// The counters should add up to the number of elements chosen
		assertEquals(600, counters[0] + counters[1] + counters[2]);
	}

	@Test
	public void testAddPassage() {
		boolean[][][] passages = new boolean[10][10][4];
		Mazes.addPassage(passages, new int[] { 1, 2 }, new int[] { 2, 2 });
		assertTrue(passages[1][2][Mazes.EAST]);
		assertFalse(passages[2][2][Mazes.WEST]);
		assertFalse(passages[1][2][Mazes.NORTH]);
		Mazes.addPassage(passages, new int[] { 1, 2 }, new int[] { 2, 2 });
		assertTrue(passages[1][2][Mazes.EAST]);
		assertFalse(passages[2][2][Mazes.WEST]);
		assertFalse(passages[1][2][Mazes.NORTH]);
		Mazes.addPassage(passages, new int[] { 1, 2 }, new int[] { 2, 2 });
		assertTrue(passages[1][2][Mazes.EAST]);
		assertFalse(passages[2][2][Mazes.WEST]);
		assertFalse(passages[1][2][Mazes.NORTH]);
		Mazes.addPassage(passages, new int[] { 1, 2 }, new int[] { 2, 2 });
		assertTrue(passages[1][2][Mazes.EAST]);
		assertFalse(passages[2][2][Mazes.WEST]);
		assertFalse(passages[1][2][Mazes.NORTH]);
	}

	@Test
	public void testExpandLocation() {
		boolean[][][] passages = new boolean[2][2][4];
		int[][] unexplored = { { 1, 0 }, { 1, 1 }, null, null, null };
		// Expand east from 0, 1
		int[] there = Mazes.expandLocation(passages, unexplored, 2, new int[] {
				0, 1 }, Mazes.EAST);
		// {1, 1} should have been returned
		assertEquals("[1, 1]", java.util.Arrays.toString(there));
		// The passage below should have been created
		assertTrue(passages[0][1][Mazes.EAST]);
		// Trying to expand in any other direction from 0, 1 should fail
		assertNull(Mazes.expandLocation(passages, unexplored, 2, new int[] { 0,
				1 }, Mazes.NORTH));
		assertNull(Mazes.expandLocation(passages, unexplored, 2, new int[] { 0,
				1 }, Mazes.SOUTH));
		assertNull(Mazes.expandLocation(passages, unexplored, 2, new int[] { 0,
				1 }, Mazes.WEST));
	}

	@Test
	public void testExpandMaze() {
		boolean[][][] passages = new boolean[2][2][4];
		int[][] done = { null, null, null, null };
		int[][] frontier = { { 0, 0 }, { 0, 1 }, null, null };
		int[][] unexplored = { { 1, 0 }, { 1, 1 }, null, null, null };
		int[] counts = { 0, 2, 2 };
		// Expand the maze from 0, 1
		Mazes.expandMaze(passages, done, frontier, unexplored, counts,
				new int[] { 0, 1 });
		assertEquals("[0, 3, 1]", java.util.Arrays.toString(counts));
		// 1, 1 should have been added to the frontier
		assertTrue(Mazes.contains(new int[] { 1, 1 }, frontier, 3));
		// The passage below should have been created
		assertTrue(passages[0][1][Mazes.EAST]);
		// Now expand again from a random location
		Mazes.expandMaze(passages, done, frontier, unexplored, counts, null);
		if (counts[0] == 1) {
			// 0, 1 was chosen
			assertEquals("[1, 2, 1]", java.util.Arrays.toString(counts));
			assertEquals("[[0, 1], null, null, null]", java.util.Arrays.deepToString(done));
			assertFalse(passages[1][0][Mazes.WEST]);
			assertFalse(passages[1][0][Mazes.NORTH]);
		} else {
			// 0, 0 or 1, 1 was chosen
			assertEquals("[0, 4, 0]", java.util.Arrays.toString(counts));
			// 1, 0 should have been added to the frontier
			assertTrue(Mazes.contains(new int[] { 1, 0 }, frontier, 4));
			// One of the two passages below should have been created, so the
			// booleans should no longer be equal
			assertFalse(passages[0][0][Mazes.EAST] == passages[1][1][Mazes.SOUTH]);
		}
	}

	@Test
	public void testAddToFront() {
		int[] start = {1, 2};
		int[][] restOfPath = {{3, 4}, {5, 6}};
		assertEquals("[[1, 2], [3, 4], [5, 6]]", java.util.Arrays.deepToString(Mazes.addToFront(start, restOfPath)));
	}

	@Test
	public void testSolveEasy() {
		boolean[][][] passages = new boolean[2][2][4];
		passages[0][0][Mazes.NORTH] = true;
		passages[0][1][Mazes.EAST] = true;
		int[][] solution = { { 0, 0 }, { 0, 1 }, { 1, 1 } };
		assertEquals(
				java.util.Arrays.deepToString(solution),
				java.util.Arrays.deepToString(Mazes.solve(passages, new int[] {
						0, 0 }, new int[] { 1, 1 })));
	}

	@Test
	public void testSolveHard() {
		boolean[][][] passages = { { { true, true, false, false }, // 0, 0
			{ true, false, false, false }, // 0, 1
			{ false, true, false, false } // 0, 2
			}, { { false, false, false, false }, // 1, 0
					{ false, true, false, false }, // 1, 1
					{ false, false, true, false } // 1, 2
			}, { { false, false, false, false }, // 2, 0
					{ true, false, true, false }, // 2, 1
					{ false, false, false, false } // 2, 2
			} };
		int[][] solution = { { 0, 0 }, { 0, 1 }, {0, 2}, {1, 2}, {1, 1}, {2, 1}, {2, 2}};
		assertEquals(
				java.util.Arrays.deepToString(solution),
				java.util.Arrays.deepToString(Mazes.solve(passages, new int[] {
						0, 0 }, new int[] { 2, 2 })));

	}

}
