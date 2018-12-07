package videopoker;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Card implements Serializable {
	
	private String rank;
	private Suit suit;
	public ImageIcon filename;
	
	public ImageIcon getFileName() {
		return filename;
	}
	
//	Konstruktor
	public Card (Suit suit, String rank) {
		this.suit = suit;
		this.rank = rank;
		createFilename();
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
	
	private void createFilename() {
		filename = new ImageIcon(getClass().getResource("/" + getRank() + "_of_" + getSuit() + ".jpg"));
	}
	

}
