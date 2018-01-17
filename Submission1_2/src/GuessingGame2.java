/**
 * Starter code for JP2 Laboratory 1, Submission 2.
 * 
 * @author Mary Ellen Foster <MaryEllen.Foster@glasgow.ac.uk>
 *
 */
public class GuessingGame2 {

	public static void main(String[] args) {
		// Initialise the scanner
		java.util.Scanner stdin = new java.util.Scanner(System.in);
		System.out.println("Input the max number which can be randomly selected >> ");
		int rand_Max = stdin.nextInt();
		int target = 1 + (int) (Math.random() * rand_Max);
		boolean guessed_correct = false;
		int guess_count = 1;
		System.out.println("Input the allowed number of guesses >> ");
		int guess_allowed = stdin.nextInt();
		while (guessed_correct==false) {
			// get user input
			System.out.println("Enter your guess >> ");
			int user_guess = stdin.nextInt();
			if (user_guess<0 || user_guess>rand_Max) {
				System.out.println("Input out of range! try again!");
				continue;
			}
			if (user_guess<target) {
				System.out.println("That value is too low!");
			} else if (user_guess>target) {
				System.out.println("That value is too high!");
			} else if (user_guess==target) {
				System.out.println("That guess was correct!");
				System.out.println("This time it took you "+guess_count+" tries");
				guessed_correct=true;
			}
			if (guess_count >= guess_allowed) {
				System.out.println("Out of guesses! The answer was "+target);
				break;
			}
			// iterates to count the number of guesses
			guess_count++;
			
		}
		// Close the scanner
		stdin.close();
	}

}
