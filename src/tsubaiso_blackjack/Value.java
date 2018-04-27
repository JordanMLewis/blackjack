/*
 *  Author: Jordan M. Lewis
 * 
 *  This enum contains the definition of each playing card value.
 */
package tsubaiso_blackjack;

public enum Value {
	TWO(2), 
	THREE(3), 
	FOUR(4), 
	FIVE(5), 
	SIX(6), 
	SEVEN(7), 
	EIGHT(8), 
	NINE(9), 
	TEN(10), 
	JACK(10), 
	QUEEN(10), 
	KING(10), 
	ACE(11);

	private final int value;
	
	//Automatically assign each constant an int value
	Value(int i){
		this.value = i;
	}
	
	public int getValue(){
		return this.value;
	}
}
