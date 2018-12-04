package videopoker;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class VideoPokerTest {
	
	@Test
	void testForPair() {
		
		List<Card> hand = new ArrayList<>();
		hand.add(new Card(Suit.HEARTS, "Two"));
		hand.add(new Card(Suit.SPADES, "Two"));
		hand.add(new Card(Suit.DIAMONDS, "Five"));
		hand.add(new Card(Suit.SPADES, "Seven"));
		hand.add(new Card(Suit.CLUBS, "Eight"));
		
		assertTrue(CheckForEquals(2, hand));
		
		
	}
	

}
