package binPacking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class assumes that no weights will be largest than the capacity. This
 * class adds the weights given to bins based on their size relative to the
 * currently existing bins and their remaining/max capacities
 **/
public class PackingStrategy {
	public static List<Bin> packBestFit(List<Integer> weights, int capacity) {
		Collections.sort(weights);
		Collections.reverse(weights);
		List<Bin> binList = new ArrayList<Bin>();
		/**
		 * Checks all bins, and finds the bin where the space remaining is closest to
		 * the given weight, then adds it to the bin.
		 **/
		for (int i = 0; i < weights.size(); i++) {
			int w = weights.get(i);
			boolean packed = false;
			int bestBin = (binList.size())-1;
			int bestBinSpace = capacity;
			boolean perfectFit = false;
			for (int b = 0; b < binList.size(); b++) {
				// If the current bin fits the current weight perfectly, then it is added
				// immediately.
				if ((binList.get(b).getSpace()) == w) {
					binList.get(b).store(w);
					perfectFit = true;
					packed = true;
					break;
				}
				// checks the current bin against the current bestBin
				if ((binList.get(b).getSpace()) >= w) {
					if (binList.get(b).getSpace() < bestBinSpace) {
						bestBin = b;
						bestBinSpace = binList.get(b).getSpace();
						packed = true;
					}
				}
			}
			// If there are no bin which can fit the current weight, a new empty bin is
			// created and the weight is added
			if (packed == false && perfectFit == false) {
				Bin x = new Bin(capacity);
				x.store(w);
				binList.add(x);
			}
			// If there was no perfect fit, then the weight is added to the next best bin
			if (perfectFit == false && packed == true) {
				try {
					binList.get(bestBin).store(w);
				} catch (IllegalArgumentException e1) {
				}
			}
		}
		return binList;

	}

	public static List<Bin> packBestFitParallel(List<Integer> weights, int capacity, int numThreads) {
		int cur0 = 0;
		List<BinPackingProblem> threadList = new ArrayList<BinPackingProblem>();
		List<Thread> threads = new ArrayList<Thread>();
		int threadSize = (weights.size() / numThreads);
		
		for (int i = 0; i <numThreads; i++) {
			threadList.add(new BinPackingProblem(weights.subList(cur0, cur0 + threadSize), capacity));
			cur0 = cur0 + threadSize;
		}
		
		for (int x = 0; x < numThreads; x++) {
			threads.add(new Thread(threadList.get(x)));
			threads.get(x).start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e1) {
			}
		}
		
		List<Bin> curBins = new ArrayList<Bin>();
		for (int a = 0; a < numThreads; a++) {
			curBins = threadList.get(a).getBins();
			
		}
		List<Bin> finalBins = new ArrayList<Bin>();
		finalBins.addAll(curBins);
		return finalBins;
	}
}