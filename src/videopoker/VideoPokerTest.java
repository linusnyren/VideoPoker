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
		hand.add(new Card(Suit.HEARTS, "5"));
		hand.add(new Card(Suit.SPADES, "Jack"));

		hand.add(new Card(Suit.DIAMONDS, "Jack"));
		hand.add(new Card(Suit.SPADES, "6"));
		hand.add(new Card(Suit.CLUBS, "Queen"));

//		Will only accept 2Pair with jacks or better
		assertTrue(video.CheckForEquals(2, hand));
		
	}
	
	
	@Test
	void testForPairFalse() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "2"));
		hand.add(new Card(Suit.SPADES, "4"));
		hand.add(new Card(Suit.DIAMONDS, "5"));
		hand.add(new Card(Suit.SPADES, "7"));
		hand.add(new Card(Suit.CLUBS, "8"));
		
		assertFalse(video.CheckForEquals(4, hand));
		
	}
	
	
	@Test
	void testForFlush() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "2"));
		hand.add(new Card(Suit.HEARTS, "3"));
		hand.add(new Card(Suit.HEARTS, "5"));
		hand.add(new Card(Suit.HEARTS, "7"));
		hand.add(new Card(Suit.HEARTS, "8"));
		
		assertTrue(video.checkForFlush(hand));
		
	}
	
	@Test
	void testForStraight() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "2"));
		hand.add(new Card(Suit.SPADES, "3"));
		hand.add(new Card(Suit.DIAMONDS, "4"));
		hand.add(new Card(Suit.SPADES, "5"));
		hand.add(new Card(Suit.CLUBS, "6"));
		
		assertTrue(video.CheckForStraight(hand));
		
	}
	
	@Test
	void testForStraightFalse() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "2"));
		hand.add(new Card(Suit.SPADES, "3"));
		hand.add(new Card(Suit.DIAMONDS, "3"));
		hand.add(new Card(Suit.SPADES, "5"));
		hand.add(new Card(Suit.CLUBS, "6"));
		
		assertFalse(video.CheckForStraight(hand));	

	}
	
	@Test
	void testForFullHouse() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "2"));
		hand.add(new Card(Suit.SPADES, "3"));
		hand.add(new Card(Suit.DIAMONDS, "3"));
		hand.add(new Card(Suit.SPADES, "2"));
		hand.add(new Card(Suit.CLUBS, "3"));
		
		assertTrue(video.checkForPairs(3, 2, hand));
	
	}
	
	@Test
	void testForFullHouseFalse() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "2"));
		hand.add(new Card(Suit.SPADES, "4"));
		hand.add(new Card(Suit.DIAMONDS, "3"));
		hand.add(new Card(Suit.SPADES, "2"));
		hand.add(new Card(Suit.CLUBS, "3"));
		
		assertFalse(video.checkForPairs(3, 2, hand));
	
	}
	
	@Test
	void testFor2Pairs() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "2"));
		hand.add(new Card(Suit.SPADES, "5"));
		hand.add(new Card(Suit.DIAMONDS, "3"));
		hand.add(new Card(Suit.SPADES, "2"));
		hand.add(new Card(Suit.CLUBS, "3"));
		
		assertTrue(video.checkForPairs(2, 2, hand));
		
	}
	
	@Test
	void testFor2PairFalse() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "2"));
		hand.add(new Card(Suit.SPADES, "5"));
		hand.add(new Card(Suit.DIAMONDS, "4"));
		hand.add(new Card(Suit.SPADES, "2"));
		hand.add(new Card(Suit.CLUBS, "3"));
		
		assertFalse(video.checkForPairs(2, 2, hand));
		
	}
	
	@Test
	void testWins() {
		Wins winning = Wins.Straight_Flush;
		assertEquals(40, winning.factor, "Testwins()");
	}
	@Test
	void testStraightFlush() {
		VideoPoker video = new VideoPoker();
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "2"));
		hand.add(new Card(Suit.HEARTS, "3"));
		hand.add(new Card(Suit.HEARTS, "4"));
		hand.add(new Card(Suit.HEARTS, "5"));
		hand.add(new Card(Suit.HEARTS, "6"));
		
		assertTrue(video.checkStraightFlush(hand));
	}
	
	@Test
	void testHandScore() throws Exception {
		boolean ok = false;
		VideoPoker video = new VideoPoker();
		List<Card> hand0 = new ArrayList<>();
		hand0.add(new Card(Suit.HEARTS, "Jack"));
		hand0.add(new Card(Suit.CLUBS, "5"));
		hand0.add(new Card(Suit.CLUBS, "4"));
		hand0.add(new Card(Suit.HEARTS, "Jack"));
		hand0.add(new Card(Suit.CLUBS, "3"));
		boolean a = false;
		if (1 == video.getHandScore(hand0,1)) {
			a = true;
			}
		List<Card> hand2 = new ArrayList<>();
		hand2.add(new Card(Suit.HEARTS, "2"));
		hand2.add(new Card(Suit.CLUBS, "3"));
		hand2.add(new Card(Suit.HEARTS, "4"));
		hand2.add(new Card(Suit.CLUBS, "2"));
		hand2.add(new Card(Suit.HEARTS, "3"));
		boolean b = false;
		if (video.getHandScore(hand2,1) == 2) {
			b = true;
		}
		List<Card> hand3 = new ArrayList<>();
		hand3.add(new Card(Suit.HEARTS, "2"));
		hand3.add(new Card(Suit.SPADES, "5"));
		hand3.add(new Card(Suit.DIAMONDS, "5"));
		hand3.add(new Card(Suit.SPADES, "2"));
		hand3.add(new Card(Suit.CLUBS, "5"));
		boolean c = false;
		if (video.getHandScore(hand3,1) == 9) {
			c = true;
		}
		List<Card> hand4 = new ArrayList<>();
		hand4.add(new Card(Suit.HEARTS, "2"));
		hand4.add(new Card(Suit.CLUBS, "3"));
		hand4.add(new Card(Suit.HEARTS, "4"));
		hand4.add(new Card(Suit.CLUBS, "5"));
		hand4.add(new Card(Suit.HEARTS, "6"));
		boolean d = false;
		if (video.getHandScore(hand4, 1) == 5) {
			d = true;
		}
		List<Card> hand5 = new ArrayList<>();
		hand5.add(new Card(Suit.HEARTS, "2"));
		hand5.add(new Card(Suit.HEARTS, "Jack"));
		hand5.add(new Card(Suit.HEARTS, "3"));
		hand5.add(new Card(Suit.HEARTS, "4"));
		hand5.add(new Card(Suit.HEARTS, "3"));
		boolean e = false;
		if (video.getHandScore(hand5, 1) == 6) {
			e = true;
		}
		List<Card> hand6 = new ArrayList<>();
		hand6.add(new Card(Suit.HEARTS, "2"));
		hand6.add(new Card(Suit.CLUBS, "3"));
		hand6.add(new Card(Suit.HEARTS, "3"));
		hand6.add(new Card(Suit.CLUBS, "2"));
		hand6.add(new Card(Suit.HEARTS, "3"));
		boolean f = false;
		if (video.getHandScore(hand6, 1) == 9) {
			f = true;
		}
		List<Card> hand7 = new ArrayList<>();
		hand7.add(new Card(Suit.HEARTS, "2"));
		hand7.add(new Card(Suit.CLUBS, "3"));
		hand7.add(new Card(Suit.HEARTS, "3"));
		hand7.add(new Card(Suit.CLUBS, "3"));
		hand7.add(new Card(Suit.HEARTS, "3"));
		boolean g = false;
		if (video.getHandScore(hand7, 1) == 20) {
			g = true;
		}
		List<Card> hand8 = new ArrayList<>();
		hand8.add(new Card(Suit.HEARTS, "2"));
		hand8.add(new Card(Suit.HEARTS, "3"));
		hand8.add(new Card(Suit.HEARTS, "4"));
		hand8.add(new Card(Suit.HEARTS, "5"));
		hand8.add(new Card(Suit.HEARTS, "6"));
		boolean h = false;
		if (video.getHandScore(hand8, 1) == 40) {
			h = true;
		}
		if (a == true && b ==true && c == true && d == true && e ==true && f == true && g == true && h == true) {
			ok = true;
		}
		assertTrue(ok);
	}

}
