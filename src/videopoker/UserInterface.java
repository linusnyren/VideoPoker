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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	private JButton saveAndQuit;
	private JButton makeBet;
	private JTextField betThis;
	private JLabel playerCredits;
	
//	Player, game and deck.
	private Player player;
	private VideoPoker video;
	private Deck deck;
	
	
	
//	Constructor
	public UserInterface () throws ClassNotFoundException, IOException {
		
		setLayout(new BorderLayout());
		
//		player = new Player(100, "John Doe");
		loadPlayer();
		video = new VideoPoker();
		
//		Instatiate panels
		topPanel = new JPanel();
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension (600, 200));
		bottomPanel = new JPanel();
		
//		Add panels to JFrame according to BorderLayout. 
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
		
		
//		Instantiate and add components (buttons and fields).
		getNewHand = new JButton("New hand");
		holdAndGetSecondHand = new JButton("Hold - Get new cards");
		saveAndQuit = new JButton("Save & quit");
		makeBet = new JButton("Make bet"); 
		betThis = new JTextField(5);
		playerCredits = new JLabel(String.format("Player credits: %d", player.getCredits()));
		
		
		topPanel.add(playerCredits);
		topPanel.add(betThis);
		topPanel.add(makeBet);
		topPanel.add(getNewHand);
		
		bottomPanel.add(holdAndGetSecondHand);
		bottomPanel.add(saveAndQuit);

		holdAndGetSecondHand.setEnabled(false); //Inactivate get second hand.
		getNewHand.setEnabled(false);
		
//		Add button listeners
		getNewHand.addActionListener( e -> getNewHand());
		holdAndGetSecondHand.addActionListener( e -> holdAndGetNewCards());
		makeBet.addActionListener(e -> checkBet());
		saveAndQuit.addActionListener(e -> saveAndQuit());
		
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
			ois.close();
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
			cards[i].setIcon(card.getFileName()); //Get image for card
			cards[i].setBorder(null); //Resets the border. 
		}

	}
	
	public void holdAndGetNewCards () {
		
		getNewHand.setEnabled(true); //Enable new game button
		holdAndGetSecondHand.setEnabled(false); //Inactivate get second hand.
		
	
		for (int i = 0 ; i < cards.length ; i++ ) {
			if (cards[i].getBorder() != border) {
				player.removeCardFromHand(i); 	// Remove the card that the player does not want to keep
				Card card = deck.draw(); 		//Draws new card 
				player.addCardToHand(i, card);		//Adds the card to the hand.
				cards[i].setIcon(card.getFileName()); //Get image for card
			}
			
			cards[i].setBorder(null); //Resets the border. 
			
//			Prepare for next round by activating the right buttons
			getNewHand.setEnabled(false);
			betThis.setEnabled(true);
			makeBet.setEnabled(true);

		}
//		TODO: Check if player won and (if applicable) make payout.

		
		System.out.println(video.getHandScore(player.getHand()));
		
		System.out.println();
		player.clearHand();
//		Save the players progress
//		savePlayer();
	}
	

//	Method for saving player
	public void savePlayer()  {
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("default_player"));
			oos.writeObject(player);
			System.out.println("Player saved");
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
//	Make bet
	public void makeBet (int bet) {
		
//	Metoden skall reducera spelarens (konto (credits) med "bet") och lägga "bet" i last bet
//	på player. Vi kommer sedan att hämta lastBet i slutet av varje runda och göra eventuell utbetalning
		
		
		
		
		
	}
	
//	Check that the user has entered a valid bet.
	public void checkBet() {
		
		int bet = 0;
		
//		Is the bet numeric?
		try {
			bet = Integer.parseInt(betThis.getText());
		} catch (NumberFormatException nfe) {
			betThis.setText("0");
			JOptionPane.showMessageDialog(null, "Please enter valid bet");
			return;
		}
		
//		Is the bet within the players limits?
		if (bet > player.getCredits()) {
			betThis.setText("0");
			JOptionPane.showMessageDialog(null, "You're trying to bet more than your holdings");
			return;
		}
		
//		"Open" the buttons for playing (and stop the player from changing bet).
		getNewHand.setEnabled(true);
		betThis.setEnabled(false);
		makeBet.setEnabled(false);
		
	}
	
//	Lets the player quit and saves the player. 
	public void saveAndQuit ()  {
		player.resetLastBet(); 	//Resets the bets.	
		player.clearHand();
		savePlayer();
		System.exit(0); 		//Exit system.
	}
	
	
	

}
