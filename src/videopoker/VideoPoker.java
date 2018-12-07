package videopoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class VideoPoker {

	/*
	 * This class will contain the games intelligence. This will be epic
	 * 
	 */
//	Hidden easteregg!
//	Get thememusic by adding this to main
//	Audio theme = new Audio();
//	theme.playSound("audio/music_theme.wav");

	public boolean checkForPairs(int a, int b, List<Card> hand) {
		boolean par = false, tvåpar = false, tretal = false;
		sortCards(hand);
		Object obj0 = hand.get(0).getRank();
		Object obj1 = hand.get(1).getRank();
		Object obj2 = hand.get(2).getRank();
		Object obj3 = hand.get(3).getRank();
		Object obj4 = hand.get(4).getRank();

		for (Card card : hand) {
//			System.out.println(card);
		}
		
		// Par
		if (obj0.equals(obj1) || obj1.equals(obj2) || obj2.equals(obj3) || obj3.equals(obj4)) {
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
		int a = calculateValueOfCard(test.get(0));
		int b = calculateValueOfCard(test.get(1));
		int c = calculateValueOfCard(test.get(2));
		int d = calculateValueOfCard(test.get(3));
		int e = calculateValueOfCard(test.get(4));
		if (par == 4) {
			if (a == b && b == c && c == d || b == c && c == d && d == e) {
				return true;
			}
		}
		if (par == 3) {
			if (a == c || b == d || c == e) {
				return true;
			}
		}

		if (par == 2) {
			for (int i = 0; i < test.size()-1; i++) {
				if (test.get(i).getRank().equals(test.get(i+1).getRank()) == true && calculateValueOfCard(test.get(i)) > 10) {
					return true;
				}
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
	public boolean checkStraightFlush(List<Card> test) {
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


public int getHandScore(List<Card> hand) throws Exception {
		sortCards(hand);
		Audio audio = new Audio();
		if (checkStraightFlush(hand) == true) {
//			System.out.printf("Straight flush ");
			audio.playSound("audio/StraightFlush.wav");
			return Wins.Straight_Flush.factor;
		}
		if (CheckForEquals(4, hand) == true) {
//			System.out.printf("Four of a kind ");
			audio.playSound("audio/FourOfAKind.wav");
			return Wins.Four_Of_A_Kind.factor;
		}
		else if (checkForPairs(3, 2, hand) == true) {
//			System.out.printf("Full house ");
			audio.playSound("audio/FullHouse.wav");
			return Wins.Full_House.factor;
		}
		else if (checkForFlush(hand) == true) {
//			System.out.printf ("Flush ");
			audio.playSound("audio/Flush.wav");
			return Wins.Flush.factor;
		}
		else if (CheckForStraight(hand) == true) {
//			System.out.printf("Straight ");
			audio.playSound("audio/Straight.wav");
			return Wins.Straight.factor;
		}
		else if (CheckForEquals(3, hand) == true) {
//			System.out.printf("three of a kind ");
			audio.playSound("audio/ThreeOfAKind.wav");
			return Wins.Three_Of_A_Kind.factor;
		}
		else if (checkForPairs(2,2, hand) == true) {
//			System.out.printf("Two pairs ");
			audio.playSound("audio/TwoPair.wav");
			return Wins.Two_Pairs.factor;
		}
		else if (CheckForEquals(2, hand) == true) {
//			System.out.printf("Pair ");
			audio.playSound("audio/Pair.wav");
			return Wins.Pair.factor;
		}
		audio.playSound("audio/Loser.wav");
		return 0;
		
	}

// skapat metod checkforflush
	
	public boolean checkForFlush(List<Card>Hand) {
			for(int i = 1; i < Hand.size(); i++) {
			
				if(!Hand.get(0).getSuit().equals(Hand.get(i).getSuit())) {
					return false;
				}
			}
			return true;
	}

}




		
							
						
	
		


