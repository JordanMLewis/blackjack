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
		
		assertEquals(1, Blackjack.checkWinner(player, dealer, deck));
	}
	
	void printLine() {
		System.out.println("==============");
	}
}

