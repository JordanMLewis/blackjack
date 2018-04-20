package tsubaiso_blackjack;
import java.util.ArrayList;
import java.util.Random;

public class Deck extends CardContainer{

	public Deck(){
		this.cards = new ArrayList<Card>();
		initializeDeck();
		shuffle();
	}
	
	/**
	 * 
	 */
	public void initializeDeck(){
		//Create all card combinations using enumerations
		for(Suit cardSuit : Suit.values()){
			for(Value cardValue : Value.values()){
				this.cards.add(new Card(cardSuit, cardValue));
			}
		}
	} //end createDeck

	/**
	 * 
	 */
	public void shuffle(){
		ArrayList<Card> tmpDeck = new ArrayList<Card>();
		Random rand = new Random();
		int randomCardIndex = 0;
		int originalDeckSize = this.cards.size();
		//Choose cards at random
		for(int i = 0; i < originalDeckSize; i++){
			randomCardIndex = rand.nextInt(this.cards.size());	//[0, size+1)
			tmpDeck.add(this.cards.get(randomCardIndex));
			this.cards.remove(randomCardIndex);
		}
		this.cards = tmpDeck;
	}//end shuffle

	public Card drawCard(){
		return this.removeTopCard();
	}
	
	/**
	 * 
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
