package binPacking;

import java.util.List;

public class BinPackingProblem implements Runnable{
	
	//define the private variables for this class
	private List<Integer> weights;
	private int capacity;
	private List<Bin> result;
	
	public BinPackingProblem(List<Integer> weights,int capacity) {
		this.weights = weights;
		this.capacity = capacity;
	}
	@Override
	public void run() {
		result = PackingStrategy.packBestFit(this.weights, this.capacity);
		
	}
	public List<Bin> getBins(){
		return result;
	}
}
