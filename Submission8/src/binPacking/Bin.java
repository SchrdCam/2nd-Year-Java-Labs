package binPacking;

import java.util.ArrayList;
import java.util.List;

public class Bin {
	// Integer capacity of the bin
	private int capacity;
	// Integer list of weights in the bin
	private List<Integer> binWeights =  new ArrayList<Integer>();

	public Bin(int capacity) {
		this.capacity = capacity;
	}
	
	// Adds the given weight to the bin, throwing the IllegalArgumentException if the weight cannot fit into the bin
	public void store(int weight) throws IllegalArgumentException{
		if (weight > this.getSpace()) {
			throw new IllegalArgumentException();
		} else {
			binWeights.add(weight);
		}
	}
	
	// return the amount of space in the bin (capacity - current held weight)
	public int getSpace() {
		int totalWeight = binWeights.stream().mapToInt(i -> i.intValue()).sum(); // sums the values of the bin's current weights 
		return capacity - totalWeight; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((binWeights == null) ? 0 : binWeights.hashCode());
		result = prime * result + capacity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bin other = (Bin) obj;
		if (binWeights == null) {
			if (other.binWeights != null)
				return false;
		} else if (!binWeights.equals(other.binWeights))
			return false;
		if (capacity != other.capacity)
			return false;
		return true;
	}	
}