package videopoker;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
//		VideoPoker vp = new VideoPoker();
//		List<Card> hand = new ArrayList<Card>();
//		vp.checkForPairs(2, 2, hand);
		
		List<Card> handtest = new ArrayList<Card>();
		Deck deck = new Deck();
		handtest.add(deck.draw());
		handtest.add(deck.draw());
		handtest.add(deck.draw());
		handtest.add(deck.draw());
		handtest.add(deck.draw());

		System.out.println(handtest.size() - 1);
		System.out.println(handtest.get(0));
		System.out.println(handtest.get(1));
		System.out.println(handtest.get(2));
		System.out.println(handtest.get(3));
		System.out.println(handtest.get(4));

		for (int j = 0; j < handtest.size()-1; j++) {
			for (int i = 0; i < handtest.size()-1; i++) {
				if(handtest.get(i).getRank() == handtest.get(j).getRank()) {
				System.out.println("Par!");
				System.out.println(handtest.get(j).getRank());
				System.out.println(handtest.get(i).getRank());
				}
			}
		}
	}
}
