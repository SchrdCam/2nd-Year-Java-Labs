/**
 * Starter code for JP2 lab 3.
 */
public abstract class BrickSet {
	/** Fields */
	protected int setNumber;
	protected String name;
	protected String theme;
	protected int numPieces;

	/** Creates a new BrickSet object with the given parameters */
	public BrickSet(int setNumber, String name, String theme, int numPieces) {
		this.setNumber = setNumber;
		this.name = name;
		this.theme = theme;
		this.numPieces = numPieces;
	}

	/** Getters and setters */
	public int getSetNumber() {
		return setNumber;
	}

	public String getName() {
		return name;
	}

	public String getTheme() {
		return theme;
	}

	public int getNumPieces() {
		return numPieces;
	}

	/** Override toString() to return a nicer string representation of the BrickSet */
	public String toString() {
		return setNumber + ": " + name + " (" + theme + "), " + numPieces + " pieces: current price (£)/ year of retirement" + getDetails();
	}
	
	/** this abstract method states that all subclasses of BrickSet must define a version of getDetails so as to fulfil the toString method*/
	protected abstract String getDetails();
}
