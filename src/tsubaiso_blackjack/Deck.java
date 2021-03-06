/*
 *  Author: Jordan M. Lewis
 *  
 *  This class is represented as a deck of cards, and is used to provide cards to players.
 * 
 */
package tsubaiso_blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck extends CardContainer{

	Random randomShuffle = new Random();
	
	public Deck(){
		this.cards = new ArrayList<Card>();
		this.initializeDeck();
		this.shuffle();
	}
	
	/**
	 *  Creates a full deck of cards with values 2 to Ace for each suit.
	 */
	private void initializeDeck(){
		Suit[] suits = Suit.values();
		Value[] values = Value.values();

		//Create all card combinations using the enumerations
		for(Suit cardSuit : suits){
			for(Value cardValue : values){
				this.cards.add(new Card(cardSuit, cardValue));
			}
		}
	}

	/**
	 * Shuffle the deck of cards in place using random seed.
	 */
	public void shuffle(){
		//Collections shuffle uses random object for seed
		if(this.cards != null && this.cards.size() > 0){
			Collections.shuffle(this.cards, this.randomShuffle);
		}
	}

	/**
	 * Removes top card from the deck.
	 * 
	 * @return Card - the top card of the deck.
	 */
	public Card drawCard(){
		return this.removeTopCard();
	}
	
	/**
	 * Draws N cards from the deck and return them to the user.
	 * 
	 * @param n - Cards to remove
	 * @return ArrayList<Card> - The cards that were removed from the container.
	 */
	public ArrayList<Card> drawNCards(int n){
		ArrayList<Card> cardsDrawn = new ArrayList<Card>();
		Card c = null;
		
		//Draw as many cards before running out
		for(int i = 0; i < n; i++){
			c = this.removeTopCard();
			if(c != null){
				cardsDrawn.add(c);
			}
		}
		return cardsDrawn;
	}
}
