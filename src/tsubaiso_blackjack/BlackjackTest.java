package tsubaiso_blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class BlackjackTest {

	@Test
	public void test(){
		testPlayerNatural21();
		testDealerNatural21();
		testPushNatural21();
		testPlayerStandsWithLosingHand();
		testAceValues();
	}
	
	private void testAceValues() {
		Hand player = new Hand();
		
		assertEquals(0, player.getValueOfCards());
		
		player.addCard(new Card(Suit.CLUBS, Value.ACE));
		assertEquals(11, player.getValueOfCards());
		
		player.addCard(new Card(Suit.SPADES, Value.ACE));
		assertEquals(12, player.getValueOfCards());
		
		player.addCard(new Card(Suit.HEARTS, Value.ACE));
		assertEquals(13, player.getValueOfCards());
		
		player.addCard(new Card(Suit.DIAMONDS, Value.ACE));
		assertEquals(14, player.getValueOfCards());
		
		player.removeTopCard();
		assertEquals(13, player.getValueOfCards());
		
		player.removeTopCard();
		assertEquals(12, player.getValueOfCards());
		
		player.removeTopCard();
		assertEquals(11, player.getValueOfCards());
		
		player.removeTopCard();
		assertEquals(0, player.getValueOfCards());
	}
	

	/**
	 *  Test that logic captures a player natural 21.
	 */
	void testPlayerNatural21() {
		//printLine();
		Hand player = new Hand();
		Hand dealer = new Hand();
		
		player.addCard(new Card(Suit.CLUBS, Value.ACE));
		player.addCard(new Card(Suit.CLUBS, Value.QUEEN));
		
		dealer.addCard(new Card(Suit.CLUBS, Value.TWO));
		dealer.addCard(new Card(Suit.CLUBS, Value.THREE));
		
		assertEquals(1, Blackjack.checkNaturalTwentyOne(player, dealer));
	}
	
	/**
	 *  Test that logic captures a dealer natural 21.
	 */
	void testDealerNatural21() {
		//printLine();
		Hand player = new Hand();
		Hand dealer = new Hand();
		
		dealer.addCard(new Card(Suit.CLUBS, Value.ACE));
		dealer.addCard(new Card(Suit.CLUBS, Value.QUEEN));
		
		player.addCard(new Card(Suit.CLUBS, Value.TWO));
		player.addCard(new Card(Suit.CLUBS, Value.THREE));
		
		assertEquals(2, Blackjack.checkNaturalTwentyOne(player, dealer));
	}
	
	/**
	 *  Test that logic captures a push of natural 21.
	 */
	void testPushNatural21() {
		//printLine();
		Hand player = new Hand();
		Hand dealer = new Hand();
		
		dealer.addCard(new Card(Suit.CLUBS, Value.ACE));
		dealer.addCard(new Card(Suit.CLUBS, Value.QUEEN));
		
		player.addCard(new Card(Suit.CLUBS, Value.ACE));
		player.addCard(new Card(Suit.CLUBS, Value.QUEEN));
		
		assertEquals(3, Blackjack.checkNaturalTwentyOne(player, dealer));
	}
	
	/**
	 *  Test that logic captures a push of natural 21.
	 */
	void testPlayerStandsWithLosingHand() {
		//printLine();
		Hand player = new Hand();
		Hand dealer = new Hand();
		Deck deck = new Deck();
		
		dealer.addCard(new Card(Suit.CLUBS, Value.ACE));
		dealer.addCard(new Card(Suit.CLUBS, Value.QUEEN));
		
		player.addCard(new Card(Suit.CLUBS, Value.THREE));
		player.addCard(new Card(Suit.CLUBS, Value.TWO));
		
		assertEquals(1, Blackjack.checkWinner(player, dealer, deck, false));
	}
	
	void printLine() {
		System.out.println("==============");
	}
}

