package game;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ComputerPlayer extends GamePlayer {
	public ComputerPlayer(String Name) {
		super(Name);
	}

	@Override
	public Symbol chooseSymbol() {
		// Each of these variables represents the number of times that each form was played by the human player 
		Long rockCount = humanList.stream().filter(p -> p.equals(Symbol.ROCK)).count();
		Long paperCount = humanList.stream().filter(p -> p.equals(Symbol.PAPER)).count();
		Long scissorsCount = humanList.stream().filter(p -> p.equals(Symbol.SCISSORS)).count();
		Long lizardCount = humanList.stream().filter(p -> p.equals(Symbol.LIZARD)).count();
		Long spockCount = humanList.stream().filter(p -> p.equals(Symbol.SPOCK)).count();
		
		// A list containing all of the possible forms is created each game and shuffled
		List<Symbol> allVars = Arrays.asList(Symbol.ROCK, Symbol.PAPER, Symbol.SCISSORS, Symbol.LIZARD, Symbol.SPOCK);
		Collections.shuffle(allVars);
		ArrayList<Symbol> topOccur = new ArrayList<Symbol>();
		// if no form has been played more than once, then the CPU plays a random form
		if ((rockCount < 2 && paperCount < 2 && scissorsCount < 2 && lizardCount < 2 && spockCount < 2) || humanList.size() == 0) {
			return allVars.get(1);
		} else { 	// otherwise the CPU selects a counter from the list of forms the opponent has played more than once
			if (rockCount > 1) {
				topOccur.add(Symbol.ROCK);
			}
			if (paperCount > 1) {
				topOccur.add(Symbol.PAPER);
			}
			if (scissorsCount > 1) {
				topOccur.add(Symbol.SCISSORS);
			}
			if (lizardCount > 1) {
				topOccur.add(Symbol.LIZARD);
			}
			if (spockCount > 1) {
				topOccur.add(Symbol.SPOCK);
			}
			Collections.shuffle(topOccur);
			Symbol choice = topOccur.get(0);
			Symbol finalVal = counter(choice);
			return finalVal;
		}
	}
	
	private Symbol counter(Symbol choice) {
		
		// the counter forms for each given form are detailed below
		List<Symbol> rockCounter = Arrays.asList(Symbol.PAPER , Symbol.SPOCK);
		List<Symbol> paperCounter = Arrays.asList(Symbol.SCISSORS , Symbol.LIZARD);
		List<Symbol> scissorCounter = Arrays.asList(Symbol.ROCK , Symbol.SPOCK);
		List<Symbol> lizardCounter = Arrays.asList(Symbol.SCISSORS , Symbol.ROCK);
		List<Symbol> spockCounter = Arrays.asList(Symbol.PAPER , Symbol.LIZARD);
		
		// based on the form the CPU decides to counter, it randomly chooses one of the two counter forms and returnsit
		Random random = new Random();
		boolean randBool = random.nextBoolean();
		switch(choice) {
		case ROCK:
			if (randBool) {
				return rockCounter.get(0);
			} else {
				return rockCounter.get(1);
			}
		case PAPER:
			if (randBool) {
				return paperCounter.get(0);
			} else {
				return paperCounter.get(1);
			}
		case SCISSORS:
			if (randBool) {
				return scissorCounter.get(0);
			} else {
				return scissorCounter.get(1);
			}
		case LIZARD:
			if (randBool) {
				return lizardCounter.get(0);
			} else {
				return lizardCounter.get(1);
			}
		case SPOCK:
			if (randBool) {
				return spockCounter.get(0);
			} else {
				return spockCounter.get(1);
			}
			// This case is never reached, so it doesn't matter what it hands back
		default:
			return Symbol.ROCK;
		}
	}
}
