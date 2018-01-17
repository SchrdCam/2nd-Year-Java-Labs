// Cameron Taylor
// 2256483t

public class BrickSet {
	// defines each of the following variables to be unchangeable by forces outside the class
	private int setNum;
	private String name;
	private String theme;
	private int pieceNum;
	private int price;

	//defines the internal variables of the new object when this class is instanciated
	public BrickSet(int uniqueSetNumber, String Name, String Theme , int PieceNumber , int RetailPrice){
		this.setNum=uniqueSetNumber;
		this.name=Name;
		this.theme=Theme;
		this.pieceNum=PieceNumber;
		this.price=RetailPrice;
	}

	// returns the retail price of the set
	public int getRetailPrice() {
		return this.price;
	}

	// updates the retail price of the set in case it has changed recently
	public void setRetailPrice(int retailPrice) {
		this.price = retailPrice;
	}

	// returns the unique identifying number of the set
	public int getUniqueSetNumber() {
		return this.setNum;
	}
	
	// returns the official name of the set
	public String getName() {
		return this.name;
	}
	
	// returns the general theme of the set
	public String getTheme() {
		return this.theme;
	}
	
	//returns the number of pieces for the set
	public int getPieceNumber() {
		return this.pieceNum;
	}
	
	// returns a double value representing how much each piece costs relative to the total price
	
	public double getPricePerPiece() {
		double pricePerPiece = ((this.price*1.00)/(this.pieceNum*1.00));
		return pricePerPiece;
	}
}
