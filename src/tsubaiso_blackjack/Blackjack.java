package tsubaiso_blackjack;

import java.util.Scanner;

public class Blackjack {

		private static Scanner scanner = new Scanner(System.in);
		private static boolean playing = true;
		private static boolean roundFinished = false;
		private static Card drawn = null;
		private static int command = 0;
		
	public static void main(String[] args){
		Printing.printWelcomeMessage();
		
		//Create deck of cards and player/dealer hands to hold them.
		Deck playingDeck = new Deck();
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();

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

			checkWinner(playerHand, dealerHand, playingDeck);
						
			//Fold hands and put cards into used Deck
			playingDeck.addCards(playerHand.foldHand());
			playingDeck.addCards(dealerHand.foldHand());
			playingDeck.shuffle();
			Printing.printEndRound();
			
			//Start new round
			roundFinished = false;
			
		}//End game loop
		scanner.close();
		Printing.printThankYouMessage();
	}

	private static void commandPlayerHit(Hand playerHand, Deck playingDeck) {
		
		Card drawn = playingDeck.drawCard();
		playerHand.addCard(drawn);
		Printing.printPlayerDraws(drawn);
		
		//Player loses if they are over 21
		if(playerHand.getValueOfCards() > 21){
			Printing.printPlayerBustMessage();
			roundFinished = true;
		}
	}

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

	public static int checkNaturalTwentyOne(Hand playerHand, Hand dealerHand) {
		/*
		 * 	If dealer's card is Ace or Ten, dealer checks for Blackjack. 
		 *  If the dealer has a Blackjack they reveal it.
		 *  If the player has a Blackjack, the round is a push.
		 *  Otherwise, the player loses.
		 */
		int res = -1;
		int pVal = playerHand.getValueOfCards();
		int dVal = dealerHand.getValueOfCards();
		
		//If dealer has a face-up Ace or Ten, they will check their other card for Blackjack.
		if((dealerHand.getTopCard().getValue() == 11) || (dealerHand.getTopCard().getValue() == 10)){
			
			//If both hands are 21, push
			if(dVal == 21 && pVal == 21){
				Printing.printHandAndValue("Player", playerHand);
				Printing.printPushMessage();	
				res = 3;
		
			//Dealer has natural 21, player loses
			} else if (dVal == 21 && pVal != 21){
				Printing.printDealerNatural21();
				res = 2;

			//Player has natural 21, player wins
			} else if (dVal != 21 && pVal == 21){
				Printing.printHandAndValue("Player", playerHand);
				Printing.printPlayerNatural21();
				res = 1;
			}
			
		//If dealer could not show, check player's hand
		} else if (pVal == 21){
			Printing.printHandAndValue("Player", playerHand);
			Printing.printPlayerNatural21();
			res = 1;
		}
		
		return res;
	}

	public static int checkWinner(Hand playerHand, Hand dealerHand, Deck playingDeck) {
		
		int res = -1;
		
		// If player stands and dealer already has higher value, dealer wins.
		Printing.printDealerHandAndValue(dealerHand);
		if(dealerHand.getValueOfCards() > playerHand.getValueOfCards() && 
	       dealerHand.getValueOfCards() < 22 && roundFinished == false){
			Printing.printDealerWinsWithValue(dealerHand);
			roundFinished = true;
			res = 1; //player loses
		}

		// Dealer must draw until the reach 17.
		while(dealerHand.getValueOfCards() < 17 && roundFinished == false){
			drawn = playingDeck.drawCard();
			dealerHand.addCard(drawn);
			Printing.printDealerDrawnAndValue(drawn, dealerHand);
		}
		
		int playerValue = playerHand.getValueOfCards();
		int dealerValue = dealerHand.getValueOfCards();
	
		//If dealer is over 21, dealer loses
		if(dealerValue > 21 && roundFinished == false){
			Printing.printDealerBustMessage();
			roundFinished = true;
			res = 2; //dealer busts
		}
	
		// After all drawing
		if(roundFinished == false) {
			
			// Player wins
			if(playerValue > dealerValue){
				Printing.printPlayerWinMessage();

			// Dealer wins
			} else if (playerValue < dealerValue){
				Printing.printDealerHandAndValue(dealerHand);
				Printing.printPlayerLoseMessage();
			} else {
				Printing.printPushMessage();
			}
			roundFinished = true;
		}
		return res;
	}
}
