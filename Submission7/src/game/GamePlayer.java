package game;

import java.util.ArrayList;

public abstract class GamePlayer {
	
	// definitions of the name for the players and the memory lists 
	private String name;
	public static ArrayList<Symbol> humanList = new ArrayList<Symbol>();
	public static ArrayList<Symbol> cpuList = new ArrayList<Symbol>();
	
	//get method for the player's name is called in the constructor
	public GamePlayer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}
	
	//get method for the player's name
	public void setName(String name) {
		this.name = name;
	}
	
	//current turn's plays are added to the history lists for the CPU to make further decisions
	public void addHistory(Symbol mySym, Symbol otherSym) {
			humanList.add(otherSym);
			cpuList.add(mySym);
	}
	
	//ensures that all subclasses of GamePlayer must have a chooseSymbol method
	public abstract Symbol chooseSymbol();

}