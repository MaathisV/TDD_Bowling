package bowling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BowlingGameTest {

	@Test
	void testGutterGame() {
		Game g = new Game();
		for (int i = 0; i < 20; i++) {
			g.roll(0);
		}
		int score = g.score();
		assertEquals(0, score);
	}
	
	@Test
	void testAllOnesGame() {
		Game g = new Game();
		for (int i = 0; i < 20; i++) {
			g.roll(1);
		}
		int score = g.score();
		assertEquals(20, score);
	}

}
