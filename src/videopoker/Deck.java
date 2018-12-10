package videopoker;

import java.util.*;

public class Deck {
	
	private List<Card> deckOfCards;
//	Array med de olika valörerna som kan förekomma i en färg.
	private static String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
	
//	Konstruktor
	public Deck() {
		
//		Instansierar ny arraylist för lagring av kort i kortlek. 
		deckOfCards = new ArrayList<>();
		
//		Fyll kortleken med kort (baserat på Suit och ranks[] ovan).
		for (Suit suit : Suit.values() ) {
			for (int i = 0 ; i < ranks.length ; i++ ) {
				deckOfCards.add(new Card(suit, ranks[i]));
			}
		}
//		Avsluta med att blanda kortleken
		shuffle();
		
	}
	//Denna skall flyttas till UI klassen sen!!!
	public Card draw(){
//		Metod som returnerar ett kort och sedan plockar bort det från "kortleken".
		if (!deckOfCards.isEmpty()) {
			
			Card card = deckOfCards.get(0);
			deckOfCards.remove(0);
			
			return card;
		}
		return null;
	}
	

	public void shuffle () {
//		Egen metod för att blanda kortleken genom att slumpmässigt dra kort och populera om deckOfCards.
		
		
		Random slump = new Random();
		List<Card> tempDeck = new ArrayList<>();
		
		while (!deckOfCards.isEmpty()) {
			int pickedCard = slump.nextInt(deckOfCards.size());
			tempDeck.add(deckOfCards.get(pickedCard));
			deckOfCards.remove(pickedCard);
		}
		
		deckOfCards = new ArrayList<>(tempDeck);
		
//		Collections.shuffle(deckOfCards); Standardmetod som inte användes. 
		
	}
	
	public List<Card> getDeck() {
		return deckOfCards; 
	}
	
	public static String [] getRanks () {
		return ranks;
	} 	
}
