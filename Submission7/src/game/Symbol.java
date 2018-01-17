package game;

public enum Symbol {
	ROCK, PAPER, SCISSORS, LIZARD, SPOCK;
	
	public GameResult getResult(Symbol s) {
		// if both players play the same form, they draw the current game
		if (this == s) {
			return GameResult.DRAW;
		}
		switch (this) {
			// Rock defeats {Lizard, Scissors}
		case ROCK:
			if (s == Symbol.LIZARD || s == Symbol.SCISSORS) {
				return GameResult.WIN;
			} else {
				return GameResult.LOSE;
			}
			// Paper defeats {Rock, Spock}
		case PAPER:
			if (s == Symbol.ROCK || s == Symbol.SPOCK) {
				return GameResult.WIN;
			} else {
				return GameResult.LOSE;
			}
			// Scissors defeats {Paper, Lizard}
		case SCISSORS:
			if (s == Symbol.PAPER || s == Symbol.LIZARD) {
				return GameResult.WIN;
			} else {
				return GameResult.LOSE;
			}
			// Lizard defeats {Spock, Paper}
		case LIZARD:
			if (s == Symbol.PAPER || s == Symbol.SPOCK) {
				return GameResult.WIN;
			} else {
				return GameResult.LOSE;
			}
			// Spock defeats {Scissors, Rock}
		case SPOCK:
			if (s == Symbol.SCISSORS || s == Symbol.ROCK) {
				return GameResult.WIN;
			} else {
				return GameResult.LOSE;
			}
		default:
			return GameResult.LOSE;
		}
	}
}