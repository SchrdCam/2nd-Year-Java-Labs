/**
 * Starter code for JP2 Laboratory 1, Submission 1.
 * 
 * @author Mary Ellen Foster <MaryEllen.Foster@glasgow.ac.uk>
 *
 */

public class GuessingGame {
	public static void main(String[] args) {
		// Initialise the scanner and choose the target value (a random number between 1 and 20)
		java.util.Scanner stdin = new java.util.Scanner(System.in);
		int target = 1 + (int) (Math.random() * 20);
		boolean guessed_correct = false;
		int guess_count = 1;
		// Start the main loop
		while (guessed_correct==false) {
			// get user input
			System.out.println("Enter your guess >> ");
			int user_guess = stdin.nextInt();
			if (user_guess<target) {
				System.out.println("That value is too low!");
			} else if (user_guess>target) {
				System.out.println("That value is too high!");
			} else if (user_guess==target) {
				System.out.println("That guess was correct!");
				System.out.println("This time it took you "+guess_count+" tries");
				guessed_correct=true;
			}
			// iterates to count the number of guesses
			guess_count++;
		}
		// Close the scanner
		stdin.close();
	}
}
