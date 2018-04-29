/*
 *  Author: Jordan M. Lewis
 *  
 *  This class is used to organize various print statements used during gameplay.
 * 
 */
package tsubaiso_blackjack;

import java.io.PrintStream;

public class EnglishPrinter implements Printer{

	//Output of print statements
	public PrintStream out = System.out;
	
	public void printDealerHandAndValue(Hand h) {
		out.print("Dealer hand: ");
		out.print(h.toString() + "- ");
		out.println(h.getValueOfCards());
	}

	public void printHandsAndValues(Hand playerHand, Hand dealerHand){
		printHandAndValue("Player", playerHand);
		printHandAndValue("Dealer", dealerHand);
	}
	
	public void printPushMessage() {
		out.println("Push. No winners.");
	}
	
	public void printPlayerWinMessage(){
		out.println("You win!");
	}
	
	public void printPlayerLoseMessage(){
		out.println("You lose.");
	}
	
	public void printWelcomeMessage() {
		out.print("Welcome to Blackjack!\n");
		out.print("\n");
	}

	public void printCommandPrompt() {
		out.println("(1)Hit, (2)Stand, (3)Leave table?");
	}

	public void printThankYouMessage() {
		out.println("Game over. Come back again soon!");
	}

	public void printDealingWithAnimation(){
		out.print("Dealing");
		try{
			Thread.sleep(1000);
			out.print(".");
			Thread.sleep(1000);
			out.print(".");
			Thread.sleep(1000);
			out.print(".\n");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			//Do nothing
		}
	}

	public void pause(){
		try{
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			//Do nothing
		}
	}
	
	public void printDealerBustMessage(){
		out.println("Dealer busts! You win.");
	}
	
	public void printPlayerBustMessage(){
		out.println("Player busts! You lose.");
	}
	
	public void printHandAndValue(String playerName, Hand h){
		out.print(playerName + "'s hand: ");
		out.print(h.toString() + "- ");
		out.println(h.getValueOfCards());
	}

	public void printDealerFirstCard(Hand h) {
		out.println("Dealer's hand: " + h.getTopCard().toString() + " and one hidden card.");
	}

	public void printEndRound() {
		out.println("End of round.\n==========================================\n");
	}

	public void printDealerWinsWithValue(Hand dealerHand) {
		out.println("Dealer wins with: " + dealerHand.getValueOfCards() + ".");
	}
	
	public void printPlayerDraws(Card c){
		System.out.println("Player draws: " + c.toString());
	}
	
	public void printDealerDrawnAndValue(Card drawn, Hand dealerHand){
		System.out.println("Dealer draws: " + drawn.toString() + " - " + dealerHand.getValueOfCards());
	}
	
	public void printPlayerNatural21(){
		System.out.println("You win with a natural 21.");
	}
	
	public void printDealerNatural21(){
		System.out.println("Dealer wins with a natural 21.");
	}
}
