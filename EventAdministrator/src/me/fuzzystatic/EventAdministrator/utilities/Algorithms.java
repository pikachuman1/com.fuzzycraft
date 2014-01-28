package me.fuzzystatic.EventAdministrator.utilities;

public class Algorithms {

	public int exponential(int initialAmount, int amountPerPlayer, double playerAmount) {
		return (int) (amountPerPlayer * Math.pow(playerAmount, 0.9) + initialAmount);	
	}
}
