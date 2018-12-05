package videopoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class VideoPoker {

	/*
	 * This class will contain the games intelligence. This will be epic
	 * 
	 */

	public boolean checkForPairs(int a, int b, List<Card> hand) {
		boolean par = false, tvåpar = false, tretal = false;
		sortCards(hand);

		for (Card card : hand) {
			System.out.println(card);
		}
		
		// Par
		if (hand.get(0).getRank().equals(hand.get(1).getRank()) || hand.get(1).getRank().equals(hand.get(2).getRank())
				|| hand.get(2).getRank().equals(hand.get(3).getRank())
				|| hand.get(3).getRank().equals(hand.get(4).getRank())) {
			par = true;
		} else {
			par = false;
		}

		// Två par
		if (hand.get(0).getRank().equals(hand.get(1).getRank()) && hand.get(2).getRank().equals(hand.get(3).getRank())
				|| hand.get(0).getRank().equals(hand.get(1).getRank())
						&& hand.get(3).getRank().equals(hand.get(4).getRank())
				|| hand.get(1).getRank().equals(hand.get(2).getRank())
						&& hand.get(3).getRank().equals(hand.get(4).getRank())) {
			tvåpar = true;
		}

		else {
			tvåpar = false;
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

//		System.out.println(t1);
//		System.out.println(t2);
//		System.out.println(t3);
//		
		if (t1 == true || t2 == true || t3 == true) {
			tretal=true;
		}
		else {
			tretal = false;
		}
//			Kontrollerar tvåpar						Kontrollerar kåk											Kontrollerar kåk
		if (a == 2 && b == 2 && tvåpar == true || a == 2 && b == 3 && par == true && tretal == true|| a == 3 && b == 2 && par == true && tretal == true) {
			return true;
		} else {
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
		
		if (par == 4) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					for (int x = 0; x < 4; x++) {
						for (int k = 0; k < 4; k++) {
							if( i!= j && j!=x && x!=k)
							if (calculateValueOfCard(test.get(i)) == calculateValueOfCard(test.get(j)) 
									&& calculateValueOfCard(test.get(j)) == calculateValueOfCard(test.get(x))
									&& calculateValueOfCard(test.get(x)) == calculateValueOfCard(test.get(k))) {
								return true;
							}
						}
					}
				}
			}
			return false;
		}
		
		if (par == 3) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					for (int x = 0; x < 4; x++) {
						if ( i != j && j != x) {
						if (calculateValueOfCard(test.get(i)) == calculateValueOfCard(test.get(j)) && calculateValueOfCard(test.get(j)) == calculateValueOfCard(test.get(x))) {
							return true;
						}
						}
					}
				}
			}
			return false;
		}

		if (par == 2) {

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (i != j) {
						if (calculateValueOfCard(test.get(i)) == calculateValueOfCard(test.get(j))) {
							if(calculateValueOfCard(test.get(i)) >= 11) {
							return true;
							}
						}
					}
				}
				
			}
			return false;
		}
		else return false;
		
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
	public boolean checkStraightFlush(List<Card> test) {
		System.out.println(CheckForStraight(test));
		System.out.println(checkForFlush(test));
		if (CheckForStraight(test) == true && checkForFlush(test) ==true ) {
			return true;
		}
		else return false;
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


public int getHandScore(int coins, List<Card> hand) {
	
		if (checkStraightFlush(hand) == true) {
			return Wins.Straight_Flush.factor*coins;
		}
		if (CheckForEquals(4, hand) == true) {
			return Wins.Four_Of_A_Kind.factor*coins;
		}
		else if (checkForPairs(2, 3, hand) == true) {
			return Wins.Full_House.factor*coins;
		}
		else if (checkForFlush(hand) == true) {
			return Wins.Flush.factor*coins;
		}
		else if (CheckForStraight(hand) == true) {
			return Wins.Straight.factor*coins;
		}
		else if (CheckForEquals(3, hand) == true) {
			return Wins.Three_Of_A_Kind.factor*coins;
		}
		else if (checkForPairs(2,2, hand) == true) {
			return Wins.Two_Pairs.factor*coins;
		}
		else if (CheckForEquals(2, hand) == true) {
			return Wins.Pair.factor*coins;
		}
		return 0;
		
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




		
							
						
	
		


