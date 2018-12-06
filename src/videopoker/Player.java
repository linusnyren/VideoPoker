package videopoker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
		private int credits;
		private int lastBet;
		private String userName;
		private List<Card> hand = new ArrayList<>();
		
		
		public Player(int credits, String userName) {
			this.credits = credits;
			this.userName = userName;
			System.out.println("Player created");
		}
		
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
 		
		public void addCardToHand (Card card) {
			hand.add(card);
		}
		
		public void addCardToHand (int position, Card card) {
			hand.add(position, card);
		}
		
		public void removeCardFromHand (int index) {
			hand.remove(index);
		}
		
		public void clearHand() {
			hand.clear();
		}
		
		
		

	}
