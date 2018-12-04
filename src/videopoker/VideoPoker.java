package videopoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideoPoker {
	
	/*	This class will contain the games intelligence.
	 * 	This will be epic
	 * 
	 */
	
	
	
	public List<Card> sortCards (List<Card> handToSort) {
		
		List<Card> workThis = handToSort;
		
		for (int i = 0 ; i < handToSort.size(); i++ ) {
			for (int j = 0 ; j < handToSort.size() - 1; j++ ) {
				if (calculateValueOfCard(handToSort.get(j)) >  calculateValueOfCard(handToSort.get(j + 1)) ) {
					Card temp = handToSort.get(j);handToSort.remove(j);
					handToSort.add(j + 1, temp);
				}
			}
		}
		
		return handToSort;
	
	}
	
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
