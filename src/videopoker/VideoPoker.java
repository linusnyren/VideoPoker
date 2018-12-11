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
		boolean tvåpar = false, kåk = false, k1 = false, k2 = false;
		sortCards(hand);
		Object obj0 = hand.get(0).getRank();
		Object obj1 = hand.get(1).getRank();
		Object obj2 = hand.get(2).getRank();
		Object obj3 = hand.get(3).getRank();
		Object obj4 = hand.get(4).getRank();

		// Två par
		if (obj0.equals(obj1) && obj2.equals(obj3) || obj0.equals(obj1) && obj3.equals(obj4)
				|| obj1.equals(obj2) && obj3.equals(obj4)) {
			tvåpar = true;
		}

		else {
			tvåpar = false;
		}

		// kåk
		if (obj0.equals(obj1) && obj2.equals(obj3) && obj3.equals(obj4)) {
			k1 = true;
		} else {
			k1 = false;
		}

		if (obj0.equals(obj1) && obj1.equals(obj2) && obj3.equals(obj4)) {
			k2 = true;
		} else {
			k2 = false;
		}

		if (k1 == true || k2 == true) {
			kåk = true;
		} else {
			kåk = false;
		}

//			Kontrollerar tvåpar						Kontrollerar kåk					Kontrollerar kåk
		if (a == 2 && b == 2 && tvåpar == true || a == 2 && b == 3 && kåk == true || a == 3 && b == 2 && kåk == true) {
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
//	Denna metod tar emot en int som avser för vilken kombination den skall testa, 4 för fyrtal etc.
//	Den tar även en lista som ett argument, det är denna lista som prövas nedan.
//	Sortcards sorterar korten i värdeordning vilket underlättar denna metod en hel del
//	Jag lägger kortens nummervärde i en instansvariabel för att göra if satserna tydligare och lite mer DRY.
	public boolean CheckForEquals(int par, List<Card> test) {
		sortCards(test);
		int a = calculateValueOfCard(test.get(0));
		int b = calculateValueOfCard(test.get(1));
		int c = calculateValueOfCard(test.get(2));
		int d = calculateValueOfCard(test.get(3));
		int e = calculateValueOfCard(test.get(4));
		
		if (par == 4) {
			if (a == d || b == e) {
				return true;
			}
		}
		if (par == 3) {
			if (a == c || b == d || c == e) {
				return true;
			}
		}

		if (par == 2) {
			for (int i = 0; i < test.size() - 1; i++) {
				if (test.get(i).getRank().equals(test.get(i + 1).getRank()) == true
						&& calculateValueOfCard(test.get(i)) > 10) {
					return true;
				}
			}
		}
		return false;
	}
//	Denna metod tar enbart emot en lista som argument
//	sortCards sorterar denna listan efter nummervärdesordning.
//	Lagrar värdena i en instansvariabel för att underlätta läsning av if sats och göra den lite mer DRY.
	public boolean CheckForStraight(List<Card> test) {
		sortCards(test);
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
//	Denna metod tar enbart emot en lista som provas.
//	Sen anropar den två andra metoder som kollar kriterierna, returnerar båda true så returnerar denna true.
	public boolean checkStraightFlush(List<Card> test) {
		if (CheckForStraight(test) == true && checkForFlush(test) == true) {
			return true;
		} else
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

//	I denna metod så körs metoderna i turordning från högst utbetalning till
//	lägsta utbetalning, där den nappar så spelas motsvarande ljud upp.
//	Sen returneras multipeln för motsvarande ev vinst.
	public int getHandScore(List<Card> hand, int volume) throws Exception {
		sortCards(hand);
		Audio audio = new Audio();
		if (checkStraightFlush(hand) == true) {
			audio.playSound("audio/StraightFlush.wav", volume);
			return Wins.Straight_Flush.factor;
		}
		if (CheckForEquals(4, hand) == true) {
			audio.playSound("audio/FourOfAKind.wav", volume);
			return Wins.Four_Of_A_Kind.factor;
		} else if (checkForPairs(3, 2, hand) == true) {
			audio.playSound("audio/FullHouse.wav", volume);
			return Wins.Full_House.factor;
		} else if (checkForFlush(hand) == true) {
			audio.playSound("audio/Flush.wav", volume);
			return Wins.Flush.factor;
		} else if (CheckForStraight(hand) == true) {
			audio.playSound("audio/Straight.wav", volume);
			return Wins.Straight.factor;
		} else if (CheckForEquals(3, hand) == true) {
			audio.playSound("audio/ThreeOfAKind.wav", volume);
			return Wins.Three_Of_A_Kind.factor;
		} else if (checkForPairs(2, 2, hand) == true) {
			audio.playSound("audio/TwoPair.wav", volume);
			return Wins.Two_Pairs.factor;
		} else if (CheckForEquals(2, hand) == true) {
			audio.playSound("audio/Pair.wav", volume);
			return Wins.Pair.factor;
		}
		audio.playSound("audio/Loser.wav", volume);
		return 0;
	}

// skapat metod checkforflush

	public boolean checkForFlush(List<Card> Hand) {
		for (int i = 1; i < Hand.size(); i++) {

			if (!Hand.get(0).getSuit().equals(Hand.get(i).getSuit())) {
				return false;
			}
		}
		return true;
	}
}
