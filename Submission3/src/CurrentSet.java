
public class CurrentSet extends BrickSet {
	private int retailPrice;
	
	// This constructor calls the constructor of the parent class BrickSet and defines its private variable of the retail price of the set
	public CurrentSet (int setNumber, String name, String theme, int numPieces, int retailPrice) {
		super(setNumber,name,theme,numPieces);
		this.retailPrice = retailPrice;
	}

	public int getRetailPrice() {
		return retailPrice;
	}
	
	public void setRetailPrice(int retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	// Computes price per piece 
	public double getPricePerPiece() {
		return (double)this.retailPrice/this.numPieces;
	}
	
	// retrieves the subclass specific variables for the toString method 
	public String getDetails() {
		return String.valueOf(this.retailPrice);
	}
}
