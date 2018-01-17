package game;

import java.util.Scanner;

public class HumanPlayer extends GamePlayer {
	private Scanner stdin;
	
	public HumanPlayer(String Name, Scanner stdin) {
		super(Name);
		this.stdin = stdin;
	}

	@Override
	// asks the player to input their next form
	public Symbol chooseSymbol() throws IllegalArgumentException{
		Symbol choice = Symbol.ROCK;
		// recursively retries this method if an invalid input is received until a valid input is given
		try {
			System.out.println("Please make a choice on what you'd like to play next");
			System.out.println("Please type your answer in ALL CAPS			        ");
			System.out.println("Options : ROCK, PAPER, SCISSORS, LIZARD, SPOCK  >>  ");
			choice = Symbol.valueOf((stdin.next()).toUpperCase());
		} catch (IllegalArgumentException e1) {
			this.chooseSymbol();
		}
		return choice;
	}
}