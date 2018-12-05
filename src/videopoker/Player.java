package videopoker;

import java.util.ArrayList;
import java.util.List;

public class Player {
		private int credits;
		private int lastBet;
		private String userName;
		private List<Card> hand = new ArrayList<>();
		
		
		public Player(int credits, String userName) {
			this.credits = credits;
			this.userName = userName;
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
		
		public void addCardToHand (Card card) {
			hand.add(card);
		}
		
		public void removeCardFromHand (Card card) {
			hand.remove(card);
		}
		
		
		

	}
