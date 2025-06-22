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
	void testAllOneSpareGame() {
		g.roll(5);
		g.roll(5); //Spare
		g.roll(3);
		rollMany(17,0);
		int score = g.score();
		assertEquals(16, score); //Add spare Bonus
	}

}
