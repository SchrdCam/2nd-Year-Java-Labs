
public class RetiredSet extends BrickSet {
	private int retiredYear;
	
	// This constructor calls the constructor of the parent class BrickSet and defines its private variable of the year the set was retired
	public RetiredSet (int setNumber, String name, String theme, int numPieces, int retiredYear) {
		super(setNumber,name,theme,numPieces);
		this.retiredYear = retiredYear;
	}

	public int getRetiredYear() {
		return retiredYear;
	}
	
	// retrieves the subclass specific variables for the toString method 
	public String getDetails() {
		return String.valueOf(this.retiredYear);
	}

}
