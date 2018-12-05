package videopoker;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class VideoPokerTest {
	
	@Test
	void testForPair() {
		
		VideoPoker video = new VideoPoker();
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "Five"));
		hand.add(new Card(Suit.SPADES, "Two"));
		hand.add(new Card(Suit.DIAMONDS, "Two"));
		hand.add(new Card(Suit.SPADES, "Seven"));
		hand.add(new Card(Suit.CLUBS, "Eight"));
		
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
		
		assertFalse(video.CheckForEquals(2, hand));
		
	}
	
	
//	@Test
//	void testForFlush() {
//		
//		VideoPoker video = new VideoPoker();
//		
//		List<Card> hand = new ArrayList<>();
//		hand.add(new Card(Suit.HEARTS, "Two"));
//		hand.add(new Card(Suit.HEARTS, "Three"));
//		hand.add(new Card(Suit.HEARTS, "Five"));
//		hand.add(new Card(Suit.HEARTS, "Seven"));
//		hand.add(new Card(Suit.HEARTS, "Eight"));
//		
//		assertTrue(video.CheckForFlush(hand));
//		
//	}
	
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
	void testHandSize() {
		Deck test = new Deck(); // Playerhand metoden kommer flyttas till UI sen
		assertTrue(test.playerHand().size() == 5);
	}
	

}
