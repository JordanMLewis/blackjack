/*
 *  Author: Jordan M. Lewis
 *  
 *  This class contains the main game play logic for Blackjack.
 * 
 */
package tsubaiso_blackjack;

import java.util.Scanner;

public class Blackjack {

		private static Scanner scanner = new Scanner(System.in);
		private static boolean playing = true;
		private static boolean roundFinished = false;
		private static Card drawn = null;
		private static int command = 0;
		private static Printer Printing = new EnglishPrinter();
		
	public static void main(String[] args){
		Printing.printWelcomeMessage();
		
		//Create deck of cards and player/dealer hands to hold them.
		Deck playingDeck = new Deck();
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();

		gameLoop(playerHand, dealerHand, playingDeck);
		
		scanner.close();
		Printing.printThankYouMessage();
	}

	/*
	 *  Deals cards, plays the game, checks winner, and continues.
	 */
	private static void gameLoop(Hand playerHand, Hand dealerHand, Deck playingDeck) {
		//Main game loop
		while(playing){
			
			Printing.printDealingWithAnimation();
			
			//Give two cards to player and dealer
			playerHand.addCards(playingDeck.drawNCards(2));
			dealerHand.addCards(playingDeck.drawNCards(2));
			
			//If dealer or player has 21, end round
			if(checkNaturalTwentyOne(playerHand, dealerHand) > -1){
				roundFinished = true;
			}
			
			playRound(playerHand, dealerHand, playingDeck);
			checkWinner(playerHand, dealerHand, playingDeck, roundFinished);
						
			//Fold hands and put cards into used Deck
			playingDeck.addCards(playerHand.foldHand());
			playingDeck.addCards(dealerHand.foldHand());
			playingDeck.shuffle();
			Printing.printEndRound();
			
			//Start new round
			roundFinished = false;
			
		}//End game loop
		return;
	}

	/*
	 *  Ask user for commands until stand or bust.
	 */
	public static void playRound(Hand playerHand, Hand dealerHand, Deck playingDeck) {
		
		// Play round
		while(!roundFinished){
			Printing.printHandAndValue("Player", playerHand);
			Printing.printDealerFirstCard(dealerHand);
			
			command = getCommandFromUser(scanner);

			//Hit 
			if(command == 1){
				commandPlayerHit(playerHand, playingDeck);
			//Stand
			} else if (command == 2) {
				break;
			//Leave table
			} else if(command == 3){
				playing = false;
				break;
			}
		}
	}

	/*
	 *  Add a card to the player's hand.
	 */
	private static void commandPlayerHit(Hand playerHand, Deck playingDeck) {
		
		//Draw a card and print value
		Card drawn = playingDeck.drawCard();
		playerHand.addCard(drawn);
		Printing.printPlayerDraws(drawn);
		
		//Player loses if they are over 21
		if(playerHand.getValueOfCards() > 21){
			Printing.printPlayerBustMessage();
			roundFinished = true;
		}
	}

	/*
	 *  Ask user until command is entered.
	 */
	private static int getCommandFromUser(Scanner scanner) {
		int command = 0;
		while(true){
			try{
				Printing.printCommandPrompt();
				command = Integer.parseInt(scanner.nextLine());
				break;
			} catch (NumberFormatException e){
				continue;
			}
		}
		return command;
	}

	/*
	 * 	If dealer's card is Ace or Ten, dealer checks for Blackjack. 
	 *  If the dealer has a Blackjack they reveal it.
	 *  If the player has a Blackjack, the round is a push.
	 *  Otherwise, the player loses.
	 */
	public static int checkNaturalTwentyOne(Hand playerHand, Hand dealerHand) {
		int res = -1;
		int pVal = playerHand.getValueOfCards();
		int dVal = dealerHand.getValueOfCards();
		
		//If dealer has a face-up Ace or Ten, they will check their other card for Blackjack.
		if((dealerHand.getTopCard().getValue() == 11) || (dealerHand.getTopCard().getValue() == 10)){
			
			//If both hands are 21, push
			if(dVal == 21 && pVal == 21){
				Printing.printHandsAndValues(playerHand, dealerHand);
				Printing.printPushMessage();	
				res = 3;
		
			//Dealer has natural 21, player loses
			} else if (dVal == 21 && pVal != 21){
				Printing.printHandsAndValues(playerHand, dealerHand);
				Printing.printDealerNatural21();
				res = 2;

			//Player has natural 21, player wins
			} else if (dVal != 21 && pVal == 21){
				Printing.printHandsAndValues(playerHand, dealerHand);
				Printing.printPlayerNatural21();
				res = 1;
			}
			
		//If dealer could not show, check player's hand
		} else if (pVal == 21){
			Printing.printHandsAndValues(playerHand, dealerHand);
			Printing.printPlayerNatural21();
			res = 1;
		}
	 
		return res;
	}

	/*
	 *  Check the winner of the round after player has chosen commands
	 */
	public static int checkWinner(Hand playerHand, Hand dealerHand, Deck playingDeck, boolean roundOver) {
		
		int res = -1;
		boolean finished = roundOver;

		if(!finished){
			Printing.printDealerHandAndValue(dealerHand);
		}
		
		// If player stands and dealer already has higher value, dealer wins.
		if(dealerHand.getValueOfCards() > playerHand.getValueOfCards() && finished == false){
			Printing.printDealerWinsWithValue(dealerHand);
			finished = true;
			res = 1; //player loses
		}

		// Dealer must draw until at least 17.
		while(dealerHand.getValueOfCards() < 17 && finished == false){
			drawn = playingDeck.drawCard();
			dealerHand.addCard(drawn);
			Printing.printDealerDrawnAndValue(drawn, dealerHand);
		}
		
		// No more cards to add, simplify code
		int playerValue = playerHand.getValueOfCards();
		int dealerValue = dealerHand.getValueOfCards();
	
		//If dealer is over 21, dealer loses
		if(dealerValue > 21 && finished == false){
			Printing.printDealerBustMessage();
			finished = true;
			res = 2; //dealer busts
		}
	
		// After all drawing
		if(finished == false) {
			
			// Player wins
			if(playerValue > dealerValue){
				Printing.printPlayerWinMessage();

			// Dealer wins
			} else if (playerValue < dealerValue){
				Printing.printDealerHandAndValue(dealerHand);
				Printing.printPlayerLoseMessage();

			// Equal value, no winners
			} else {
				Printing.printPushMessage();
			}
			
			finished = true;
		}
		return res;
	}
}
