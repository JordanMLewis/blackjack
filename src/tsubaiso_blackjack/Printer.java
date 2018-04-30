/*
 *  Author: Jordan M. Lewis
 *  
 *  This interface specifies all game play messages that must 
 *  be implemented if a new language is added.
 */
package tsubaiso_blackjack;

abstract interface Printer {
			
		// Welcome, thank you, etc
		public abstract void printDealingWithAnimation();
		public abstract void printWelcomeMessage();
		public abstract void printThankYouMessage();
		public abstract void printEndRound();

		//Commands
		public abstract void printCommandPrompt();
		
		// Print drawing card
		public abstract void printHandsAndValues(Hand playerHand, Hand dealerHand);
		public abstract void printHandAndValue(String playerName, Hand h);
		public abstract void printDealerFirstCard(Hand h);
		public abstract void printPlayerDraws(Card c);
		public abstract void printDealerDrawnAndValue(Card drawn, Hand dealerHand);
		
		//Bust
		public abstract void printDealerBustMessage();
		public abstract void printPlayerBustMessage();
		
		// Win/lose
		public abstract void printPlayerWinMessage();
		public abstract void printPlayerLoseMessage();		
		public abstract void printPushMessage();
		public abstract void printDealerWinsWithValue(Hand dealerHand);
		
		//Natural 21
		public abstract void printPlayerNatural21();
		public abstract void printDealerNatural21();
		
		//Extra
		public abstract void pause(int n);
}
