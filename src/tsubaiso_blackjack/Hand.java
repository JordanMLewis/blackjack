/*
 *  Author: Jordan M. Lewis
 *  
 *  This class represents the cards in the player's hand. 
 * 
 */
package tsubaiso_blackjack;

import java.util.ArrayList;

public class Hand extends CardContainer{
	
	/**
	 * Create a Hand, which holds Cards for playing.
	 * 
	 * @see CardHolder
	 */
	public Hand(){
		this.cards = new ArrayList<Card>();
	}
	
	/**
	 * Calculate the value of all cards in the container.
	 * The first Ace is counted as 11, subsequent Aces are counted as 1.
	 * 
	 * @return And int, the numeric value of all cards in the container
	 */
 	public int getValueOfCards(){
		int handValue = 0;
		int cardValue = 0;
		int numAces = 0;

		for(Card c : this.cards){
			cardValue = c.getValue();

			//Add cards normally, handle Aces afterwards
			if(cardValue != Value.ACE.getValue()){
				handValue += cardValue;
			} else {
				numAces++;
			}
		}//end for
		
		//Handle aces, don't go over 21
		for(int i = 0; i < numAces; i++){
			if(handValue <= 10){
				handValue += 11;
			} else {
				handValue += 1;
			}
		}//end for
		return handValue;
	}
	
 	/**
 	 * Wrapper class for readability. Removes all cards from player's hand.
 	 * 
 	 * @see CardHolder.removeAllCards
 	 * @return ArraList of Cards
 	 */
	public ArrayList<Card> foldHand(){
		return this.removeAllCards();
	}
}