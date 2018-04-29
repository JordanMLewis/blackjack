package tsubaiso_blackjack;

import java.io.PrintStream;

abstract interface Printer {
		
		//Output of print statements
		public static PrintStream out = System.out;
		
		// Welcome, thank you
		public abstract void printDealingWithAnimation();
		public abstract void printWelcomeMessage();
		public abstract void printThankYouMessage();
		public abstract void printEndRound();

		//Commands
		public abstract void printCommandPrompt();
		
		// Print drawing card
		public abstract void printHandsAndValues(Hand playerHand, Hand dealerHand);
		public abstract void printHandAndValue(String playerName, Hand h);
		public abstract void printDealerHandAndValue(Hand h);
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
		public abstract void pause();
}
