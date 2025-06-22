package bowling;

public class Game {
	
	private static final int NUMBER_OF_PINS = 10;
	private static final int NUMBER_OF_FRAMES = 10;
	private int rolls[] = new int[21];
	private int currentRoll = 0;

	public void roll(int KnockedPins) {
		rolls[currentRoll++] = KnockedPins;
	}
	
	private boolean isSpare(int firstFrameRollIndex) {
		return rolls[firstFrameRollIndex] + rolls[firstFrameRollIndex+1] == NUMBER_OF_PINS;
	}
	
	private boolean isStrike(int frameFirstRollIndex) {
		return rolls[frameFirstRollIndex] == NUMBER_OF_PINS;
	}
	
	private int spareBonus(int rollIndex) {
		return rolls[rollIndex+2];
	}

	private int strikeBonus(int rollIndex) {
		return rolls[rollIndex+1] + spareBonus(rollIndex);
	}

		
	public int score() {
		int score = 0;
		int rollIndex = 0;
		for (int frame = 0; frame < NUMBER_OF_FRAMES; frame++) {
			if (isStrike(rollIndex)) {
				score += NUMBER_OF_PINS + strikeBonus(rollIndex);
				rollIndex++;
			} else if (isSpare(rollIndex)) {
				score += NUMBER_OF_PINS + spareBonus(rollIndex);
				rollIndex += 2;
			} else {
				score += rolls[rollIndex] + rolls[rollIndex+1];
				rollIndex += 2;
			}
		}
		return score;
	}
}
