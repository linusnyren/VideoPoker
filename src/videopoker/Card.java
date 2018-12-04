package videopoker;

public class Card {
	
	private String rank;
	private Suit suit;
	
	
//	Konstruktor
	public Card (Suit suit, String rank) {
		this.suit = suit;
		this.rank = rank;
		
	}


//	Getters
	public Suit getSuit() {
		return suit;
	}
	
	public String getRank() {
		return rank;
	}
	
	
	@Override
	public String toString () {
//		För snyggare utskrift av kort. 
		return String.format("%s of %s", rank, suit.name().toLowerCase());
	}
	
	@Override
	public boolean equals(Object card) {
//	Definierad equals metod för att möjliggöra jämförelser mellan kort. 		
		return this.getSuit() == ((Card) card).getSuit() && this.getRank() == ((Card) card).getRank();
	}
	

}
