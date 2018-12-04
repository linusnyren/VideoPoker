package videopoker;

import java.util.List;

public class VideoPoker {
	
	/*	This class will contain the games intelligence.
	 * 	This will be epic
	 * 
	 */
	
	
	
//	public List<Card> sortCards (List<Card> handToSort) {
//		
//	}
	
	public int calculateValueOfCard(Card card) {
		
		int value = 2;
		
		for (String rank : Deck.getRanks()) {
			if (card.getRank().equals(rank)) {
				return value;
			}
			value++;
		}
		
		return -1;
	}

}
