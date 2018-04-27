/*
 *  Author: Jordan M. Lewis
 *  
 *  This class represents a playing card.
 * 
 */
package tsubaiso_blackjack;

public class Card {
	
	private Suit suit;
	private Value value;
	
	public Card(Suit s, Value v){
		this.suit = s;
		this.value = v;
	}
	
	public String toString(){
		return "<" + this.value.toString() + " OF " +
			   this.suit.toString() + ">";
	}
	
	public int getValue(){
		return this.value.getValue();
	}
	
	public Suit getSuit(){
		return this.suit;
	}	
}
