/*
 *  Author: Jordan M. Lewis
 *  
 *  This class represents a container for cards and provides 
 *  various methods for modifying a group of cards.
 * 
 */
package tsubaiso_blackjack;

import java.util.ArrayList;

abstract class CardContainer {

	protected ArrayList<Card> cards;
	
	/**
	 * Add a Card at the end of the container.
	 * 
	 * @param c the Card to be added
	 * @return void 
	 */
	public void addCard(Card c){
		this.cards.add(c);
	}
	
	/**
	 * Add multiple Cards to the end of the container.
	 * 
	 * @param cards ArrayList of Cards to be added.
	 */
	public void addCards(ArrayList<Card> cards){
		for(Card c : cards)
			this.addCard(c);
	}
	
	/**
	 * Peek at the first card in the container
	 * 
	 * @return Card
	 */
	public Card getTopCard(){
		Card topCard = null;
		if(this.cards.size() > 0){
			topCard = this.cards.get(0);
		}
		return topCard;
	}
	
	/**
	 * Remove and return the first card in the container.
	 * 
	 * @return Card
	 */
	public Card removeTopCard(){
		Card removedCard = null;
		if(this.cards.size() > 0){
			removedCard = this.cards.remove(0);
		}
		return removedCard;
	}
	
	/**
	 * Remove and return all Cards in the container.
	 * 
	 * @return ArrayList of Cards
	 */
	public ArrayList<Card> removeAllCards(){
		ArrayList<Card> tmp = this.cards;
		this.cards.clear();
		return tmp;
	}
	
	/**
	 * Get the number of cards in the container. 
	 * 
	 * @return int, number of cards in the container.
	 */
	public int size(){
		return this.cards.size();
	}
	
	/**
	 * Print name of each card in the container on one line.
	 * 
	 * @return String of Cards in the container
	 */
	public String toString(){
		String str = "";
		for(Card c : this.cards)
			str += c.toString() + " ";
		return str;
	}
}
