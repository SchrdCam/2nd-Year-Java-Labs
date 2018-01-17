package creditcard;

public class CreditCardMain {

	public static void main(String[] args) throws IllegalCardLengthException, IllegalCardFormatException {

		// Read the credit card number
		java.util.Scanner stdin = new java.util.Scanner(System.in);
		System.out.println("Enter the credit card number: ");
		String creditCard = stdin.next();
		stdin.close();
		CreditCardChecker c = new CreditCardChecker();
		try {
			c.validate(creditCard);
		} catch (IllegalCardFormatException e1) {
			System.out.println(e1.getMessage());
		} catch (IllegalCardLengthException e2) {
			System.out.println(e2.getMessage());
		} catch (Exception e3) {
			System.out.println(e3.getMessage());
		}
		boolean finalBool = c.validate(creditCard);
		if (finalBool == true) {
			System.out.println("This card is Valid");
		} else {
			System.out.println("This card is Invalid");
		}
	}
}