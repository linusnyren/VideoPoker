package videopoker;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class VideoPokerTest {
	
	@Test
	void testForPair() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "Five"));
		hand.add(new Card(Suit.SPADES, "Jack"));

		hand.add(new Card(Suit.DIAMONDS, "Jack"));
		hand.add(new Card(Suit.SPADES, "Six"));
		hand.add(new Card(Suit.CLUBS, "Queen"));

//		Will only accept twoPair with jacks or better
		assertTrue(video.CheckForEquals(2, hand));
		
	}
	
	
	@Test
	void testForPairFalse() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "Two"));
		hand.add(new Card(Suit.SPADES, "Four"));
		hand.add(new Card(Suit.DIAMONDS, "Five"));
		hand.add(new Card(Suit.SPADES, "Seven"));
		hand.add(new Card(Suit.CLUBS, "Eight"));
		
		assertFalse(video.CheckForEquals(4, hand));
		
	}
	
	
	@Test
	void testForFlush() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "Two"));
		hand.add(new Card(Suit.HEARTS, "Three"));
		hand.add(new Card(Suit.HEARTS, "Five"));
		hand.add(new Card(Suit.HEARTS, "Seven"));
		hand.add(new Card(Suit.HEARTS, "Eight"));
		
		assertTrue(video.checkForFlush(hand));
		
	}
	
	@Test
	void testForStraight() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "Two"));
		hand.add(new Card(Suit.SPADES, "Three"));
		hand.add(new Card(Suit.DIAMONDS, "Four"));
		hand.add(new Card(Suit.SPADES, "Five"));
		hand.add(new Card(Suit.CLUBS, "Six"));
		
		assertTrue(video.CheckForStraight(hand));
		
	}
	
	@Test
	void testForStraightFalse() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "Two"));
		hand.add(new Card(Suit.SPADES, "Three"));
		hand.add(new Card(Suit.DIAMONDS, "Three"));
		hand.add(new Card(Suit.SPADES, "Five"));
		hand.add(new Card(Suit.CLUBS, "Six"));
		
		assertFalse(video.CheckForStraight(hand));	

	}
	
	@Test
	void testForFullHouse() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "Two"));
		hand.add(new Card(Suit.SPADES, "Three"));
		hand.add(new Card(Suit.DIAMONDS, "Three"));
		hand.add(new Card(Suit.SPADES, "Two"));
		hand.add(new Card(Suit.CLUBS, "Three"));
		
		assertTrue(video.checkForPairs(3, 2, hand));
	
	}
	
	@Test
	void testForFullHouseFalse() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "Two"));
		hand.add(new Card(Suit.SPADES, "Four"));
		hand.add(new Card(Suit.DIAMONDS, "Three"));
		hand.add(new Card(Suit.SPADES, "Two"));
		hand.add(new Card(Suit.CLUBS, "Three"));
		
		assertFalse(video.checkForPairs(3, 2, hand));
	
	}
	
	@Test
	void testForTwoPairs() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "Two"));
		hand.add(new Card(Suit.SPADES, "Five"));
		hand.add(new Card(Suit.DIAMONDS, "Three"));
		hand.add(new Card(Suit.SPADES, "Two"));
		hand.add(new Card(Suit.CLUBS, "Three"));
		
		assertTrue(video.checkForPairs(2, 2, hand));
		
	}
	
	@Test
	void testForTwoPairFalse() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "Two"));
		hand.add(new Card(Suit.SPADES, "Five"));
		hand.add(new Card(Suit.DIAMONDS, "Four"));
		hand.add(new Card(Suit.SPADES, "Two"));
		hand.add(new Card(Suit.CLUBS, "Three"));
		
		assertFalse(video.checkForPairs(2, 2, hand));
		
	}
	
	
	@Test 
	void testHandSize() {
		Deck test = new Deck(); // Playerhand metoden kommer flyttas till UI sen
		assertTrue(test.playerHand().size() == 5);
	}
	@Test
	void testWins() {
//		System.out.println(Wins.Straight_Flush);
		Wins winning = Wins.Straight_Flush;
		assertEquals(40, winning.factor, "Testwins()");
	}
	@Test
	void testStraightFlush() {
		VideoPoker video = new VideoPoker();
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "Two"));
		hand.add(new Card(Suit.HEARTS, "Three"));
		hand.add(new Card(Suit.HEARTS, "Four"));
		hand.add(new Card(Suit.HEARTS, "Five"));
		hand.add(new Card(Suit.HEARTS, "Six"));
		
		assertTrue(video.checkStraightFlush(hand));
	}
	
	@Test
	void testHandScore() {
		boolean ok = false;
		VideoPoker video = new VideoPoker();
		List<Card> hand0 = new ArrayList<>();
		hand0.add(new Card(Suit.HEARTS, "Two"));
		hand0.add(new Card(Suit.CLUBS, "Five"));
		hand0.add(new Card(Suit.CLUBS, "Four"));
		hand0.add(new Card(Suit.HEARTS, "Two"));
		hand0.add(new Card(Suit.CLUBS, "Three"));
		boolean a = false;
		if (1 == video.getHandScore(1, hand0)) {
			a = true;
			}
//		System.out.println("hand0 " +a);
		List<Card> hand2 = new ArrayList<>();
		hand2.add(new Card(Suit.HEARTS, "Two"));
		hand2.add(new Card(Suit.CLUBS, "Three"));
		hand2.add(new Card(Suit.HEARTS, "Four"));
		hand2.add(new Card(Suit.CLUBS, "Two"));
		hand2.add(new Card(Suit.HEARTS, "Three"));
		boolean b = false;
		if (video.getHandScore(1, hand2) == 2) {
			b = true;
		}
//		System.out.println("Hand2 " +b);

		List<Card> hand3 = new ArrayList<>();
		hand3.add(new Card(Suit.HEARTS, "Two"));
		hand3.add(new Card(Suit.SPADES, "Five"));
		hand3.add(new Card(Suit.DIAMONDS, "Five"));
		hand3.add(new Card(Suit.SPADES, "Two"));
		hand3.add(new Card(Suit.CLUBS, "Five"));
		boolean c = false;
		if (video.getHandScore(1, hand3) == 9) {
			c = true;
		}
//		System.out.println("Hand3 " +c);

		List<Card> hand4 = new ArrayList<>();
		hand4.add(new Card(Suit.HEARTS, "Two"));
		hand4.add(new Card(Suit.CLUBS, "Three"));
		hand4.add(new Card(Suit.HEARTS, "Four"));
		hand4.add(new Card(Suit.CLUBS, "Five"));
		hand4.add(new Card(Suit.HEARTS, "Six"));
		boolean d = false;
		if (video.getHandScore(1, hand4) == 5) {
			d = true;
		}
//		System.out.println("Hand4 " +d);

		List<Card> hand5 = new ArrayList<>();
		hand5.add(new Card(Suit.HEARTS, "Two"));
		hand5.add(new Card(Suit.HEARTS, "Jack"));
		hand5.add(new Card(Suit.HEARTS, "Three"));
		hand5.add(new Card(Suit.HEARTS, "Four"));
		hand5.add(new Card(Suit.HEARTS, "Three"));
		boolean e = false;
		if (video.getHandScore(1, hand5) == 6) {
			e = true;
		}
//		System.out.println("Hand5 " +e);

		List<Card> hand6 = new ArrayList<>();
		hand6.add(new Card(Suit.HEARTS, "Two"));
		hand6.add(new Card(Suit.CLUBS, "Three"));
		hand6.add(new Card(Suit.HEARTS, "Three"));
		hand6.add(new Card(Suit.CLUBS, "Two"));
		hand6.add(new Card(Suit.HEARTS, "Three"));
		boolean f = false;
		if (video.getHandScore(1, hand6) == 9) {
			f = true;
		}
//		System.out.println("Hand6 " +f);

		List<Card> hand7 = new ArrayList<>();
		hand7.add(new Card(Suit.HEARTS, "Two"));
		hand7.add(new Card(Suit.CLUBS, "Three"));
		hand7.add(new Card(Suit.HEARTS, "Three"));
		hand7.add(new Card(Suit.CLUBS, "Three"));
		hand7.add(new Card(Suit.HEARTS, "Three"));
		boolean g = false;
		if (video.getHandScore(1, hand7) == 20) {
			g = true;
		}
//		System.out.println("Hand7 " +g);

		List<Card> hand8 = new ArrayList<>();
		hand8.add(new Card(Suit.HEARTS, "Two"));
		hand8.add(new Card(Suit.HEARTS, "Three"));
		hand8.add(new Card(Suit.HEARTS, "Four"));
		hand8.add(new Card(Suit.HEARTS, "Five"));
		hand8.add(new Card(Suit.HEARTS, "Six"));
		boolean h = false;
		if (video.getHandScore(1, hand8) == 40) {
			h = true;
		}
//		System.out.println("Hand8 " +h);

		if (a == true && b ==true && c == true && d == true && e ==true && f == true && g == true && h == true) {
			ok = true;
		}
		assertTrue(ok);
	}

}
