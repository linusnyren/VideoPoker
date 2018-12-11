package videopoker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
		private int credits;
		private int lastBet;
		private String userName;
		private List<Card> hand = new ArrayList<>();
		
		
//		Constructor
		public Player(int credits, String userName) {
			this.credits = credits;
			this.userName = userName;
		}
		
//		Resets the lastbet to 0. 
		public void resetLastBet() {
			lastBet = 0;
		}
		
//		Getters and setters. 
		public int getCredits() {
			return credits;
		}
		public void setCredits(int credits) {
			this.credits = credits;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getNameCredits() {
			return "Player " +userName +" got " +credits +" Smackischars";
		}
		
		public List<Card> getHand () {
			return hand;
		}
		
		public int getLastbet() {
			return lastBet;
		}

		public void setLastBet (int lastBet) {
			this.lastBet = lastBet;
		}
 		
		
//		Adds a card last in the player hand. 
		public void addCardToHand (Card card) {
			hand.add(card);
		}
		
//		Adds a card and inserts it on the given index. 
		public void addCardToHand (int position, Card card) {
			hand.add(position, card);
		}
		
//		Removes the card on the given index. 
		public void removeCardFromHand (int index) {
			hand.remove(index);
		}
		
//		Clears the players hand of cards.
		public void clearHand() {
			hand.clear();
		}
	}