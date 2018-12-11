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
	
//	Constructor
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
//		For nicer printout of cards 
		return String.format("%s of %s", rank, suit.name().toLowerCase());
	}
	
	@Override
	public boolean equals(Object card) {
//	Method to compare cards based on suit and rank. 		
		return this.getSuit() == ((Card) card).getSuit() && this.getRank() == ((Card) card).getRank();
	}
	
	private void createFilename() {
//		Creates a filenamne for access to cardimages (in resources).
		filename = new ImageIcon(getClass().getResource("/" + getRank() + "_of_" + getSuit() + ".jpg"));
//								 resource                /    2		       _of_    hearts       .jpg  
	}
	

}
