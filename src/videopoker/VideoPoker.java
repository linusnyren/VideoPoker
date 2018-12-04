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
	public boolean CheckForEquals(int par, List<Card> test) {
		
		if ( par == 3) {
			if (calculateValueOfCard(test.get(0)) == calculateValueOfCard(test.get(1)) && calculateValueOfCard(test.get(1)) == calculateValueOfCard(test.get(2))){
			return true;
			}
		}
		
		else if (par == 2) {
			if (calculateValueOfCard(test.get(0)) == calculateValueOfCard(test.get(1))) {
			return true;
			}
		}
		return false;
		
	}
	public boolean CheckForStraight(List<Card> test) {
		int a = calculateValueOfCard(test.get(0));
		int b = calculateValueOfCard(test.get(1));
		int c = calculateValueOfCard(test.get(2));
		int d = calculateValueOfCard(test.get(3));
		int e = calculateValueOfCard(test.get(4));
		if (a+1 == b && b+1 == c && c+1 == d && d+1 == e)
			return true;
		
		else
			return false;
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
