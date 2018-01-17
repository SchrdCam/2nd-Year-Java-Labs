package game;

import java.util.Scanner;

public class GameMain {

	/**
	 * Prompts the user for the tournament parameters and then runs a tournament.
	 */
	public static void main(String[] args) {
		// Read everything from standard input
		Scanner stdin = new Scanner(System.in);

		// First player is always a computer
		GamePlayer player1 = new ComputerPlayer("Computer");

		// Second player may be a computer or a human
		GamePlayer player2;
		System.out.println("Enter name of human player, or empty string for two computer players");
		String name = stdin.nextLine();
		if (name.length() == 0) {
			System.out.println("Using two computer players");
			player2 = new ComputerPlayer("Computer2");
		} else {
			player2 = new HumanPlayer(name, stdin);
		}

		// Get the number of games required to win the tournament -- and be sure
		// it is a positive integer
		int numGames = -1;
		while (numGames <= 0) {
			System.out.println("Enter number of games to win: ");
			try {
				numGames = stdin.nextInt();
			} catch (NumberFormatException ex) {
				System.out.println("Invalid input!");
			}
			if (numGames <= 0) {
				System.out.println("Invalid input!");
			}
		}

		// Run the tournament with the given parameters
		GamePlayer winner = playTournament(player1, player2, numGames);

		System.out.println("------------------");
		System.out.println("Overall winner is: " + winner.getName());

		stdin.close();
	}

	private static GamePlayer playTournament(GamePlayer player1, GamePlayer player2, int numGames) {		
		int p1Wins = 0;
		int p2Wins = 0;
		//This is the main game loop
		while (p1Wins< numGames && p2Wins< numGames) {
			Symbol p1Sym = player1.chooseSymbol();
			Symbol p2Sym = player2.chooseSymbol();
			//The results of the choices are compared
			GameResult result = p1Sym.getResult(p2Sym);
			// if the CPU wins, then it increments player1 wins
			if (result == GameResult.WIN) {
				System.out.println("Player 1 Wins! ");
				System.out.println(player1.getName()+" played "+p1Sym+", while "+player2.getName()+" played "+p2Sym);
				p1Wins= p1Wins + 1;
			// if the human player/player 2 wins, then it increments player2 wins
			} if (result == GameResult.LOSE) {
				System.out.println("Player 2 Wins! ");
				System.out.println(player2.getName()+" played "+p2Sym+", while "+player1.getName()+" played "+p1Sym);
				p2Wins= p2Wins + 1;
			// This ensures if neither of the above conditions are met a draw is returned
			} if (result!= GameResult.LOSE && result!=GameResult.WIN){
				System.out.println("It's a Draw!");
			}
			// The played forms are saved to the humanList and cpuList
			player1.addHistory(p2Sym, p1Sym);

		}
		// generic game end conditions
		if (p1Wins == numGames) {
			return player1;
		} else  {
			return player2;
		}
	}
}
