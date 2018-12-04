package videopoker;

import java.util.ArrayList;
import java.util.List;

public class VideoPoker {
	
	/*	This class will contain the games intelligence.
	 * 	This will be epic
	 * 
	 */
	
	public boolean checkForPairs(int a, int b, List<Card> hand) {
//kolla par + par || par + tretal
		Deck deck = new Deck();
		deck.shuffle();
		hand.add(deck.draw());
		hand.add(deck.draw());
		hand.add(deck.draw());
		hand.add(deck.draw());
		hand.add(deck.draw());
		
		return false;
		
	}
 }
