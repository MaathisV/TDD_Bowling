package bowling;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	private static final int NUMBER_OF_PINS = 10;
	private static final int NUMBER_OF_FRAMES = 10;
	private int rolls[] = new int[21];
	private int currentRoll = 0;
	private boolean gameOver = false;

	
	public List<Integer> frameScores() {
	    List<Integer> scores = new ArrayList<>();
	    int rollIndex = 0;

	    for (int frame = 0; frame < 10; frame++) {
	        if (isStrike(rollIndex)) {
	            int score = 10 + strikeBonus(rollIndex);
	            scores.add(score);
	            rollIndex += 1;
	        } else if (isSpare(rollIndex)) {
	            int score = 10 + spareBonus(rollIndex);
	            scores.add(score);
	            rollIndex += 2;
	        } else {
	            int score = rolls[rollIndex] + rolls[rollIndex + 1];
	            scores.add(score);
	            rollIndex += 2;
	        }
	    }

	    return scores;
	}

	public void roll(int KnockedPins) {
		if (gameOver) {
			throw new IllegalStateException("Game is already over.");			
		}
		
		rolls[currentRoll++] = KnockedPins;
		if (isGameFinished()) {
			gameOver = true;
		}
		
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
	
	private int getTenthFrameFirstRollIndex(int rollIndex) {
	    int tenthFrameStart = rollIndex - (isStrike(rollIndex - 1) ? 1 : 2);
		return tenthFrameStart;
	}
	
	private boolean isGameFinished() {
	    int rollIndex = 0;
	    int frame = 0;

	    while (frame < 10 && rollIndex < currentRoll) {
	        if (isStrike(rollIndex)) {
	            rollIndex += 1;
	        } else {
	            rollIndex += 2;
	        }
	        frame++;
	    }

	    if (frame < 10) return false;

	    int tenthFrameStart = getTenthFrameFirstRollIndex(rollIndex);

	    if (isStrike(tenthFrameStart)) {
	        return currentRoll >= rollIndex + 2;
	    } else if (isSpare(tenthFrameStart)) {
	        return currentRoll >= rollIndex + 1;
	    } else {
	        return currentRoll >= rollIndex;
	    }
	}

}
