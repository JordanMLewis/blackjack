package tsubaiso_blackjack;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CardHolderTest {

	@Test
	public void test() {
		sizeTest();
		removeCardTest();
	}
	
	void sizeTest(){
		
		Hand hand = new Hand();
		
		hand.addCard(new Card(Suit.CLUBS, Value.ACE));
		hand.addCard(new Card(Suit.CLUBS, Value.TWO));
		hand.addCard(new Card(Suit.CLUBS, Value.THREE));
		
		assertEquals(3, hand.size());
		
		hand.removeTopCard();
		assertEquals(2, hand.size());
		
		hand.removeTopCard();
		assertEquals(1, hand.size());
		
		hand.removeTopCard();
		assertEquals(0, hand.size());
	}
	
	void removeCardTest(){
		Hand hand = new Hand();

		//removing with no cards should be null
		assertEquals(null, hand.removeTopCard());
	
		//removing all should be empty arraylist
		assertEquals(new ArrayList<Card>(), hand.removeAllCards());
		
		//peeking with no cards is null
		assertEquals(null, hand.getTopCard());
	}
}
