package wishlist;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;

public class WishList {

	/**
	 * creates the wishList list to which BrickSet objects are added and taken from
	 **/
	public ArrayList<BrickSet> wishList = new ArrayList<BrickSet>();

	/** the no args constructor **/
	public WishList() {
	}

	/**
	 * This uses the Collections class method sort() to order the list given by set
	 * number
	 **/
	public Collection<BrickSet> getSets() {
		Collections.sort(wishList);
		return wishList;
	}

	/**
	 * This method takes a given set and checks the wishList to see if it is already
	 * within the wishlist. If it is within the list already, then the list is not
	 * changed and returns False, but if it is not, The set is added to the list and
	 * True is returned.
	 **/
	public boolean addSet(BrickSet set) {
		for (BrickSet Brick : wishList) {
			if (Brick.equals(set)) {
				return false;
			}
		}
		wishList.add(set);
		return true;
	}

	/**
	 * This method takes a given set and checks the wishList to see if it is already
	 * within the wishlist. If it is within the list already, then the set is
	 * removed from the list and returns True, but if it is not, The list is
	 * unaltered and False is returned.
	 **/
	public boolean removeSet(BrickSet set) {
		for (BrickSet Brick : wishList) {
			if (Brick.equals(set)) {
				wishList.remove(set);
				return true;
			}
		}
		return false;
	}

	/** This method saves the wishList to a specified output file **/
	public void saveToFile(String filename) throws IOException {
		PrintWriter printer = new PrintWriter(new FileWriter(filename));
		for (BrickSet set : wishList) {
			printer.write(set.getSetNumber() + "," + set.getName() + "," + set.getTheme() + "," + set.getNumPieces()
					+ "," + set.getRetailPrice() + "\n");
		}
		printer.close();
	}

	/**
	 * This method takes a filename and loads a wishlist in from the file,
	 * formatting as necessary, and returning it to the request location. The
	 * beginning defines a BufferedReader and current line string, and then a while
	 * loop begins that splits each BrickSet in the list into 5 parts, sets the
	 * numbers in the split BrickSet to Int, and combines them all into a variable
	 * write, which is then added to wish. wish is then returned to the place of
	 * origin in the code.
	 **/
	public static WishList loadFromFile(String filename) throws IOException {
		WishList wishList = new WishList();
		String curLine = null;
		BufferedReader buffr = new BufferedReader(new FileReader(filename));
		while ((curLine = buffr.readLine()) != null) {
			String[] curLineSp = curLine.split(",");
			int SetNo = Integer.parseInt(curLineSp[0]);
			int SetNumPieces = Integer.parseInt(curLineSp[3]);
			int SetRetailPrice = Integer.parseInt(curLineSp[4]);

			BrickSet write = new BrickSet(SetNo, curLineSp[1], curLineSp[2], SetNumPieces, SetRetailPrice);
			wishList.addSet(write);
		}
		buffr.close();
		return wishList;
	}
}
