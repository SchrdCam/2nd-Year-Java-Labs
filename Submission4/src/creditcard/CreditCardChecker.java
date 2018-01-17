package creditcard;

public class CreditCardChecker {
	public boolean validate(String creditCard) throws IllegalCardLengthException, IllegalCardFormatException {
		boolean validLength = true;
		if (creditCard.length() < 13 || creditCard.length() > 19) {
			throw new IllegalCardLengthException("This length is invalid : " + creditCard.length());
		}
		for (int i = 0; i < (creditCard.length()); i++) {
			char currentChar = creditCard.charAt(i);
			if (Character.isDigit(currentChar)) {
				continue;
			} else {
				throw new IllegalCardFormatException("This character is invalid : " + currentChar);
			}
		}
		if (creditCard.startsWith("34") || creditCard.startsWith("37")) {
			// American Express
			validLength = (creditCard.length() == 15);
		} else if (creditCard.startsWith("4")) {
			// Visa
			validLength = (creditCard.length() == 13 || creditCard.length() == 16 || creditCard.length() == 19);
		} else if (creditCard.startsWith("5")) {
			// MasterCard
			int prefix = Integer.valueOf(creditCard.substring(0, 2));
			if (prefix >= 51 && prefix <= 55) {
				validLength = (creditCard.length() == 16);
			}
		}
		// Luhn check algorithm
		int sum = 0;
		// Go through the characters one at a time from the end
		for (int i = 1; i <= creditCard.length(); i++) {
			// Get the character value
			int value = Character.getNumericValue(creditCard.charAt(creditCard.length() - i));
			// If it is an even position, the number has a different
			if (i % 2 == 0) {
				if (value <= 4) {
					sum += (value * 2);
				} else {
					sum += (value * 2 - 9);
				}
			} else {
				// Odd positions just get added to the sum directly
				sum += value;
			}
		}
		if (sum % 10 == 0 && validLength == true) {
			return true;
		} else {
			return false;
		}
	}
}
