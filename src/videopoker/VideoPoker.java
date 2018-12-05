package videopoker;

import java.util.ArrayList;
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
	
	
// skapat metod checkforflush
	
	public boolean checkForFlush(List<Card>Hand) {
	 
			
		
			List<Card>cards = new ArrayList<>(Hand);
			Card cardone = Hand.get(0);
			
			for(int i = 1; i < cards.size(); i++) {
			
				if(cardone.getSuit()!=cards.get(i).getSuit()) {
					
					
				}
					
					
			
		
			}
			return true;
			
			
	}
}




		
							
						
	
		


