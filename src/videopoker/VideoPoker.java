package videopoker;

import java.util.ArrayList;
import java.util.List;

public class VideoPoker {

	/*
	 * This class will contain the games intelligence. This will be epic
	 * 
	 */

	public boolean checkForPairs(int a, int b, List<Card> hand) {
		boolean par = false,tvåpar = false ,tretal = false;
		sortCards(hand);
		for (Card card : hand) {
			System.out.println(card);
		}
		//Par
		if 	(hand.get(0).getRank().equals(hand.get(1).getRank()) ||
			hand.get(1).getRank().equals(hand.get(2).getRank())  ||
			hand.get(2).getRank().equals(hand.get(3).getRank())  ||
			hand.get(3).getRank().equals(hand.get(4).getRank()))
		{
		par = true;
		}
		else {
		par = false;
		}

		//Två par
		if (hand.get(0).getRank().equals(hand.get(1).getRank()) &&
			hand.get(2).getRank().equals(hand.get(3).getRank())
			||
			hand.get(0).getRank().equals(hand.get(1).getRank()) &&
			hand.get(3).getRank().equals(hand.get(4).getRank())
			||
			hand.get(1).getRank().equals(hand.get(2).getRank()) &&
			hand.get(3).getRank().equals(hand.get(4).getRank()))	
		{
		tvåpar=true;	
		}
		
		 else {
			tvåpar=false;
		}

	//Tre-tal
		boolean t1,t2,t3;
		if (	hand.get(0).getRank().equals(hand.get(1).getRank()) &&
				hand.get(1).getRank().equals(hand.get(2).getRank()) &&
				!hand.get(3).getRank().equals(hand.get(0).getRank())&&
				!hand.get(4).getRank().equals(hand.get(0).getRank())) 
		{t1 = true;}
			else {t1 = false;}

		if (
				hand.get(1).getRank().equals(hand.get(2).getRank()) &&
				hand.get(2).getRank().equals(hand.get(3).getRank()) &&
				!hand.get(0).getRank().equals(hand.get(1).getRank())&&
				!hand.get(4).getRank().equals(hand.get(1).getRank()))
		{t2 = true;}
		else {t2 = false;}
		
		if(
				hand.get(2).getRank().equals(hand.get(3).getRank()) &&
				hand.get(3).getRank().equals(hand.get(4).getRank()) &&
				!hand.get(0).getRank().equals(hand.get(2).getRank())&&
				!hand.get(1).getRank().equals(hand.get(2).getRank())
				)
		{t3 = true;}
		else{ t3 = false;}

		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		
		if (t1 == true || t2 == true || t3 == true) {
			tretal=true;
		}
		else {
			tretal = false;
		}
//			Kontrollerar tvåpar						Kontrollerar kåk											Kontrollerar kåk
		if(a == 2 && b == 2 && tvåpar == true || a == 2 && b == 3 && par == true && tretal == true || a == 3 && b == 2 && par == true && tretal == true)
	 {
		return true;
	}
	else {
		return false;
	}
	}

	public List<Card> sortCards(List<Card> handToSort) {

		List<Card> workThis = handToSort;

		for (int i = 0; i < handToSort.size(); i++) {
			for (int j = 0; j < handToSort.size() - 1; j++) {
				if (calculateValueOfCard(handToSort.get(j)) > calculateValueOfCard(handToSort.get(j + 1))) {
					Card temp = handToSort.get(j);
					handToSort.remove(j);
					handToSort.add(j + 1, temp);
				}
			}
		}

		return handToSort;

	}

	public boolean CheckForEquals(int par, List<Card> test) {

		if (par == 3) {
			if (calculateValueOfCard(test.get(0)) == calculateValueOfCard(test.get(1))
					&& calculateValueOfCard(test.get(1)) == calculateValueOfCard(test.get(2))) {
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
		if (a + 1 == b && b + 1 == c && c + 1 == d && d + 1 == e)
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
