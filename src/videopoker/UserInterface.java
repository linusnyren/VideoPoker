package videopoker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class UserInterface extends JFrame {
	
//	Panels for placing cards and buttons
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel centerPanel;
	
//	JLabels and variabels for playing cards
	private JLabel [] cards;
	private Border border = new LineBorder(Color.BLACK, 2);
	
//	Components
	private JButton getNewHand;
	private JButton holdAndGetSecondHand;
	
//	Player
	private Player player;
	private VideoPoker video;
	private Deck deck;
	
	
	
//	Constructor
	public UserInterface () throws ClassNotFoundException, IOException {
		
		setLayout(new BorderLayout());
		
//		player = new Player(100, "John Doe");
		loadPlayer();
		savePlayer();
		video = new VideoPoker();
		
//		Instatiate panels
		topPanel = new JPanel();
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension (600, 200));
		bottomPanel = new JPanel();
		
//		Add panels to JFrame
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
//		Add cards (JLabels) to centerLabel
		cards = new JLabel [5];

//		Create labels for cards and add listeners	
		for (int i = 0 ; i < cards.length ; i++ ) {
			cards[i] = new JLabel();
			centerPanel.add(cards[i]);
			cards[i].addMouseListener(l);
		}
		
		
//		Instantiate and add buttons
		getNewHand = new JButton("New hand");
		holdAndGetSecondHand = new JButton("Hold - Get new cards");
		topPanel.add(getNewHand);
		bottomPanel.add(holdAndGetSecondHand);
		holdAndGetSecondHand.setEnabled(false); //Inactivate get second hand.
		
//		Add button listeners
		getNewHand.addActionListener( e -> getNewHand());
		holdAndGetSecondHand.addActionListener( e -> holdAndGetNewCards());
		
//		Final settings
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
	}
	
//	The listeners "mission" is to note which card the user chooses.
	MouseListener l = new MouseAdapter() {
		
		public void mouseClicked(MouseEvent e) {
			
			for (int i = 0 ; i < cards.length ; i++ ) {
				if (e.getSource() == cards[i]) {
					if (cards[i].getBorder() == border) {
						cards[i].setBorder(null);
					} else {
						cards[i].setBorder(border);
					}
					
				}
			}
			
		}

	};
	
//	Method for getting player
	public void loadPlayer () throws IOException, ClassNotFoundException {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("default_player"));
			player = (Player) ois.readObject();
		} catch (FileNotFoundException fnf) {
			player = new Player(1000, "John Doe");
		}
	}
	
	
//	Get new hand
	public void getNewHand() {
		
		deck = new Deck(); //Instantiate new deck
		
		getNewHand.setEnabled(false); //Inactivate the get new hand button.
		holdAndGetSecondHand.setEnabled(true); //Enable button for second hand.
		
		for (int i = 0 ; i < cards.length ; i++ ) {
			Card card = deck.draw();	//Draws new card
			player.addCardToHand(card); //Adds the cards to the player hand
//			TODO: replace static filename with filename provided by card.
//			cards[i].setIcon(new ImageIcon(getClass().getResource("/2_of_clubs.jpg")));//Lägg card.getFileName() här;
			cards[i].setIcon(card.getFileName()); //Lägg card.getFileName() här;
		}

		for (Card card : player.getHand()) {
			System.out.println(card);
		}
		
		System.out.println();
	}
	
	public void holdAndGetNewCards () {
		
		getNewHand.setEnabled(true); //Enable new game button
		holdAndGetSecondHand.setEnabled(false); //Inactivate get second hand.
		
	
		for (int i = 0 ; i < cards.length ; i++ ) {
			if (cards[i].getBorder() != border) {
				player.removeCardFromHand(i); 	// Remove the card that the player does not want to keep
				Card card = deck.draw(); 		//Draws new card 
				player.addCardToHand(i, card);		//Adds the card to the hand.
//				TODO: replace static filename with filename provided by card.
//				cards[i].setIcon(new ImageIcon(getClass().getResource("/2_of_clubs.jpg")));//Lägg card.getFileName() här;
				cards[i].setIcon(card.getFileName()); //Lägg card.getFileName() här;
			}
			
			cards[i].setBorder(null); //Resets the border. 
			
			
			
//			TODO: Check if player won and (if applicable) make payout.
			
			
		}
		
		for (Card card : player.getHand()) {
			System.out.println(card);
		}
		
		
		System.out.println(video.getHandScore(player.getHand()));
		
		System.out.println();
		player.clearHand();
		
	}
	
//	Method checks if border is activated (for second round and replaces cards that are not chosen. 
	
	
	
//	Method for saving player
	public void savePlayer() throws FileNotFoundException, IOException {
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("default_player"));
		oos.writeObject(player);

	}
	
	
//	Make bet
	public void makeBet () {
		
		
		
		
		
		
	}
	
	
	

}
