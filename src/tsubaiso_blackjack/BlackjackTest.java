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
		testDealerBustAfterDrawing();
	}
	
	/**
	 *  Test that logic captures a player natural 21.
	 */
	void testPlayerNatural21() {
		printLine();
		Hand player = new Hand();
		Hand dealer = new Hand();
		
		player.addCard(new Card(Suit.CLUBS, Value.ACE));
		player.addCard(new Card(Suit.CLUBS, Value.QUEEN));
		
		dealer.addCard(new Card(Suit.CLUBS, Value.TWO));
		dealer.addCard(new Card(Suit.CLUBS, Value.THREE));
		
		assertEquals(Blackjack.checkNaturalTwentyOne(player, dealer), 1);
	}
	
	/**
	 *  Test that logic captures a dealer natural 21.
	 */
	void testDealerNatural21() {
		printLine();
		Hand player = new Hand();
		Hand dealer = new Hand();
		
		dealer.addCard(new Card(Suit.CLUBS, Value.ACE));
		dealer.addCard(new Card(Suit.CLUBS, Value.QUEEN));
		
		player.addCard(new Card(Suit.CLUBS, Value.TWO));
		player.addCard(new Card(Suit.CLUBS, Value.THREE));
		
		assertEquals(Blackjack.checkNaturalTwentyOne(player, dealer), 2);
	}
	
	/**
	 *  Test that logic captures a push of natural 21.
	 */
	void testPushNatural21() {
		printLine();
		Hand player = new Hand();
		Hand dealer = new Hand();
		
		dealer.addCard(new Card(Suit.CLUBS, Value.ACE));
		dealer.addCard(new Card(Suit.CLUBS, Value.QUEEN));
		
		player.addCard(new Card(Suit.CLUBS, Value.ACE));
		player.addCard(new Card(Suit.CLUBS, Value.QUEEN));
		
		assertEquals(Blackjack.checkNaturalTwentyOne(player, dealer), 3);
	}
	
	/**
	 *  Test that logic captures a push of natural 21.
	 */
	void testPlayerStandsWithLosingHand() {
		printLine();
		Hand player = new Hand();
		Hand dealer = new Hand();
		Deck deck = new Deck();
		
		dealer.addCard(new Card(Suit.CLUBS, Value.ACE));
		dealer.addCard(new Card(Suit.CLUBS, Value.QUEEN));
		
		player.addCard(new Card(Suit.CLUBS, Value.THREE));
		player.addCard(new Card(Suit.CLUBS, Value.TWO));
		
		assertEquals(Blackjack.checkWinner(player, dealer, deck), 1);
	}
	
	/**
	 *  Test that logic captures a push of natural 21.
	 */
	void testDealerBustAfterDrawing() {
		printLine();
		Hand player = new Hand();
		Hand dealer = new Hand();
		Deck deck = new Deck();
		
		player.addCard(new Card(Suit.CLUBS, Value.THREE));
		player.addCard(new Card(Suit.CLUBS, Value.TWO));
		
		dealer.addCard(new Card(Suit.CLUBS, Value.TWO));
		dealer.addCard(new Card(Suit.CLUBS, Value.QUEEN));
		dealer.addCard(new Card(Suit.CLUBS, Value.QUEEN));
		
		assertEquals(Blackjack.checkWinner(player, dealer, deck), 2);
	}
	
	void printLine() {
		System.out.println("==============");
	}
}

