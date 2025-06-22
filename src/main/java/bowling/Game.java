package bowling;

public class Game {
	
	int score = 0;

	public void roll(int KnockedPins) {
		score += KnockedPins;

	}

	public int score() {
		return score;
	}

}
