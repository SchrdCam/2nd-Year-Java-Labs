// Cameron Taylor
// 2256483t

public class CreditCardChecker {

	public static void main(String[] args) {
		
		java.util.Scanner stdin = new java.util.Scanner(System.in);
		System.out.println("Enter the credit card number: ");
		String creditCard = stdin.next();
		boolean LuhnCheck = false;
		String cardType = checkCardType(creditCard);// cartType calls the type checking method, checkCardType()
		boolean isValid = checkValid(creditCard,cardType);// using the type evaluated above and the card number, this method verifies that the card num is valid for its type
		if (isValid == true) {
			LuhnCheck = Luhn(creditCard);// Checks the card number to see if it is a valid card number
		}
		if(isValid==true && LuhnCheck == true) {
			System.out.println("Card Type : "+cardType);
			System.out.println("Card Number is VALID");
		} else {
			System.out.println("Card Type : "+cardType);
			System.out.println("Card Number is INVALID");
		}
		stdin.close();
	}
	
	public static String checkCardType(String creditCard) { 
		if(creditCard.startsWith("4")) {
			return new String("Visa");
		} else if(creditCard.startsWith("34") ||creditCard.startsWith("37")) {
			return new String("American Express");
		} else if(((Integer.parseInt(creditCard.substring(0,2)))<=55 && (Integer.parseInt(creditCard.substring(0,2)))>=51)) { 
			return new String("MasterCard");
		// If none of the above criteria are met, the type of card being used is not recognised by the system
		} else {
			return new String("Unknown Card Type");
		}
	}
	public static boolean checkValid(String creditCard, String cardType) { 
		boolean valid = false;
		if(cardType==("Unknown Card Type")) { // returns the card as invalid if the issuer is not Visa, MasterCard or AE
			return valid;
		}
		if((creditCard.length()==13 || creditCard.length()==16 || creditCard.length()==19) && cardType.equals("Visa")) { 
			return valid = true; 
		} else if(creditCard.length()==15 && cardType.equals("American Express")) {
			return valid = true;
		} else if(creditCard.length()==16 && cardType.equals("MasterCard")) {
			return valid = true;
		// This case is executed where the card is evaluated to be typed the same as one of the issuers but is of insufficient length to be a valid number
		} else if(("Visa American Express MasterCard").contains(cardType) && valid!=true){
			System.out.println("Invalid card number length for this type of Card");
			return valid;
		}else {
			return valid;
		}
	} 
	public static boolean Luhn(String creditCard) {
		int len = creditCard.length();
		int total = 0;
		boolean validLuhn = true;
		// this variable allows the for loop to alternate the part of the loop it conducts each time it loops
		boolean even = true;
		for (int i = 0 ; i<creditCard.length() ; i+=1) {
			even = !even;
			int tempVal = Integer.parseInt(creditCard.substring(len-1,len));
			// This operation is performed if the current digit is an even digit
			if (even == true) { 
				tempVal = tempVal*2;
				// This statement keeps all numbers in single digits after being multiplied by 2
				if (tempVal > 9) {
					tempVal = tempVal - 9;
				}
				total = total + tempVal;
			// This operation is performed it the current digit is an odd digit
			} else if (even == false) {
				total = total+tempVal;
			}
			len = len -1;
		}
		int rem = total%10; // rem is the remainder from total divided by 10
		if (rem == 0) { // if the remainder is 0, then the number satisfies the Luhn equation
			return validLuhn;
		}else {
			return validLuhn = false;
		}
	}
}
