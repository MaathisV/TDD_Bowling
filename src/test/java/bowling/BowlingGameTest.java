package bowling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BowlingGameTest {
	
	Game g = new Game();

	private void rollMany(int numRolls, int pins) {
		for (int i = 0; i < numRolls; i++) {
			g.roll(pins);
		}
	}
	
	private void rollSpare() {
		g.roll(5);
		g.roll(5);
	}
	

	private void rollStrike() {
		g.roll(10);
	}
	
	
	@Test
	void testGutterGame() {
		int numRolls = 20;
		int pins = 0;
		rollMany(numRolls, pins);
		int score = g.score();
		assertEquals(0, score);
	}

	@Test
	void testAllOnesGame() {
		rollMany(20, 1);
		int score = g.score();
		assertEquals(20, score);
	}
	
	@Test
	void testSpareGame() {
		rollSpare();
		g.roll(3);
		rollMany(17,0);
		int score = g.score();
		assertEquals(16, score);
	}

	@Test
	void testStrikeGame() {
		rollStrike();
		g.roll(3);
		g.roll(4);
		rollMany(16,0);
		int score = g.score();
		assertEquals(24, score);
	}
	
	@Test
	void testPerfectGame() {
		rollMany(12,10);
		int score = g.score();
		assertEquals(300, score);
	}
	
	
	
	//NEW TESTS ADDED
	@Test
	void ErrorThrowAfterEndGame() {
		rollStrike();
		g.roll(3);
		g.roll(5);
		rollSpare();
		g.roll(8);
		rollMany(13,2);
	    assertThrows(IllegalStateException.class, () -> g.roll(3));
	}
}
