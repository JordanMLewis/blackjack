package tsubaiso_blackjack;

import java.io.PrintStream;

public class Printing {

	//Output of print statements
	public static PrintStream out = System.out;
	
	public static void printDealerHandAndValue(Hand h) {
		out.print("Dealer hand: ");
		out.print(h.toString() + "- ");
		out.println(h.getValueOfCards());
	}

	public static void printPushMessage() {
		out.println("Push. No winners.");
	}
	
	public static void printPlayerWinMessage(){
		out.println("You win!");
	}
	
	public static void printDealerWinMessage(){
		out.println("You lose.");
	}
	
	public static void printWelcomeMessage() {
		out.print("Welcome to Blackjack!\n");
		out.print("\n");
	}

	public static void printCommandPrompt() {
		out.println("(1)Hit, (2)Stand, (3)Leave table?");
	}

	public static void printThankYouMessage() {
		out.println("Game over. Come back again soon!");
	}

	public static void printDealingWithAnimation(){
		out.print("Dealing");
		try{
			//Thread.sleep(1000);
			out.print(".");
			//Thread.sleep(1000);
			out.print(".");
			//Thread.sleep(1000);
			out.print(".\n");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			//Do nothing
		}
	}
	
	public static void printDealerBustMessage(){
		out.println("Dealer busts! You win.");
	}
	
	public static void printHandAndValue(String playerName, Hand h){
		out.print(playerName + "'s hand: ");
		out.print(h.toString() + "- ");
		out.println(h.getValueOfCards());
	}

	public static void printDealerFirstCard(Hand h) {
		out.println("Dealer's hand: " + h.getTopCard().toString() + " and one hidden card.");
	}

	public static void printEndRound() {
		System.out.println("End of round.\n==========================================\n");
	}
}
