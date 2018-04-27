package tsubaiso_blackjack;

import java.util.Scanner;

public class Blackjack {

		private static boolean playing = true;
		private static boolean roundFinished = false;
		private static Card drawn = null;
		private static Scanner scanner = new Scanner(System.in);
		
	public static void main(String[] args){
		int command = 0;
		
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
			if(checkNaturalTwentyOne(playerHand, dealerHand)){
				roundFinished = true;
			}

			// Play round
			while(!roundFinished){
				Printing.printHandAndValue("Player", playerHand);
				Printing.printDealerFirstCard(dealerHand);
				command = getCommandFromUser(scanner);
				//Hit 
				if(command == 1){
					roundFinished = commandPlayerHit(playerHand, playingDeck);
				//Stand
				} else if (command == 2) {
					break;
				//Leave table
				} else if(command == 3){
					playing = false;
					break;
				}
			}

			System.out.println("Dealer's hand: " + dealerHand.toString() + "- " + dealerHand.getValueOfCards());
			if(roundFinished == false){
				if(dealerHand.getValueOfCards() > playerHand.getValueOfCards() && roundFinished == false){
					System.out.println("Dealer wins with: " + dealerHand.getValueOfCards() + ".");
				}

				//Dealer must draw until the reach 17.
				while(dealerHand.getValueOfCards() < 17 && roundFinished == false){
					drawn = playingDeck.drawCard();
					System.out.println("Dealer draws: " + drawn.toString());
					dealerHand.addCard(drawn);
				}
				
				int playerValue = playerHand.getValueOfCards();
				int dealerValue = dealerHand.getValueOfCards();
				
				System.out.println("Dealer's hand is valued at: " + dealerValue);
				if(dealerValue > 21 && roundFinished == false){
					Printing.printDealerBustMessage();
					roundFinished = true;
				}
				
				if(playerValue > dealerValue && roundFinished == false){
					Printing.printPlayerWinMessage();
					roundFinished = true;
				} else if (playerValue < dealerValue && roundFinished == false){
					Printing.printDealerWinMessage();
					roundFinished = true;
				} else {
					Printing.printDealerBustMessage();
					roundFinished = true;
				}
	
			}
						
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

	private static boolean commandPlayerHit(Hand playerHand, Deck playingDeck) {
		boolean roundFinished = false;
		
		Card drawn = playingDeck.drawCard();
		System.out.println("Player draws: " + drawn.toString());
		playerHand.addCard(drawn);
		
		//Player loses if they are over 21
		if(playerHand.getValueOfCards() > 21){
			System.out.println("Bust. Player is over 21.");
			roundFinished = true;
		}
		
		return roundFinished;
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

	private static boolean checkNaturalTwentyOne(Hand playerHand, Hand dealerHand) {
		/*
		 * 	If dealer's card is Ace or Ten, dealer checks for Blackjack. 
		 *  If the dealer has a Blackjack they reveal it.
		 *  If the player has a Blackjack, the round is a push.
		 *  Otherwise, the player loses.
		 */
		boolean result = false;
		int pVal = playerHand.getValueOfCards();
		int dVal = dealerHand.getValueOfCards();
		
		//If dealer has a face-up Ace or Ten, they will check their other card for Blackjack.
		if((dealerHand.getTopCard().getValue() == 11) || (dealerHand.getTopCard().getValue() == 10)){
			
			//If both hands are 21, push
			if(dVal == 21 && pVal == 21){
				Printing.printHandAndValue("Player", playerHand);
				//Printing.printHandAndValue("Dealer", dealerHand);
				Printing.printPushMessage();	
				result = true;
		
			//Dealer has natural 21, player loses
			} else if (dVal == 21 && pVal != 21){
				//Printing.printDealerHandAndValue(dealerHand);
				result = true;

			//Player has natural 21, player wins
			} else if (dVal != 21 && pVal == 21){
				Printing.printHandAndValue("Player", playerHand);
				result = true;
			}
			
		//If dealer could not show, check player's hand
		} else if (pVal == 21){
			Printing.printHandAndValue("Player", playerHand);
			System.out.println("You win with a natural 21.");
			result = true;
		}
		
		return result;
	}
}
